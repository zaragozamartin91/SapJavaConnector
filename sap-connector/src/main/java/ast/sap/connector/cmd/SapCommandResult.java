package ast.sap.connector.cmd;

import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.log.JobLog;
import ast.sap.connector.job.track.JobStatus;
import com.google.common.base.Optional;

public class SapCommandResult {
    private static final SapCommandResult EMPTY_RESULT = new SapCommandResult();

    private Optional<SapBapiret2> ret = Optional.absent();
    private Optional<JobStatus> jobStatus = Optional.absent();
    private Optional<JobLog> jobLog = Optional.absent();

    public Optional<SapBapiret2> getRet() {
        return ret;
    }

    public Optional<JobStatus> getJobStatus() {
        return jobStatus;
    }

    public Optional<JobLog> getJobLog() {
        return jobLog;
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

    private SapCommandResult() {
    }

    public static SapCommandResult emptyResult() {
        return EMPTY_RESULT;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY_RESULT);
    }
}
