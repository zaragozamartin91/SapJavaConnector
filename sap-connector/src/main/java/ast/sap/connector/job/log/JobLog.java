package ast.sap.connector.job.log;

import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.SapStruct;

public class JobLog {
	private final SapStruct returnStruct;
	private final OutTableParam logEntries;

	public JobLog(SapStruct returnStruct, OutTableParam logEntries) {
		this.returnStruct = returnStruct;
		this.logEntries = logEntries;
	}

	public SapStruct getReturnStruct() {
		return returnStruct;
	}

	public OutTableParam getLogEntries() {
		return logEntries;
	}
}
