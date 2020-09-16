package Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The class that defines the string constants for the Croatian language.
 * @author Sime
 *
 */
public class CroatianLanguage implements ILanguage {

	@Override
	public ArrayList<String> getCommonWords() {
		return new ArrayList<String>(Arrays.asList("vijesti", "novosti", "sport", "politika", "horoskop", "moda",
				"news", "home", "life", "style", "life&style", "viral", "video", "proèitajte", "video:", "foto",
				"foto:", "nova", "novi", "novo", "pogledajte", "rtl", "vijesti", "svijet", "info",
				"hrvatska", "zanimljivosti"));

	}

	@Override
	public ArrayList<String> getVerbs() {
		return new ArrayList<String>(Arrays.asList("sam", "smo", "ste", "biti", "bio", "bila", "bilo", "bili", "imam",
				"imaš", "ima", "imamo", "imate", "imaju", "imati", "æe", "æemo", "æete", "jesam", "jesi", "jest",
				"jesmo", "jeste", "jesu", "nisam", "nisi", "nije", "nismo", "niste", "nisu", "može", "mogu", "možemo"));

	}

	@Override
	public ArrayList<String> getPrepositions() {
		return new ArrayList<String>(Arrays.asList("ispod", "pored", "nakon", "iza", "ispred", "pokraj", "kod"));
	}

	@Override
	public ArrayList<String> getPronouns() {
		return new ArrayList<String>(Arrays.asList("ona", "ono", "oni", "ovo", "mene", "tebe", "njega", "nju", "nas",
				"vas", "njih", "onaj", "onog", "onom", "onome", "onoj", "koji", "koja", "kojo", "koje", "kojim",
				"kojima", "kojemu", "jedan", "jedna", "jedno", "jednom", "jednoj", "jednom", "koliko", "kada", "kad",
				"kud", "kuda", "moj", "moja", "moje", "moji", "tvoj", "tvoja", "tvoje", "tvoji", "njegov", "njegova",
				"njegovi", "njegove", "njezin", "njen", "njeno", "njena", "njezina", "njeni", "njezini", "njezine",
				"svoj", "svoja", "svoje", "svog", "svoga", "svoji", "svojih", "meni", "nam", "nama", "njemu", "njoj",
				"zašto", "zato", "gdje", "tamo", "neki", "neka", "neko", "sav", "sva", "svo", "svi", "sve", "evo",
				"eno", "tamo", "ovdje", "negdje", "sada", "tada", "prije", "tko", "što", "koga"));
	}

	@Override
	public ArrayList<String> getConjunctions() {
		return new ArrayList<String>(Arrays.asList("èak", "niti", "pak", "ili", "ali", "dok", "god", "nego", "veæ",
				"kad", "samo", "osim", "dakle", "zato", "stoga", "èim", "pošto", "kako", "jer", "ako", "iako", "što",
				"gdje", "kada", "kad", "kao", "nimalo", "...", "!!!", "???", "zašto", "još", "zbog"));
	}

	@Override
	public String getAppTitle() {
		return "IZVJEŠTAJ VIJESTI";
	}

	@Override
	public String getUseCroatian() {
		return "Hrvatski";
	}

	@Override
	public String getUseEnglish() {
		return "Engleski";
	}

	@Override
	public String getWebPanelTitle() {
		return "Online izvori vijesti";
	}

	@Override
	public ArrayList<String> getUrls() {
		return new ArrayList<String>(
				Arrays.asList("https://www.dnevnik.hr/", "https://www.jutarnji.hr", "https://www.rtl.hr/vijesti-hr"));
	}

	@Override
	public String scrapeTitle() {
		return "Dohvati novosti";
	}

	@Override
	public String settingsTitle() {
		return "Postavke";
	}

	@Override
	public String analysisTitle() {
		return "Analiza podataka";
	}

	@Override
	public String helpTitle() {
		return "Kako koristiti aplikaciju";
	}

	@Override
	public String scrapeButton() {
		return "Dohvati";
	}

	@Override
	public String analyseButton() {
		return "Analiziraj";
	}

