package ast.sap.connector.cmd.impl;

import com.google.common.base.Optional;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.xmi.XmiLoginData;

public class SetChainStartConditionCommand extends SapXmiCommand {

	private String chain;
	//TODO: que tipo es este campo
	private Optional<String> startCondition;
	
	private Optional<String> externalApi;

	public SetChainStartConditionCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, String chain, String externalApi) {
		super(sapRepository, xmiLoginData);
		this.chain = chain;
		this.externalApi = Optional.fromNullable(externalApi);
	}

	@Override
	protected SapCommandResult perform() {
		SapFunction function = repository().getFunction("RSPC_API_CHAIN_SET_STARTCOND")
				.setInParameter("I_CHAIN", chain);
		
		SapFunctionResult execute = function.execute();
		return SapCommandResult.emptyResult();
	}


}
