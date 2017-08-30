package ast.sap.connector.xmi;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sap.conn.jco.JCoException;

import ast.sap.connector.dst.SapDestination;
import ast.sap.connector.dst.SapDestinationFactory;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.SapBapiret2;

public class XmiSessionTest {
	private static final String DESTINATION_NAME = "testDestination";
	private static SapRepository repository;

	@BeforeClass
	public static void before() throws JCoException, RepositoryGetFailException, FileNotFoundException, IOException {
		SapDestination destination = SapDestinationFactory.INSTANCE.getDestination(DESTINATION_NAME);
		destination.getAttributes();
		repository = destination.openContext();
	}

	@Test
	public void testXmiSession() {
		XmiSession xmiSession = new XmiSession(repository, new XmiLoginData());
		Object sessionId = xmiSession.getSessionData().getSessionId();
		Assert.assertNotNull(sessionId);
		Assert.assertFalse(sessionId.toString().isEmpty());

		xmiSession.logout();
	}

	@Test
	public void testLogout() {
		XmiSession xmiSession = new XmiSession(repository, new XmiLoginData());
		SapBapiret2 ret = xmiSession.logout();
		Assert.assertFalse(ret.hasError());
	}
}
