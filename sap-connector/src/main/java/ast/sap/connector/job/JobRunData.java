package ast.sap.connector.job;

public class JobRunData {
	private final String jobName;
	private final String jobId; // jobCount
	private final String externalUsername;
	private final String targetServer;

	public JobRunData(String jobName, String jobId, String externalUsername, String targetServer) {
		this.jobName = jobName;
		this.jobId = jobId;
		this.externalUsername = externalUsername;
		this.targetServer = targetServer;
	}

	public String getJobName() {
		return jobName;
	}

	public String getJobId() {
		return jobId;
	}

	public String getExternalUsername() {
		return externalUsername;
	}

	public String getTargetServer() {
		return targetServer;
	}
}
