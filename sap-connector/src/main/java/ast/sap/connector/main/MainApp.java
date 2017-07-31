package ast.sap.connector.main;

import com.sap.conn.jco.JCoException;

import ast.sap.connector.cmd.HelpCommand;
import ast.sap.connector.cmd.SapCommand;
import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.dst.SapDestination;
import ast.sap.connector.dst.SapDestinationFactory;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.main.args.InputArgumentsData;
import ast.sap.connector.main.args.InputArgumentsParser;
import ast.sap.connector.util.ConnectionData;
import ast.sap.connector.util.DestinationConfigBuilder;

public class MainApp {
	public static void main(String[] args) {
		System.out.println("java.library.path--------------------------------------------------------------------------------------------------------------");
		System.out.println(System.getProperty("java.library.path"));
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
		
		InputArgumentsData inputArgs = InputArgumentsParser.INSTANCE.parse(args);

		System.out.println("DATOS DE ENTRADA:");
		System.out.println(inputArgs);

		if (inputArgs.isHelp()) {
			new HelpCommand().execute();
			return;
		}

		String destinationName = "mainDestination";
		System.out.println("CONSTRUYENDO CONFIGURACION DE DESTINO SAP " + destinationName);
		ConnectionData connectionData = new ConnectionData(
				inputArgs.getClientNumber(),
				inputArgs.getUser(),
				inputArgs.getPassword(),
				inputArgs.getLanguage(),
				inputArgs.getHost(),
				inputArgs.getSystemNumber());
		new DestinationConfigBuilder().build(destinationName, connectionData);

		SapDestination destination = SapDestinationFactory.INSTANCE.getDestination(destinationName);

		try {
			System.out.println("ATRIBUTOS DE CONEXION:");
			destination.getAttributes();

			SapRepository sapRepository = destination.openContext();

			SapCommand command = CommandFactory.INSTANCE.getCommand(inputArgs, sapRepository);
			SapCommandResult commandResult = command.execute();
			System.out.println("RESULTADO DEL COMANDO: " + commandResult);
		} catch (RepositoryGetFailException e) {
			System.err.println("OCURRIO UN ERROR AL OBTENER EL REPOSITORIO DE " + destinationName);
			e.printStackTrace();
		} catch (JCoException e) {
			System.err.println("OCURRIO UN ERROR AL OBTENER LOS ATRIBUTOS DE LA CONEXION:");
			e.printStackTrace();
			e.printStackTrace();
		} finally {
			try {
				destination.closeContext();
			} catch (Exception e) {
			}
		}
	}
}
