package ast.sap.connector.job;

import ast.sap.connector.dst.SapRepository;

public class JobTracker {
	private final SapRepository sapRepository;

	public JobTracker(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}

	// TODO
	public JobStatus getStatus(TrackerJobData jobData) {
		return new JobStatus(null, null);
	}
}
