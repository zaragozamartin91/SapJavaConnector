package ast.sap.connector.dst;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.util.DestinationConfigBuilder;

import com.sap.conn.jco.JCoAttributes;
import com.sap.conn.jco.JCoException;

public class SapDestinationTest {
	private static final String DESTINATION_NAME = "testDestination";
	private static Properties destinationProperties;

	@BeforeClass
	public static void beforeAll() throws IOException {
		destinationProperties = DestinationConfigBuilder.INSTANCE.getJcoDestinationProperties(DESTINATION_NAME);
	}

	@Test
	public void testOpenContext() throws RepositoryGetFailException {
		SapDestination sapDestination = SapDestinationFactory.INSTANCE.getDestination(DESTINATION_NAME);
		sapDestination.openContext();
		sapDestination.closeContext();
	}

	@Test
	public void testGetAttributes() throws JCoException {
		SapDestination sapDestination = SapDestinationFactory.INSTANCE.getDestination(DESTINATION_NAME);
		JCoAttributes attributes = sapDestination.getAttributes();

		Assert.assertTrue(destinationProperties.getProperty("jco.client.user").equalsIgnoreCase(
				attributes.getUser().trim()));
	}

	@Test
	public void testStatelessRepository() throws RepositoryGetFailException {
		SapDestination sapDestination = SapDestinationFactory.INSTANCE.getDestination(DESTINATION_NAME);
		sapDestination.statelessRepository();
	}
}
