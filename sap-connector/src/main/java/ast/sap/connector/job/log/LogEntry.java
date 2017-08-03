package ast.sap.connector.job.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import ast.sap.connector.func.OutTableParam;

public class LogEntry {
	private static final SimpleDateFormat OUT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat OUT_TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

	private Date time;
	private Date date;
	private String text;

	private LogEntry() {
	};

	public static LogEntry fromTableParam(OutTableParam entry) {
		LogEntry logEntry = new LogEntry();

		logEntry.text = entry.getValue("TEXT").toString();

		logEntry.date = (Date) entry.getValue("ENTERDATE");
		logEntry.time = (Date) entry.getValue("ENTERTIME");

		return logEntry;
	}

	public Date getTime() {
		return time;
	}

	public Date getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return getPrettyString();
	}

	public String getPrettyString() {
		StringBuilder out = new StringBuilder();
		out.append(OUT_DATE_FORMAT.format(date));
		out.append(" - ");
		out.append(OUT_TIME_FORMAT.format(time));
		out.append(" - ");
		out.append(text);
		return out.toString();
	}
}
