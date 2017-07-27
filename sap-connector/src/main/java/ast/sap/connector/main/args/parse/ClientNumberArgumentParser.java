package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class ClientNumberArgumentParser extends AbstractArgumentParser {
	public ClientNumberArgumentParser() {
		super("-n", "\\d+");
	}

	@Override
	public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
		inputArgumentsData.setClientNumber(getArgValue(arg));
		return inputArgumentsData;
	}
}
