package ast.sap.connector.job.run;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.job.BaseJobData;

public final class JobStopper {
	private final SapRepository sapRepository;

	public JobStopper(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}
	
	/**
	 * Detiene un job en curso.
	 * 
	 * @see https://www.sapdatasheet.org/abap/func/bapi_xbp_job_abort.html
	 * 
	 * @param jobData - Informacion sobre el Job a detener.
	 * @return 
	 */
	public SapBapiret2 stopJob(BaseJobData jobData) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_JOB_ABORT")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("JOBCOUNT", jobData.getJobId())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());

		SapFunctionResult result = function.execute();
		return new SapBapiret2(result.getStructure("RETURN"));
	}
}
