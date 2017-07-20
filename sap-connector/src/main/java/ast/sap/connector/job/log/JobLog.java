package ast.sap.connector.job.log;

import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.SapBapiret2;

public class JobLog {
	private final SapBapiret2 returnStruct;
	private final OutTableParam logEntries;

	public JobLog(SapBapiret2 sapBapiret2, OutTableParam logEntries) {
		this.returnStruct = sapBapiret2;
		this.logEntries = logEntries;
	}

	public SapBapiret2 getReturnStruct() {
		return returnStruct;
	}

	public OutTableParam getLogEntries() {
		return logEntries;
	}
}
