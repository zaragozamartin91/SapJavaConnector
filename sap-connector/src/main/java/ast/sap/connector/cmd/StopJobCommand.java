package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.BaseJobData;
import ast.sap.connector.job.run.JobStopper;
import ast.sap.connector.xmi.XmiLoginData;

public class StopJobCommand extends SapCommand {
	private final BaseJobData jobData;

	public StopJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, BaseJobData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	protected Object perform() {
		SapRepository sapRepository = repository();
		JobStopper jobStopper = new JobStopper(sapRepository);
		SapBapiret2 ret = jobStopper.stopJob(jobData);
		return ret;
	}
}
