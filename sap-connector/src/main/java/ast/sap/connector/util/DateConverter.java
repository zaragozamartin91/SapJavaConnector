package ast.sap.connector.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @deprecated NO SE USA
 * 
 * @author martin.zaragoza
 *
 */
public enum DateConverter {
	INSTANCE;

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");

	public static class DayHourPair {
		public final String day;
		public final String hour;

		public DayHourPair(String day, String hour) {
			this.day = day;
			this.hour = hour;
		}
	}

	public DayHourPair convert(Date date) {
		String dateString = FORMATTER.format(date);

		String dt = dateString.substring(0, 8);
		String tm = dateString.substring(8);

		return new DayHourPair(dt, tm);
	}
}
