package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "dd.MM.yyyy.";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

	public Date getDateFromString(String date) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat(datePattern);

		return format.parse(date);
	}

	public String dateToString(Date date) throws ParseException {
		if (date != null) {
			return dateFormatter.format(date);

		}
		return "";
	}

}
