package ast.sap.connector.main;

import com.google.common.base.Strings;

public class OutputError {

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param code
	 * @param message
	 * 
	 * Crea un OutputError con el code y message indicado por parametro
	 */
	public OutputError(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @param errorCode
	 * @param e
	 * 
	 * Crea un OutputError y setea el mensaje de error a partir del errorCode o de la excepcion en caso que el primero sea nulo o vacio
	 */
	public OutputError(ErrorCode errorCode, Exception e) {
		this.code = errorCode.code;
		this.message = (Strings.isNullOrEmpty(errorCode.message) ? e.toString() : errorCode.message);
	}

	/**
	 * @param errorCode
	 * 
	 * Crea un OutputError y setea el mensaje a partir del errorCode
	 */
	public OutputError(ErrorCode errorCode) {
		this.code = errorCode.code;
		this.message = errorCode.message;
	}

	@Override
	public String toString() {
		return "OutputError [code=" + code + ", message=" + message + "]";
	}
	
	

}
