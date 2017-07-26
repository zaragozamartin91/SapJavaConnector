package ast.sap.connector.main.args;

public class UsrArgumentParser extends AbstractArgumentParser {
    public UsrArgumentParser() {
        super("-u", ".+");
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        inputArgumentsData.setUser(this.getArgValue(arg));
        return inputArgumentsData;
    }
}