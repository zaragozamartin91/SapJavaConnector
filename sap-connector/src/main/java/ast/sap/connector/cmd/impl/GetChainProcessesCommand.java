package ast.sap.connector.cmd.impl;

import ast.sap.connector.chain.ChainData;
import ast.sap.connector.chain.processes.ChainProcessesReader;
import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.xmi.XmiLoginData;

public class GetChainProcessesCommand extends SapXmiCommand {

	private ChainData chainData;

	public GetChainProcessesCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, ChainData chainData) {
		super(sapRepository, xmiLoginData);
		this.chainData = chainData;
	}

	@Override
	protected SapCommandResult perform() {
		ChainProcessesReader chainProcReader = new ChainProcessesReader(repository());
		chainProcReader.getProcesses(chainData);
		return SapCommandResult.emptyResult();
	}


}
	