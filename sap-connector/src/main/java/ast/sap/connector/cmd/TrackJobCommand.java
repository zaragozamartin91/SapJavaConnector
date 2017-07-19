package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.xmi.XmiLoginData;

public class TrackJobCommand extends SapCommand {

	public TrackJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData) {
		super(sapRepository, xmiLoginData);
	}

	@Override
	public Object perform(SapRepository sapRepository) {
		return null;
	}

}
