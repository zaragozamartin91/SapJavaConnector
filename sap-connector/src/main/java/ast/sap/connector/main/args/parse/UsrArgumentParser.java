package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class UsrArgumentParser extends AbstractArgumentParser {
	public UsrArgumentParser() {
		super("-u", ".+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setUser(value);
	}
}
