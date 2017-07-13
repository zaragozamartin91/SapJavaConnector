package ast.sap.connector;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

import ast.sap.connector.conn.ConnectionData;
import ast.sap.connector.conn.util.DestinationConfigBuilder;

public class Jco3Test {
	private static final String DESTINATION_NAME = "testDestination_3";
	private static final File DESTINATION_FILE = new File(DESTINATION_NAME + DestinationConfigBuilder.JCO_DESTINATION_FILE_EXTENSION);

	@Before
	public void before() {
		DESTINATION_FILE.delete();
	}
	
	@After
	public void after() {
		DESTINATION_FILE.delete();
	}

	@Test
	public void testBuildDestination() throws JCoException {
		DestinationConfigBuilder destinationBuilder = new DestinationConfigBuilder();

		ConnectionData connectionData = new ConnectionData("01", "user", "****", "en", "127.0.0.1", "00");
		destinationBuilder.build(DESTINATION_NAME, connectionData);

		JCoDestinationManager.getDestination(DESTINATION_NAME);
	}

}
