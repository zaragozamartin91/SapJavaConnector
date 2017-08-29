package ast.sap.connector.chain.start;

import ast.sap.connector.chain.ChainData;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.exception.RspcExecuteException;

/**
 * @author franco.milanese
 *
 */
public class ChainStarter {

	private final SapRepository repository;

	public ChainStarter(SapRepository repository) {
		this.repository = repository;
	}

	/**
	 * Ejecuta una Process Chain de SAP
	 * 
	 * @see http://www.sapdatasheet.org/abap/func/rspc_api_chain_start.html
	 * 
	 * @param chain
	 * 
	 * @return ChainData
	 * 			- LogId de una process chain dentro de la estructura ChainData
	 * 
	 * @throws RspcExecuteException
	 */
	public ChainData startChain(String chain) throws RspcExecuteException{

		SapFunction function = repository.getFunction("RSPC_API_CHAIN_START")
				.setInParameter("I_CHAIN", chain);
		SapFunctionResult result = null;
		String logId = null;
		result = function.execute();
		logId = result.getOutParameterValue("E_LOGID").toString();
		return new ChainData(logId);
	}
}