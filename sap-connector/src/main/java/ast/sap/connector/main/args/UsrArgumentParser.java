package ast.sap.connector.main.args;

public class UsrArgumentParser extends AbstractArgumentParser {
    UsrArgumentParser(String prefix, String search) {
        super(prefix, search);
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        inputArgumentsData.setUser(this.getArgValue(arg));
        return inputArgumentsData;
    }
}
