package ast.sap.connector.job;

import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapStruct;

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
	 * @param jobRunData
	 *            - Informacion del job a correr.
	 * @return Resultado del job. Estructura {@link SapBapiret2}.
	 */
	SapStruct runJob(FullJobData jobData);

}