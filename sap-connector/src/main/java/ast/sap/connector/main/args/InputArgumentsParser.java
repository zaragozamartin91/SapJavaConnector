package ast.sap.connector.main.args;

import ast.sap.connector.main.args.parse.ArgumentParser;
import ast.sap.connector.main.args.parse.ClientNumberArgumentParser;
import ast.sap.connector.main.args.parse.CmdArgumentParser;
import ast.sap.connector.main.args.parse.HostArgumentParser;
import ast.sap.connector.main.args.parse.JobIdArgumentParser;
import ast.sap.connector.main.args.parse.JobNameArgumentParser;
import ast.sap.connector.main.args.parse.PasswordArgumentParser;
import ast.sap.connector.main.args.parse.SystemNumberArgumentParser;
import ast.sap.connector.main.args.parse.UsrArgumentParser;

public enum InputArgumentsParser {
	INSTANCE;

	private static final ArgumentParser[] ARGUMENT_PARSERS = {
			new JobIdArgumentParser(),
			new UsrArgumentParser(),
			new ClientNumberArgumentParser(),
			new SystemNumberArgumentParser(),
			new CmdArgumentParser(),
			new HostArgumentParser(),
			new PasswordArgumentParser(),
			new JobNameArgumentParser()
	};

	public InputArgumentsData parse(String[] args) {
		InputArgumentsData inputArgumentsData = new InputArgumentsData();

		for (ArgumentParser argumentParser : ARGUMENT_PARSERS) {
			parseArgument(args, inputArgumentsData, argumentParser);
		}

		return inputArgumentsData;
	}

	private InputArgumentsData parseArgument(String[] args, InputArgumentsData inputArgumentsData, ArgumentParser argumentParser) {
		for (String arg : args) {
			if (argumentParser.matches(arg)) {
				return argumentParser.setArgValue(inputArgumentsData, arg);
			}
		}
		return inputArgumentsData;
	}
}
