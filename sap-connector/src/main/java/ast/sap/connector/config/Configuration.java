package ast.sap.connector.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.google.common.base.Optional;

import ast.sap.connector.util.DestinationConfigBuilder;

/**
 * TODO: LA CONFIGURACION ACTUAL ES OBSOLETA. TODOS LOS DATOS NECESARIOS PARA CORRER PROVIENEN DE LOS ARGUMENTOS DE ENTRADA
 * DEL COMPONENTE.
 * 
 * @author mzaragoz
 *
 */
public class Configuration {
	public static final Configuration INSTANCE = new Configuration();

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

	public static Configuration newFromJcoDestination(String destinationName) throws FileNotFoundException, IOException {
		FileInputStream destinationInputStream = null;
		try {
			Properties configProperties = new Properties();
			destinationInputStream = new FileInputStream(DestinationConfigBuilder.getJcoDestinationFile(destinationName));
			configProperties.load(destinationInputStream);

			return newFromPropertiesFile(configProperties);
		} finally {
			if (Optional.fromNullable(destinationInputStream).isPresent()) {
				destinationInputStream.close();
			}
		}
	}

	public synchronized static Configuration newFromPropertiesFile(Properties configProperties) {
		INSTANCE.clientNumber = configProperties.getProperty("clientNumber", "500");
		INSTANCE.systemNumber = configProperties.getProperty("systemNumber", "01");
		
		return INSTANCE;
	}
}
