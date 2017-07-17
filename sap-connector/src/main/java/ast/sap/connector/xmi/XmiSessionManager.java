package ast.sap.connector.xmi;

import ast.sap.connector.dst.RepositoryGetFailException;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;

public enum XmiSessionManager {
	INSTANCE;

	public XmiSessionData login(SapRepository sapRepository, XmiLoginData xmiLoginData) throws RepositoryGetFailException {
		SapFunction function = sapRepository.getFunction("BAPI_XMI_LOGON")
				.setInParameter("EXTCOMPANY", xmiLoginData.getCompany())
				.setInParameter("EXTPRODUCT", xmiLoginData.getProduct())
				.setInParameter("INTERFACE", xmiLoginData.getXmiInterface())
				.setInParameter("VERSION", xmiLoginData.getVersion());

		SapFunctionResult result = function.execute();
		Object sessionId = result.getOutParameterValue("SESSIONID");

		return new XmiSessionData(sessionId, xmiLoginData.getXmiInterface());
	}

	public SapStruct logout(SapRepository sapRepository, XmiSessionData xmiSessionData) {
		SapFunction function = sapRepository.getFunction("BAPI_XMI_LOGOFF")
				.setInParameter("INTERFACE", xmiSessionData.getXmiInterface());
		
		SapFunctionResult result = function.execute();
		
		return result.getStructure("RETURN");
	}
}
