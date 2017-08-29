package ast.sap.connector.job.run;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ast.sap.connector.dst.SapDestination;
import ast.sap.connector.dst.SapDestinationFactory;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.JobCreateData;
import ast.sap.connector.job.JobData;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.create.JobCreator;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.job.create.StepVariantPair;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSession;

import com.sap.conn.jco.JCoException;

public class AsapJobRunnerTest {

	private static SapRepository repository;
	private static XmiSession xmiSession;

	@BeforeClass
	public static void before() throws JCoException, RepositoryGetFailException {
		SapDestination destination = SapDestinationFactory.INSTANCE.getDestination("testDestination");
		destination.getAttributes();
		repository = destination.openContext();
		XmiLoginData loginData = new XmiLoginData();
		xmiSession = new XmiSession(repository, loginData);
	}

	@Test
	public void testRunJob() {
		JobCreator jobDummy = new JobCreator(repository);
		String jobName = "TEST_JOB_RUNNER";
		String externalUsername = "mzaragoz";
		JobCreateData jobCreateData = JobData.newJobCreateData(jobName, externalUsername);
		NewJobData newJob = jobDummy.createJob(jobCreateData, Collections.singletonList(new StepVariantPair("SHOWCOLO")));
		String jobId = newJob.getJobCount();
		JobRunData jobRunData = JobData.newJobRunData(jobName, externalUsername, jobId);
		AsapJobRunner runner = new AsapJobRunner(repository);
		SapBapiret2 bapiRet2 = runner.runJob(jobRunData);
		Assert.assertFalse(bapiRet2.hasError());
	}
	
	@Test
	public void testRunNonExistingJobId() {
		JobRunData jobRunData = JobData.newJobRunData("TEST_JOB_RUNNER", "mazaragoz", "1111111");
		AsapJobRunner runner = new AsapJobRunner(repository);
		SapBapiret2 bapiRet2 = runner.runJob(jobRunData);
		System.out.println("testRunNonExistingJobId - Message: "+bapiRet2.getMessage());
		Assert.assertTrue(bapiRet2.hasError());
	}
	
	
	@AfterClass
	public static void after() {
		xmiSession.logout();
	}

}
