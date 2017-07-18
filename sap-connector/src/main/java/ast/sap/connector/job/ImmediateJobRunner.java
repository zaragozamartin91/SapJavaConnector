package ast.sap.connector.job;

import com.google.common.base.Preconditions;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;

public class ImmediateJobRunner implements JobRunner {
	private final SapRepository sapRepository;

	public ImmediateJobRunner(SapRepository sapRepository) {
		Preconditions.checkNotNull(sapRepository, "El repositorio de funciones no puede ser nulo");
		this.sapRepository = sapRepository;
	}

	@Override
	public SapStruct runJob(RunnerJobData jobRunData) {
		SapFunction jobStartAsapFunction = sapRepository.getFunction("BAPI_XBP_JOB_START_IMMEDIATELY")
				.setInParameter("JOBNAME", jobRunData.getJobName())
				.setInParameter("JOBCOUNT", jobRunData.getJobId())
				.setInParameter("EXTERNAL_USER_NAME", jobRunData.getExternalUsername())
				.setInParameter("TARGET_SERVER", jobRunData.getTargetServer());

		SapFunctionResult result = jobStartAsapFunction.execute();
		return result.getStructure("RETURN");
	}
}
