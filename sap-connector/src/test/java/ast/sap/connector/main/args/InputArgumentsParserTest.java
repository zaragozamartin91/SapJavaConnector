package ast.sap.connector.main.args;

import static org.junit.Assert.*;

import org.junit.Test;

public class InputArgumentsParserTest {
	@Test
	public void testParseFull() {
		String[] args = {
				"-n", "500",
				"-c", "RUN_JOB",
				"-h", "127.0.0.1",
				"-j", "XBP_TEST_1",
				"-p", "Julio2017",
				"-s", "01",
				"-u", "martin.zaragoza",
				"-i", "14201001",
				"-x", "SERVER",
				"-l", "ES",
				"-t", "SOME_STEP",
				"-v", "SOME_VARIANT" };

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
		assertEquals("ES", inputArgs.getLanguage());
		assertEquals("SOME_STEP", inputArgs.getSingleStep());
		assertEquals("SOME_VARIANT", inputArgs.getSingleVariant());
	}

	@Test(expected = InputArgsParseException.class)
	public void testParseFail() {
		String[] noCommandArgs = {
				"-n", "500",
				"-h", "127.0.0.1",
				"-j", "XBP_TEST_1",
				"-p", "Julio2017",
				"-s", "01",
				"-u", "martin.zaragoza",
				"-i", "14201001",
				"-x", "SERVER",
				"-l", "ES",
				"-t", "SOME_STEP",
				"-v", "SOME_VARIANT" };

		InputArgumentsParser.INSTANCE.parse(noCommandArgs);
	}

	@Test(expected = InputArgsParseException.class)
	public void testParseFailMissingVariantFieldValue() {
		String[] noCommandArgs = {
				"-n", "500",
				"-h", "127.0.0.1",
				"-c", "CREATE_MONITOR_JOB",
				"-j", "XBP_TEST_1",
				"-p", "Julio2017",
				"-s", "01",
				"-u", "martin.zaragoza",
				"-i", "14201001",
				"-t", "SOME_STEP",
				"-v", "SOME_VARIANT",
				"-vk1", "P_FECHA" };

		InputArgumentsParser.INSTANCE.parse(noCommandArgs);
	}

	@Test(expected = InputArgsParseException.class)
	public void testParseFailMissingVariantFieldKey() {
		String[] noCommandArgs = {
				"-n", "500",
				"-h", "127.0.0.1",
				"-c", "CREATE_MONITOR_JOB",
				"-j", "XBP_TEST_1",
				"-p", "Julio2017",
				"-s", "01",
				"-u", "martin.zaragoza",
				"-i", "14201001",
				"-t", "SOME_STEP",
				"-v", "SOME_VARIANT",
				"-vv1", "21/03/1991" };

		InputArgumentsParser.INSTANCE.parse(noCommandArgs);
	}

	@Test
	public void testParseWithVariantFields() {
		String[] args = {
				"-n", "500",
				"-c", "RUN_JOB",
				"-h", "127.0.0.1",
				"-j", "XBP_TEST_1",
				"-p", "Julio2017",
				"-s", "01",
				"-u", "martin.zaragoza",
				"-i", "14201001",
				"-x", "SERVER",
				"-l", "ES",
				"-t", "SOME_STEP",
				"-v", "SOME_VARIANT",
				"-vk1", "P_FECHA",
				"-vv1", "21/03/1991" };

		InputArgumentsParser.INSTANCE.parse(args);
	}

	@Test(expected = InputArgsParseException.class)
	public void testParseFailWrongClientNumber() {
		String[] args = {
				"-n", "ASD",
				"-c", "RUN_JOB" };

		InputArgumentsParser.INSTANCE.parse(args);
	}
}
