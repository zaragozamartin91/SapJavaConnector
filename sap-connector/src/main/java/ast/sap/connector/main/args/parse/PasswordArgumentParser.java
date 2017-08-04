package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.AbstractArgumentParser;
import ast.sap.connector.main.args.InputArgumentsData;

public class PasswordArgumentParser extends AbstractArgumentParser {
	public PasswordArgumentParser() {
		super("-p", ".+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setPassword(value);
	}
}
