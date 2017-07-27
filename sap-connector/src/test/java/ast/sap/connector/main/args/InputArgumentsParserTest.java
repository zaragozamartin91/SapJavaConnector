package ast.sap.connector.main.args;

import static org.junit.Assert.*;

import org.junit.Test;

public class InputArgumentsParserTest {
	@Test
	public void testParseFull() {
		String[] args = {
				"-n500",
				"-cRUN_JOB",
				"-h127.0.0.1",
				"-jXBP_TEST_1",
				"-pJulio2017",
				"-s01",
				"-umartin.zaragoza",
				"-i14201001",
				"-xSERVER" };
		InputArgumentsData inputArgs = InputArgumentsParser.INSTANCE.parse(args);
		assertEquals("500", inputArgs.getClientNumber());
		assertEquals("RUN_JOB", inputArgs.getCommand());
		assertEquals("127.0.0.1", inputArgs.getHost());
		assertEquals("XBP_TEST_1", inputArgs.getJobName());
		assertEquals("Julio2017", inputArgs.getPassword());
		assertEquals("01", inputArgs.getSystemNumber());
		assertEquals("martin.zaragoza", inputArgs.getUser());
		assertEquals("14201001", inputArgs.getJobId());
		assertEquals("SERVER", inputArgs.getExecServer());
	}
}
