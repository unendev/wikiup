import logging
import os
import re
import time
import yaml
from concurrent.futures import ThreadPoolExecutor

import markdownify
import requests
from bs4 import BeautifulSoup

# --- Configuration ---
API_URL = "https://dontstarve.fandom.com/api.php"
# Use the SOCKS5 proxy provided earlier
PROXIES = {
    'http': 'socks5h://127.0.0.1:10808',
    'https': 'socks5h://127.0.0.1:10808',
}
HEADERS = {
    'User-Agent': 'DontStarveWikiScraper/1.0 (https://github.com/your-repo; your-email@example.com)'
}
SESSION = requests.Session()
SESSION.headers.update(HEADERS)
SESSION.proxies.update(PROXIES)

def setup_logging():
    """Sets up basic logging for the script."""
    logging.basicConfig(level=logging.INFO, 
                        format='%(asctime)s - %(levelname)s - %(message)s',
                        handlers=[
                            logging.FileHandler("scraping_api.log", mode='w'),
                            logging.StreamHandler()
                        ])

def get_all_page_titles():
    """Gets all page titles from the entire wiki using Special:AllPages."""
    logging.info("Fetching all page titles from the wiki...")
    page_titles = []
    params = {
        "action": "query",
        "format": "json",
        "list": "allpages",
        "aplimit": "max",
        "apfilterredir": "nonredirects" # We only want actual pages, not redirects
    }
    
    last_continue = {}
    while True:
        req_params = params.copy()
        req_params.update(last_continue)
        
        try:
            response = SESSION.get(API_URL, params=req_params, timeout=30)
            response.raise_for_status()
            data = response.json()
            
            pages = data.get("query", {}).get("allpages", [])
            for page in pages:
                page_titles.append(page["title"])
            
            if 'continue' in data:
                last_continue = data['continue']
                logging.info(f"Continuing to next page of results ({len(page_titles)} titles fetched so far)...")
                time.sleep(0.5)
            else:
                break
        except requests.exceptions.RequestException as e:
            logging.error(f"Failed to fetch all page titles: {e}")
            break
            
    logging.info(f"Found a total of {len(page_titles)} pages on the wiki.")
    return page_titles

