package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A class responsible for the actual scraping logic. Used by the class Scraper to perform the scraping task.
 * @author Sime
 *
 */
public class ScraperJob {

	private ArrayList<String> listOfWords;
	private String url;
	private Document doc;

	public ScraperJob(ArrayList<String> listOfWords, String url) {

		this.url = url;
		this.listOfWords = listOfWords;

	}

	/**
	 * Inserts scraped information in the list. The list is passed from the parent (Scraper class).
	 */
	public void populateList() {

		try {

			this.doc = Jsoup.connect(url).timeout(6000).get();

			final Elements ele_h2 = doc.select("h2");
			final Elements ele_h3 = doc.select("h3");
			final Elements ele_h4 = doc.select("h4");
			final List<Elements> elements = new ArrayList<>();
			elements.add(ele_h2);
			elements.add(ele_h3);
			elements.add(ele_h4);

			for (Elements el : elements) {

				for (Element e : el) {
					if (!listOfWords.contains(e.text())) {
						listOfWords.add(e.text());
					}

				}

			}

		} catch (IOException e1) {

			System.out.println("Scraper took too long to fetch " + url + ".... cancelling fetch");
		}
	}

}
