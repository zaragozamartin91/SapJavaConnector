package ast.sap.connector.conn.dst;

public class FunctionGetFailException extends RuntimeException {
	private static final long serialVersionUID = 167935136995907050L;

	public FunctionGetFailException() {
	}

	public FunctionGetFailException(String message) {
		super(message);
	}

	public FunctionGetFailException(Throwable cause) {
		super(cause);
	}

	public FunctionGetFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public FunctionGetFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
