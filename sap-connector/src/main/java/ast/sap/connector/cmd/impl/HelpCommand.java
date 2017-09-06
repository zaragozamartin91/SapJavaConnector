package ast.sap.connector.cmd.impl;

import java.util.Arrays;
import java.util.regex.Pattern;

import ast.sap.connector.cmd.AvailableCommand;
import ast.sap.connector.cmd.SapCommand;
import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.main.args.InputArgumentsParser;

/**
 * Comando que imprime el menu de ayuda en stdout.
 * 
 * @author martin.zaragoza
 *
 */
public class HelpCommand implements SapCommand {
	private HelpCommand log(String msg) {
		System.out.println(msg);
		return this;
	}

	@Override
	public SapCommandResult execute() {
		log("\nEJEMPLO DE USO:");
		log("\tsapConnector -u mzaragoz -p **** -j TEST_JOB -n 500 -c CREATE_RUN_JOB -h saphanatest -s 01");

		log("\nParametros disponibles:");
		InputArgumentsParser.INSTANCE.printUsage(System.out);
		
		String availableCmdsStr = Arrays.toString(AvailableCommand.values())
				.replaceAll(Pattern.quote(","), "\n\t,")
				.replaceAll(Pattern.quote("["), "[\n\t")
				.replaceAll(Pattern.quote("]"), "\n]");
		log("\nComandos disponibles: " + availableCmdsStr);

		log("\nALGUNOS PARAMETROS PUEDEN OMITIRSE DEPENDIENDO DEL COMANDO A EJECUTAR Y DE LA CONFIGURACION "
				+ "DEL COMPONENTE (POR EJEMPLO: SI EN EL ARCHIVO DE CONFIGURACION FIGURAN LAS PROPIEDADES username "
				+ "Y password, ENTONCES LOS PARAMETROS -u Y -p PUEDEN OMITIRSE).");
		return SapCommandResult.emptyResult();
	}
}
