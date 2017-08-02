package ast.sap.connector.cmd;

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
		log("XMI_LOGIN|TRACK_JOB|HELP|CREATE_JOB|RUN_JOB|USER_GET_DETAIL|STOP_JOB|RAISE_EVENT|COMPANYCODE_GETLIST");
		return SapCommandResult.emptyResult();
	}
}
