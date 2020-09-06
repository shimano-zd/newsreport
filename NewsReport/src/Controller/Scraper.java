package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {

	public ArrayList<String> scrapeWebsites(ArrayList<String> urls) throws IOException {

		ArrayList<String> result = new ArrayList<String>();
		
		for(String url : urls) {
			
			ScraperJob sj = new ScraperJob(result, url);
			sj.populateList();
		}
		
		return result;
	}

}
