package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;

public class ReadTableCommand extends AbstractSapCommand {

	public ReadTableCommand(SapRepository sapRepository) {
		super(sapRepository);
	}

	@Override
	public SapCommandResult execute() throws RepositoryGetFailException {
		// TODO TERMINAR
		return SapCommandResult.emptyResult();
	}

}
