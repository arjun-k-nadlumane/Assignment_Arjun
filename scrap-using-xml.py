import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin, urlparse
from xml.etree import ElementTree as ET

def get_clean_text_from_url(url):
    try:
        # Fetching webpage content
        response = requests.get(url)
        if response.status_code == 200:
            # Parsing HTML content
            soup = BeautifulSoup(response.text, 'html.parser')
            # Extracting text from the soup object
            cleaned_text = soup.get_text(separator=' ')
            return cleaned_text.strip(), soup
        else:
            print("Failed to retrieve content. Status code:", response.status_code)
            return None, None
    except Exception as e:
        print("An error occurred:", str(e))
        return None, None

def save_text_to_file(text, filename='output3.txt'):
    try:
        with open(filename, 'a', encoding='utf-8') as file:  # Append mode to save text from multiple pages
            file.write(text + '\n\n')  # Add some separation between pages
        print("Text saved to", filename)
    except Exception as e:
        print("An error occurred while saving the text:", str(e))

def extract_website_content(base_url, visited_urls=set(), max_depth=3, current_depth=0):
    if current_depth > max_depth:
        return

    if base_url in visited_urls:
        return

    visited_urls.add(base_url)

    cleaned_text, soup = get_clean_text_from_url(base_url)

    if cleaned_text:
        save_text_to_file(cleaned_text)

        # Find all links on the page and follow them recursively
        for link in soup.find_all('a', href=True):
            next_url = urljoin(base_url, link['href'])
            if urlparse(next_url).netloc == urlparse(base_url).netloc:  # Ensure it's within the same domain
                extract_website_content(next_url, visited_urls, max_depth, current_depth + 1)

def get_sitemap_urls(base_url):
    sitemap_url = urljoin(base_url, '/sitemap.xml')
    try:
        response = requests.get(sitemap_url)
        if response.status_code == 200:
            tree = ET.fromstring(response.content)
            return [elem.text for elem in tree.findall('.//{http://www.sitemaps.org/schemas/sitemap/0.9}loc')]
        else:
            print("Failed to retrieve sitemap.xml. Status code:", response.status_code)
            return []
    except Exception as e:
        print("An error occurred while fetching sitemap.xml:", str(e))
        return []

def main(base_url):
    sitemap_urls = get_sitemap_urls(base_url)
    if sitemap_urls:
        for url in sitemap_urls:
            extract_website_content(url)
    else:
        extract_website_content(base_url)

# Example usage
base_url = "https://www.bnmit.org/"  # Replace with the base URL of the website
main(base_url)
