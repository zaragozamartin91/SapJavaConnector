package ast.sap.connector.conn.xmi;

import ast.sap.connector.conn.dst.RepositoryGetFailException;
import ast.sap.connector.conn.dst.SapRepository;
import ast.sap.connector.conn.func.SapStruct;

public class XmiSession {
	private final SapRepository sapRepository;
	private final XmiSessionData sessionData;

	public XmiSession(XmiLoginData loginData, SapRepository sapRepository) throws RepositoryGetFailException {
		this.sapRepository = sapRepository;
		this.sessionData = XmiSessionManager.INSTANCE.login(sapRepository, loginData);
	}

	public SapStruct logout() {
		return XmiSessionManager.INSTANCE.logout(sapRepository, sessionData);
	}
}
