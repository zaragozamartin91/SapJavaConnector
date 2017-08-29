package ast.sap.connector.cmd.impl;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.xmi.XmiLoginData;

public class GetChainErrorsCommand extends SapXmiCommand {

	private String chainName;
	private String logId;

	public GetChainErrorsCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, String chainName, String logId) {
		super(sapRepository, xmiLoginData);
		this.chainName = chainName;
		this.logId = logId;
	}

	@Override
	protected SapCommandResult perform() {
		SapFunction function = repository().getFunction("RSPC_API_CHAIN_GET_ERRORS")
				.setInParameter("I_CHAIN", chainName)
				.setInParameter("I_LOGID", logId);
		SapFunctionResult execute = function.execute();
		System.out.println(execute.getOutTableParameter("E_T_LOG"));
		System.out.println(execute.getOutTableParameter("E_T_LOG_DETAILS"));
		
		return SapCommandResult.emptyResult();
	}


}
