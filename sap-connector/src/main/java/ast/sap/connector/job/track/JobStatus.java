package ast.sap.connector.job.track;

import ast.sap.connector.func.SapBapiret2;

/**
 * Estado del job
 */
public class JobStatus {
	private final StatusCode statusCode;
	private final SapBapiret2 returnStruct;

	public JobStatus(String status, SapBapiret2 sapBapiret2) {
		this.statusCode = StatusCode.valueOf(status);
		this.returnStruct = sapBapiret2;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public SapBapiret2 getReturnStruct() {
		return returnStruct;
	}
}
