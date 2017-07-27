package ast.sap.connector.main.args;

import ast.sap.connector.job.BaseJobData;
import ast.sap.connector.job.FullJobData;
import ast.sap.connector.main.CmdExeMode;

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
	private CmdExeMode mode = CmdExeMode.MONITOR;

	/* COMANDO DE PRUEBA */
	private String testCommand;

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

	public CmdExeMode getMode() {
		return mode;
	}

	public void setMode(CmdExeMode mode) {
		this.mode = mode;
	}

	public String getTestCommand() {
		return testCommand;
	}

	public void setTestCommand(String testCommand) {
		this.testCommand = testCommand;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public BaseJobData newBaseJobData() {
		return new BaseJobData(jobName, jobId, user);
	}
	
	/* TODO : VERIFICAR SI EL PARAMETRO EXECSERVER ES IGUAL AL HOST O SI DEBE INGRESARSE UN NOMBRE DE SERVER PROPIO DE SAP  */
	public FullJobData newFullJobData() {
		return new FullJobData(jobName, jobId, user, host);
	}
}
