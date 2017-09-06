package ast.sap.connector.job.run;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.sap.conn.jco.JCoException;

import ast.sap.connector.dst.SapDestination;
import ast.sap.connector.dst.SapDestinationFactory;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.job.JobCreateData;
import ast.sap.connector.job.JobData;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.create.JobCreator;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.job.create.StepVariantPair;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSession;
import static org.mockito.Mockito.*;

public class AsapJobRunnerTest {
	private static String username;
	private static SapRepository repository;
	private static XmiSession xmiSession;

//	@BeforeClass
//	@Ignore
//	public static void before() throws JCoException, RepositoryGetFailException {
//		SapDestination destination = SapDestinationFactory.INSTANCE.getDestination("testDestination");
//		destination.getAttributes();
//		repository = destination.openContext();
//		XmiLoginData loginData = new XmiLoginData();
//		xmiSession = new XmiSession(repository, loginData);
//	}
//
//	@Test
//	@Ignore
//	public void testRunJob() {
//		JobCreator jobDummy = new JobCreator(repository);
//		String jobName = "TEST_JOB_RUNNER";
//		String externalUsername = username;
//		JobCreateData jobCreateData = JobData.newJobCreateData(jobName, externalUsername);
//		NewJobData newJob = jobDummy.createJob(jobCreateData, Collections.singletonList(new StepVariantPair("SHOWCOLO")));
//
//		String jobId = newJob.getJobCount();
//		JobRunData jobRunData = JobData.newJobRunData(jobName, externalUsername, jobId);
//		AsapJobRunner runner = new AsapJobRunner(repository);
//		SapBapiret2 bapiRet2 = runner.runJob(jobRunData);
//		assertFalse(bapiRet2.hasError());
//	}
//
//	@Test
//	@Ignore
//	public void testRunNonExistingJobId() {
//		JobRunData jobRunData = JobData.newJobRunData("TEST_JOB_RUNNER", "mazaragoz", "1111111");
//		AsapJobRunner runner = new AsapJobRunner(repository);
//		SapBapiret2 bapiRet2 = runner.runJob(jobRunData);
//		System.out.println("testRunNonExistingJobId - Message: " + bapiRet2.getMessage());
//		assertTrue(bapiRet2.hasError());
//	}

	@Test
	public void testMock() {
		SapStruct mockBapiRet = mock(SapStruct.class);
		when(mockBapiRet.getValue("TYPE")).thenReturn("");

		SapFunctionResult mockFunctionResult = mock(SapFunctionResult.class);
		when(mockFunctionResult.getStructure("RETURN")).thenReturn(mockBapiRet);

		SapFunction mockFunction = mock(SapFunction.class);
		when(mockFunction.execute()).thenReturn(mockFunctionResult);

		SapRepository mockRepo = mock(SapRepository.class);
		when(mockRepo.getFunction("BAPI_XBP_JOB_START_ASAP")).thenReturn(mockFunction);

		AsapJobRunner jobRunner = new AsapJobRunner(mockRepo);
		JobRunData jobData = JobData.newJobRunData("SOME_JOB", "mzaragoz", "123456");
		jobRunner.runJob(jobData);
	}

//	@AfterClass
//	@Ignore
//	public static void after() {
//		xmiSession.logout();
//	}

}
