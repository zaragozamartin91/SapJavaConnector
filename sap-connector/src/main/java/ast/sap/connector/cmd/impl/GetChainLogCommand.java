package ast.sap.connector.cmd.impl;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.exception.RspcExecuteException;
import ast.sap.connector.xmi.XmiLoginData;

public class GetChainLogCommand extends SapXmiCommand {

	private String chainName;
	private String logId;

	public GetChainLogCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, String chainName, String logId) {
		super(sapRepository, xmiLoginData);
		this.chainName = chainName;
		this.logId = logId;
	}

	@Override
	protected SapCommandResult perform() throws RspcExecuteException{
		SapFunction function = repository().getFunction("RSPC_API_CHAIN_GET_LOG")
				.setInParameter("I_CHAIN", chainName)
				.setInParameter("I_LOGID", logId);
		SapFunctionResult execute = function.execute();
		System.out.println(execute.getOutTableParameter("E_T_LOG"));
		
		return SapCommandResult.emptyResult();
	}
}
