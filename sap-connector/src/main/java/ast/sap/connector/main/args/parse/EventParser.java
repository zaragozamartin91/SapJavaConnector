package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.AbstractArgumentParser;
import ast.sap.connector.main.args.InputArgumentsData;

public class EventParser extends AbstractArgumentParser {
	public EventParser() {
		super("-e", "\\S+");
	}

	@Override
	protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
		return inputArgumentsData.setEventId(value);
	}
}
