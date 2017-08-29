package ast.sap.connector.main;

import ast.sap.connector.cmd.SapCommandResult;

/**
 * @author franco.milanese
 *
 *         Parsea la salida del comando ejecutado
 */
public enum OutputParser {
	INSTANCE;

	/**
	 * @param commandResult
	 * @return Codigo y mensaje de la estructura SapBapiRet2
	 */
	public OutputError parseOutput(SapCommandResult commandResult) {
		Integer code = null;
		String message = null;
		
		if (commandResult.getStatusCode().isPresent()) {
			if (commandResult.getStatusCode().get().notFinished()) {
				return new OutputError(ErrorCode.ERROR_JOB_STATUS);
			}
			
		}
		
		if (commandResult.getRet().isPresent()) {
			code = commandResult.getRet().get().getNumber();
			message = commandResult.getRet().get().getMessage();
			return new OutputError(code, message);
		} 
		return new OutputError(ErrorCode.UNKNOWN);
	}

}
