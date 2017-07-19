package ast.sap.connector.job;

public class FullJobData extends BaseJobData {
	private final String targetServer;

	public String getTargetServer() {
		return targetServer;
	}

	public FullJobData(String jobName, String jobId, String externalUsername, String targetServer) {
		super(jobName, jobId, externalUsername);
		this.targetServer = targetServer;
	}
}
