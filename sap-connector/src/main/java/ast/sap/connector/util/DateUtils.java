package ast.sap.connector.util;

import java.util.Calendar;
import java.util.Date;

public enum DateUtils {
	INSTANCE;
	
	public Date addDates(Date date, Date time) {
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		Calendar calTime = Calendar.getInstance();
		calTime.setTime(time);

		calDate.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
		calDate.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
		calDate.set(Calendar.SECOND, calTime.get(Calendar.SECOND));
		
		return calDate.getTime();
	}
}
