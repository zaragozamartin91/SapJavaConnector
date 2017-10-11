package ast.sap.connector.main;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.log.JobLog;
import ast.sap.connector.job.track.JobFullStatus;
import ast.sap.connector.job.track.JobStatus;
import ast.sap.connector.job.track.JobStatusCode;

public class OutputParserTest {

	@Test
	public void testParseOutputBapiRet() {
		SapBapiret2 bapiret2 = mock(SapBapiret2.class);
		when(bapiret2.getNumber()).thenReturn(123);
		when(bapiret2.getMessage()).thenReturn("PRUEBA OUTPUT PARSER");
		
		SapCommandResult sapCommandResult = new SapCommandResult(bapiret2);
		OutputError parsedOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
		
		assertEquals(bapiret2.getMessage(), parsedOutput.getMessage());
		assertEquals(bapiret2.getNumber(), parsedOutput.getCode());

	}

	@Test
	public void testParseOutputStatus() {
		JobFullStatus jobFullStatus = mock(JobFullStatus.class);
		JobStatus jobStatus = mock(JobStatus.class);
		JobLog jobLog = mock(JobLog.class);
		
		when(jobStatus.getStatusCode()).thenReturn(JobStatusCode.Z);
		when(jobFullStatus.getJobStatus()).thenReturn(jobStatus);
		when(jobFullStatus.getJobLog()).thenReturn(jobLog);
		
		SapCommandResult sapCommandResult = new SapCommandResult(jobFullStatus);
		OutputError parseOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
		assertEquals(ErrorCode.UNFINISHED_JOB.code, parseOutput.getCode());
		assertEquals(ErrorCode.UNFINISHED_JOB.message, parseOutput.getMessage());
	}

	@Test
	public void testParseOutputMessage() {
		SapCommandResult sapCommandResult = new SapCommandResult("PRUEBA");
		OutputError parseOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
		assertEquals(ErrorCode.UNKNOWN.code, parseOutput.getCode());
	}

}
