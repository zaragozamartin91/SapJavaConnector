package ast.sap.connector.job.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import ast.sap.connector.func.OutTableRow;
import ast.sap.connector.util.DateUtils;

/**
 * Entrada de log.
 * 
 * @author mzaragoz
 *
 */
public class LogEntry {
	private static final SimpleDateFormat OUT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

	private final Date date;
	private final String text;
	private final String msgId;
	private final String msgNo;
	private final String msgType;

	/**
	 * Construye y parsea una entrada de log a partir de una fila de tabla de salida de una funcion de SAP.
	 * 
	 * @param entry
	 *            - TABLA de salida de funcion de SAP.
	 */
	public LogEntry(OutTableRow entry) {
		text = entry.getValue("TEXT").toString();

		Date date = (Date) entry.getValue("ENTERDATE");
		Date time = (Date) entry.getValue("ENTERTIME");
		this.date = DateUtils.INSTANCE.addHours(date, time);

		this.msgId = (String) entry.getValue("MSGID");
		this.msgNo = (String) entry.getValue("MSGNO");
		this.msgType = (String) entry.getValue("MSGTYPE");
	};

	public Date getDate() {
		return new Date(date.getTime());
	}

	public String getText() {
		return text;
	}

	public String getMsgId() {
		return msgId;
	}

	public String getMsgNo() {
		return msgNo;
	}

	public String getMsgType() {
		return msgType;
	}

	@Override
	public String toString() {
		return getPrettyString();
	}

	/**
	 * Obtiene una entrada de log como un String formateado para facilitar su lectura.
	 * 
	 * @return entrada de log como String formateado.
	 */
	public String getPrettyString() {
		StringBuilder out = new StringBuilder();
		out.append(OUT_DATE_FORMAT.format(date));
		out.append(" - ");
		out.append(text);
		out.append(" - ");
		out.append(msgId);
		out.append(" - ");
		out.append(msgNo);
		out.append(" - ");
		out.append(msgType);
		return out.toString();
	}
}
