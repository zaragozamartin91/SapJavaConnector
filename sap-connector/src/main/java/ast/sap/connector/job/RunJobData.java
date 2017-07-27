package ast.sap.connector.job;

/**
 * Datos de un job a correr.
 * 
 * @author martin.zaragoza
 *
 */
public class RunJobData extends TrackJobData {
	private final String targetServer;

	/**
	 * Parámetro correspondiente a EXECSERVER.
	 * 
	 * @see https://www.sapdatasheet.org/abap/tabl/bapixmjob-execserver.html
	 * 
	 * @return Parametro EXECSERVER.
	 */
	public String getTargetServer() {
		return targetServer;
	}

	public RunJobData(String jobName, String jobId, String externalUsername, String targetServer) {
		super(jobName, jobId, externalUsername);
		this.targetServer = targetServer;
	}
}
