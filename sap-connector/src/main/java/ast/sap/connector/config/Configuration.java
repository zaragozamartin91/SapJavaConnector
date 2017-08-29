package ast.sap.connector.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

/**
 * 
 * TODO : AGREGAR UN CAMPO DE CONFIGURACION "date.format" PARA ESTABLECER EL FORMATO DE FECHAS QUE EL COMPONENTE MANEJARA.
 * 
 * @author franco.milanese
 *
 */
public class Configuration {
	private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
	private static Properties configProperties;

	public static void loadConnectorConfig(String fileName) {
		FileInputStream fileInputStream = null;
		try {
			configProperties = new Properties();
			fileInputStream = new FileInputStream(fileName);
			configProperties.load(fileInputStream);
		} catch (Exception e) {
			LOGGER.warn(String.format("[WARNING] No se pudo cargar el archivo %s. Se utilizarán las configuraciones por defecto.", fileName));
		} finally {
			if (Optional.fromNullable(fileInputStream).isPresent()) {
				try {
					fileInputStream.close();
				} catch (IOException e) {}
			}
		}
	}

	public static String getProperty(String key) {
		return configProperties.getProperty(key);
	}

	public static String getUsername() {
		return configProperties.getProperty("username");
	}

	public static String getPassword() {
		return configProperties.getProperty("password");
	}

	/**
	 * Obtiene el formato de fechas que llegaran desde sap al hacer una solicitud (como obtener el parametro P_FECHA de una variante).
	 * 
	 * TODO : DE MANERA PROVISORIA SE RETORNA EL FORMATO DE FECHAS QUE SE VIO EN LAS PRUEBAS DE SAP EN EL BANCO.
	 * 
	 * @return Formato de fechas.
	 */
	public static SimpleDateFormat getDateFormat() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Configuration.getProperty("date_format"));
		return simpleDateFormat;
	}

	public static SimpleDateFormat getOutDateFormat() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
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
		return 3;
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
