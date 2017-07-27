package ast.sap.connector.main;

import ast.sap.connector.cmd.RunJobCommand;
import ast.sap.connector.cmd.SapCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.main.args.InputArgumentsData;

public enum CommandFactory {
	INSTANCE;
	
	/* TODO TERMINAR */
	public SapCommand create(InputArgumentsData inputArguments, SapRepository sapRepository) {
		return new RunJobCommand(null, null, null);
	}
}
