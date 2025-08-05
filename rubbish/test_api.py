import requests
import json
import time

def test_query():
    url = "http://localhost:8000/api/ask/v0.2"
    payload = {
        "query": "蜘蛛的血量",
        "n_results": 10,
        "rerank_top_k": 3
    }
    
    try:
        response = requests.post(url, json=payload)
        response.raise_for_status()
        result = response.json()
        print("Response received:")
        print(json.dumps(result, indent=2, ensure_ascii=False))
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    print("Testing API...")
    time.sleep(3)  # Wait for server to start
    test_query() 