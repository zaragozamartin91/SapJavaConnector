package ast.sap.connector.main;

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

	/* TODO : TERMINAR */
	// public static void main(String[] args) throws RepositoryGetFailException {
	// Properties configProperties = new Properties();
	// Configuration.newFromPropertiesFile(configProperties);
	//
	// InputArgumentsData inputArguments = InputArgumentsParser.INSTANCE.parse(args);
	//
	// String destinationName = "sampleDestination;";
	//
	// // TODO : DEFINIR SI EL SYSTEM_NUMBER Y EL CLIENT_NUMBER ENTRARAN POR ARGUMENTOS O SERA PARTE DE LA CONFIGURACION
	// ConnectionData connectionData = new ConnectionData(
	// inputArguments.getClientNumber(),
	// inputArguments.getUser(),
	// inputArguments.getPassword(), "EN",
	// inputArguments.getHost(),
	// inputArguments.getSystemNumber());
	// new DestinationConfigBuilder().build(destinationName, connectionData);
	//
	// SapDestination destination = SapDestinationFactory.INSTANCE.getDestination(destinationName);
	// SapRepository sapRepository = destination.openContext();
	//
	// SapXmiCommand command = CommandFactory.INSTANCE.create(inputArguments, sapRepository);
	// Object output = command.execute();
	//
	// OutputParser.INSTANCE.parseOutput(output);
	//
	// destination.closeContext();
	// }

	// public static void main(String[] args) throws JCoException {
	// try {
	// String destinationName = "testDestination_1";
	//
	// SapDestination sapDestination = SapDestinationFactory.INSTANCE.getDestination(destinationName);
	// System.out.println("MOSTRANDO ATRIBUTOS DE CONEXION:");
	// sapDestination.getAttributes();
	//
	// System.out.println("EJECUTANDO BAPI_USER_GET_DETAIL...");
	// Properties jcoDestinationProperties = DestinationConfigBuilder.getJcoDestinationProperties(destinationName);
	// String username = jcoDestinationProperties.getProperty("jco.client.user");
	// SapRepository repository = sapDestination.openContext();
	// SapFunction function = repository.getFunction("BAPI_USER_GET_DETAIL")
	// .setInParameter("USERNAME", username);
	//
	// SapFunctionResult result = function.execute();
	// OutTableParam returnTable = result.getOutTableParameter("RETURN");
	//
	// for (int i = 0; i < returnTable.getRowCount(); i++) {
	// Object message = returnTable.getValue("MESSAGE");
	// System.out.println("Message: " + message);
	// returnTable.nextRow();
	// }
	//
	// sapDestination.closeContext();
	// } catch (Exception e) {
	// System.out.println("OCURRIO UN ERROR:");
	// e.printStackTrace();
	// }
	// }

	public static void main(String[] args) {
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
			SapRepository sapRepository = destination.openContext();
			SapCommand command = CommandFactory.INSTANCE.getCommand(inputArgs, sapRepository);
			SapCommandResult commandResult = command.execute();
			System.out.println("RESULTADO DEL COMANDO: " + commandResult);
		} catch (RepositoryGetFailException e) {
			System.err.println("OCURRIO UN ERROR AL OBTENER EL REPOSITORIO DE " + destinationName);
			e.printStackTrace();
		} finally {
			try {
				destination.closeContext();
			} catch (Exception e) {
			}
		}
	}
}
