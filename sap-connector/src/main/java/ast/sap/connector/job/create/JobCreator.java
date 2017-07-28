package ast.sap.connector.job.create;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.job.JobCreateData;

public class JobCreator {
	private final SapRepository sapRepository;

	public JobCreator(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}

	public NewJobData createJob(JobCreateData jobData) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_JOB_OPEN")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());

		SapFunctionResult result = function.execute();

		String jobCount = result.getOutParameterValue("JOBCOUNT").toString();
		SapBapiret2 ret = new SapBapiret2(result.getStructure("RETURN"));
		return new NewJobData(jobCount, ret);
	}
}
