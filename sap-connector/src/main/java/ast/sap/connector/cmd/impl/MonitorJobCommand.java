package ast.sap.connector.cmd.impl;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.log.JobLog;
import ast.sap.connector.job.log.JoblogReadData;
import ast.sap.connector.job.log.JoblogReader;
import ast.sap.connector.job.run.AsapJobRunner;
import ast.sap.connector.job.run.JobRunner;
import ast.sap.connector.job.track.JobStatus;
import ast.sap.connector.job.track.JobTracker;
import ast.sap.connector.job.track.StatusCode;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando de lanzamiento y seguimiento de jobs.
 * 
 * Ejecutara un job hasta su finalizacion.
 * 
 * @author martin.zaragoza
 *
 */
public class MonitorJobCommand extends SapXmiCommand {
	private final JobRunData jobData;

	public MonitorJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, JobRunData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	public SapCommandResult perform() {
		SapRepository sapRepository = repository();
		JobRunner jobRunner = new AsapJobRunner(sapRepository);
		System.out.println("Corriendo job " + jobData);
		SapBapiret2 runRet = jobRunner.runJob(jobData);

		if (errorArised(runRet)) {
			System.err.println("Ocurrio un error al disparar la tarea: " + runRet.getMessage());
			return new SapCommandResult(runRet);
		} else {
			return monitorJob(sapRepository);
		}
	}

	private SapCommandResult monitorJob(SapRepository sapRepository) {
		System.out.println("Monitoreando job");
		boolean jobRunning = true;
		JobTracker jobTracker = new JobTracker(sapRepository);

		do {
			JobStatus jobStatus = jobTracker.getStatus(jobData);
			StatusCode statusCode = jobStatus.getStatusCode();
			System.out.println("statusCode: " + statusCode.label);
			jobRunning = statusCode.equals(StatusCode.R) || statusCode.equals(StatusCode.Z);

			if(jobRunning) sleep();
		} while (jobRunning);

		JoblogReader joblogReader = new JoblogReader(sapRepository);
		JobLog jobLog = joblogReader.readLog(new JoblogReadData(jobData.getJobName(), jobData.getJobId(), jobData.getExternalUsername()));

		return new SapCommandResult(jobLog);
	}

	private void sleep() {
		try {
			System.out.println("Durmiendo...");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	private boolean errorArised(SapBapiret2 runRet) {
		/* SI EL TYPE DEL BAPIRET2 ES 'E' ENTONCES OCURRIO UN ERROR. */
		return "E".equals(runRet.getType());
	}
}
