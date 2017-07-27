package ast.sap.connector.job;

/**
 * Datos de un job a crear.
 * 
 * @author martin.zaragoza
 *
 */
public class CreateJobData {
	private final String jobName;
	private final String externalUsername;

	public CreateJobData(String jobName, String externalUsername) {
		this.jobName = jobName;
		this.externalUsername = externalUsername;
	}

	/**
	 * Nombre del job. "Background job name".
	 * 
	 * @see https://www.sapdatasheet.org/abap/tabl/bapixmjob-jobname.html
	 * 
	 * @return Nombre del job.
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * Parametro EXTUSER. "XMI logging: Name of user in external management tool"
	 * 
	 * @see https://www.sapdatasheet.org/abap/tabl/bapixmlogr-extuser.html
	 * 
	 * @return "Name of user in external management tool".
	 */
	public String getExternalUsername() {
		return externalUsername;
	}

}