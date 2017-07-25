package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSession;

/**
 * Comando de sap a ejecutar.
 * 
 * @author martin.zaragoza
 *
 */
public abstract class SapCommand {
	private final SapRepository sapRepository;
	private final XmiLoginData xmiLoginData;

	public SapCommand(SapRepository sapRepository, XmiLoginData xmiLoginData) {
		this.sapRepository = sapRepository;
		this.xmiLoginData = xmiLoginData;
	}

	public Object execute() throws RepositoryGetFailException {
		XmiSession xmiSession = new XmiSession(sapRepository, xmiLoginData);
		Object output = perform();
		xmiSession.logout();

		return output;
	}
	
	protected SapRepository repository() {
		return sapRepository;
	}

	protected abstract Object perform();
}
