package ast.sap.connector.main.args;

import ast.sap.connector.main.CmdExeMode;

public class InputArgumentsData {
    /* PARAMETROS DE INICIO DE SESION CON SAP */
    private String user;
    private String password;
    private String host;
    private int timeoutSecs;
    private String clientNumber;
    private String systemNumber;

    /* PARAMETROS DE EJECUCION DE JOBS */
    private String jobName;
    private String command;
    private CmdExeMode mode = CmdExeMode.MONITOR;

    /* COMANDO DE PRUEBA */
    private String testCommand;

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getTimeoutSecs() {
        return timeoutSecs;
    }

    public String getJobName() {
        return jobName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setTimeoutSecs(int timeoutSecs) {
        this.timeoutSecs = timeoutSecs;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public CmdExeMode getMode() {
        return mode;
    }

    public void setMode(CmdExeMode mode) {
        this.mode = mode;
    }

    public String getTestCommand() {
        return testCommand;
    }

    public void setTestCommand(String testCommand) {
        this.testCommand = testCommand;
    }
}
