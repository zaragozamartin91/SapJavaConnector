package ast.sap.connector.cmd;

import ast.sap.connector.dst.exception.RepositoryGetFailException;

public interface SapCommand {

	JobCommandResult execute() throws RepositoryGetFailException;

}