package ast.sap.connector.job.run;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.job.BaseJobData;

public final class JobStopper {
	private final SapRepository sapRepository;

	public JobStopper(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}
	
	/**
	 * 
	 * @see https://www.sapdatasheet.org/abap/func/bapi_xbp_job_abort.html
	 * 
	 * @param jobData
	 * @return
	 */
	public SapStruct cancelJob(BaseJobData jobData) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_JOB_ABORT")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("JOBCOUNT", jobData.getJobId())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());

		SapFunctionResult result = function.execute();
		return result.getStructure("RETURN");
	}
}
