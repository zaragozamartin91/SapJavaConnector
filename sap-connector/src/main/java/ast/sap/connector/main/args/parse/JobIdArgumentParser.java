package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class JobIdArgumentParser extends AbstractArgumentParser {
	public JobIdArgumentParser() {
		super("-i", ".+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setJobId(value);
	}
}
