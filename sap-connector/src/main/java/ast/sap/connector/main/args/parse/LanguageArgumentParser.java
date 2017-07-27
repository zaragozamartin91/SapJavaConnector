package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class LanguageArgumentParser extends AbstractArgumentParser {

	public LanguageArgumentParser() {
		super("-l", "\\w+");
	}

	@Override
	public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
		inputArgumentsData.setLanguage(getArgValue(arg));
		return inputArgumentsData;
	}

}
