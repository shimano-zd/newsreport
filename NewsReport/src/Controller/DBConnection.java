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

/**
 * Class used to connect to the database and perform CRUD operations.
 * @author Sime
 *
 */
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

	/**
	 * Gets dates on which a specific topic appeared in the news portals and their occurrence.
	 * @param topic A string representing the topic of the news, e.g. "police" or "flood"
	 * @return Returns a list of NewsModel with specific dates and occurrence of the topic.
	 * @throws SQLException
	 */
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

	/**
	 * Gets top five news that appeared in news portals on a specific date.
	 * @param year Year (int) of the date that we're searching on.
	 * @param month Month (int) of the date that we're searching on.
	 * @param day Day (int) of the date that we're searching on.
	 * @return Returns a list of NewsModel. The select procedure return the top 5 results by default.
	 * @throws SQLException
	 */
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

	/**
	 * Inserts new reports into the database.
	 * @param topics A list of topics that appeared on a specific date.
	 * @param year
	 * @param month
	 * @param day
	 * @throws SQLException
	 */
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

	/**
	 * Inserts the new date in the database.
	 * @param year The date's year (int).
	 * @param month The date's month (int).
	 * @param day The date's day (int).
	 * @throws SQLException
	 */
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

	/**
	 * Inserts the new topic into the database.
	 * @param topic String representation of the topic.
	 * @throws SQLException
	 */
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
