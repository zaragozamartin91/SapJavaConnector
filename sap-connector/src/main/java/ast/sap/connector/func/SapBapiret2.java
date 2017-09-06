package ast.sap.connector.func;

import com.google.common.base.Optional;

/**
 * Estructura de datos bastante comun en sap.
 * 
 * @see http://www.se80.co.uk/saptables/b/bapi/bapiret2.htm
 * 
 * @author martin.zaragoza
 *
 */
public class SapBapiret2 {
	private String type;
	private String id;
	private Integer number;
	private String message;
	private String logNo;
	private Integer logMsgNo;
	private String messagev1;
	private String messagev2;
	private String messagev3;
	private String messagev4;
	private String parameter;
	private Integer row;
	private String field;
	private String system;

	
	public SapBapiret2(SapStruct sapStruct) {
		type = (String) sapStruct.getValue("TYPE");
		id = (String) sapStruct.getValue("ID");
		number = Integer.valueOf(Optional.fromNullable(sapStruct.getValue("NUMBER")).or("-1").toString());
		message = (String) sapStruct.getValue("MESSAGE");
		logNo = (String) sapStruct.getValue("LOG_NO");
		logMsgNo = Integer.valueOf(Optional.fromNullable(sapStruct.getValue("LOG_MSG_NO")).or("-1").toString());
		messagev1 = (String) sapStruct.getValue("MESSAGE_V1");
		messagev2 = (String) sapStruct.getValue("MESSAGE_V2");
		messagev3 = (String) sapStruct.getValue("MESSAGE_V3");
		messagev4 = (String) sapStruct.getValue("MESSAGE_V4");
		parameter = (String) sapStruct.getValue("PARAMETER");
		row = Integer.valueOf(Optional.fromNullable(sapStruct.getValue("ROW")).or("-1").toString());
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
	
	

	public void setType(String type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setLogNo(String logNo) {
		this.logNo = logNo;
	}

	public void setLogMsgNo(Integer logMsgNo) {
		this.logMsgNo = logMsgNo;
	}

	public void setMessagev1(String messagev1) {
		this.messagev1 = messagev1;
	}

	public void setMessagev2(String messagev2) {
		this.messagev2 = messagev2;
	}

	public void setMessagev3(String messagev3) {
		this.messagev3 = messagev3;
	}

	public void setMessagev4(String messagev4) {
		this.messagev4 = messagev4;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	@Override
	public String toString() {
		return "SapBapiret2 [type=" + type + ", id=" + id + ", number=" + number + ", message=" + message + ", logNo="
				+ logNo + ", logMsgNo=" + logMsgNo + ", messagev1=" + messagev1 + ", messagev2=" + messagev2
				+ ", messagev3=" + messagev3 + ", messagev4=" + messagev4 + ", parameter=" + parameter + ", row=" + row
				+ ", field=" + field + ", system=" + system + "]";
	}

	public boolean hasError() {
		return "E".equals(this.getType());
	}
}
