package ast.sap.connector.func;

public class SapBapiret2 {
	private final String type;
	private final String id;
	private final int number;
	private final String message;
	private final String logNo;
	private final int logMsgNo;
	private final String messagev1;
	private final String messagev2;
	private final String messagev3;
	private final String messagev4;
	private final String parameter;
	private final int row;
	private final String field;
	private final String system;

	private SapBapiret2(SapStruct sapStruct) {
		type = (String) sapStruct.getValue("TYPE");
		id = (String) sapStruct.getValue("ID");
		number = (int) sapStruct.getValue("NUMBER");
		message = (String) sapStruct.getValue("MESSAGE");
		logNo = (String) sapStruct.getValue("LOG_NO");
		logMsgNo = (int) sapStruct.getValue("LOG_MSG_NO");
		messagev1 = (String) sapStruct.getValue("MESSAGE_V1");
		messagev2 = (String) sapStruct.getValue("MESSAGE_V2");
		messagev3 = (String) sapStruct.getValue("MESSAGE_V3");
		messagev4 = (String) sapStruct.getValue("MESSAGE_V4");
		parameter = (String) sapStruct.getValue("PARAMETER");
		row = (int) sapStruct.getValue("ROW");
		field = (String) sapStruct.getValue("FIELD");
		system = (String) sapStruct.getValue("SYSTEM");
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public String getMessage() {
		return message;
	}

	public String getLogNo() {
		return logNo;
	}

	public int getLogMsgNo() {
		return logMsgNo;
	}

	public String getMessagev1() {
		return messagev1;
	}

	public String getMessagev2() {
		return messagev2;
	}

	public String getMessagev3() {
		return messagev3;
	}

	public String getMessagev4() {
		return messagev4;
	}

	public String getParameter() {
		return parameter;
	}

	public int getRow() {
		return row;
	}

	public String getField() {
		return field;
	}

	public String getSystem() {
		return system;
	}
}
