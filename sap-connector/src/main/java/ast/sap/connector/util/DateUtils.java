package ast.sap.connector.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Herramientas para manipulacion de fechas.
 * 
 * @author martin.zaragoza
 *
 */
public enum DateUtils {
	INSTANCE;

	/**
	 * Suma dos fechas.
	 * 
	 * @param date1
	 *            - Primera fecha.
	 * @param date2
	 *            - Segunda fecha.
	 * @return Fechas sumadas.
	 */
	public Date addDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		cal1.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal1.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
		cal1.set(Calendar.SECOND, cal2.get(Calendar.SECOND));

		return cal1.getTime();
	}
}
