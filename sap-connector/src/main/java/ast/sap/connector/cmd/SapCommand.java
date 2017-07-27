package ast.sap.connector.cmd;

import ast.sap.connector.dst.exception.RepositoryGetFailException;

public interface SapCommand {

	SapCommandResult execute() throws RepositoryGetFailException;

}