package ast.sap.connector.main.args;

public class PasswordArgumentParser extends AbstractArgumentParser {
    PasswordArgumentParser() {
        super("-p", ".+");
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        inputArgumentsData.setPassword( getArgValue(arg) );
        return inputArgumentsData;
    }
}
