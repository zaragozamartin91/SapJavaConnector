package ast.sap.connector.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.conn.jco.JCoException;

import ast.sap.connector.cmd.CommandFactory;
import ast.sap.connector.cmd.SapCommand;
import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.impl.EncryptPasswordCommand;
import ast.sap.connector.cmd.impl.HelpCommand;
import ast.sap.connector.config.Configuration;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.SingleDestinationDataProvider;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.exception.RspcExecuteException;
import ast.sap.connector.main.args.InputArgumentsData;
import ast.sap.connector.main.args.InputArgumentsParser;
import ast.sap.connector.util.ConnectionData;
import ast.sap.connector.util.Connector;
import ast.sap.connector.util.Encryptor;
import ast.sap.connector.xmi.exception.XmiLoginException;

public class MainApp {
	public static Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) {
		logger.debug("java.library.path--------------------------------------------------------------------------------------------------------------");
		logger.debug(System.getProperty("java.library.path"));
		logger.debug("-------------------------------------------------------------------------------------------------------------------------------");

		InputArgumentsData inputArgs;
		try {
			inputArgs = InputArgumentsParser.INSTANCE.parse(args);
		} catch (Exception e1) {
			return;
		}

		logger.debug("INPUT DE LA TERMINAL:");
		logger.debug("" + inputArgs);

		if (inputArgs.isHelp()) {
			new HelpCommand().execute();
			return;
		}

		// TODO : LEVANTAR ARCHIVO DE CONFIGURACION Y CARGAR LAS PROPIEDADES DE
		// CONEXION POR DEFECTO
		Configuration.loadConnectorConfig("connectorsap.properties");

		String username = inputArgs.getUser() == null ? Configuration.getProperty("username") : inputArgs.getUser();
		inputArgs.setUser(username);

		String clientNumber = inputArgs.getClientNumber() == null ? Configuration.getProperty("client_number") : inputArgs.getClientNumber();
		inputArgs.setClientNumber(clientNumber);

		if (inputArgs.isEncryptPassword()) {
			EncryptPasswordCommand encryptPasswordCommand = new EncryptPasswordCommand(inputArgs.getPassword());
			System.out.println(encryptPasswordCommand.execute().getMessage());
			return;
		}

		/* El password en el archivo de configuracion SIEMPRE DEBERA ESTAR ENCRIPTADO, independientemente del valor de Configuration.encryptionOn() */
		String password;
		if (Configuration.encryptionOn()) {
			logger.info("Encriptacion de passwords habilitada.");
			password = inputArgs.getPassword() == null ? Encryptor.INSTANCE.decrypt(Configuration.getPassword())
					: Encryptor.INSTANCE.decrypt(inputArgs.getPassword());
		} else {
			password = inputArgs.getPassword() == null ? Encryptor.INSTANCE.decrypt(Configuration.getPassword()) : inputArgs.getPassword();
		}
		inputArgs.setPassword(password);

		String host = inputArgs.getHost() == null ? Configuration.getProperty("host") : inputArgs.getHost();
		inputArgs.setHost(host);

		String systemNumber = inputArgs.getSystemNumber() == null ? Configuration.getProperty("sys_number") : inputArgs.getSystemNumber();
		inputArgs.setSystemNumber(systemNumber);

		logger.debug("DATOS FINALES:");
		logger.debug(inputArgs.toString());

		// String destinationName = "mainDestination";
		String destinationName = "secureDestination";

		logger.debug("CONSTRUYENDO CONFIGURACION DE DESTINO SAP " + destinationName);
		ConnectionData connectionData = new ConnectionData(clientNumber, username, password, inputArgs.getLanguage(), host, systemNumber);

		// DestinationConfigBuilder.INSTANCE.build(destinationName,
		// connectionData);

		/* SE INTENTARA TRABAJAR SIN UN ARCHIVO DE DESTINO */
		SingleDestinationDataProvider.buildNew(destinationName, connectionData).register();

		Connector connector = Connector.INSTANCE.config(destinationName);
		// SapDestination destination = SapDestinationFactory.INSTANCE.getDestination(destinationName);

		OutputError output = null;
		try {
			SapRepository sapRepository = connector.loadDestination().openContext();

			SapCommand command = CommandFactory.INSTANCE.getCommand(inputArgs, sapRepository);
			SapCommandResult commandResult = command.execute();
			logger.debug("RESULTADO DEL COMANDO: " + commandResult);

			output = OutputParser.INSTANCE.parseOutput(commandResult);
		} catch (RepositoryGetFailException e) {
			System.err.println("OCURRIO UN ERROR AL OBTENER EL REPOSITORIO DE " + destinationName);
			ErrorCode errorCode = ErrorCode.REPOSITORY_GET_FAIL;
			output = new OutputError(errorCode, e);
			System.err.println("[ERROR] " + output.getCode() + " - " + output.getMessage());
			e.printStackTrace();
		} catch (RspcExecuteException e) {
			System.err.println("OCURRIO UN ERROR AL EJECUTAR MODULO RSPC:");
			ErrorCode errorCode = ErrorCode.fromName(e.getAbapException().getKey());
			output = new OutputError(errorCode, e);
			System.err.println("[ERROR] " + output.getCode() + " - " + output.getMessage());
			e.printStackTrace();
		} catch (JCoException e) {
			System.err.println("OCURRIO UN ERROR AL OBTENER LOS ATRIBUTOS DE LA CONEXION:");
			// ErrorCode errorCode = ErrorCode.fromCode(e.getGroup());
			ErrorCode errorCode = ErrorCode.fromName(e.getKey());
			output = new OutputError(errorCode, e);
			System.err.println("[ERROR] " + output.getCode() + " - " + output.getMessage());
			e.printStackTrace();
		} catch (XmiLoginException e) {
			System.err.println("OCURRIO UN ERROR AL INICIAR SESION CONTRA XMI:");
			ErrorCode errorCode = ErrorCode.XMI_LOGIN_EXCEPTION;
			output = new OutputError(errorCode, e);
			System.err.println("[ERROR] " + output.getCode() + " - " + output.getMessage());
			e.printStackTrace();
		} catch (Throwable e) {
			logger.error("Error fatal", e);
			ErrorCode errorCode = ErrorCode.UNKNOWN;
			output = new OutputError(errorCode.getCode(), "" + e);
		} finally {
			try {
				connector.closeContext();
			} catch (Exception e) {
			}

			/*
			 * EL CODIGO DE ERROR DEL PROGRAMA SE ESTABLECE AQUI https://stackoverflow .com/questions/887066/how-to-get-the-exit-status
			 * -of-a-java-program-in-windows-batch-file
			 */
			logger.debug(output.getMessage());
			System.out.println(output);
			System.exit(output.getCode());
		}
	}
}
