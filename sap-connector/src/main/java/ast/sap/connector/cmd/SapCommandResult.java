package ast.sap.connector.cmd;

import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.job.def.JobDefinition;
import ast.sap.connector.job.log.JobLog;
import ast.sap.connector.job.track.JobStatus;

import com.google.common.base.Optional;

/**
 * Representa el resultado de la ejecucion de un comando.
 * 
 * TODO : algunos de los campos que posee son para la ejecucion de comandos debug. Se debe determinar que campos
 * se quedan y cuales se van.
 * 
 * @author mzaragoz
 *
 */
public class SapCommandResult {
	private static final SapCommandResult EMPTY_RESULT = new SapCommandResult();

	private Optional<SapBapiret2> ret = Optional.absent();
	private Optional<JobStatus> jobStatus = Optional.absent();
	private Optional<JobLog> jobLog = Optional.absent();
	private Optional<NewJobData> newJobData = Optional.absent();
	private Optional<String> message = Optional.absent();
	private Optional<JobDefinition> jobDefinition = Optional.absent();

	private Optional<Object> extraResult = Optional.absent();

	public Optional<SapBapiret2> getRet() {
		return ret;
	}

	public Optional<JobStatus> getJobStatus() {
		return jobStatus;
	}

	public Optional<JobLog> getJobLog() {
		return jobLog;
	}

	public Optional<NewJobData> getNewJobData() {
		return newJobData;
	}

	public Optional<String> getMessage() {
		return message;
	}	

	public Optional<JobDefinition> getJobDefinition() {
		return jobDefinition;
	}

	public SapCommandResult(SapBapiret2 ret) {
		this.ret = Optional.fromNullable(ret);
	}

	public SapCommandResult(JobStatus jobStatus) {
		this.jobStatus = Optional.fromNullable(jobStatus);
	}

	public SapCommandResult(JobLog jobLog) {
		this.jobLog = Optional.fromNullable(jobLog);
	}

	public SapCommandResult(NewJobData newJobData) {
		this.newJobData = Optional.fromNullable(newJobData);
	}

	public SapCommandResult(String message) {
		this.message = Optional.fromNullable(message);
	}

	public SapCommandResult(Object extraResult) {
		this.extraResult = Optional.fromNullable(extraResult);
	}
	
	public SapCommandResult(JobDefinition jobDefinition) {
		this.jobDefinition = Optional.fromNullable(jobDefinition);
	}

	public Optional<Object> getExtraResult() {
		return extraResult;
	}

	private SapCommandResult() {
	}

	public static SapCommandResult emptyResult() {
		return EMPTY_RESULT;
	}

	public boolean isEmpty() {
		return this.equals(EMPTY_RESULT);
	}

	@Override
	public String toString() {
		return "SapCommandResult [\n ret=" + ret
				+ ",\n jobStatus=" + jobStatus
				+ ",\n jobLog=" + jobLog
				+ ",\n newJobData=" + newJobData
				+ ",\n message=" + message
				+ ",\n extraResult= " + extraResult
				+ ",\n jobDefinition= " + jobDefinition
				+ "\n]";
	}
}
