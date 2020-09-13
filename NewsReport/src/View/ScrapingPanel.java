package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;

import Controller.ApplicationState;
import Controller.DBConnection;
import Controller.ExceptionHandler;
import Controller.Scraper;
import Controller.WordCounter;
import Model.DateLabelFormatter;
import Model.ILanguage;
import Model.NewsModel;

/**
 * The panel used to scrape the news portals for popular topics and store the retrieved data in the database.
 * @author Sime
 *
 */
public class ScrapingPanel extends JPanel implements ILanguageStateObserver {

	private JTextArea textArea;
	private JLabel scrapingPanelTitle;
	private JLabel scrapingPanelSubtitle;
	private JTextField textFieldUrl1;
	private JTextField textFieldUrl2;
	private JTextField textFieldUrl3;
	private JLabel webIcon;
	private JButton buttonScrape;
	private JButton buttonSave;
	private JLabel loadingLabel;
	private Icon loadingAnimation;

	private Scraper scraper;
	private ApplicationState appState;
	private DateLabelFormatter dateFormatter;

	private ArrayList<NewsModel> topFive;

	private DBConnection dbConnection;

	public ScrapingPanel() {

		setLayout(null);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.lightGray));
		setBackground(Color.WHITE);
		setBounds(20, 49, 639, 423);

		appState = ApplicationState.instance();
		appState.addLanguageObserver(this);
		dateFormatter = new DateLabelFormatter();

		createComponents();
		activateComponents();
		

	}

	public void createComponents() {

		scraper = new Scraper();
		topFive = new ArrayList<NewsModel>();

		textFieldUrl1 = new JTextField();
		textFieldUrl1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldUrl1.setEditable(false);
		textFieldUrl1.setBounds(10, 55, 300, 20);
		add(textFieldUrl1);
		textFieldUrl1.setColumns(10);

		textFieldUrl2 = new JTextField();
		textFieldUrl2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldUrl2.setEditable(false);
		textFieldUrl2.setBounds(10, 86, 300, 20);
		add(textFieldUrl2);
		textFieldUrl2.setColumns(10);

		textFieldUrl3 = new JTextField();
		textFieldUrl3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldUrl3.setEditable(false);
		textFieldUrl3.setBounds(10, 117, 300, 20);
		add(textFieldUrl3);
		textFieldUrl3.setColumns(10);

		webIcon = new JLabel("");
		webIcon.setIcon(new ImageIcon(getClass().getResource("/images/icons8-internet-40.png")));
		webIcon.setBounds(10, 11, 40, 30);
		add(webIcon);

		buttonScrape = new JButton("");
		buttonScrape.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonScrape.setBackground(Color.WHITE);
		buttonScrape.setIcon(new ImageIcon(getClass().getResource("/images/icons8-search-property-40.png")));
		buttonScrape.setBounds(341, 55, 128, 82);
		add(buttonScrape);

		buttonSave = new JButton("");
		buttonSave.setEnabled(false);
		buttonSave.setIcon(new ImageIcon(getClass().getResource("/images/icons8-database.png")));
		buttonSave.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonSave.setBackground(Color.WHITE);
		buttonSave.setBounds(479, 55, 144, 82);
		add(buttonSave);

		loadingAnimation = new ImageIcon(getClass().getResource("/images/loading-spinner.gif"));
		
		loadingLabel = new JLabel(loadingAnimation);
		loadingLabel.setBackground(Color.WHITE);
		loadingLabel.setBounds(250, 200, 150, 150);
		loadingLabel.setVisible(false);
		add(loadingLabel);

		textArea = new JTextArea();
		textArea.setBounds(10, 152, 613, 260);
		textArea.setEditable(false);
		add(textArea);

		scrapingPanelTitle = new JLabel();
		scrapingPanelTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		scrapingPanelTitle.setBounds(70, 11, 138, 20);
		add(scrapingPanelTitle);

		scrapingPanelSubtitle = new JLabel();
		scrapingPanelSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		scrapingPanelSubtitle.setBounds(70, 30, 553, 14);
		add(scrapingPanelSubtitle);

		refreshLanguage();

	}

	private void activateComponents() {
		buttonScrape.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

//				LoadingRunnable loading = new LoadingRunnable();
//				try {
//					loading.execute();
//				} catch (Exception ex) {
//					ExceptionHandler.handle(ex, appState.getActiveLanguage().scrapeError());
//				} finally {
//
//				}
				
				topFive.clear();
				
				ScrapingAsyncTask scrapingTask = new ScrapingAsyncTask();
				scrapingTask.start();
					
			}
		});

		buttonSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				SaveToDatabseAsyncTask saveToDatabase = new SaveToDatabseAsyncTask();
				saveToDatabase.start();
				buttonSave.setEnabled(false);
				
					
			}
		});
	}
	
	/**
	 * A thread used to scrape the information in the background.
	 * @author Sime
	 *
	 */
	private class ScrapingAsyncTask extends Thread{

		@Override
		public void run() {
			
			try {
				
				startLoading();
				ArrayList<String> scrapedData = scraper.scrapeWebsites(appState.getActiveLanguage().getUrls());
				refreshNews(new WordCounter(scrapedData, appState.getActiveLanguage()).getMostFrequentWords());
				
				
			}catch(Exception ex) {
				ExceptionHandler.handle(ex, appState.getActiveLanguage().scrapeError());;
			}finally {
				stopLoading();
			}
		}
		
	}
	
	/**
	 * A thread used to store the information in the database.
	 * @author Sime
	 *
	 */
	private class SaveToDatabseAsyncTask extends Thread{
		@Override
		public void run() {
			
			try {
				
				startLoading();
				LocalDate localDate = LocalDate.now();
				if(dbConnection == null) {
					dbConnection = new DBConnection();
				}
				dbConnection.insertNewReport(topFive, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
				textArea.setText("Saved!");
				
			}catch(Exception ex) {
				ExceptionHandler.handle(ex, appState.getActiveLanguage().sqlError());
			}finally {
				stopLoading();
			}
			
		}
	}
	
	/**
	 * The method called by background threads to start the loading animation in the UI thread.
	 */
	private void startLoading() {
		buttonSave.setEnabled(false);
		buttonScrape.setEnabled(false);
		loadingLabel.setVisible(true);
	}

	/**
	 * Used to stop the loading animation after a thread's done.
	 */
	private void stopLoading() {
		loadingLabel.setVisible(false);
		buttonScrape.setEnabled(true);
		buttonSave.setEnabled(true);
	}
	
//	private class LoadingRunnable extends SwingWorker<Void, Void> {
//
//		ArrayList<String> data = new ArrayList<String>();
//		ArrayList<NewsModel> result = new ArrayList<>();
//
//		@Override
//		protected Void doInBackground() throws Exception {
//
//			startLoading();
//
//			ArrayList<String> urls = new ArrayList<>();
//			urls.add(textFieldUrl1.getText());
//			urls.add(textFieldUrl2.getText());
//			urls.add(textFieldUrl3.getText());
//
//			data = scraper.scrapeWebsites(urls);
//
//			WordCounter wc = new WordCounter(data, appState.getActiveLanguage());
//			result = wc.getMostFrequentWords();
//
//			return null;
//		}
//
//		@Override
//		protected void done() {
//
//			stopLoading();
//
//			topFive = result;
//			showMostFrequentNews();
//
//		}
//
//	}

	/**
	 * Displays the most frequent news topic in the panel's text area.
	 */
	private void showMostFrequentNews() {

		textArea.setText("");

		for (NewsModel n : topFive) {
			textArea.setText(textArea.getText() + "\n" + "Topic " + n.getTopic() + " was mentioned " + n.getOccurrence()
					+ " times.");
		}

	}

	/**
	 * Update the language strings in response to app state.
	 */
	@Override
	public void updateOnLanguageChange() {
		refreshLanguage();

	}

	private void refreshLanguage() {
		ILanguage activeLanguage = appState.getActiveLanguage();

		textFieldUrl1.setText(activeLanguage.getUrls().get(0));
		textFieldUrl2.setText(activeLanguage.getUrls().get(1));
		textFieldUrl3.setText(activeLanguage.getUrls().get(2));

		buttonSave.setText(activeLanguage.saveButton());
		buttonScrape.setText(activeLanguage.scrapeButton());

		scrapingPanelTitle.setText(activeLanguage.scrapeTitle());
		scrapingPanelSubtitle.setText(activeLanguage.scrapeSubtitle());
		
		textArea.setText("");

	}

	/**
	 * The method refreshes the "top five" news after a thread is done analyzing.
	 * @param news
	 */
	private void refreshNews(ArrayList<NewsModel> news) {
		topFive = news;
		showMostFrequentNews();
	}

}
