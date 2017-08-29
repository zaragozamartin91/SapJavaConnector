package ast.sap.connector.dst;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sap.conn.jco.JCoException;

import ast.sap.connector.dst.exception.RepositoryGetFailException;

public class SapRepositoryTest {
	private static final String DESTINATION_NAME = "testDestination";
	private static SapDestination sapDestination;

	@BeforeClass
	public static void beforeAll() throws JCoException {
		sapDestination = SapDestinationFactory.INSTANCE.getDestination(DESTINATION_NAME);
		sapDestination.getAttributes();
	}

	@Test
	public void testGetFunction() throws RepositoryGetFailException {
		try {
			SapRepository repository = sapDestination.openContext();
			String functionName = "BAPI_XBP_JOB_START_ASAP";
			repository.getFunction(functionName);
		} finally {
			sapDestination.closeContext();
		}
	}
}
