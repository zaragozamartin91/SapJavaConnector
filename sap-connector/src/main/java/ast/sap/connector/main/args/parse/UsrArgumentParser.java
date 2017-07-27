package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class UsrArgumentParser extends AbstractArgumentParser {
    public UsrArgumentParser() {
        super("-u", ".+");
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        inputArgumentsData.setUser(getArgValue(arg));
        return inputArgumentsData;
    }
}
