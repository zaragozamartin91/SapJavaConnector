package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class PasswordArgumentParser extends AbstractArgumentParser {
    public PasswordArgumentParser() {
        super("-p", ".+");
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        inputArgumentsData.setPassword( getArgValue(arg) );
        return inputArgumentsData;
    }
}
