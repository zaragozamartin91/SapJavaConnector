package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class JobNameArgumentParser extends AbstractArgumentParser {
	public JobNameArgumentParser() {
		super("-j", ".+");
	}

	@Override
	public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
		inputArgumentsData.setJobName(getArgValue(arg));
		return inputArgumentsData;
	}
}
