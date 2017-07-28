package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class CmdArgumentParser extends AbstractArgumentParser {
    public CmdArgumentParser() {
        super("-c", ".+");
    }

    @Override
    protected InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value) {
        return inputArgumentsData.setCommand(value);
    }
}
