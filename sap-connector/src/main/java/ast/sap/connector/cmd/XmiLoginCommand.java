package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.xmi.XmiLoginData;

public class XmiLoginCommand extends SapXmiCommand {

	public XmiLoginCommand(SapRepository sapRepository, XmiLoginData xmiLoginData) {
		super(sapRepository, xmiLoginData);
	}

	@Override
	protected SapCommandResult perform() {
		System.out.println("SESION XMI INICIADA EXITOSAMENTE");
		return SapCommandResult.emptyResult();
	}
}
