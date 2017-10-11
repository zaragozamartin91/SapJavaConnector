package ast.sap.connector.main;

import java.util.List;

import ast.sap.connector.chain.processes.ProcessLogPair;
import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.track.JobStatusCode;

/**
 * @author franco.milanese
 *
 *         Parsea la salida del comando ejecutado
 */
public enum OutputParser {
	INSTANCE;

	private static final int MAX_RET_CODE = 70;

	/**
	 * @param commandResult
	 * @return Codigo y mensaje de la estructura SapBapiRet2
	 */
	public OutputError parseOutput(SapCommandResult commandResult) {
		/* El chainLogId solo esta presente en el comando startchain. Si este parametro figura, entonces el 
		 * disparo de la cadena fue exitoso */
		if(commandResult.getChainLogId().isPresent()) {
			return new OutputError(ErrorCode.SUCCESS);
		}
		
		/* Si el estado de la cadena es no finalizado entonces se setea el codigo de error correspondiente */
		if(commandResult.getChainStatus().isPresent()) {
			if(!commandResult.getChainStatus().get().hasFinishedSuccessfully()) {
				return new OutputError(ErrorCode.UNFINISHED_CHAIN);
			}
		}
		
		if(commandResult.getProcessLogPairs().isPresent()) {
			List<ProcessLogPair> processLogPairs = commandResult.getProcessLogPairs().get();
			/* Verifico si en la lista de procesos existe algun job que finalizo de manera anormal. En caso afirmativo
			 * seteo el codigo de error adecuado, caso contrario, retorna SUCCESS */
			for (ProcessLogPair processLogPair : processLogPairs) {
				if(processLogPair.getJobFullStatus().getJobStatus().getStatusCode().notFinished()) {
					return new OutputError(ErrorCode.UNFINISHED_CHAIN_JOB);
				}
			}
			return new OutputError(ErrorCode.SUCCESS);
		}
		
		if (commandResult.getJobStatusCode().isPresent()) {
			JobStatusCode jobStatusCode = commandResult.getJobStatusCode().get();
			if (jobStatusCode.notFinished()) return new OutputError(ErrorCode.UNFINISHED_JOB);
		}

		/* Obtiene el code del bapiRet */
		if (commandResult.getRet().isPresent()) {
			SapBapiret2 ret = commandResult.getRet().get();
			String message = ret.getMessage();

			int trueCode = ret.getNumber();
			if (trueCode == 0) return new OutputError(0, message);
			
			if(trueCode == 34) return new OutputError(ErrorCode.CANNOT_RUN_JOB);
			if(trueCode == 49) return new OutputError(ErrorCode.CANNOT_FIND_JOB);
			if(trueCode == 71) return new OutputError(ErrorCode.NONEXISTENT_REPORT);
			if(trueCode == 262) return new OutputError(ErrorCode.NONEXISTENT_VARIANT);
			
			if (trueCode >= 75 && trueCode < 255) return new OutputError(trueCode, message);

			if (trueCode > 1121) {
				/*
				 * Los codigos de error en linux van del 0 al 255. Parseo el codigo de retorno de la ejecucion de la funcion y tomo los errores del 60 hasta el
				 * 69
				 */
				int code = trueCode - 1121 + 60;
				code = code >= MAX_RET_CODE ? MAX_RET_CODE : code;

				return new OutputError(code, message, trueCode);
			}
			
			return new OutputError(ErrorCode.UNKNOWN.code, message);
		}

		return new OutputError(ErrorCode.UNKNOWN);
	}
}
