package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class ClientNumberArgumentParser extends AbstractArgumentParser {
	public ClientNumberArgumentParser() {
		super("-n", "\\d+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setClientNumber(value);
	}
}
