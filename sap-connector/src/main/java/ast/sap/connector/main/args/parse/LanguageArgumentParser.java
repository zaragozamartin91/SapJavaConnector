package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class LanguageArgumentParser extends AbstractArgumentParser {
	public LanguageArgumentParser() {
		super("-l", "\\w+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setLanguage(value);
	}
}
