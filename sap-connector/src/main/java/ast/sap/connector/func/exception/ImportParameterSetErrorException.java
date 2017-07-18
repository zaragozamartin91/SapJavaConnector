package ast.sap.connector.func.exception;

public class ImportParameterSetErrorException extends RuntimeException {
	private static final long serialVersionUID = -4436290308578463655L;

	public ImportParameterSetErrorException() {
	}

	public ImportParameterSetErrorException(String message) {
		super(message);
	}

	public ImportParameterSetErrorException(Throwable cause) {
		super(cause);
	}

	public ImportParameterSetErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImportParameterSetErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
