package ast.sap.connector.main.args;

public class CmdArgumentParser extends AbstractArgumentParser {
    public CmdArgumentParser(String prefix, String search) {
        super(prefix, search);
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        String argValue = this.getArgValue(arg);
        inputArgumentsData.setCommand(argValue);
        return inputArgumentsData;
    }
}
