package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class ExecServerArgumentParser extends AbstractArgumentParser {
	public ExecServerArgumentParser() {
		super("-x", "\\S+");
	}

	@Override
	public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
		inputArgumentsData.setExecServer(getArgValue(arg));
		return inputArgumentsData;
	}
}
