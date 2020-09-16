package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A class used to scrape the news portals on the internet.
 * @author Sime
 *
 */
public class Scraper {

	/**
	 * Performs the scraping jobs for a passed list of urls.
	 * @param urls A list of websites that should be scraped.
	 * @return Returns a list of strings which have been gathered from scraped websites.
	 * @throws IOException
	 */
	public ArrayList<String> scrapeWebsites(ArrayList<String> urls) throws IOException {

		
		ArrayList<String> result = new ArrayList<String>();
		
		for(String url : urls) {
			if(url.isBlank() || url.isEmpty())
				continue;
			
			ScraperJob sj = new ScraperJob(result, url);
			sj.populateList();
		}
		
		
		return result;
	}

}
