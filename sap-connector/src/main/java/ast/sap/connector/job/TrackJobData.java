package ast.sap.connector.job;

/**
 * Datos de un job a monitorear.
 * 
 * @author martin.zaragoza
 *
 */
public class TrackJobData extends CreateJobData {
	private final String jobId;

	/**
	 * Parametro JOBCOUNT. "Job ID"
	 * 
	 * @see https://www.sapdatasheet.org/abap/tabl/bapixmjob-jobcount.html
	 * 
	 * @return Job ID.
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * Construye una instancia de datos de job.
	 * 
	 * @param jobName
	 *            - Nombre del job.
	 * @param jobId
	 *            - Id del job. Tambien llamado job count o job number.
	 * @param externalUsername
	 *            - "Name of user in external management tool" (ver SAP XMI).
	 */
	public TrackJobData(String jobName, String externalUsername, String jobId) {
		super(jobName, externalUsername);
		this.jobId = jobId;
	}
}