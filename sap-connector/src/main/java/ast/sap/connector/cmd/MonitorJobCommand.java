package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.FullJobData;
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
public class MonitorJobCommand extends SapCommand {
	private final FullJobData jobData;

	public MonitorJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, FullJobData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	public Object perform() {
		SapRepository sapRepository = repository();
		JobRunner jobRunner = new AsapJobRunner(sapRepository);
		SapBapiret2 runRet = jobRunner.runJob(jobData);

		if (errorArised(runRet)) {
			System.out.println("Ocurrio un error al disparar la tarea");
			return runRet.getMessage();
		} else {
			return monitorJob(sapRepository);
		}
	}

	private Object monitorJob(SapRepository sapRepository) {
		boolean jobRunning = true;
		JobTracker jobTracker = new JobTracker(sapRepository);

		do {
			JobStatus jobStatus = jobTracker.getStatus(jobData);
			StatusCode statusCode = jobStatus.getStatusCode();
			jobRunning = statusCode.equals(StatusCode.R) || statusCode.equals(StatusCode.Z);

			sleep();
		} while (jobRunning);

		JoblogReader joblogReader = new JoblogReader(sapRepository);
		JobLog jobLog = joblogReader.readLog(new JoblogReadData(jobData.getJobName(), jobData.getJobId(), jobData.getExternalUsername()));

		return jobLog;
	}

	private void sleep() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}

	private boolean errorArised(SapBapiret2 runRet) {
		// TODO Auto-generated method stub
		return false;
	}

}
