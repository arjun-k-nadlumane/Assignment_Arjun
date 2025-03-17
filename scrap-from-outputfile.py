import requests
from bs4 import BeautifulSoup
import os

def get_clean_text_from_url(url):
    try:
 
        response = requests.get(url)
        response.raise_for_status() 
        soup = BeautifulSoup(response.text, 'html.parser')
        text = ' '.join([element.get_text() for element in soup.find_all(string=True)])
        cleaned_text = ' '.join(text.split())
        return cleaned_text
    except requests.RequestException as e:
        print("Request failed for URL:", url, "-", str(e))
        return None
    except Exception as e:
        print("An error occurred while processing URL:", url, "-", str(e))
        return None

def save_text_to_file(text, filename):
    try:
        with open(filename, 'w', encoding='utf-8') as file:
            file.write(text)
        print("Text saved to", filename)
    except OSError as e:
        print("Error writing to file:", str(e))
    except Exception as e:
        print("An error occurred while saving the text to", filename, ":", str(e))

def extract_content_for_links(links, output_folder):
    try:
        for i in range(0, len(links), 50):
            subset_links = links[i:i+50]
            subset_content = ""
            for link in subset_links:
                link = link.strip() 
                if not link:
                    continue 
                cleaned_text = get_clean_text_from_url(link)
                if cleaned_text:
                    subset_content += cleaned_text + '\n\n'
            
            filename = os.path.join(output_folder, f"output_{i // 50}.txt")
            save_text_to_file(subset_content, filename)
    except Exception as e:
        print("An error occurred while extracting content from the links:", str(e))

def read_links_from_file(links_file):
    try:
        if not os.path.exists(links_file):
            print("Input file not found:", links_file)
            return []
        with open(links_file, 'r') as f:
            links = f.readlines()
        return [link.strip() for link in links if link.strip()]
    except Exception as e:
        print("An error occurred while reading links from the file:", str(e))
        return []


links_file = r'C:\Users\Arjun Nadlumane\Desktop\Projects\webscraping\output3.txt' 
output_folder = r'C:\Users\Arjun Nadlumane\Desktop\Projects\webscraping\output_directory3' 

if not os.path.exists(output_folder):
    os.makedirs(output_folder)

links = read_links_from_file(links_file)
extract_content_for_links(links, output_folder)
