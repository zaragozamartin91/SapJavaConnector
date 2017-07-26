package ast.sap.connector.main.args;

public enum InputArgumentsParser {
    INSTANCE;

    private static final ArgumentParser[] ARGUMENT_PARSERS = {
            new CmdArgumentParser(),
            new HostArgumentParser(),
            new PasswordArgumentParser()
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
