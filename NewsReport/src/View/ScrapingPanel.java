package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.print.DocFlavor.STRING;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.SwingConstants;

/**
 * The panel used to scrape the news portals for popular topics and store the
 * retrieved data in the database.
 * 
 * @author Sime
 *
 */
public class ScrapingPanel extends JPanel implements ILanguageStateObserver {

	private JTextArea textArea;
	private JLabel scrapingPanelTitle;
	private JLabel scrapingPanelSubtitle;
	

	private WebSiteInput websiteinput;
	private ArrayList<WebSiteInput> webSiteInputs;

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
	

	private JButton buttonAddSite;

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
		webSiteInputs = new ArrayList<WebSiteInput>();

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
		loadingLabel.setBounds(246, 262, 150, 150);
		loadingLabel.setVisible(false);
		add(loadingLabel);

		textArea = new JTextArea();
		textArea.setBounds(10, 241, 613, 171);
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

//		textFieldUrl4 = new JTextField();
//		textFieldUrl4.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//		textFieldUrl4.setEditable(true);
//		textFieldUrl4.setColumns(10);
//		textFieldUrl4.setBounds(10, 179, 300, 20);
//		add(textFieldUrl4);

//		textFieldUrl5 = new JTextField();
//		textFieldUrl5.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//		textFieldUrl5.setEditable(true);
//		textFieldUrl5.setColumns(10);
//		textFieldUrl5.setBounds(10, 210, 300, 20);
//		add(textFieldUrl5);

		buttonAddSite = new JButton("Add site");
		buttonAddSite.setIcon(new ImageIcon(ScrapingPanel.class.getResource("/images/icon8-plus.png")));
		buttonAddSite.setBackground(Color.WHITE);
		buttonAddSite.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		buttonAddSite.setBounds(10, 52, 107, 23);
		add(buttonAddSite);

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
				textArea.setText("");

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

		buttonAddSite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (webSiteInputs.size() > 4) {
					JOptionPane.showMessageDialog(null, "You can only search 5 sites at the same time!");
					return;
				}

				WebSiteInput newInput = new WebSiteInput();
				webSiteInputs.add(newInput);

				newInput.setBounds(10, (56 + (30 * webSiteInputs.size())), 300, 20);

				add(newInput);
				validate();
				repaint();

				newInput.getRemoveButton().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						removeInputPanel(newInput);

					}
				});

			}
		});
	}

	private void removeInputPanel(WebSiteInput panel) {

		int indexOfPanel = webSiteInputs.indexOf(panel);

		webSiteInputs.removeIf(x -> x.getId() == panel.getId());
		remove(panel);
		
		for (WebSiteInput input : webSiteInputs) {

			input.setBounds(10, (56 + (30 * (webSiteInputs.indexOf(input) +1))), 300, 20);

		}

		validate();
		repaint();

	}

	/**
	 * A thread used to scrape the information in the background.
	 * 
	 * @author Sime
	 *
	 */
	private class ScrapingAsyncTask extends Thread {

		@Override
		public void run() {

			try {

				ArrayList<String> websites = new ArrayList<String>();
				
				for(WebSiteInput input : webSiteInputs) {
					websites.add(input.getText());
				}

				boolean websitesProvided = false;

				for (String s : websites) {
					if (!s.isEmpty() || !s.isBlank()) {
						websitesProvided = true;
						break;
					}
				}

				if (!websitesProvided) {
					JOptionPane.showMessageDialog(null, appState.getActiveLanguage().youHaveToProvideWebsite());
					return;
				}
				startLoading();
				ArrayList<String> scrapedData = scraper.scrapeWebsites(websites);

				refreshNews(new WordCounter(scrapedData, appState.getActiveLanguage()).getMostFrequentWords());

			} catch (Exception ex) {
				ExceptionHandler.handle(ex, appState.getActiveLanguage().scrapeError());
				;
			} finally {
				stopLoading();
			}
		}

	}

	/**
	 * A thread used to store the information in the database.
	 * 
	 * @author Sime
	 *
	 */
	private class SaveToDatabseAsyncTask extends Thread {
		@Override
		public void run() {

			try {

				startLoading();
				LocalDate localDate = LocalDate.now();
				if (dbConnection == null) {
					dbConnection = new DBConnection();
				}
				dbConnection.insertNewReport(topFive, localDate.getYear(), localDate.getMonthValue(),
						localDate.getDayOfMonth());
				textArea.setText("Saved!");

			} catch (Exception ex) {
				ExceptionHandler.handle(ex, appState.getActiveLanguage().sqlError());
			} finally {
				stopLoading();
			}

		}
	}

	/**
	 * The method called by background threads to start the loading animation in the
	 * UI thread.
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

	/**
	 * Displays the most frequent news topic in the panel's text area.
	 */
	private void showMostFrequentNews() {

		textArea.setText("");

		for (NewsModel n : topFive) {
			textArea.setText(textArea.getText() + "\n" + appState.getActiveLanguage().topic() + n.getTopic()
					+ appState.getActiveLanguage().mentioned() + n.getOccurrence()
					+ appState.getActiveLanguage().times());
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

		for(WebSiteInput input : webSiteInputs) {
			remove(input);
		}
		webSiteInputs.clear();
		

		for (String url : activeLanguage.getUrls()) {
			WebSiteInput input = new WebSiteInput();

			input.setText(url);
			input.getRemoveButton().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					removeInputPanel(input);

				}
			});

			webSiteInputs.add(input);
			input.setBounds(10, (56 + (30 * webSiteInputs.size())), 300, 20);
			add(input);

		}
		
		validate();
		repaint();

		buttonSave.setText(activeLanguage.saveButton());
		buttonScrape.setText(activeLanguage.scrapeButton());

		scrapingPanelTitle.setText(activeLanguage.scrapeTitle());
		scrapingPanelSubtitle.setText(activeLanguage.scrapeSubtitle());

		textArea.setText("");

	}

	/**
	 * The method refreshes the "top five" news after a thread is done analyzing.
	 * 
	 * @param news
	 */
	private void refreshNews(ArrayList<NewsModel> news) {
		topFive = news;
		showMostFrequentNews();
	}
}
