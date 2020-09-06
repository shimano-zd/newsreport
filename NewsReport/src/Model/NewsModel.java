package Model;

import java.util.Date;

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
