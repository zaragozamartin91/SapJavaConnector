package ast.sap.connector.main.args;

public class HostArgumentParser extends AbstractArgumentParser {
    HostArgumentParser() {
        super("-h", ".+");
    }

    @Override
    public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
        inputArgumentsData.setHost(getArgValue(arg));
        return inputArgumentsData;
    }
}
