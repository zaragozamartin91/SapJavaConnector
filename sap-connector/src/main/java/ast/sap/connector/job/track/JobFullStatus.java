package ast.sap.connector.job.track;

import ast.sap.connector.job.log.JobLog;

public class JobFullStatus {
	private JobLog jobLog;
	private JobStatus jobStatus;

	public JobFullStatus(JobLog jobLog, JobStatus jobStatus) {
		this.jobLog = jobLog;
		this.jobStatus = jobStatus;
	}

	public JobLog getJobLog() {
		return jobLog;
	}

	public JobStatus getJobStatus() {
		return jobStatus;
	}
}
