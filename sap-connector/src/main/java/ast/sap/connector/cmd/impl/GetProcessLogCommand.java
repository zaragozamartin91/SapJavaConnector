package ast.sap.connector.cmd.impl;

import com.google.common.base.Optional;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.exception.RspcExecuteException;
import ast.sap.connector.xmi.XmiLoginData;

public class GetProcessLogCommand extends SapXmiCommand {

	private String logId;
	private String type;
	private String variant;
	private Optional<String> jobId;
	
	public GetProcessLogCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, String logId, String type, String variant, String jobId) {
		super(sapRepository, xmiLoginData);
		this.logId = logId;
		this.type = type;
		this.variant = variant;
		this.jobId = Optional.fromNullable(jobId);
	}

	@Override
	protected SapCommandResult perform() throws RspcExecuteException{
		SapFunction function = repository().getFunction("RSPC_API_PROCESS_GET_LOG")
				.setInParameter("I_LOGID", logId)
				.setInParameter("I_TYPE", type)
				.setInParameter("I_VARIANT", variant);
		if (jobId.isPresent()) {
			function.setInParameter("I_JOBCOUNT", jobId.get());
		}
		SapFunctionResult execute = function.execute();
		System.out.println(execute.getOutParameterValue("E_JOBNAME"));
		System.out.println(execute.getOutParameterValue("E_JOBCOUNT"));
		System.out.println(execute.getOutParameterValue("E_STATUS"));
		System.out.println(execute.getOutTableParameter("E_T_PROCESS_LOG"));
		System.out.println(execute.getOutTableParameter("E_T_JOB_LOG"));
		return SapCommandResult.emptyResult();
				
	}


}
