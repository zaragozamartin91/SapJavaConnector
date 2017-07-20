package ast.sap.connector.job.log;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;

public class JoblogReader {
	private final SapRepository sapRepository;

	public JoblogReader(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}

	/**
	 * @see http://www.sapdatasheet.org/abap/func/bapi_xbp_job_joblog_read.html
	 * 
	 * @param jobData
	 * @return
	 */
	public JobLog readLog(JoblogReadData jobData) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_JOB_START_ASAP")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("JOBCOUNT", jobData.getJobId())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername())
				.setInParameter("DIRECTION", jobData.getDirection());

		if (jobData.getLines() > 0) {
			function.setInParameter("JOBCOUNT", jobData.getLines());
		}

		SapFunctionResult result = function.execute();
		SapStruct ret = result.getStructure("RETURN");
		SapBapiret2 bapiRet2 = new SapBapiret2(ret);
		OutTableParam logEntries = result.getOutTableParameter("JOB_PROTOCOL_NEW");
		return new JobLog(bapiRet2, logEntries);
	}
}
