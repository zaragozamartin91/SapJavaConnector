package ast.sap.connector.dst.exception;

public class DestinationGetFailException extends RuntimeException {
	private static final long serialVersionUID = -4316591956662837006L;

	public DestinationGetFailException() {
	}

	public DestinationGetFailException(String message) {
		super(message);
	}

	public DestinationGetFailException(Throwable cause) {
		super(cause);
	}

	public DestinationGetFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public DestinationGetFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
