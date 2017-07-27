package ast.sap.connector.job.run;

import com.google.common.base.Preconditions;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.job.RunJobData;

public class ImmediateJobRunner implements JobRunner {
	private final SapRepository sapRepository;

	public ImmediateJobRunner(SapRepository sapRepository) {
		Preconditions.checkNotNull(sapRepository, "El repositorio de funciones no puede ser nulo");
		this.sapRepository = sapRepository;
	}

	@Override
	public SapBapiret2 runJob(RunJobData jobData) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_JOB_START_IMMEDIATELY")
				.setInParameter("JOBNAME", jobData.getJobName())
				.setInParameter("JOBCOUNT", jobData.getJobId())
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername())
				.setInParameter("TARGET_SERVER", jobData.getTargetServer());

		SapFunctionResult result = function.execute();
		return new SapBapiret2(result.getStructure("RETURN"));
	}
}
