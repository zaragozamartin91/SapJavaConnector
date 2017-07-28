package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class ExecServerArgumentParser extends AbstractArgumentParser {
	public ExecServerArgumentParser() {
		super("-x", "\\S+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setExecServer(value);
	}
}
