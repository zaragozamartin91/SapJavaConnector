package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.SapFunction;

public class ReadTableCommand extends AbstractSapCommand {

	public ReadTableCommand(SapRepository sapRepository) {
		super(sapRepository);
	}

	@Override
	public SapCommandResult execute() throws RepositoryGetFailException {
		SapFunction function = repository().getFunction("RFC_READ_TABLE")
				.setInParameter("QUERY_TABLE", "TBTCO");
		return null;
	}

}
