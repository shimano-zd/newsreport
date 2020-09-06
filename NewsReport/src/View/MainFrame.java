package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.CroatianLanguage;
import Model.DateLabelFormatter;
import Model.EnglishLanguage;
import Model.ILanguage;
import Model.ILanguageFactory;
import Model.LanguageFactory;
import Model.LanguageType;
import Model.NewsModel;
import Model.PanelType;

import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Controller.ApplicationState;
import Controller.DBConnection;
import Controller.Scraper;
import org.jdatepicker.JDatePicker;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame implements IActivePanelObserver, ILanguageStateObserver {

	private JPanel contentPane;
	private JLabel titleLabel;
	private ILanguageFactory languageFactory;
	private ILanguage language;
	private MenuPanel menuPanel;
	private ScrapingPanel scrapingPanel;
	private SettingsPanel settingsPanel;
	private HelpPanel helpPanel;
	private AnalysisPanel analysisPanel;
	private ApplicationState appState;

	public MainFrame() {

		setTitle("NewsReport");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 560);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		createComponents();

		appState = ApplicationState.instance();
		appState.addPanelObserver(this);
		appState.addLanguageObserver(this);

	}

	private void createComponents() {

		languageFactory = new LanguageFactory();
		language = languageFactory.createLanguage(LanguageType.English);

		menuPanel = new MenuPanel();
		menuPanel.setSize(130, 468);
		menuPanel.setLocation(10, 42);

		settingsPanel = new SettingsPanel();
		settingsPanel.setSize(673, 470);
		settingsPanel.setLocation(150, 42);
		settingsPanel.setVisible(false);

		scrapingPanel = new ScrapingPanel();
		scrapingPanel.setSize(673, 468);
		scrapingPanel.setLocation(150, 42);
		scrapingPanel.setVisible(true);

		helpPanel = new HelpPanel();
		helpPanel.setSize(673, 468);
		helpPanel.setLocation(150, 42);
		helpPanel.setVisible(false);

		analysisPanel = new AnalysisPanel();
		analysisPanel.setSize(673, 468);
		analysisPanel.setLocation(150, 42);
		analysisPanel.setVisible(false);

		titleLabel = new JLabel(language.getAppTitle());
		titleLabel.setForeground(Color.GRAY);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		titleLabel.setBounds(371, 11, 159, 20);
		getContentPane().add(titleLabel);

		contentPane.add(menuPanel);
		contentPane.add(scrapingPanel);
		contentPane.add(settingsPanel);
		contentPane.add(helpPanel);
		contentPane.add(analysisPanel);

	}

	@Override
	public void updateActivePanel(PanelType type) {

		settingsPanel.setVisible(false);
		scrapingPanel.setVisible(false);
		analysisPanel.setVisible(false);
		helpPanel.setVisible(false);

		switch (type) {
		case Settings:
			settingsPanel.setVisible(true);
			break;
		case Scraping:
			scrapingPanel.setVisible(true);
			break;
		case Help:
			helpPanel.setVisible(true);
			break;
		case Analysis:
			analysisPanel.setVisible(true);
			break;
		default:
			break;
		}
	}

	@Override
	public void updateOnLanguageChange() {

		titleLabel.setText(appState.getActiveLanguage().getAppTitle());

	}

}
