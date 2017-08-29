package ast.sap.connector.cmd.impl;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.run.JobRunner;
import ast.sap.connector.job.run.SmartJobRunner;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando de disparo de jobs (sin monitoreo).
 * 
 * @author martin.zaragoza
 *
 */
public class RunJobCommand extends SapXmiCommand {
	private JobRunData jobData;

	public RunJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, JobRunData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	public SapCommandResult perform() {
		SapRepository sapRepository = repository();
		JobRunner jobRunner = new SmartJobRunner(sapRepository);
//		JobRunner jobRunner = new ImmediateJobRunner(sapRepository);
		SapBapiret2 ret = jobRunner.runJob(jobData);
		return new SapCommandResult(ret);
	}
}
