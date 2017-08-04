package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.AbstractArgumentParser;
import ast.sap.connector.main.args.InputArgumentsData;

public class HostArgumentParser extends AbstractArgumentParser {
    public HostArgumentParser() {
        super("-h", ".+");
    }

    @Override
    protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
        return inputArgumentsData.setHost(value);
    }
}
