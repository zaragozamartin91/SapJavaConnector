package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.xmi.XmiLoginData;

public class CreateJobCommand extends SapXmiCommand {

	public CreateJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData) {
		super(sapRepository, xmiLoginData);
	}

	@Override
	protected SapCommandResult perform() {
		// TODO Auto-generated method stub
		return null;
	}
}
