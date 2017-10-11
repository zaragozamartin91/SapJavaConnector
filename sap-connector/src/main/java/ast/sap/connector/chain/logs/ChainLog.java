package ast.sap.connector.chain.logs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.OutTableRow;

public class ChainLog {

	private List<ChainLogEntry> chainLogEntries = new ArrayList<ChainLogEntry>();
	
	public ChainLog(OutTableParam entries) {
		parseEntries(entries);
	}

	private void parseEntries(OutTableParam entries) {
		int rowCount = entries.getRowCount();
		for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
			try {
				OutTableRow currentRow = entries.currentRow();
				ChainLogEntry chainLogEntry = new ChainLogEntry(currentRow);
				chainLogEntries.add(chainLogEntry);
			} catch (Exception e) {
				throw new ChainLogParseException("Ocurrio un error al parsear el log de la cadena", e);
			}
			entries.nextRow();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ChainLog [chainLogEntries=");
		for (ChainLogEntry chainLogEntry : chainLogEntries) {
			sb.append("\n  " + chainLogEntry.getPrettyString());
		}
		sb.append("\n]");
		return sb.toString();
	}

	/**
	 * Obtiene las entradas de log parseadas.
	 * 
	 * @return entradas de log parseadas
	 */
	public List<ChainLogEntry> getChainLogEntries() {
		return Collections.unmodifiableList(this.chainLogEntries);
	}

	/**
	 * Imprime el log en la terminal.
	 */
	public void printLogStdout() {
		for (ChainLogEntry chainLogEntry : chainLogEntries) {
			System.out.println(chainLogEntry);
		}
	}

}
