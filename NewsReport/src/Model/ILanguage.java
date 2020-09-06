package Model;

import java.util.ArrayList;

public interface ILanguage {

	 ArrayList<String> getCommonWords();
	 ArrayList<String> getVerbs();
	 ArrayList<String> getPrepositions();
	 ArrayList<String> getPronouns();
	 ArrayList<String> getConjunctions();
	 ArrayList<String> getUrls();
	 
	 String getAppTitle();
	 String getUseCroatian();
	 String getUseEnglish();
	 String getWebPanelTitle();
	 
	 
	 String scrapeTitle();
	 String scrapeSubtitle();
	 String settingsTitle();
	 String analysisTitle();
	 String analysisSubtitle();
	 String helpTitle();
	 String exitTitle();
	 
	 String scrapeMenuTitle();
	 String analysisMenuTitle();
	 String exitMenuTitle();
	 String helpMenuTitle();
	 String settingsMenuTitle();
	 
	 String scrapeButton();
	 String analyseButton();
	 String applicationLanguageTitle();
	 String applicationLanguageSubtitle();
	 String saveButton();
	 String confirmExit();
	 
	 String helpStepOne();
	 String helpStepTwo();
	 String helpStepThree();
	 String helpStepThreeA();
	 String helpStepThreeB();
	 
	 String searchByDate();
	 String searchByTopic();
	 
	 //LanguageType getLanguageType();
	 
	 String sqlError();
	 String scrapeError();
	 String mustChooseTopic();
	 String mustChooseDate();
	 
	 String queryGetTopicsOnDate();
	 String queryGetDatesForTopic();
	 
	 String queryInsertTopicForDate();
	 String queryInsertNewDate();
	 String queryInsertNewTopic();
	 
	 String topicsOnDateChartTitle();
	 String chartTopics();
	 String chartOccurrence();
	 String datesForTopicChartTitle();
	 String chartDates();
	 
	 
	 
}
