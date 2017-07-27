package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class HostArgumentParser extends AbstractArgumentParser {
    public HostArgumentParser() {
        super("-h", ".+");
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        inputArgumentsData.setHost(getArgValue(arg));
        return inputArgumentsData;
    }
}
