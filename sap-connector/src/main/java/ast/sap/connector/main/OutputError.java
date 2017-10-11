package ast.sap.connector.main;

import com.google.common.base.Strings;

public class OutputError {
	private int code;
	private int trueCode;
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

	public int getTrueCode() {
		return trueCode;
	}

	/**
	 * @param code
	 * @param message
	 * 
	 *            Crea un OutputError con el code y message indicado por parametro
	 */
	public OutputError(int code, String message) {
		this.code = code;
		this.trueCode = code;
		this.message = message;
	}

	public OutputError(int code, String message, int trueCode) {
		this(code, message);
		this.trueCode = trueCode;
	}

	/**
	 * @param errorCode
	 *            Codigo de error.
	 * @param e
	 *            Excepcion que provoco el error.
	 * 
	 *            Crea un OutputError y setea el mensaje de error a partir del errorCode o de la excepcion en caso que el primero sea nulo o vacio
	 */
	public OutputError(ErrorCode errorCode, Throwable e) {
		this.code = errorCode.code;
		this.trueCode = code;
		this.message = (Strings.isNullOrEmpty(errorCode.message) ? e.toString() : errorCode.message);
	}

	/**
	 * @param errorCode
	 * 
	 *            Crea un OutputError y setea el mensaje a partir del errorCode
	 */
	public OutputError(ErrorCode errorCode) {
		this.code = errorCode.code;
		this.message = errorCode.message;
	}

	@Override
	public String toString() {
		return "OutputError [code=" + code + ", trueCode=" + trueCode + ", message=" + message + "]";
	}

	/**
	 * Retorna true si el codigo de error es exito.
	 * 
	 * @return true si el codigo de error es exito, false en caso contrario.
	 */
	public boolean isSuccess() {
		return this.code == ErrorCode.SUCCESS.code;
	}
}
