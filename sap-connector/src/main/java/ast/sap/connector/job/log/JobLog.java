package ast.sap.connector.job.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.OutTableRow;
import ast.sap.connector.func.SapBapiret2;

public class JobLog {
	private final SapBapiret2 returnStruct;
	private List<LogEntry> logEntries = new ArrayList<LogEntry>();

	public JobLog(SapBapiret2 sapBapiret2, OutTableParam entries) {
		this.returnStruct = sapBapiret2;
		parseEntries(entries);
	}

	private void parseEntries(OutTableParam entries) {
		for (int rowIndex = 0; rowIndex < entries.getRowCount(); rowIndex++) {
			try {
				OutTableRow currentRow = entries.currentRow();
				LogEntry logEntry = new LogEntry(currentRow);
				logEntries.add(logEntry);
			} catch (Exception e) {
				throw new JobLogParseException("Ocurrio un error al parsear el log del job", e);
			}
			entries.nextRow();
		}
	}

	public SapBapiret2 getReturnStruct() {
		return returnStruct;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("JobLog [returnStruct=" + returnStruct + ", logEntries=");
		for (LogEntry logEntry : logEntries) {
			sb.append("\n  " + logEntry.getPrettyString());
		}
		sb.append("\n]");
		return sb.toString();
	}

	/**
	 * Obtiene las entradas de log parseadas.
	 * 
	 * @return entradas de log parseadas
	 */
	public List<LogEntry> getLogEntries() {
		return Collections.unmodifiableList(this.logEntries);
	}
}
