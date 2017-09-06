package ast.sap.connector.chain.processes;

import ast.sap.connector.chain.ChainData;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;

public class ChainProcessesReader {

	
	
	private final SapRepository repository;

	public ChainProcessesReader(SapRepository repository) {
		this.repository = repository;
	}

	public void getProcesses(ChainData chainData) {
		SapFunction function = repository.getFunction("RSPC_API_CHAIN_GET_PROCESSES")
				.setInParameter("I_CHAIN", chainData.getChain())
				.setInParameter("I_LOGID", chainData.getLogId());
		SapFunctionResult result = function.execute();
		System.out.println(result.getOutTableParameter("E_T_PROCESSLIST"));
	}

}
