package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * A utility class used to format dates.
 * @author Sime
 *
 */
public class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "dd.MM.yyyy.";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	/**
	 * Parses a string into the corresponding date format.
	 */
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	/**
	 * Parses a date into the corresponding string format by using Calendar.
	 */
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

	/**
	 * Simple parser for getting a date from a string.
	 * @param date String representation of a date.
	 * @return Return's JavaUtil's Date.
	 * @throws ParseException
	 */
	public Date getDateFromString(String date) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat(datePattern);

		return format.parse(date);
	}

	/**
	 * Simple parser for getting a string representation of a date.
	 * @param date JavaUtil's Date object.
	 * @return Returns a string of a given format.
	 * @throws ParseException
	 */
	public String dateToString(Date date) throws ParseException {
		if (date != null) {
			return dateFormatter.format(date);

		}
		return "";
	}

}
