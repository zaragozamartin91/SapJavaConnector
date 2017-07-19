package ast.sap.connector.xmi;

import ast.sap.connector.dst.SapDestination;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.xmi.exception.XmiLoginException;

public class XmiSession {
	private final SapRepository sapRepository;
	private final XmiSessionData sessionData;

	/**
	 * Inicia una sesion con XMI. Ver {@link XmiSessionManager}.
	 * 
	 * @param sapRepository
	 *            - Repositorio de funciones de sap iniciado para operar con contexto mediante {@link SapDestination#openContext()}.
	 * @param xmiLoginData
	 *            - Datos de inicio de sesion con XMI.
	 * 
	 * @throws XmiLoginException Si no es posible iniciar sesion con xmi.
	 * 
	 */
	public XmiSession(SapRepository sapRepository, XmiLoginData loginData) throws XmiLoginException {
		this.sapRepository = sapRepository;
		this.sessionData = XmiSessionManager.INSTANCE.login(sapRepository, loginData);
	}

	public SapStruct logout() {
		return XmiSessionManager.INSTANCE.logout(sapRepository, sessionData);
	}
}
