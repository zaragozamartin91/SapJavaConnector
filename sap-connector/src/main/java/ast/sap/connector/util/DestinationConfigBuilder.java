package ast.sap.connector.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import com.sap.conn.jco.ext.DestinationDataProvider;

import ast.sap.connector.dst.ConnectionData;

public class DestinationConfigBuilder {
	private static final File CURRENT_DIR = new File("");
	public static final String JCO_DESTINATION_FILE_EXTENSION = ".jcoDestination";

	private final File parentDir;

	public DestinationConfigBuilder(File parentDir) {
		this.parentDir = parentDir;
	}

	public DestinationConfigBuilder() {
		this(CURRENT_DIR);
	}

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
		File destCfg = parentDir.equals(CURRENT_DIR) ? new File(destinationName + JCO_DESTINATION_FILE_EXTENSION)
				: new File(parentDir, destinationName + JCO_DESTINATION_FILE_EXTENSION);
		try {
			FileOutputStream fos = new FileOutputStream(destCfg, false);
			connectProperties.store(fos, "for tests only !");
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException("Unable to create the destination files", e);
		}
		return destCfg;
	}
}
