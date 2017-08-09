package ast.sap.connector.cmd.impl;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.JobTrackData;
import ast.sap.connector.xmi.XmiLoginData;

public class JobReadCommand extends SapXmiCommand {
	private JobTrackData jobData;
	

	public JobReadCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, JobTrackData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}


	/* LA FUNCION BAPI_XBP_JOB_READ, OBTIENE UNA ESTRUCTURA DE TIPO BP20JOB EN EL PARAMETRO JOBHEAD.
	 * ESTA ESTRUCTURA TIENE UN CAMPO LLAMADO EVENTID EL CUAL TIENE EL NOMBRE DEL EVENTO NECESARIO PARA
	 * DISPARAR EL JOB (EN CASO QUE EL MISMO ESTE PROGRAMADO PARA INICIAR ANTE LA OCURRENCIA DE UN EVENTO).  */
	@Override
	protected SapCommandResult perform() {
		SapFunction function = repository().getFunction("BAPI_XBP_JOB_READ")
			.setInParameter("JOBNAME", jobData.getJobName())
			.setInParameter("JOBCOUNT", jobData.getJobId())
			.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());
		
		SapFunctionResult result = function.execute();
		return SapCommandResult.emptyResult();
	}

}
