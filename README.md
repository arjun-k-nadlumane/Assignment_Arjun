# Web Scraping Project

## Overview
🚀This project highlights an advanced web scraping approach that not only extracts the provided website's content  but also intelligently scrapes all the associated website and all the urls present in the given web url. making it a powerful tool!

This project consists of **two separate scripts** for web scraping:

1. **`scrap-using-xml.py`** - Extracts all links from the website’s `sitemap.xml`. If no sitemap is found, it recursively scrapes links directly from the website and saves them to `output.txt`.
2. **`scrap-from-outputfile.py`** - Reads extracted links from `output.txt`, visits each link, extracts the text content, and stores it in `output_directory` in batches of 50 links per file.

## Features
✅ Extracts URLs from `sitemap.xml`, or crawls the website if no sitemap is available.
✅ Saves all extracted links in `output.txt`.
✅ Scrapes **text content only** (no images, videos, or other media).
✅ Saves scraped data in `output_directory` with **50 links per file** to prevent overflow.
✅ Avoids scraping duplicate URLs.
✅ Handles errors gracefully and ensures smooth execution.

## Requirements
- Python 3.x
- Required libraries:
  ```sh
  pip install requests beautifulsoup4
  ```

## How to Use

### **Step 1: Extract URLs from a Website**
Run the first script to extract all URLs from `sitemap.xml` (or directly crawl the website if no sitemap exists):
```sh
python scrap-using-xml.py
```
🔹 This will generate a file named `output.txt`, which contains all extracted URLs.

---

### **Step 2: Scrape Text Content from Extracted URLs**
Run the second script to visit each link in `output.txt` and save the extracted content:
``
python scrap-from-outputfile.py
```
🔹 Scraped content will be saved in `output_directory` as:
- `output_directory/output_0.txt` (content from first 50 links)
- `output_directory/output_1.txt` (next 50 links), and so on.

## Output Files
📄 `output.txt` - Contains all extracted URLs.
📂 `output_directory/` - Stores scraped text content.

## Notes
⚠️ Ensure that the target website allows web scraping (check `robots.txt`).
⚠️ This script **does not** download images or multimedia files.
⚠️ Run responsibly to avoid overwhelming servers.
