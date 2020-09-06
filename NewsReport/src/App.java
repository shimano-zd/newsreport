import java.awt.EventQueue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Controller.DBConnection;
import Controller.Scraper;
import Controller.WordCounter;
import Model.CroatianLanguage;
import Model.EnglishLanguage;
import Model.ILanguageFactory;
import Model.NewsModel;
import View.MainFrame;

public class App {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

//		Scraper scraper = new Scraper();
//		LanguageFactory lang = new CroatianLanguage();
//	ArrayList<String> urls = lang.getUrls();
//
//		// kreiranje brojaca rijeci
//		WordCounter counter = new WordCounter(scraper.scrapeWebsites(urls), lang);
//
//		// pohranimo rezultat tj vrsnih top 5 rijeci i njihov broj pojave
//		ArrayList<NewsModel> res = counter.getMostFrequentWords();
//
//		for(NewsModel n : res) {
//		System.out.println("Topic: "+n.getTopic() + " ---> count: " + n.getOccurrence());
//
//		}

//		try {
//			DBConnection con = new DBConnection();
//
//			// con.insertNewReportCro(res, 2019, 8, 27);
//			ResultSet rs = con.getTopCroatianTopics(2019, 8, 27);
//
//			while (rs.next()) {
//				System.out.println(rs.getString(1) + ": " + rs.getLong(3) + " occurrences");
//			}
//			con.closeSQLConnection();
//
//		} catch (ClassNotFoundException e) {
//
//			e.printStackTrace();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}

	}

}
