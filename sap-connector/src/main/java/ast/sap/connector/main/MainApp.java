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
import ast.sap.connector.main.args.InputArgsParseException;
import ast.sap.connector.main.args.InputArgumentsData;
import ast.sap.connector.main.args.InputArgumentsParser;
import ast.sap.connector.util.ConnectionData;
import ast.sap.connector.util.Connector;
import ast.sap.connector.util.Encryptor;
import ast.sap.connector.util.InvalidConnectionDataException;
import ast.sap.connector.util.Selector;
import ast.sap.connector.xmi.exception.XmiLoginException;

public class MainApp {
	public static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) {
		LOGGER.debug("java.library.path--------------------------------------------------------------------------------------------------------------");
		LOGGER.debug(System.getProperty("java.library.path"));
		LOGGER.debug("-------------------------------------------------------------------------------------------------------------------------------");

		InputArgumentsData inputArgs;
		try {
			inputArgs = InputArgumentsParser.INSTANCE.parse(args);
		} catch (InputArgsParseException e1) {
			LOGGER.error(e1.getMessage());
			return;
		}

		LOGGER.debug("INPUT DE LA TERMINAL:");
		LOGGER.debug("" + inputArgs);

		if (inputArgs.isHelp()) {
			new HelpCommand().execute();
			return;
		}

		if (inputArgs.isEncryptPassword()) {
			EncryptPasswordCommand encryptPasswordCommand = new EncryptPasswordCommand(inputArgs.getPassword());
			System.out.println(encryptPasswordCommand.execute().getMessage().or(""));
			return;
		}

		// TODO : LEVANTAR ARCHIVO DE CONFIGURACION Y CARGAR LAS PROPIEDADES DE
		// CONEXION POR DEFECTO
		Configuration.loadConnectorConfig("connectorsap.properties");

		String username = Selector.GET.firstOrSecond(inputArgs.getUser(), Configuration.getUsername());
		inputArgs.setUser(username);

		String clientNumber = Selector.GET.firstOrSecond(inputArgs.getClientNumber(), Configuration.getClientNumber());
		inputArgs.setClientNumber(clientNumber);

		/* El password en el archivo de configuracion SIEMPRE DEBERA ESTAR ENCRIPTADO, independientemente del valor de Configuration.encryptionOn() */
		String password;
		if (Configuration.encryptionOn()) {
			LOGGER.info("Encriptacion de passwords habilitada.");
			String rawPassword = Selector.GET.firstOrSecond(inputArgs.getPassword(), Configuration.getPassword());
			password = Encryptor.INSTANCE.decrypt(rawPassword);
		} else {
			password = inputArgs.getPassword() == null ? Encryptor.INSTANCE.decrypt(Configuration.getPassword()) : inputArgs.getPassword();
		}
		inputArgs.setPassword(password);

		String host = Selector.GET.firstOrSecond(inputArgs.getHost(), Configuration.getHost());
		inputArgs.setHost(host);

		String systemNumber = Selector.GET.firstOrSecond(inputArgs.getSystemNumber(), Configuration.getSystemNumber());
		inputArgs.setSystemNumber(systemNumber);

		LOGGER.debug("DATOS FINALES:");
		LOGGER.debug(inputArgs.toString());

		// String destinationName = "mainDestination";
		String destinationName = "secureDestination";

		OutputError output = null;
		Connector connector = Connector.INSTANCE;
		try {
			LOGGER.debug("CONSTRUYENDO CONFIGURACION DE DESTINO SAP " + destinationName);

			ConnectionData connectionData = new ConnectionData(clientNumber, username, password, inputArgs.getLanguage(), host, systemNumber);
			connectionData.validate();

			/* SE INTENTARA TRABAJAR SIN UN ARCHIVO DE DESTINO */
			SingleDestinationDataProvider.buildNew(destinationName, connectionData).register();
			connector.config(destinationName);
			SapRepository sapRepository = connector.loadDestination().openContext();

			SapCommand command = CommandFactory.INSTANCE.getCommand(inputArgs, sapRepository);
			SapCommandResult commandResult = command.execute();
			LOGGER.debug("RESULTADO DEL COMANDO: " + commandResult);

			output = OutputParser.INSTANCE.parseOutput(commandResult);
		} catch (RepositoryGetFailException e) {
			LOGGER.error("OCURRIO UN ERROR AL OBTENER EL REPOSITORIO DE " + destinationName);
			ErrorCode errorCode = ErrorCode.REPOSITORY_GET_FAIL;
			output = new OutputError(errorCode, e);
			LOGGER.error("[ERROR] " + output.getCode() + " - " + output.getMessage(), e);
		} catch (RspcExecuteException e) {
			LOGGER.error("OCURRIO UN ERROR AL EJECUTAR MODULO RSPC");
			ErrorCode errorCode = ErrorCode.getError(e.getAbapException().getKey());
			output = new OutputError(errorCode, e);
			LOGGER.error("[ERROR] " + output.getCode() + " - " + output.getMessage(), e);
		} catch (JCoException e) {
			LOGGER.error("OCURRIO UN ERROR AL OBTENER LOS ATRIBUTOS DE LA CONEXION");
			ErrorCode errorCode = ErrorCode.getError(e.getKey());
			output = new OutputError(errorCode, e);
			LOGGER.error("[ERROR] " + output.getCode() + " - " + output.getMessage(), e);
		} catch (XmiLoginException e) {
			LOGGER.error("OCURRIO UN ERROR AL INICIAR SESION CONTRA XMI");
			ErrorCode errorCode = ErrorCode.XMI_LOGIN_EXCEPTION;
			output = new OutputError(errorCode, e);
			LOGGER.error("[ERROR] " + output.getCode() + " - " + output.getMessage(), e);
		} catch (InvalidConnectionDataException e) {
			LOGGER.error("DATOS DE CONEXION INVALIDOS");
			ErrorCode errorCode = ErrorCode.INSUFFICIENT_CREDENTIALS;
			output = new OutputError(errorCode, e);
			LOGGER.error("[ERROR] " + output.getCode() + " - " + output.getMessage(), e);
		} catch (Throwable e) {
			LOGGER.error("Error fatal", e);
			ErrorCode errorCode = ErrorCode.UNKNOWN;
			output = new OutputError(errorCode.code, "" + e);
		} finally {
			try {
				connector.closeContext();
			} catch (Exception e) {
			}

			/* Si no se detecto ningun errorCode entonces se cierra el programa normalmente evitando usar System#exit */
			if (output.getCode() == ErrorCode.SUCCESS.code)
				return;

			/*
			 * EL CODIGO DE ERROR DEL PROGRAMA SE ESTABLECE AQUI https://stackoverflow .com/questions/887066/how-to-get-the-exit-status
			 * -of-a-java-program-in-windows-batch-file
			 */
			LOGGER.debug(output.getMessage());
			System.out.println(output);
			System.exit(output.getCode());
		}
	}
}
