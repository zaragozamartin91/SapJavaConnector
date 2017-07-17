package ast.sap.connector.dst;

public class RepositoryGetFailException extends Exception {
	private static final long serialVersionUID = 7410291102048241774L;

	public RepositoryGetFailException() {
		super();
	}

	public RepositoryGetFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RepositoryGetFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryGetFailException(String message) {
		super(message);
	}

	public RepositoryGetFailException(Throwable cause) {
		super(cause);
	}

}
