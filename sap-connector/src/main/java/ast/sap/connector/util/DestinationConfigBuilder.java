package ast.sap.connector.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.sap.conn.jco.ext.DestinationDataProvider;

public enum DestinationConfigBuilder {
	INSTANCE;
	
	public static final String JCO_DESTINATION_FILE_EXTENSION = ".jcoDestination";

	public File build(String destinationName, ConnectionData connectionData) {
		Properties connectProperties = buildProperties(connectionData);
		return createDestinationDataFile(destinationName, connectProperties);
	}

	public File buildWithPool(String destinationName, ConnectionData connectionData, int poolCapacity, int peakLimit) {
		Properties connectProperties = buildProperties(connectionData);

		connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, Integer.toString(poolCapacity));
		connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, Integer.toString(peakLimit));

		return createDestinationDataFile(destinationName, connectProperties);
	}

	private Properties buildProperties(ConnectionData connectionData) {
		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, connectionData.getHost());
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, connectionData.getSystemNumber());
		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, connectionData.getSapClient());
		connectProperties.setProperty(DestinationDataProvider.JCO_USER, connectionData.getUserId());
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, connectionData.getPassword());
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, connectionData.getLanguage());

		return connectProperties;
	}

	private File createDestinationDataFile(String destinationName, Properties connectProperties) {
		File destCfg = getJcoDestinationFile(destinationName);

		if (destCfg.isDirectory()) {
			throw new IllegalStateException("El archivo " + destCfg.getAbsolutePath() + " es un directorio!");
		}

		try {
			FileOutputStream fos = new FileOutputStream(destCfg, false);
			connectProperties.store(fos, "for tests only !");
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException("Unable to create the destination files", e);
		}
		return destCfg;
	}

	public static File getJcoDestinationFile(String destinationName) {
		return new File(destinationName + JCO_DESTINATION_FILE_EXTENSION);
	}

	public static Properties getJcoDestinationProperties(String destinationName) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		FileInputStream destinationStream = new FileInputStream(getJcoDestinationFile(destinationName));
		properties.load(destinationStream);
		return properties;
	}
}
