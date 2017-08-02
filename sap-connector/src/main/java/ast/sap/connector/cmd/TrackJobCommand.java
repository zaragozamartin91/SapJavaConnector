package ast.sap.connector.cmd;

import com.google.common.base.Optional;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.JobTrackData;
import ast.sap.connector.job.track.JobStatus;
import ast.sap.connector.job.track.JobTracker;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando para la observacion del status del job.
 * 
 * @author martin.zaragoza
 *
 */
public class TrackJobCommand extends SapXmiCommand {
	private final JobTrackData jobData;

	public TrackJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, JobTrackData jobData) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
	}

	@Override
	protected SapCommandResult perform() {
		SapRepository sapRepository = repository();
		JobTracker jobTracker = new JobTracker(sapRepository);
		JobStatus jobStatus = jobTracker.getStatus(jobData);
		String msg = Optional.fromNullable(jobStatus.getReturnStruct().getMessage()).or("");
		return msg.isEmpty() ? new SapCommandResult(jobStatus) : new SapCommandResult(msg);
	}

}
