package ast.sap.connector.main.args;

import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.ParserProperties;

import com.google.common.base.Preconditions;

import ast.sap.connector.job.variant.VariantKeyValuePair;

/**
 * Parser de los argumentos de entrada que llegan desde linea de comandos.
 * 
 * @author martin.zaragoza
 *
 */
public class InputArgumentsParser {
	public static final InputArgumentsParser INSTANCE = new InputArgumentsParser();

	@Option(name = "-n", aliases = { "--client" }, required = false, usage = "numero de instancia de server sap")
	private String clientNumber;

	@Option(name = "-c", aliases = { "--command" }, required = true, usage = "comando a correr")
	private String command;

	@Option(name = "-e", aliases = { "--event" }, required = false, usage = "event id a disparar", hidden = true)
	private String eventId;

	@Option(name = "-x", aliases = { "--execsrv" }, required = false, usage = "servidor de ejecucion de job", hidden = true)
	private String execServer;

	@Option(name = "-l", aliases = { "--lang" }, required = false, usage = "lenguaje al cual iniciar sesion en sap", hidden = true)
	private String language;

	@Option(name = "-h", aliases = { "--host" }, required = false, usage = "ip/host id del servidor sap")
	private String host;

	@Option(name = "-i", aliases = { "--jobid" }, required = false, usage = "id del job a correr/inspeccionar")
	private String jobId;

	@Option(name = "-j", aliases = { "--jobname" }, required = false, usage = "nombre del job a crear/correr/inspeccionar o nombre de cadena a correr")
	private String jobName;

	@Option(name = "-p", aliases = { "--pass" }, required = false, usage = "password de usuario sap a loguearse/encriptar")
	private String password;

	@Option(name = "-t", aliases = { "--step" }, required = false, usage = "step del job", depends = "-j")
	private String jobStep;

	@Option(name = "-v", aliases = { "--variant" }, required = false, usage = "variante del step del job", depends = "-t")
	private String stepVariant;

	@Option(name = "-s", aliases = { "--sysno" }, required = false, usage = "numero de sistema")
	private String systemNumber;

	@Option(name = "-u", aliases = { "--user" }, required = false, usage = "sap username")
	private String user;

	/* CAMPOS DE VARIANTES --------------------------------------------------------------------------------------------------- */

	@Option(name = "-vk1", required = false, usage = "clave de 1er campo de variante a modificar", depends = { "-v", "-vv1" })
	private String variantFieldKey1;

	@Option(name = "-vv1", required = false, usage = "valor de 1er campo de variante a modificar", depends = "-vk1")
	private String variantFieldValue1;

	@Option(name = "-vk2", required = false, usage = "clave de 2do campo de variante a modificar", depends = { "-v", "-vv2" })
	private String variantFieldKey2;

	@Option(name = "-vv2", required = false, usage = "valor de 2do campo de variante a modificar", depends = "-vk2")
	private String variantFieldValue2;

	@Option(name = "-vk3", required = false, usage = "clave de 3ero campo de variante a modificar", depends = { "-v", "-vv3" })
	private String variantFieldKey3;

	@Option(name = "-vv3", required = false, usage = "valor de 3ero campo de variante a modificar", depends = "-vk3")
	private String variantFieldValue3;

	/* ---------------------------------------------------------------------------------------------------------------------- */

	private InputArgumentsParser() {
		setDefaults();
	}

	/**
	 * Parsea los argumentos de entrada de la aplicacion y obtiene informacion a partir de los mismos.
	 * 
	 * @param args
	 *            Argumentos de entrada de la aplicacion.
	 * @return Informacion de los argumentos obtenidos
	 */
	public InputArgumentsData parse(String[] args) throws InputArgsParseException {
		InputArgumentsParser dataHolder = new InputArgumentsParser();
		CmdLineParser parser = new CmdLineParser(dataHolder);
		ParserProperties.defaults().withUsageWidth(160);
		try {
			parser.parseArgument(args);
			dataHolder.validate();
			return dataHolder.buildArgsData();
		} catch (IllegalStateException e) {
			throw new InputArgsParseException(e);
		} catch (CmdLineException e) {
			dataHolder.setDefaults();
			parser.printUsage(System.err);
			throw new InputArgsParseException(e);
		}
	}

	private void validate() throws IllegalStateException {
		Preconditions.checkState(clientNumber.matches("\\d+"), "El valor de numero de cliente no es numerico!");
		Preconditions.checkState(systemNumber.matches("\\d+"), "El valor de numero de sistema no es numerico!");
	}

	private void setDefaults() {
		clientNumber = null;
		command = null;
		eventId = null;
		execServer = null;
		language = "EN";
		host = null;
		jobId = null;
		jobName = "CONN_JOB";
		password = null;
		jobStep = null;
		stepVariant = null;
		systemNumber = null;
		user = null;

		variantFieldKey1 = null;
		variantFieldValue1 = null;
		variantFieldKey2 = null;
		variantFieldValue2 = null;
		variantFieldKey3 = null;
		variantFieldValue3 = null;
	}

	private InputArgumentsData buildArgsData() {
		InputArgumentsData argsData = new InputArgumentsData()
				.setClientNumber(clientNumber)
				.setCommand(command)
				.setEventId(eventId)
				.setExecServer(execServer)
				.setLanguage(language)
				.setHost(host)
				.setJobId(jobId)
				.setJobName(jobName)
				.setPassword(password)
				.setSingleStep(jobStep)
				.setSingleVariant(stepVariant)
				.setSystemNumber(systemNumber)
				.setUser(user);

		List<VariantKeyValuePair> variantKeyValuePairs = new ArrayList<>();
		if (variantFieldKey1 != null) {
			variantKeyValuePairs.add(new VariantKeyValuePair(variantFieldKey1, variantFieldValue1));
		}
		if (variantFieldKey2 != null) {
			variantKeyValuePairs.add(new VariantKeyValuePair(variantFieldKey2, variantFieldValue2));
		}
		if (variantFieldKey3 != null) {
			variantKeyValuePairs.add(new VariantKeyValuePair(variantFieldKey3, variantFieldValue3));
		}
		return argsData.setVariantKeyValuePairs(variantKeyValuePairs);
	}
}
