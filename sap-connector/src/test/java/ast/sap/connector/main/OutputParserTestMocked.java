package ast.sap.connector.main;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.track.JobStatus;

public class OutputParserTestMocked {

	@Mock
	SapBapiret2 sapBapiret2;
	
	@Test
	public void testParseOutput(){
		SapCommandResult sapCommandResult = new SapCommandResult(new JobStatus("Z", sapBapiret2));
		OutputError parseOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
		int code = ErrorCode.ERROR_JOB_STATUS.getCode();
		int code2 = parseOutput.getCode();
		Assert.assertEquals(code, code2);
	}


}
