import requests
from bs4 import BeautifulSoup
import subprocess
import argparse
from urllib.parse import urljoin
import time
import logging
import os
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager

# Configure basic logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

def get_page_links(category_url: str):
    """
    Fetches a Fandom wiki category page using a headless browser (Selenium)
    to bypass anti-crawling measures, and extracts all unique links.
    """
    logging.info(f"Discovering links from category page using Selenium: {category_url}")
    
    # --- Start: Force WebDriver Manager to use a mirror in China ---
    logging.info("Configuring WebDriver to use a mirror for download.")
    os.environ['WDM_CHROMEDRIVER_URL'] = 'https://registry.npmmirror.com/-/binary/chromedriver'
    # The following line is for newer versions of selenium/webdriver-manager that might use a different URL schema
    os.environ['WDM_GOOGLECHROME_DRIVER_URL'] = 'https://registry.npmmirror.com/-/binary/chrome-for-testing'
    # --- End: Mirror Configuration ---

    # Setup headless Chrome browser
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument("--log-level=3") # Suppress unnecessary logs from webdriver
    
    driver = None
    try:
        # Initialize WebDriver
        service = Service(ChromeDriverManager().install())
        driver = webdriver.Chrome(service=service, options=chrome_options)
        
        # Get the page
        driver.get(category_url)
        
        # --- Start: Handle Cookie Consent Banner ---
        try:
            logging.info("Waiting for the cookie consent banner to appear...")
            # Wait up to 10 seconds for the accept button to be clickable
            accept_button = WebDriverWait(driver, 10).until(
                EC.element_to_be_clickable((By.ID, 'onetrust-accept-btn-handler'))
            )
            logging.info("Cookie consent banner found. Clicking the accept button.")
            accept_button.click()
            # Wait a moment for the banner to disappear and page to settle
            time.sleep(2)
        except Exception:
            # If the banner doesn't appear or something goes wrong, we log it but continue.
            # This makes the script robust in case the banner is not always shown.
            logging.warning("Cookie consent banner not found or could not be clicked. Continuing anyway.")
        # --- End: Handle Cookie Consent Banner ---

        # Let the page load, especially if there's JavaScript involved
        logging.info("Waiting for page to load...")
        time.sleep(5) # Increased wait time to 5 seconds
        
        # --- Start: Debugging Step ---
        logging.info("Saving screenshot to debug_screenshot.png")
        driver.save_screenshot('debug_screenshot.png')
        
        logging.info("Saving page source to debug_page_source.html")
        with open('debug_page_source.html', 'w', encoding='utf-8') as f:
            f.write(driver.page_source)
        # --- End: Debugging Step ---

        # Get the page source after rendering
        page_source = driver.page_source
        
    except Exception as e:
        logging.error(f"Selenium failed to fetch page {category_url}: {e}")
        return []
    finally:
        if driver:
            driver.quit() # Ensure the browser is closed

    soup = BeautifulSoup(page_source, 'html.parser')
    
    # The links to category members are within a specific div
    content_area = soup.find('div', class_='category-page__members')
    if not content_area:
        logging.warning("Could not find the category members div. No links will be extracted.")
        return []

    links = set() # Use a set to avoid duplicates
    # Find all list items, as each link is within a <li>
    for li_tag in content_area.find_all('li', class_='category-page__member'):
        a_tag = li_tag.find('a')
        if a_tag and a_tag.has_attr('href'):
            href = a_tag['href']
            # We only want actual content pages, which typically don't contain a colon in the link part
            if ':' not in href:
                full_url = urljoin(category_url, href)
                links.add(full_url)
    
    logging.info(f"Discovered {len(links)} unique content page links via Selenium.")
    return sorted(list(links))


def main():
    """
    Main function to drive the crawling process.
    """
    parser = argparse.ArgumentParser(
        description="Crawl a Fandom Wiki category to scrape all its member pages.",
        formatter_class=argparse.RawTextHelpFormatter
    )
    parser.add_argument(
        "start_url", 
        type=str, 
        help="The URL of the Fandom Wiki category page to start crawling from.\n"
             "Example for creatures: https://dontstarve.fandom.com/wiki/Category:Creatures\n"
             "Example for items: https://dontstarve.fandom.com/wiki/Category:Items"
    )
    parser.add_argument(
        "--delay",
        type=float,
        default=1.0,
        help="Delay in seconds between scraping each page to be polite to the server."
    )

    args = parser.parse_args()

    page_urls = get_page_links(args.start_url)

    if not page_urls:
        logging.error("No pages to scrape. Exiting.")
        return

    total_pages = len(page_urls)
    logging.info(f"Starting to scrape {total_pages} pages...")

    for i, url in enumerate(page_urls):
        logging.info(f"--- Scraping page {i+1}/{total_pages}: {url} ---")
        try:
            # We call the existing script using subprocess
            command = ["python", "scripts/scrape_wiki.py", url]
            process = subprocess.run(
                command,
                check=True,        # Raise an exception if the script returns a non-zero exit code
                capture_output=True, # Capture stdout and stderr
                text=True          # Decode stdout/stderr as text
            )
            # Log the output from the scrape_wiki.py script
            if process.stdout:
                logging.info(f"Output from scrape_wiki.py:\n{process.stdout.strip()}")
            if process.stderr:
                logging.warning(f"Errors from scrape_wiki.py:\n{process.stderr.strip()}")

        except FileNotFoundError:
            logging.error("Error: 'python' command not found. Make sure Python is in your system's PATH.")
            break
        except subprocess.CalledProcessError as e:
            logging.error(f"The script 'scripts/scrape_wiki.py' failed for URL {url} with exit code {e.returncode}.")
            # Log the output even on failure
            if e.stdout:
                logging.error(f"STDOUT: {e.stdout.strip()}")
            if e.stderr:
                logging.error(f"STDERR: {e.stderr.strip()}")
        except Exception as e:
            logging.error(f"An unexpected error occurred while processing {url}: {e}")

        # Be polite and wait a bit before the next request
        if i < total_pages - 1:
            logging.info(f"Waiting for {args.delay} seconds...")
            time.sleep(args.delay)

    logging.info("--- Crawling finished! ---")

if __name__ == "__main__":
    main() 