def scrape_page_from_api(page_title: str, game_version: str):
    """
    Scrapes a single page using its title, extracts the content,
    converts it to Markdown, and saves it. The category is dynamically determined.
    """
    try:
        # Sanitize title for use in filename
        file_name = re.sub(r'[\s_]+', '-', page_title).lower()
        file_name = re.sub(r'[\\/*?:"<>|]', "", file_name)
        
        output_dir = os.path.join("data", game_version)
        os.makedirs(output_dir, exist_ok=True)
        output_path = os.path.join(output_dir, f"{file_name}.md")

        if os.path.exists(output_path):
            # This check is now more important than ever to avoid re-scraping thousands of pages.
            # --- MODIFICATION ---
            # We will re-scrape a page if it was previously saved as "Uncategorized"
            # to see if our improved logic can find a better category.
            try:
                with open(output_path, "r", encoding="utf-8") as f:
                    # Read just enough to check the front matter
                    if "category: Uncategorized" in f.read(200):
                        logging.info(f"Re-scraping '{page_title}' to improve its category.")
                    else:
                        logging.debug(f"Skipping already scraped page: '{page_title}'")
                        return # Skip if it exists and has a proper category
            except Exception as e:
                logging.warning(f"Could not read existing file {output_path} to check category, skipping. Error: {e}")
                return
        
        logging.info(f"Scraping page: '{page_title}' [Game: {game_version}]")
        
        # 1. Get the page's HTML content and categories from the API
        parse_params = {
            "action": "parse",
            "page": page_title,
            "format": "json",
            "prop": "text|categories", # Request both text and categories
            "formatversion": 2 # Use latest format
        }
        response = SESSION.get(API_URL, params=parse_params)
        response.raise_for_status()
        page_data = response.json().get("parse", {})
        html_content = page_data.get("text", "")
        
        # --- NEW, IMPROVED CATEGORY LOGIC ---
        categories = page_data.get("categories", [])
        all_cat_titles = [c.get("*") for c in categories if c.get("*")]

        # Define a list of "generic" categories to ignore if better ones exist.
        generic_cats = {
            "Don't Starve Together", "Guides", "Pages with script errors", 
            "Don't Starve Pocket Edition", "Don't Starve", "Don't Starve Reign of Giants"
        }

        # Find the best category by filtering out the generic ones.
        specific_cats = [cat for cat in all_cat_titles if cat not in generic_cats]

        category_name = "Uncategorized"
        if specific_cats:
            # If we have specific categories, use the first one.
            category_name = specific_cats[0]
        elif "Don't Starve Together" in all_cat_titles:
            # As a fallback, use the main game category if it exists.
            category_name = "Don't Starve Together"
        elif all_cat_titles:
            # If no other choice, just take the first category available.
            category_name = all_cat_titles[0]
        # Otherwise, it remains "Uncategorized".

        # If, after all our logic, the category is still "Uncategorized",
        # log the raw categories for debugging purposes.
        if category_name == "Uncategorized":
            logging.warning(f"Page '{page_title}' classified as 'Uncategorized'. Raw categories received from API: {all_cat_titles}")

        if not html_content:
            logging.warning(f"No HTML content returned for page '{page_title}'.")
            return

        # 2. Parse HTML and get the main content area
        soup = BeautifulSoup(html_content, "html.parser")
        main_content = soup.find("div", class_="mw-parser-output")

        if not main_content:
            logging.warning(f"Could not find main content area for page '{page_title}'. Skipping.")
            return

        # Perform minimal, safe cleanup: remove the category links box at the bottom.
        catlinks = main_content.find("div", class_="catlinks")
        if catlinks:
            catlinks.decompose()

        # --- PRE-PROCESSING STEP TO FIX IMAGE-ONLY LINKS ---
        for a_tag in main_content.find_all('a'):
            if a_tag.find('img') and not a_tag.get_text(strip=True):
                item_name = a_tag.get('title')
                if not item_name:
                    href = a_tag.get('href', '')
                    item_name = href.split('/wiki/')[-1].replace('_', ' ').strip()
                
                if item_name:
                    a_tag.replace_with(item_name)
            
        # Convert the entire main content to Markdown.
        markdown_content = markdownify.markdownify(str(main_content), heading_style="ATX").strip()

        if not markdown_content:
            logging.warning(f"Main content for '{page_title}' is empty after conversion. Skipping.")
            return

        # 4. Create YAML front matter with the dynamically found category
        source_url = f"https://dontstarve.fandom.com/wiki/{page_title.replace(' ', '_')}"
        front_matter = f"""---
game_version: {game_version}
category: {category_name}
source_url: {source_url}
---

"""
        final_content = front_matter + markdown_content

        # 5. Save the markdown content to a file
        with open(output_path, "w", encoding="utf-8") as f:
            f.write(final_content)
        logging.info(f"Successfully saved '{page_title}' to {output_path} (Category: {category_name})")

    except requests.exceptions.RequestException as e:
        logging.error(f"Network error scraping page '{page_title}': {e}")
    except Exception as e:
        logging.error(f"An unexpected error occurred for page '{page_title}': {e}", exc_info=True)


def main():
    """Main function to run the full-site scraping job."""
    
    # The game version is now hardcoded as this script is for a full DST scrape.
    game_version = "dst"
    logging.info(f"--- Starting Full Site Scrape for game '{game_version}' ---")
    
    # 1. Get ALL page titles from the wiki.
    page_titles = get_all_page_titles()

    if not page_titles:
        logging.error("No pages found on the wiki. Something is wrong. Exiting.")
        return

    # 2. Use a thread pool to scrape all pages.
    # The check for existing files inside scrape_page_from_api is critical here.
    with ThreadPoolExecutor(max_workers=5) as executor:
        # We no longer need to pass the category_name
        futures = [executor.submit(scrape_page_from_api, title, game_version) for title in page_titles]
        for future in futures:
            future.result() # Wait for completion and handle exceptions
    
    logging.info("--- Full Site Scrape Job Completed ---")


if __name__ == '__main__':
    setup_logging()
    main() 