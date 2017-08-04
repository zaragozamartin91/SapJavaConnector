package ast.sap.connector.cmd.impl;

import ast.sap.connector.cmd.SapCommand;
import ast.sap.connector.cmd.SapCommandResult;

public class HelpCommand implements SapCommand {
	private HelpCommand log(String msg) {
		System.out.println(msg);
		return this;
	}

	@Override
	public SapCommandResult execute() {
		log("USO DE CONECTOR SAP:");
		log("> sapConnector -uUSUARIO -pPASSWORD -jNOMBRE_JOB -nCLIENT_NUMBER -cCOMANDO -hHOST -iJOB_ID -sSYS_NUMBER -xEXEC_SERVER -eEVENT_ID");
		log("COMANDOS DISPONIBLES:");
		log("XMI_LOGIN|TRACK_JOB|RUN_JOB|CREATE_JOB|USER_GET_DETAIL|STOP_JOB|RAISE_EVENT|CRYSTAL_GETUSERLIST|GET_JOB_OUTPUT");
		return SapCommandResult.emptyResult();
	}
}
