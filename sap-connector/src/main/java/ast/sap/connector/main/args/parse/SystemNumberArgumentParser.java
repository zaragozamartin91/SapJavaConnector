package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.AbstractArgumentParser;
import ast.sap.connector.main.args.InputArgumentsData;

public class SystemNumberArgumentParser extends AbstractArgumentParser {
	public SystemNumberArgumentParser() {
		super("-s", "\\d+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setSystemNumber(value);
	}
}
