package ast.sap.connector.conn.xmi;

public class XmiSession {
	private final Object sessionId;
	private final String xmiInterface;

	public XmiSession(Object sessionId, String xmiInterface) {
		super();
		this.sessionId = sessionId;
		this.xmiInterface = xmiInterface;
	}

	public Object getSessionId() {
		return sessionId;
	}

	public String getXmiInterface() {
		return xmiInterface;
	}
}
