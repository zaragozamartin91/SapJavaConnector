package ast.sap.connector.cmd;

import ast.sap.connector.dst.exception.RepositoryGetFailException;

/**
 * Comando de sap.
 * 
 * @author martin.zaragoza
 *
 */
public interface SapCommand {

	/**
	 * Ejecucion de comando de sap.
	 * 
	 * @return Resultado de la ejecucion.
	 * @throws RepositoryGetFailException
	 */
	SapCommandResult execute() throws RepositoryGetFailException;

}