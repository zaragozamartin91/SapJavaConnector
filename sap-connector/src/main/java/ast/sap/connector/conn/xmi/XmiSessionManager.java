package ast.sap.connector.conn.xmi;

import ast.sap.connector.conn.dst.RepositoryGetFailException;
import ast.sap.connector.conn.dst.SapRepository;
import ast.sap.connector.conn.func.SapFunction;
import ast.sap.connector.conn.func.SapFunctionResult;

public enum XmiSessionManager {
	INSTANCE;

	public XmiSession login(SapRepository sapRepository, XmiLoginData xmiLoginData) throws RepositoryGetFailException {
		SapFunction function = sapRepository.getFunction("BAPI_XMI_LOGON")
				.setInParameter("EXTCOMPANY", xmiLoginData.getCompany())
				.setInParameter("EXTPRODUCT", xmiLoginData.getProduct())
				.setInParameter("INTERFACE", xmiLoginData.getXmiInterface())
				.setInParameter("VERSION", xmiLoginData.getVersion());

		SapFunctionResult result = function.execute();
		Object sessionId = result.getOutParameterValue("SESSIONID");

		return new XmiSession(sessionId, xmiLoginData.getXmiInterface());
	}

//	public String logout(SapRepository sapRepository, XmiSession xmiSession) {
//		SapFunction function = sapRepository.getFunction("BAPI_XMI_LOGOFF")
//				.setInParameter("INTERFACE", xmiSession.getXmiInterface());
//		
//		SapFunctionResult result = function.execute();
//	}
}
