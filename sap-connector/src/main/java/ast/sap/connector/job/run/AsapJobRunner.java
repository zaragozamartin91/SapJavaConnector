package ast.sap.connector.job.run;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.job.JobRunData;

public class AsapJobRunner implements JobRunner {
	private final SapRepository sapRepository;

	public AsapJobRunner(SapRepository sapRepository) {
		Preconditions.checkNotNull(sapRepository, "El repositorio de funciones no puede ser nulo");
		this.sapRepository = sapRepository;
	}

	@Override
	public SapBapiret2 runJob(JobRunData jobData) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_JOB_START_ASAP");
		function.setInParameter("JOBNAME", jobData.getJobName());
		function.setInParameter("JOBCOUNT", jobData.getJobId());
		function.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());

		Optional<String> targetServer = Optional.fromNullable(jobData.getTargetServer());
		if (targetServer.isPresent()) {
			function.setInParameter("TARGET_SERVER", targetServer.get());
		}

		SapFunctionResult result = function.execute();
		return new SapBapiret2(result.getStructure("RETURN"));
	}
}
