package ast.sap.connector.conn.dst;

public class NonexistentFunctionException extends RuntimeException {
	private static final long serialVersionUID = 963299260626471538L;

	public NonexistentFunctionException() {
	}

	public NonexistentFunctionException(String message) {
		super(message);
	}

	public NonexistentFunctionException(Throwable cause) {
		super(cause);
	}

	public NonexistentFunctionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonexistentFunctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
