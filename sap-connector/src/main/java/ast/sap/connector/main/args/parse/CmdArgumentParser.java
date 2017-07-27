package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

public class CmdArgumentParser extends AbstractArgumentParser {
    public CmdArgumentParser() {
        super("-c", ".+");
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        String argValue = this.getArgValue(arg);
        inputArgumentsData.setCommand(argValue);
        return inputArgumentsData;
    }
}
