package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.SapFunction;

public class UserGetDetailCommand extends AbstractSapCommand {
	public UserGetDetailCommand(SapRepository sapRepository) {
		super(sapRepository);
	}

	@Override
	public SapCommandResult execute() throws RepositoryGetFailException {
		SapFunction function = repository().getFunction("BAPI_USER_GET_DETAIL");
		return null;
	}
}
