package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando de lanzamiento y monitoreo de jobs.
 * 
 * @author martin.zaragoza
 *
 */
public class LaunchTrackJobCommand extends SapCommand {

	public LaunchTrackJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData) {
		super(sapRepository, xmiLoginData);
	}

	@Override
	public Object perform(SapRepository sapRepository) {
		return null;
	}

}
