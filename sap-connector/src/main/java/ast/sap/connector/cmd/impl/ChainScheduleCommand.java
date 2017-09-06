package ast.sap.connector.cmd.impl;

import com.google.common.base.Optional;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.xmi.XmiLoginData;

public class ChainScheduleCommand extends SapXmiCommand {

	private String chain;
	private Optional<String> periodic;

	public ChainScheduleCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, String chain, String periodic) {
		super(sapRepository, xmiLoginData);
		this.chain = chain;
		this.periodic = Optional.fromNullable(periodic);
	}

	@Override
	protected SapCommandResult perform() {
		SapFunction function = repository().getFunction("RSPC_API_CHAIN_SCHEDULE")
				.setInParameter("I_CHAIN", chain);
		
		if (periodic.isPresent()) {
			function.setInParameter("I_PERIODIC", periodic);			
		}
		SapFunctionResult execute = function.execute();
		System.out.println(execute.getOutTableParameter("E_T_CONFLICTS"));
		return SapCommandResult.emptyResult();
	}


}
