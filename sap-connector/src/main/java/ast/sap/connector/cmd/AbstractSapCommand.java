package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;

public abstract class AbstractSapCommand implements SapCommand {
	private final SapRepository sapRepository;

	public AbstractSapCommand(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}

	protected SapRepository repository() {
		return sapRepository;
	}
}