package ast.sap.connector.job;

public class JobData implements RunnerJobData {
	private String jobName;
	private String jobId; // jobCount / jobNumber
	private String externalUsername;
	private String targetServer;

	@Override
	public String getJobName() {
		return jobName;
	}

	@Override
	public String getJobId() {
		return jobId;
	}

	@Override
	public String getExternalUsername() {
		return externalUsername;
	}

	@Override
	public String getTargetServer() {
		return targetServer;
	}

	public TrackerJobData setJobName(String jobName) {
		this.jobName = jobName;
		return this;
	}

	public TrackerJobData setJobId(String jobId) {
		this.jobId = jobId;
		return this;
	}

	public TrackerJobData setExternalUsername(String externalUsername) {
		this.externalUsername = externalUsername;
		return this;
	}

	public TrackerJobData setTargetServer(String targetServer) {
		this.targetServer = targetServer;
		return this;
	}
}
