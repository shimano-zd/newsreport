package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import Controller.ApplicationState;
import Controller.DBConnection;
import Controller.ExceptionHandler;
import Model.DateLabelFormatter;
import Model.ILanguage;
import Model.NewsModel;

import javax.swing.JTextField;
import javax.swing.JButton;

public class AnalysisPanel extends JPanel implements ILanguageStateObserver {

	private JLabel analysisPanelSubtitle;
	private JLabel analysisPanelTitle;
	private JLabel analysisIcon;

	private ApplicationState appState;
	private DBConnection dbConnection;

	private JLabel searchByTopicLabel;
	private JLabel searchByDateLabel;
	private JTextField topicTextField;

	private JButton searchByTopicButton;
	private JButton searchByDateButton;

	private JDatePickerImpl datePicker;
	private DateLabelFormatter dateFormatter;

	private JLabel loadingLabel;
	private Icon loadingAnimation;

	private ChartPanel chartPanel;
	private JFreeChart chart;
	private DefaultCategoryDataset dataset;

	public AnalysisPanel() {
		setLayout(null);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.lightGray));
		setBackground(Color.WHITE);
		setBounds(20, 49, 639, 423);

		appState = ApplicationState.instance();
		appState.addLanguageObserver(this);
		dateFormatter = new DateLabelFormatter();

		createComponents();
		activate();
	}

	private void createComponents() {
		analysisPanelTitle = new JLabel();
		analysisPanelTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
		analysisPanelTitle.setBounds(70, 11, 138, 20);
		add(analysisPanelTitle);

		analysisPanelSubtitle = new JLabel();
		analysisPanelSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		analysisPanelSubtitle.setBounds(70, 30, 553, 14);
		add(analysisPanelSubtitle);

		analysisIcon = new JLabel("");
		analysisIcon.setIcon(new ImageIcon(getClass().getResource("/images/icons8-increase-40.png")));
		analysisIcon.setBounds(10, 11, 40, 30);
		add(analysisIcon);

		searchByTopicLabel = new JLabel();
		searchByTopicLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		searchByTopicLabel.setBounds(10, 72, 144, 20);
		add(searchByTopicLabel);

		topicTextField = new JTextField();
		topicTextField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		topicTextField.setBounds(157, 72, 237, 23);
		add(topicTextField);
		topicTextField.setColumns(10);

		searchByDateLabel = new JLabel();
		searchByDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		searchByDateLabel.setBounds(10, 103, 144, 20);
		add(searchByDateLabel);

		UtilDateModel dateModel = new UtilDateModel();
		Properties dateProps = new Properties();
		dateProps.put("text.today", "Today");
		dateProps.put("text.month", "Month");
		dateProps.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, dateProps);

		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setSize(237, 23);
		datePicker.setLocation(157, 100);

		add(datePicker);

		searchByTopicButton = new JButton("");
		searchByTopicButton.setIcon(new ImageIcon(AnalysisPanel.class.getResource("/images/icons8-search.png")));
		searchByTopicButton.setBackground(Color.WHITE);
		searchByTopicButton.setBounds(404, 72, 89, 23);
		add(searchByTopicButton);

		searchByDateButton = new JButton("");
		searchByDateButton.setIcon(new ImageIcon(AnalysisPanel.class.getResource("/images/icons8-search.png")));
		searchByDateButton.setBackground(Color.WHITE);
		searchByDateButton.setBounds(404, 103, 89, 23);
		add(searchByDateButton);

		dataset = new DefaultCategoryDataset();
