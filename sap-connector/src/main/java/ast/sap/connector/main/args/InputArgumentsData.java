package ast.sap.connector.main.args;

import com.google.common.base.Optional;

import ast.sap.connector.job.CreateJobData;
import ast.sap.connector.job.RunJobData;
import ast.sap.connector.job.TrackJobData;
import ast.sap.connector.xmi.XmiLoginData;

public class InputArgumentsData {
	/* PARAMETROS DE INICIO DE SESION CON SAP */
	private String user;
	private String password;
	private String host;
	private int timeoutSecs;
	private String clientNumber;
	private String systemNumber;

	/* PARAMETROS DE EJECUCION DE JOBS */
	private String jobName;
	private String jobId;
	private String command;

	private String language = "EN";

	private String execServer;

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public int getTimeoutSecs() {
		return timeoutSecs;
	}

	public String getJobName() {
		return jobName;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setTimeoutSecs(int timeoutSecs) {
		this.timeoutSecs = timeoutSecs;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getExecServer() {
		return execServer;
	}

	public void setExecServer(String execServer) {
		this.execServer = execServer;
	}

	@Override
	public String toString() {
		return "InputArgumentsData [user=" + user + ", password=" + password + ", host=" + host + ", timeoutSecs=" + timeoutSecs + ", clientNumber="
				+ clientNumber + ", systemNumber=" + systemNumber + ", jobName=" + jobName + ", jobId=" + jobId + ", command=" + command + ", language="
				+ language + ", execServer=" + execServer + "]";
	}

	public CreateJobData newBaseJobData() {
		return new TrackJobData(jobName, jobId, user);
	}

	/* TODO : VERIFICAR SI EL PARAMETRO EXECSERVER ES IGUAL AL HOST O SI DEBE INGRESARSE UN NOMBRE DE SERVER PROPIO DE SAP */
	public RunJobData newFullJobData() {
		String exSrv = Optional.fromNullable(execServer).or(host);
		return new RunJobData(jobName, jobId, user, exSrv);
	}

	public XmiLoginData newXmiLoginData() {
		return new XmiLoginData("AST", "CONNECTOR_SAP");
	}

	public boolean isHelp() {
		return Optional.fromNullable(command).or("HELP").equalsIgnoreCase("HELP");
	}
}
