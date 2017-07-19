package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.job.FullJobData;
import ast.sap.connector.job.run.AsapJobRunner;
import ast.sap.connector.job.run.JobRunner;
import ast.sap.connector.xmi.XmiLoginData;

public class RunJobCommand extends SapCommand {
	private FullJobData jobData;

	public RunJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, FullJobData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	public Object perform(SapRepository sapRepository) {
		JobRunner jobRunner = new AsapJobRunner(sapRepository);
		SapStruct ret = jobRunner.runJob(jobData);
		return ret.getValue("MESSAGE").toString();
	}
}
