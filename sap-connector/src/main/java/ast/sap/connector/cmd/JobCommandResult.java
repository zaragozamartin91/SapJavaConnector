package ast.sap.connector.cmd;

import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.log.JobLog;
import ast.sap.connector.job.track.JobStatus;
import com.google.common.base.Optional;

public class JobCommandResult {
    private static final JobCommandResult EMPTY_RESULT = new JobCommandResult();

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

    public JobCommandResult(SapBapiret2 ret) {
        this.ret = Optional.fromNullable(ret);
    }

    public JobCommandResult(JobStatus jobStatus) {
        this.jobStatus = Optional.fromNullable(jobStatus);
    }

    public JobCommandResult(JobLog jobLog) {
        this.jobLog = Optional.fromNullable(jobLog);
    }

    private JobCommandResult() {
    }

    public static JobCommandResult emptyResult() {
        return EMPTY_RESULT;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY_RESULT);
    }
}
