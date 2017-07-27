package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.BaseJobData;
import ast.sap.connector.job.track.JobTracker;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando para la observacion del status del job.
 * 
 * @author martin.zaragoza
 *
 */
public class TrackJobCommand extends SapXmiCommand {
	private final BaseJobData jobData;

	public TrackJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, BaseJobData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	protected SapCommandResult perform() {
		SapRepository sapRepository = repository();
		JobTracker jobTracker = new JobTracker(sapRepository);
		return new SapCommandResult(jobTracker.getStatus(jobData));
	}

}
