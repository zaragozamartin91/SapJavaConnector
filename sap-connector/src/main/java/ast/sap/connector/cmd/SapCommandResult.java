package ast.sap.connector.cmd;

import java.util.List;

import com.google.common.base.Optional;

import ast.sap.connector.chain.ChainData;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.job.def.BapiXmJob;
import ast.sap.connector.job.def.JobDefinition;
import ast.sap.connector.job.log.JobLog;
import ast.sap.connector.job.log.LogEntry;
import ast.sap.connector.job.read.Bp20job;
import ast.sap.connector.job.read.JobHead;
import ast.sap.connector.job.track.JobFullStatus;
import ast.sap.connector.job.track.JobStatus;
import ast.sap.connector.job.track.StatusCode;

/**
 * Representa el resultado de la ejecucion de un comando.
 * 
 * TODO : algunos de los campos que posee son para la ejecucion de comandos debug. Se debe determinar que campos se quedan y cuales se van.
 * 
 * @author mzaragoz
 *
 */
public class SapCommandResult {
	private static final SapCommandResult EMPTY_RESULT = new SapCommandResult();

	private Optional<SapBapiret2> ret = Optional.absent();
	private Optional<StatusCode> statusCode = Optional.absent();
	private Optional<List<LogEntry>> logEntries = Optional.absent();
	private Optional<String> jobCount = Optional.absent();
	private Optional<String> message = Optional.absent();
	private Optional<BapiXmJob> bapiXmJob = Optional.absent();
	private Optional<Bp20job> bp20job = Optional.absent();
	private Optional<String> chainLogId = Optional.absent();

	public Optional<SapBapiret2> getRet() {
		return ret;
	}

	public Optional<StatusCode> getStatusCode() {
		return statusCode;
	}

	public Optional<String> getJobCount() {
		return jobCount;
	}

	public Optional<String> getMessage() {
		return message;
	}

	public Optional<BapiXmJob> getBapiXmJob() {
		return bapiXmJob;
	}

	public Optional<Bp20job> getBp20job() {
		return bp20job;
	}

	public Optional<List<LogEntry>> getLogEntries() {
		return logEntries;
	}
	
	public Optional<String> getChainLogId() {
		return chainLogId;
	}
	
	/* FIN DE GETTERS ------------------------------------------------------------------------------ */


	public SapCommandResult(SapBapiret2 ret) {
		this.ret = Optional.fromNullable(ret);
	}

	public SapCommandResult(JobStatus jobStatus) {
		this(jobStatus.getReturnStruct());
		this.statusCode = Optional.fromNullable(jobStatus.getStatusCode());
	}

	public SapCommandResult(NewJobData newJobData) {
		this(newJobData.getRet());
		this.jobCount = Optional.fromNullable(newJobData.getJobCount());
	}

	public SapCommandResult(String message) {
		this.message = Optional.fromNullable(message);
	}

	public SapCommandResult(JobDefinition jobDefinition) {
		this(jobDefinition.getSapBapiret2());
		this.bapiXmJob = Optional.fromNullable(jobDefinition.getBapiXmJob());
	}

	public SapCommandResult(JobHead jobHead) {
		this(jobHead.getRet());
		this.bp20job = Optional.fromNullable(jobHead.getBp20job());
	}

	public SapCommandResult(SapBapiret2 ret, List<LogEntry> logEntries) {
		this(ret);
		this.logEntries = Optional.fromNullable(logEntries);
	}

	public SapCommandResult(JobLog jobLog) {
		this(jobLog.getReturnStruct(), jobLog.getLogEntries());
	}

	private SapCommandResult() {
	}

	public SapCommandResult(ChainData chainData) {
		this.chainLogId = Optional.fromNullable(chainData.getLogId());
	}

	public SapCommandResult(JobFullStatus jobFullStatus) {
		this(jobFullStatus.getJobLog());
		this.statusCode = Optional.fromNullable(jobFullStatus.getJobStatus().getStatusCode());
	}

	public static SapCommandResult emptyResult() {
		return EMPTY_RESULT;
	}

	/* FIN DE CONSTRUCTORES --------------------------------------------------------------------------- */

	public boolean isEmpty() {
		return this.equals(EMPTY_RESULT);
	}

	@Override
	public String toString() {
		return "SapCommandResult ["
				+ "\n ret=" + ret
				+ ",\n statusCode=" + statusCode
				+ ",\n logEntries=" + logEntries
				+ ",\n jobCount=" + jobCount
				+ ",\n message=" + message
				+ ",\n bapiXmJob= " + bapiXmJob
				+ ",\n bp20job= " + bp20job
				+ ",\n logId= " + chainLogId
				+ "\n]";
	}
}
