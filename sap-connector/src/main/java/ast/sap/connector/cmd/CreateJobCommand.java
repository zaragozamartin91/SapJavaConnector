package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.JobCreateData;
import ast.sap.connector.job.create.JobCreator;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.xmi.XmiLoginData;

public class CreateJobCommand extends SapXmiCommand {
	private final JobCreateData jobData;

	public CreateJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, JobCreateData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	protected SapCommandResult perform() {
		JobCreator jobCreator = new JobCreator(repository());
		NewJobData newJobData = jobCreator.createJob(jobData);
		return new SapCommandResult(newJobData);
	}
}