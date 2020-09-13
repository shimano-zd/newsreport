package Model;

import java.util.Date;
/**
 * A model class representing a news report.
 * The class contains the topic of the news, the date on which it was retrieved and the occurrence of the topic.
 * @author Sime
 *
 */
public class NewsModel {

	private String topic;
	private long occurrence;
	private Date date;

	public NewsModel(String topic, long occurrence) {
		this.topic = topic;
		this.occurrence = occurrence;
	}

	public String getTopic() {
		return this.topic;
	}

	public long getOccurrence() {
		return this.occurrence;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}
}
