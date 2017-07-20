package ast.sap.connector.config;

import java.util.Properties;

public class Configuration {
	private String clientNumber;
	private String systemNumber;

	public String getClientNumber() {
		return clientNumber;
	}

	public String getSystemNumber() {
		return systemNumber;
	}

	private Configuration() {
	}

	public static Configuration loadFromPropertiesFile(Properties configProperties) {
		/* TODO : CARGAR CONFIGURACIONES DESDE ARCHIVO DE PROPERTIES.
		 * SI NO EXISTE UN JCODESTINATION, CREARLO */
		return new Configuration();
	}
}
