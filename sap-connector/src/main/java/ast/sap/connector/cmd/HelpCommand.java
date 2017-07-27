package ast.sap.connector.cmd;

public class HelpCommand implements SapCommand {
	private HelpCommand log(String msg) {
		System.out.println(msg);
		return this;
	}

	@Override
	public SapCommandResult execute() {
		log("USO DE CONECTOR SAP:");
		log("> sapCon -uUSUARIO -pPASSWORD -jNOMBRE_JOB -nCLIENT_NUMBER -cCOMANDO -hHOST -iJOB_ID -sSYS_NUMBER -xEXEC_SERVER");
		log("COMANDOS DISPONIBLES:");
		log("XMI_LOGIN|TRACK_JOB|HELP|CREATE_JOB");
		return SapCommandResult.emptyResult();
	}
}
