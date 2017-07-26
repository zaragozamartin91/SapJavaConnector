package ast.sap.connector.main.args;

public interface ArgumentParser {
    boolean matches(String arg);

    String getArgValue(String arg);

    InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg);
}
