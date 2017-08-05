package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSession;

/**
 * Comando xmi de sap a ejecutar. Los comandos xmi realizan los siguientes pasos: 
 * <ul>
 * <li>Inician sesion con XMI.      </li>
 * <li>Ejecutan una accion.         </li>
 * <li>Obtienen salida del comando. </li>
 * <li>Cierran sesion con XMI.      </li>
 * <li>Retorna la salida del comando.      </li>
 * </ul>
 * 
 * @see XmiSession
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
	public SapCommandResult execute() {
		XmiSession xmiSession = new XmiSession(repository(), xmiLoginData);
		SapCommandResult output = perform();
		xmiSession.logout();

		return output;
	}

	/**
	 * Accion puntual a realizar por el comando.
	 * 
	 * @return Resultado de la accion realizada.
	 */
	protected abstract SapCommandResult perform();
}
