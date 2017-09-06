package ast.sap.connector.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

/**
 * 
 * Configuraciones del componente.
 * 
 * @author franco.milanese
 *
 */
public class Configuration {
	private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
	private static Properties configProperties;

	/**
	 * Carga las configuraciones del componente a partir de un archivo. El archivo debe existir en la ruta raiz del componente.
	 * 
	 * @param fileName
	 *            Nombre del archivo.
	 */
	public static void loadConnectorConfig(String fileName) {
		FileInputStream fileInputStream = null;
		try {
			LOGGER.info("Cargando configuraciones desde {}", new File(fileName).getAbsolutePath());
			configProperties = new Properties();
			fileInputStream = new FileInputStream(fileName);
			configProperties.load(fileInputStream);
		} catch (Exception e) {
			LOGGER.warn("No se pudo cargar el archivo {}. Se utilizarán las configuraciones por defecto.", new File(fileName).getAbsolutePath());
		} finally {
			if (Optional.fromNullable(fileInputStream).isPresent()) {
				try {
					fileInputStream.close();
				} catch (IOException e) {}
			}
		}
	}

	public static String getProperty(String key, String... def) {
		return def.length == 0 ? configProperties.getProperty(key) : configProperties.getProperty(key, def[0]);
	}

	public static String getClientNumber() {
		return getProperty("client_number");
	}

	public static String getUsername() {
		return getProperty("username");
	}

	public static String getPassword() {
		return getProperty("password", "");
	}

	public static String getHost() {
		return getProperty("host");
	}

	public static String getSystemNumber() {
		return getProperty("sys_number");
	}

	/**
	 * Obtiene el formato de fechas que llegaran desde el servidor SAP al hacer una solicitud (como obtener el parametro P_FECHA de una variante).
	 * 
	 * @return formato de fechas que llegaran desde el servidor SAP al hacer una solicitud.
	 */
	public static SimpleDateFormat getSapInDateFormat() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Configuration.getProperty("sap_in_date_format"));
		simpleDateFormat.setLenient(false);
		return simpleDateFormat;
	}

	/**
	 * Obtiene el formato de fecha que hay que utilizar para establecer el valor de un campo de tipo fecha en una variante del server sap.
	 * 
	 * @return formato de fecha que hay que utilizar para establecer el valor de un campo de tipo fecha en una variante del server sap.
	 */
	public static SimpleDateFormat getSapOutDateFormat() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		return simpleDateFormat;
	}

	/**
	 * Obtiene el formato de fecha utilizado para ingresar los valores de campos de variantes a modificar en la linea de comandos al invocar el componente.
	 * 
	 * @return formato de fecha utilizado para ingresar los valores de campos de variantes a modificar en la linea de comandos al invocar el componente.
	 */
	public static SimpleDateFormat getArgsInDateFormat() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Configuration.getProperty("args_in_date_format"));
		simpleDateFormat.setLenient(false);
		return simpleDateFormat;
	}

	/**
	 * Determina si la modalidad de ejecucion con passwords encriptados esta habilitada.
	 * 
	 * @return True en caso de trabajar con passwords encriptados, false en caso contrario.
	 */
	public static boolean encryptionOn() {
		return configProperties.getProperty("encryption_on", "false").equalsIgnoreCase("true");
	}

	/**
	 * Obtiene el maximo numero de intentos de reconexion ante una caida de la sesion con el servidor sap.
	 * 
	 * @return numero de intentos de reconexion ante una caida de la sesion con el servidor sap
	 */
	public static int getReconnectMaxTries() {
		return Integer.parseInt(configProperties.getProperty("reconn_max_tries", "5"));
	}

	/**
	 * Retorna true si el componente esta configurado para que imprima el LOG de un JOB a medida que el mismo es monitoreado. False en caso contrario.
	 * 
	 * @return true si el componente esta configurado para que imprima el LOG de un JOB a medida que el mismo es monitoreado. False en caso contrario.
	 */
	public static boolean printContinuously() {
		return configProperties.getProperty("print_cont", "false").equalsIgnoreCase("true");
	}
}
