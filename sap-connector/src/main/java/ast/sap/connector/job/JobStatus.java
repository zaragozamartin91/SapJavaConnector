package ast.sap.connector.job;

import ast.sap.connector.func.SapStruct;

public class JobStatus {
	private final String status;
	private final SapStruct returnStruct;

	public JobStatus(String status, SapStruct returnStruct) {
		this.status = status;
		this.returnStruct = returnStruct;
	}

	public String getStatus() {
		return status;
	}

	public SapStruct getReturnStruct() {
		return returnStruct;
	}
}
