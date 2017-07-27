package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.job.CreateJobData;
import ast.sap.connector.xmi.XmiLoginData;

public class CreateJobCommand extends SapXmiCommand {
	private final CreateJobData jobData;

	public CreateJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, CreateJobData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	protected SapCommandResult perform() {
		SapFunction function = repository().getFunction("BAPI_XBP_JOB_OPEN")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());
		
		SapFunctionResult result = function.execute();
		
		return null;
	}
}
