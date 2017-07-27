package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class SystemNumberArgumentParser extends AbstractArgumentParser {
	public SystemNumberArgumentParser() {
		super("-s", "\\d+");
	}

	@Override
	public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
		inputArgumentsData.setSystemNumber(getArgValue(arg));
		return inputArgumentsData;
	}
}