//		dataset.setValue(50.5, "Occurrence", "Clinton");
//		dataset.setValue(60.2, "Occurrence", "Bush");
//		dataset.setValue(48.1, "Occurrence", "Biden");
//		dataset.setValue(40.2, "Occurrence", "Obama");

		chart = ChartFactory.createBarChart("Data Chart", "", "", dataset);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);

		chartPanel = new ChartPanel(chart);
		chartPanel.setSize(613, 265);
		chartPanel.setPreferredSize(new Dimension(300, 200));
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setLocation(10, 147);
		chartPanel.setVisible(true);
		add(chartPanel);

		loadingAnimation = new ImageIcon(getClass().getResource("/images/loading-spinner.gif"));

		loadingLabel = new JLabel(loadingAnimation);
		loadingLabel.setBackground(Color.WHITE);
		loadingLabel.setBounds(250, 200, 150, 150);
		loadingLabel.setVisible(false);
		add(loadingLabel);

		refreshLanguage();
	};

	private void showTopicsOnDateChart(ArrayList<NewsModel> topicsOnDate, String dateString) {

		dataset = new DefaultCategoryDataset();
		for (NewsModel n : topicsOnDate) {
			dataset.setValue(n.getOccurrence(), appState.getActiveLanguage().chartOccurrence(), n.getTopic());
		}

		chart = ChartFactory.createBarChart(appState.getActiveLanguage().topicsOnDateChartTitle() + " " + dateString,
				appState.getActiveLanguage().chartTopics(), appState.getActiveLanguage().chartOccurrence(), dataset);
		chart.getCategoryPlot().setRangeGridlinePaint(Color.black);
		chartPanel.setChart(chart);

	}
	
	private void showDatesForTopicChart(ArrayList<NewsModel> dates, String topic) {
		dataset = new DefaultCategoryDataset();
		for (NewsModel n : dates) {
			try {
				String topicDate = dateFormatter.dateToString(n.getDate());
				dataset.setValue(n.getOccurrence(), appState.getActiveLanguage().chartOccurrence(), topicDate);
			}catch(ParseException pe) {
				ExceptionHandler.handle(pe, appState.getActiveLanguage().sqlError());
				return;
			}
		}

		chart = ChartFactory.createBarChart(appState.getActiveLanguage().datesForTopicChartTitle() + " " + topic,
				appState.getActiveLanguage().chartDates(), appState.getActiveLanguage().chartOccurrence(), dataset);
		
		chart.getCategoryPlot().setRangeGridlinePaint(Color.black);
		chartPanel.setChart(chart);
		
	}

	private void activate() {
		searchByTopicButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GetDatesForTopicAsyncTask getDates = new GetDatesForTopicAsyncTask();
				getDates.start();
						
			}
		});

		searchByDateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GetTopicOnDateAsyncTask getTopics = new GetTopicOnDateAsyncTask();
				getTopics.start();
			}
		});
	};

	@Override
	public void updateOnLanguageChange() {
		refreshLanguage();

	}

	private void refreshLanguage() {
		ILanguage activeLanguage = appState.getActiveLanguage();

		analysisPanelTitle.setText(activeLanguage.analysisTitle());
		analysisPanelSubtitle.setText(activeLanguage.analysisSubtitle());
		searchByDateLabel.setText(activeLanguage.searchByDate());
		searchByTopicLabel.setText(activeLanguage.searchByTopic());
		
		chartPanel.setVisible(false);
	}

	private class GetTopicOnDateAsyncTask extends Thread {
		@Override
		public void run() {

			String dateString = datePicker.getJFormattedTextField().getText();
			if (dateString.isBlank() || dateString.isEmpty()) {
				JOptionPane.showMessageDialog(null, appState.getActiveLanguage().mustChooseDate());
				return;
			}

			try {

				startLoading();

				if (dbConnection == null) {
					dbConnection = new DBConnection();
				}
				Date date = dateFormatter.getDateFromString(dateString);
				LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				ArrayList<NewsModel> topicsOnDate = dbConnection.getNewsReportFromDate(localDate.getYear(),
						localDate.getMonthValue(), localDate.getDayOfMonth());

				showTopicsOnDateChart(topicsOnDate, dateString);

			} catch (Exception ex) {
				ExceptionHandler.handle(ex, appState.getActiveLanguage().sqlError());
			} finally {
				stopLoading();
			}

		}
	}

	private class GetDatesForTopicAsyncTask extends Thread {
		@Override
		public void run() {

			if (topicTextField.getText().isBlank() || topicTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, appState.getActiveLanguage().mustChooseTopic());
				return;

			}

			String topic = topicTextField.getText();

			try {
				if (dbConnection == null) {
					dbConnection = new DBConnection();
				}

				startLoading();
				ArrayList<NewsModel> datesForTopic = dbConnection.getDatesForTopic(topic);

				showDatesForTopicChart(datesForTopic, topic);

			} catch (Exception ex) {
				ExceptionHandler.handle(ex, appState.getActiveLanguage().sqlError());
			} finally {
				stopLoading();
			}

		}
	}

	private void startLoading() {
		searchByDateButton.setEnabled(false);
		searchByTopicButton.setEnabled(false);
		chartPanel.setVisible(false);
		loadingLabel.setVisible(true);

	}

	private void stopLoading() {
		searchByDateButton.setEnabled(true);
		searchByTopicButton.setEnabled(true);
		chartPanel.setVisible(true);
		loadingLabel.setVisible(false);
	}

}
