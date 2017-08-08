package ast.sap.connector.main.args;

import ast.sap.connector.job.JobData;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.xmi.XmiLoginData;

import com.google.common.base.Optional;

public final class InputArgumentsData {
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

	private String eventId;

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

	public InputArgumentsData setUser(String user) {
		this.user = user;
		return this;
	}

	public InputArgumentsData setPassword(String password) {
		this.password = password;
		return this;
	}

	public InputArgumentsData setHost(String host) {
		this.host = host;
		return this;
	}

	public InputArgumentsData setTimeoutSecs(int timeoutSecs) {
		this.timeoutSecs = timeoutSecs;
		return this;
	}

	public InputArgumentsData setJobName(String jobName) {
		this.jobName = jobName;
		return this;
	}

	public InputArgumentsData setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
		return this;
	}

	public InputArgumentsData setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
		return this;
	}

	public String getCommand() {
		return command;
	}

	public InputArgumentsData setCommand(String command) {
		this.command = command;
		return this;
	}

	public String getJobId() {
		return jobId;
	}

	public InputArgumentsData setJobId(String jobId) {
		this.jobId = jobId;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public InputArgumentsData setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getExecServer() {
		return execServer;
	}

	public InputArgumentsData setExecServer(String execServer) {
		this.execServer = execServer;
		return this;
	}

	public String getEventId() {
		return eventId;
	}

	public InputArgumentsData setEventId(String eventId) {
		this.eventId = eventId;
		return this;
	}

	@Override
	public String toString() {
		return "InputArgumentsData [user=" + user + ", password=" + password + ", host=" + host + ", timeoutSecs="
				+ timeoutSecs + ", clientNumber=" + clientNumber + ", systemNumber=" + systemNumber + ", jobName="
				+ jobName + ", jobId=" + jobId + ", command=" + command + ", language=" + language + ", execServer="
				+ execServer + ", eventId=" + eventId + "]";
	}

	/*
	 * TODO : VERIFICAR SI EL PARAMETRO EXECSERVER ES IGUAL AL HOST O SI DEBE
	 * INGRESARSE UN NOMBRE DE SERVER PROPIO DE SAP
	 */
	public JobRunData newJobRunData() {
		Optional<String> exSrv = Optional.fromNullable(execServer);
		return exSrv.isPresent() ? JobData.newJobRunData(jobName, user, jobId, exSrv.get()) : JobData.newJobTrackData(
				jobName, user, jobId);
	}

	public XmiLoginData newXmiLoginData() {
		return new XmiLoginData("AST", "SAP_CONNECTOR");
	}

	/**
	 * Verifica si el comando a ejecutar es de tipo HELP.
	 * 
	 * @return True si el comando solicitado a invocar es HELP.
	 */
	public boolean isHelp() {
		return Optional.fromNullable(command).or("HELP").equalsIgnoreCase("HELP");
	}
}
