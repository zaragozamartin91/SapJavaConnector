package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSession;

/**
 * Comando xmi de sap a ejecutar. Los comandos xmi realizan los siguientes pasos: > Inician sesion con XMI. > Ejecutan una accion. > Obtienen salida del
 * comando. > Cierran sesion con XMI.
 * 
 * @author martin.zaragoza
 *
 */
public abstract class SapXmiCommand extends AbstractSapCommand {
	private final XmiLoginData xmiLoginData;

	public SapXmiCommand(SapRepository sapRepository, XmiLoginData xmiLoginData) {
		super(sapRepository);
		this.xmiLoginData = xmiLoginData;
	}

	@Override
	public JobCommandResult execute() throws RepositoryGetFailException {
		XmiSession xmiSession = new XmiSession(repository(), xmiLoginData);
		JobCommandResult output = perform();
		xmiSession.logout();

		return output;
	}

	protected abstract JobCommandResult perform();
}
