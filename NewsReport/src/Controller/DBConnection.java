package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.LanguageType;
import Model.NewsModel;

public class DBConnection {

	private String database = "Sime";
	private String table;
	private String url = "jdbc:sqlserver://SQL-A;databaseName=" + database;
	private String username = "adventure_worker";
	private String password = "Net#Lab!";

	private Connection connection;
	private ApplicationState appState;

	public DBConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.connection = null;

		appState = ApplicationState.instance();
	}

	public ArrayList<NewsModel> getDatesForTopic(String topic) throws SQLException {

		openSQLConnection();

		PreparedStatement statement = connection.prepareStatement(appState.getActiveLanguage().queryGetDatesForTopic());
		statement.setString(1, topic);

		ResultSet rs = statement.executeQuery();
		ArrayList<NewsModel> news = new ArrayList<NewsModel>();

		while (rs.next()) {
			NewsModel model = new NewsModel(topic, rs.getLong("occurrence"));
			model.setDate(rs.getDate("reportDate"));
			news.add(model);

		}
		rs.close();
		statement.close();

		closeSQLConnection();
		return news;

	}

	public ArrayList<NewsModel> getNewsReportFromDate(int year, int month, int day) throws SQLException {

		openSQLConnection();

		PreparedStatement statement = connection.prepareStatement(appState.getActiveLanguage().queryGetTopicsOnDate());

		statement.setInt(1, year);
		statement.setInt(2, month);
		statement.setInt(3, day);
		ResultSet rs = statement.executeQuery();
		ArrayList<NewsModel> news = new ArrayList<NewsModel>();

		while (rs.next()) {
			NewsModel model = new NewsModel(rs.getString("topic"), rs.getLong("occurrence"));
			news.add(model);

		}

		rs.close();
		statement.close();

		closeSQLConnection();
		return news;

	}

	public void insertNewReport(ArrayList<NewsModel> topics, int year, int month, int day) throws SQLException {

		openSQLConnection();

		PreparedStatement statement = connection
				.prepareStatement(appState.getActiveLanguage().queryInsertTopicForDate());

		for (NewsModel topic : topics) {

			insertNewDate(year, month, day);
			insertNewTopic(topic.getTopic());

			statement.setString(1, topic.getTopic());
			statement.setLong(2, topic.getOccurrence());
			statement.setInt(3, year);
			statement.setInt(4, month);
			statement.setInt(5, day);

			statement.executeUpdate();
			statement.close();
		}

		closeSQLConnection();

	}

	private void insertNewDate(int year, int month, int day) throws SQLException {

		openSQLConnection();

		PreparedStatement statement = connection.prepareStatement(appState.getActiveLanguage().queryInsertNewDate());
		statement.setInt(1, year);
		statement.setInt(2, month);
		statement.setInt(3, day);

		statement.executeUpdate();
		statement.close();

		closeSQLConnection();

	}

	private void insertNewTopic(String topic) throws SQLException {

		openSQLConnection();
		PreparedStatement statement = connection.prepareStatement(appState.getActiveLanguage().queryInsertNewTopic());
		statement.setString(1, topic);
		statement.executeUpdate();
		statement.close();

		closeSQLConnection();
	}

	private void openSQLConnection() throws SQLException {
		this.connection = DriverManager.getConnection(url, username, password);
	}

	public void closeSQLConnection() throws SQLException {
		this.connection.close();
	}

}
