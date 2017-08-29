package ast.sap.connector.cmd.impl;

import ast.sap.connector.chain.ChainData;
import ast.sap.connector.chain.start.ChainStarter;
import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.exception.RspcExecuteException;
import ast.sap.connector.xmi.XmiLoginData;

public class StartChainCommand extends SapXmiCommand {
	private String chain;

	public StartChainCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, String chain) {
		super(sapRepository, xmiLoginData);
		this.chain = chain;
	}

	@Override
	protected SapCommandResult perform() throws RspcExecuteException {
		ChainStarter chainStarter = new ChainStarter(repository());
		ChainData chainData = chainStarter.startChain(chain);
		return new SapCommandResult(chainData);
	}

}
