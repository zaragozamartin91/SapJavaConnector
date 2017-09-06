package ast.sap.connector.job.track;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.job.JobData;
import ast.sap.connector.job.JobRunData;

public class JobTrackerTest {
	@Test
	public void testGetStatus() throws InterruptedException {
		String status = "F";

		/* INICIO DE MOCK -------------------------------------------------------------------- */

		SapStruct retMock = mock(SapStruct.class);
		when(retMock.getValue("TYPE")).thenReturn("");

		SapFunctionResult resultMock = mock(SapFunctionResult.class);
		when(resultMock.getStructure("RETURN")).thenReturn(retMock);
		when(resultMock.getOutParameterValue("STATUS")).thenReturn(status);

		SapFunction functionMock = mock(SapFunction.class);
		when(functionMock.execute()).thenReturn(resultMock);
		when(functionMock.setInParameter(anyString(), any())).thenReturn(functionMock);

		SapRepository repoMock = mock(SapRepository.class);
		when(repoMock.getFunction("BAPI_XBP_JOB_STATUS_GET")).thenReturn(functionMock);

		/* FIN DE MOCK -------------------------------------------------------------------- */

		JobTracker jobTracker = new JobTracker(repoMock);
		JobRunData jobData = JobData.newJobRunData("SOME_JOB", "mzaragoz", "123456");
		JobStatus jobStatus = jobTracker.getStatus(jobData);

		assertEquals(StatusCode.F, jobStatus.getStatusCode());
		assertFalse(jobStatus.getReturnStruct().hasError());

		verify(repoMock, times(1)).getFunction("BAPI_XBP_JOB_STATUS_GET");
		verify(functionMock, times(1)).execute();
		verify(functionMock, times(1)).setInParameter("JOBNAME", jobData.getJobName());
		verify(functionMock, times(1)).setInParameter("JOBCOUNT", jobData.getJobId());
		verify(functionMock, times(1)).setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());
		verify(resultMock, times(1)).getOutParameterValue("STATUS");
		verify(resultMock, times(1)).getStructure("RETURN");
	}

}
