package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.FullJobData;
import ast.sap.connector.job.run.AsapJobRunner;
import ast.sap.connector.job.run.JobRunner;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando de disparo de jobs (sin monitoreo).
 * 
 * @author martin.zaragoza
 *
 */
public class RunJobCommand extends SapXmiCommand {
	private FullJobData jobData;

	public RunJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, FullJobData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	public JobCommandResult perform() {
		SapRepository sapRepository = repository();
		JobRunner jobRunner = new AsapJobRunner(sapRepository);
		SapBapiret2 ret = jobRunner.runJob(jobData);
		return new JobCommandResult(ret);
	}
}
