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
		log("\nCOMANDOS DISPONIBLES:");
		log(""
				+ "* XMI_LOGIN\n"
				+ "* TRACK_JOB\n"
				+ "* RUN_JOB\n"
				+ "* CREATE_JOB\n"
				+ "* USER_GET_DETAIL\n"
				+ "* STOP_JOB\n"
				+ "* RAISE_EVENT\n"
				+ "* CRYSTAL_GETUSERLIST\n"
				+ "* GET_JOB_OUTPUT\n"
				+ "* MONITOR_JOB\n"
				+ "* RUN_STOP_JOB\n"
				+ "* READ_SPOOL\n"
				+ "* READ_JOB_DEFINITION\n"
				+ "* JOB_COUNT\n"
				+ "* READ_JOB\n"
				+ "* MODIFY_HEADER\n"
				+ "* CHANGE_VARIANT\n"
				+ "* CREATE_RUN_JOB: crea y corre un job con un unico step y variante. Parametros obligatorios[-j -t -v -m]\n"
				+ "* ENCRYPT_PASSWORD: encripta un password. Parametros obligatorios[-p]\n"
				+ "* CREATE_MONITOR_JOB: crea, corre y monitorea un job hasta su finalizacion imprimiendo el log del mismo por intervalos");

		log("\nOPCIONES DISPONIBLES:");
		log(""
				+ "* -uUSUARIO\n"
				+ "* -pPASSWORD\n"
				+ "* -jJOB_NAME\n"
				+ "* -nSAP_CLIENT_NUMBER\n"
				+ "* -cCOMANDO\n"
				+ "* -hHOST_SAP\n"
				+ "* -iJOB_ID\n"
				+ "* -sSAP_SYSTEM_NUMBER\n"
				+ "* -xEXEC_SERVER\n"
				+ "* -eEVENT_ID\n"
				+ "* -mVALORES_DE_CAMPOS_DE_VARIANTE. EJ: -m[(fecha:21.03.1991),(valor:1234)]\n"
				+ "* -sSTEP\n"
				+ "* -vVARIANTE");

		log("\nEJEMPLO DE USO:");
		log("\tsapConnector -umzaragoz -p**** -jTEST_JOB -n500 -cCREATE_RUN_JOB -hsaphanatest -s01");
		
		log("\nALGUNOS PARAMETROS PUEDEN OMITIRSE DEPENDIENDO DEL COMANDO A EJECUTAR Y DE LA CONFIGURACION "
				+ "DEL COMPONENTE (POR EJEMPLO: SI EN EL ARCHIVO DE CONFIGURACION FIGURAN LAS PROPIEDADES username "
				+ "Y password, ENTONCES LOS PARAMETROS -u Y -p PUEDEN OMITIRSE).");
		return SapCommandResult.emptyResult();
	}
}
