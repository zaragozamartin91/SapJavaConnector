package ast.sap.connector.conn.dst;

public class ContextCloseException extends RuntimeException {
	private static final long serialVersionUID = -5206432038012686100L;

	public ContextCloseException() {
		super();
	}

	public ContextCloseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ContextCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContextCloseException(String message) {
		super(message);
	}

	public ContextCloseException(Throwable cause) {
		super(cause);
	}
}
