package ast.sap.connector.chain.monitor;

import java.util.Collections;
import java.util.List;

import ast.sap.connector.chain.processes.ProcessLogPair;
import ast.sap.connector.chain.status.ChainStatus;

/**
 * Estructura para el retorno del monitoreo de la cadena
 * 
 * @author franco.milanese
 *
 */
public class ChainFullStatus {
	private ChainStatus chainStatus;
	private List<ProcessLogPair> processLogPairs;

	/**
	 * @param chainStatus Estado final de la cadena.
	 * 			
	 * @param processLogPairs Listado de Procesos que contiene la cadena.
	 */
	public ChainFullStatus(ChainStatus chainStatus, List<ProcessLogPair> processLogPairs) {
		this.chainStatus = chainStatus;
		this.processLogPairs = Collections.unmodifiableList(processLogPairs);
	}

	public ChainStatus getChainStatus() {
		return chainStatus;
	}

	public List<ProcessLogPair> getProcessLogPairs() {
		return processLogPairs;
	}

	@Override
	public String toString() {
		return "ChainFullStatus [chainStatus=" + chainStatus + ", processLogPairs=" + processLogPairs + "]";
	}
}
