package ast.sap.connector.func.exception;

public class FunctionExecuteException extends RuntimeException {
	private static final long serialVersionUID = 6774919672109212766L;

	public FunctionExecuteException() {
	}

	public FunctionExecuteException(String message) {
		super(message);
	}

	public FunctionExecuteException(Throwable cause) {
		super(cause);
	}

	public FunctionExecuteException(String message, Throwable cause) {
		super(message, cause);
	}

	public FunctionExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
