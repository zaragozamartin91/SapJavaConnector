package ast.sap.connector.job.track;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.job.BaseJobData;

/**
 * Monitor de status de jobs.
 * 
 * @author martin
 *
 */
public class JobTracker {
	private final SapRepository sapRepository;

	public JobTracker(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}

	/**
	 * Obtiene el estado de un job.
	 * 
	 * @see http://www.sapdatasheet.org/abap/func/bapi_xbp_job_status_get.html
	 * 
	 * @param jobData - Informacion del job a monitorear.
	 * @return Estado del job.
	 */
	public JobStatus getStatus(BaseJobData jobData) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_JOB_STATUS_GET")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("JOBCOUNT", jobData.getJobId())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());

		SapFunctionResult result = function.execute();
		String status = result.getOutParameterValue("STATUS").toString();
		SapStruct ret = result.getStructure("RETURN");
		
		return new JobStatus(status, ret);
	}
}
