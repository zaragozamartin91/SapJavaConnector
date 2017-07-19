package ast.sap.connector.job;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;

public final class JobStopper {
	private final SapRepository sapRepository;

	public JobStopper(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}
	
	public SapStruct cancelJob(BaseJobData jobData) {
		SapFunction jobStartAsapFunction = sapRepository.getFunction("BAPI_XBP_JOB_ABORT")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("JOBCOUNT", jobData.getJobId())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());

		SapFunctionResult result = jobStartAsapFunction.execute();
		return result.getStructure("RETURN");
	}
}
