package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.BaseJobData;
import ast.sap.connector.job.log.JoblogReadData;
import ast.sap.connector.job.log.JoblogReader;
import ast.sap.connector.xmi.XmiLoginData;

public class GetJobOutputCommand extends SapCommand {
	private BaseJobData jobData;

	public GetJobOutputCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, BaseJobData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	protected Object perform() {
		SapRepository sapRepository = repository();
		JoblogReader joblogReader = new JoblogReader(sapRepository);
		JoblogReadData joblogReadData = new JoblogReadData(jobData.getJobName(), jobData.getJobId(), jobData.getExternalUsername());
		return joblogReader.readLog(joblogReadData);
	}

}
