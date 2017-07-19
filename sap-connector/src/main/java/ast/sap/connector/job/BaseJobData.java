package ast.sap.connector.job;

public class BaseJobData {
	private final String jobName;
	private final String jobId;
	private final String externalUsername;

	public String getJobName() {
		return jobName;
	}

	public String getJobId() {
		return jobId;
	}

	public String getExternalUsername() {
		return externalUsername;
	}

	/**
	 * Construye una instancia de datos de job.
	 * 
	 * @param jobName - Nombre del job.
	 * @param jobId - Id del job. Tambien llamado job count o job number.
	 * @param externalUsername - "Name of user in external management tool" (ver SAP XMI).
	 */
	public BaseJobData(String jobName, String jobId, String externalUsername) {
		this.jobName = jobName;
		this.jobId = jobId;
		this.externalUsername = externalUsername;
	}
}