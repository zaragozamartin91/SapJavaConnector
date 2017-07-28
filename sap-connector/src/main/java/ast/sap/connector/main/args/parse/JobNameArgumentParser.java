package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class JobNameArgumentParser extends AbstractArgumentParser {
	public JobNameArgumentParser() {
		super("-j", ".+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setJobName(value);
	}
}
