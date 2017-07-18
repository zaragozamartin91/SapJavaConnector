package ast.sap.connector.xmi;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.SapStruct;

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
