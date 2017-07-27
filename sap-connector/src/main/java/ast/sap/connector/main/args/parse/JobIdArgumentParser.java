package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class JobIdArgumentParser extends AbstractArgumentParser {
	public JobIdArgumentParser() {
		super("-i", ".+");
	}

	@Override
	public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
		inputArgumentsData.setJobId(getArgValue(arg));
		return inputArgumentsData;
	}
}
