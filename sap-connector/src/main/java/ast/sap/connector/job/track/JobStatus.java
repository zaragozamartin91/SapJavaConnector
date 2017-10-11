package ast.sap.connector.job.track;

import ast.sap.connector.func.SapBapiret2;

/**
 * Estado del job
 */
public class JobStatus {
	private final JobStatusCode statusCode;
	private final SapBapiret2 returnStruct;

	public JobStatus(String status, SapBapiret2 sapBapiret2) {
		this.statusCode = JobStatusCode.valueOf(status);
		this.returnStruct = sapBapiret2;
	}

	public JobStatusCode getStatusCode() {
		return statusCode;
	}

	public SapBapiret2 getReturnStruct() {
		return returnStruct;
	}

	@Override
	public String toString() {
		return "JobStatus [statusCode=" + statusCode + ", returnStruct=" + returnStruct + "]";
	}
}
