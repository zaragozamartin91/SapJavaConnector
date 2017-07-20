package ast.sap.connector.job.run;

import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.FullJobData;

/**
 * Ejecutor de JOBS.
 * 
 * @author martin
 *
 */
public interface JobRunner {

	/**
	 * Ejecuta un JOB.
	 * 
	 * @see https://www.sapdatasheet.org/abap/func/bapi_xbp_job_start_asap.html
	 * @see https://www.sapdatasheet.org/abap/func/sxmi_xbp_job_start_immediately.html
	 * 
	 * @param jobData
	 *            - Informacion del job a correr.
	 * @return Resultado del job. Estructura {@link SapBapiret2}.
	 */
	SapBapiret2 runJob(FullJobData jobData);

}