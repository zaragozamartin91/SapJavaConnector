package ast.sap.connector.job;

public interface TrackerJobData {

	String getJobName();

	String getJobId();

	/**
	 * XMI logging: Name of user in external management tool.
	 * 
	 * @return Name of user in external management tool
	 */
	String getExternalUsername();

}