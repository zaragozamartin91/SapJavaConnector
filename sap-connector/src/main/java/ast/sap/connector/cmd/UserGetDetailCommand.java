package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;

public class UserGetDetailCommand extends AbstractSapCommand {
	private final String username;

	public UserGetDetailCommand(SapRepository sapRepository, String username) {
		super(sapRepository);
		this.username = username;
	}

	@Override
	public SapCommandResult execute() {
		SapFunction function = repository().getFunction("BAPI_USER_GET_DETAIL")
				.setInParameter("USERNAME", username);

		SapFunctionResult result = function.execute();
		SapBapiret2 ret = new SapBapiret2(result.getStructure("RETURN"));

		System.out.println("Message: " + ret.getMessage());

		System.out.println("User alias: " + result.getOutParameterValue("ALIAS"));

		return new SapCommandResult(ret);
	}
}