	@Override
	public String applicationLanguageTitle() {
		return "Jezik aplikacije";
	}

	@Override
	public String applicationLanguageSubtitle() {
		return "Promjena jezika takoðer utjeèe na dohvat stranica i analizu podataka.";
	}

	@Override
	public String saveButton() {
		return "Spremi";
	}

	@Override
	public String scrapeSubtitle() {
		return "Dohvati najèešæe teme s novinskih portala.";
	}

	@Override
	public String confirmExit() {
		return "Jeste li sigurni da želite zatvoriti aplikaciju?";
	}

	@Override
	public String exitTitle() {
		return "Izlaz";
	}

	@Override
	public String scrapeMenuTitle() {
		// TODO Auto-generated method stub
		return "Dohvat";
	}

	@Override
	public String analysisMenuTitle() {
		return "Analiza";
	}

	@Override
	public String exitMenuTitle() {
		return "Izlaz";
	}

	@Override
	public String helpMenuTitle() {
		return "Pomoæ";
	}

	@Override
	public String settingsMenuTitle() {
		return "Postavke";
	}

	@Override
	public String helpStepOne() {
		return "1. Na \"Dohvat\" panelu dohvatite najnovije vijesti.";
	}

	@Override
	public String helpStepTwo() {
		return "2. Na \"Dohvat\" panelu, kliknite \"dohvati\" pa \"spremi\" kako bi rezultat spremili u bazu podataka.";
	}

	@Override
	public String helpStepThree() {
		return "3. Na panelu \"Analiza\", pretražite bazu prema:";
	}

	@Override
	public String helpStepThreeA() {
		return "a) Datumu - pregled najèešæih tema za odreðeni datum.";
	}

	@Override
	public String helpStepThreeB() {
		return "b) Temi - pregled datuma na koje se zadana tema najèešæe pojavila u vijestima.";
	}

	@Override
	public String sqlError() {
		return "Dogodila se greška prilikom spajanja na bazu!";
	}

	@Override
	public String scrapeError() {
		return "Dogodila se greška prilikom dohvata vijesti!";
	}

	@Override
	public String analysisSubtitle() {
		return "Pretraga dosadašnjih vijesti po naslovima ili datumu.";
	}

	@Override
	public String searchByDate() {
		return "Pretraga po datumu:";
	}

	@Override
	public String searchByTopic() {
		return "Pretraga po temi:";
	}

	@Override
	public String mustChooseTopic() {
		return "Morate unijeti temu.";
	}

	@Override
	public String mustChooseDate() {
		return "Morate odabrati datum.";
	}

	@Override
	public String queryGetTopicsOnDate() {
		return "EXECUTE getTopCroatianTopicsOnDate ?, ?, ?";
	}

	@Override
	public String queryGetDatesForTopic() {
		return "EXECUTE getDatesForCroatianTopics ?";
	}

	@Override
	public String queryInsertTopicForDate() {
		return "EXECUTE addCroTopicToDate ?, ?, ?, ?, ?";
	}

	@Override
	public String queryInsertNewDate() {
		return "EXECUTE addNewDate ?, ?, ?";
	}

	@Override
	public String queryInsertNewTopic() {
		return "EXECUTE insertNewCroTopic ?";
	}

	@Override
	public String topicsOnDateChartTitle() {
		return "Teme na datum ";
	}

	@Override
	public String chartTopics() {
		return "Teme";
	}

	@Override
	public String chartOccurrence() {
		return "Broj èlanaka";
	}

	@Override
	public String datesForTopicChartTitle() {
		return "Uèestalost èlanaka za temu ";
	}

	@Override
	public String chartDates() {
		return "Datumi";
	}

	@Override
	public String youHaveToProvideWebsite() {
		return "Morate unijeti barem jednu stranicu!";
	}

	@Override
	public String topic() {
		return "Tema ";
	}

	@Override
	public String mentioned() {
		return " se spominje ";
	}

	@Override
	public String times() {
		return " puta";
	}

	@Override
	public String youCanOnlySearchFiveSites() {
		return "Možete pretraživati najviše pet portala!";
	}

}
