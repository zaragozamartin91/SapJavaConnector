package ast.sap.connector.func.exception;

public class NonexistentTableParameterException extends RuntimeException {
	private static final long serialVersionUID = 5451335497432429798L;

	public NonexistentTableParameterException() {
	}

	public NonexistentTableParameterException(String message) {
		super(message);
	}

	public NonexistentTableParameterException(Throwable cause) {
		super(cause);
	}

	public NonexistentTableParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonexistentTableParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
