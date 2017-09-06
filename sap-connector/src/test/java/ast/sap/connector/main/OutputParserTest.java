//package ast.sap.connector.main;
//
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import ast.sap.connector.cmd.SapCommandResult;
//import ast.sap.connector.func.SapBapiret2;
//import ast.sap.connector.job.log.JobLog;
//import ast.sap.connector.job.track.JobFullStatus;
//import ast.sap.connector.job.track.JobStatus;
//
//public class OutputParserTest {
//
//	@Mock
//	static SapBapiret2 bapiret2 = Mockito.mock(SapBapiret2.class);
//
//	@BeforeClass
//	public static void before() {
//		bapiret2.setNumber(123);
//		bapiret2.setMessage("PRUEBA OUTPUT PARSER");
//	}
//
//	@Test
//	public void testParseOutputBapiRet() {
//		SapCommandResult sapCommandResult = new SapCommandResult(bapiret2);
//		OutputError parsedOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
//		Integer expected = bapiret2.getNumber();
//		Integer code = parsedOutput.getCode();
//		Assert.assertEquals(expected, code);
//		Assert.assertEquals(bapiret2.getMessage(), parsedOutput.getMessage());
//
//	}
//
//	@Test
//	public void testParseOutputMonitor() {
//		SapCommandResult sapCommandResult = new SapCommandResult(new JobFullStatus(Mockito.mock(JobLog.class), new JobStatus("Z", null)));
//		OutputError parseOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
//		Assert.assertEquals(ErrorCode.ERROR_JOB_STATUS.getCode(), parseOutput.getCode());
//	}
//
//	@Test
//	public void testParseOutput() {
//		SapCommandResult sapCommandResult = new SapCommandResult("PRUEBA");
//		OutputError parseOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
//		Assert.assertEquals(ErrorCode.UNKNOWN.getCode(), parseOutput.getCode());
//	}
//
//}
