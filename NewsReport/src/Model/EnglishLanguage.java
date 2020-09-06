package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class EnglishLanguage implements ILanguage {

	@Override
	public ArrayList<String> getCommonWords() {

		return new ArrayList<String>(Arrays.asList("a", "an", "the", "news", "sports", "sport", "breaking", "opinion",
				"culture", "lifestyle", "life", "style", "more", "search", "world", "business", "tech", "read", "check",
				"weather", "best", "worst", "better", "worse", "more", "less", "few", "fewer", "top", "new", "newest",
				"old", "oldest"));

	}

	@Override
	public ArrayList<String> getVerbs() {
		return new ArrayList<String>(Arrays.asList("am", "are", "is", "was", "were", "been", "have", "has", "had", "do",
				"does", "done", "will", "would", "can", "could", "say", "says", "go", "goes", "try", "tries", "get",
				"gets", "take", "takes"));

	}

	@Override
	public ArrayList<String> getPrepositions() {
		return new ArrayList<String>(Arrays.asList("at", "as", "about", "after", "above", "aside", "out", "on", "over",
				"in", "inside", "up", "under", "through", "to", "from", "between", "for", "of", "off"));

	}

	@Override
	public ArrayList<String> getPronouns() {
		return new ArrayList<String>(Arrays.asList("I", "me", "he", "she", "it", "us", "we", "you", "they", "them",
				"that", "those", "whose", "where", "when", "while", "whom", "which", "why", "with", "who", "mine", "my",
				"his", "her", "its", "our", "ours", "yours", "your", "their", "theirs", "how", "what", "one", "two",
				"three", "four"));

	}

	@Override
	public ArrayList<String> getConjunctions() {
		return new ArrayList<String>(Arrays.asList("and", "so", "yet", "but", "not", "no", "as", "however", "though"));

	}

	@Override
	public String getAppTitle() {
		return "NEWS REPORT";
	}

	@Override
	public String getUseCroatian() {
		return "Croatian";
	}

	@Override
	public String getUseEnglish() {
		return "English";
	}

	@Override
	public String getWebPanelTitle() {
		return "Online news sources";
	}

	@Override
	public ArrayList<String> getUrls() {
		return new ArrayList<>(Arrays.asList("https://www.theguardian.com/international",
				"https://www.independent.co.uk/", "https://www.bbc.com/news"));

	}

	@Override
	public String scrapeTitle() {
		return "Scrape news";
	}

	@Override
	public String settingsTitle() {
		return "Settings";
	}

	@Override
	public String analysisTitle() {
		return "Data analysis";
	}

	@Override
	public String helpTitle() {
		return "How to use the application";
	}

	@Override
	public String scrapeButton() {
		return "Scrape";
	}

	@Override
	public String analyseButton() {
		return "Analyze";
	}

	@Override
	public String applicationLanguageTitle() {
		return "Application Language";
	}

	@Override
	public String applicationLanguageSubtitle() {
		return "Changing the language also affects the scraped sites and database analysis.";
	}

	@Override
	public String saveButton() {
		return "Save";
	}

	@Override
	public String scrapeSubtitle() {
		return "Get the most frequent news topics from news portals.";
	}

	@Override
	public String confirmExit() {
		return "Are you sure you want to close the application?";
	}

	@Override
	public String exitTitle() {
		return "Exit";
	}

	@Override
	public String scrapeMenuTitle() {
		return "Scraping";
	}

	@Override
	public String analysisMenuTitle() {
		return "Analysis";
	}

	@Override
	public String exitMenuTitle() {
		return "Exit";
	}

	@Override
	public String helpMenuTitle() {
		return "Help";
	}

	@Override
	public String settingsMenuTitle() {
		return "Settings";
	}

	@Override
	public String helpStepOne() {
		// TODO Auto-generated method stub
		return "1. Use the Scrape panel to get the latest news.";
	}

	@Override
	public String helpStepTwo() {
		// TODO Auto-generated method stub
		return "2. On the Scrape panel, click \"scrape\" and then \"save\" to save the result in the database.";
	}

	@Override
	public String helpStepThree() {
		// TODO Auto-generated method stub
		return "3. On the \"analysis\" panel, search the database by:";
	}

	@Override
	public String helpStepThreeA() {
		// TODO Auto-generated method stub
		return "a) Date - get the most frequent topics for specific date";
	}

	@Override
	public String helpStepThreeB() {
		// TODO Auto-generated method stub
		return "b) Topic - get dates on which a specific topic was most frequently mentioned";
	}

	@Override
	public String sqlError() {
		return "An error occurred while connecting to the database!";
	}

	@Override
	public String scrapeError() {
		return "An error occurred while scraping news!";
	}

	@Override
	public String analysisSubtitle() {
		return "Search existing news data by topic or date.";
	}

	@Override
	public String searchByDate() {
		return "Search by date:";
	}

	@Override
	public String searchByTopic() {
		return "Search by topic:";
	}

	@Override
	public String mustChooseTopic() {
		return "You must enter a topic.";
	}

	@Override
	public String mustChooseDate() {
		return "You must choose a date.";
	}

	@Override
	public String queryGetTopicsOnDate() {
		return "EXECUTE getTopEnglishTopicsOnDate ?, ?, ?";
	}

	@Override
	public String queryGetDatesForTopic() {
		return "EXECUTE getDatesForEnglishTopics ?";
	}

	@Override
	public String queryInsertTopicForDate() {

		return "EXECUTE addEngTopicToDate ?, ?, ?, ?, ?";
	}

	@Override
	public String queryInsertNewDate() {

		return "EXECUTE addNewDate ?, ?, ?";
	}

	@Override
	public String queryInsertNewTopic() {
		return "EXECUTE insertNewEngTopic ?";
	}

	@Override
	public String topicsOnDateChartTitle() {
		return "Topics on date ";
	}

	@Override
	public String chartTopics() {
		return "Topics";
	}

	@Override
	public String chartOccurrence() {
		return "Number of articles";
	}

	@Override
	public String datesForTopicChartTitle() {
		return "Frequency of articles for topic ";
	}

	@Override
	public String chartDates() {
		return "Dates";
	}

}
