package ast.sap.connector;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sap.conn.jco.JCoException;

import ast.sap.connector.dst.SapDestination;
import ast.sap.connector.dst.SapDestinationFactory;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.JobCreateData;
import ast.sap.connector.job.JobData;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.JobTrackData;
import ast.sap.connector.job.create.JobCreator;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.job.create.StepVariantPair;
import ast.sap.connector.job.log.JoblogReadData;
import ast.sap.connector.job.log.JoblogReader;
import ast.sap.connector.job.run.AsapJobRunner;
import ast.sap.connector.job.track.JobStatus;
import ast.sap.connector.job.track.JobTracker;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSession;

/**
 * Tests de rutina a realizar para verificar el impacto de los cambios. Estos tests dependen del servidor sap sobre el cual se haran pruebas.
 * 
 * @author martin.zaragoza
 *
 */
public class RoutineTest {
	private static final String JOB_NAME = "TEST_JOB_TRACKER";
	private static String username;
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
		System.out.println("testRunNonExistingJobId - Message: " + bapiRet2.getMessage());
		Assert.assertTrue(bapiRet2.hasError());
	}

	@Test
	public void testGetStatus() throws InterruptedException {
		JobCreator jobCreator = new JobCreator(repository);
		JobCreateData jobData = JobData.newJobCreateData(JOB_NAME, username);
		NewJobData dummyJob = jobCreator.createJob(jobData, Collections.singletonList(new StepVariantPair("SHOWCOLO")));

		String jobCount = dummyJob.getJobCount();
		JobRunData jobRunData = JobData.newJobRunData(JOB_NAME, username, jobCount);

		AsapJobRunner runner = new AsapJobRunner(repository);
		SapBapiret2 runRet = runner.runJob(jobRunData);
		assertFalse(runRet.hasError());

		JobTrackData jobTrackData = JobData.newJobTrackData(JOB_NAME, username, jobCount);
		JobTracker jobTracker = new JobTracker(repository);
		System.out.println("Status before sleep: " + jobTracker.getStatus(jobTrackData));
		Thread.sleep(1000);

		JobStatus jobStatus = jobTracker.getStatus(jobTrackData);
		System.out.println("Status after sleep: " + jobTracker.getStatus(jobTrackData));
		assertTrue(jobStatus.getStatusCode().hasFinished());
	}

	@Test
	public void testJobMonitor() throws InterruptedException {
		JobCreator jobDummy = new JobCreator(repository);
		String jobName = "TEST_JOB_MONITOR";
		String externalUsername = username;
		JobCreateData jobCreateData = JobData.newJobCreateData(jobName, externalUsername);
		NewJobData newJob = jobDummy.createJob(jobCreateData, Collections.singletonList(new StepVariantPair("ZTEST_WAIT_5_SECONDS")));

		String jobId = newJob.getJobCount();
		JobRunData jobRunData = JobData.newJobRunData(jobName, externalUsername, jobId);
		AsapJobRunner runner = new AsapJobRunner(repository);
		SapBapiret2 bapiRet2 = runner.runJob(jobRunData);
		assertFalse(bapiRet2.hasError());

		Thread.sleep(1000);
		JobTrackData jobTrackData = JobData.newJobTrackData(jobName, username, jobId);
		JobTracker jobTracker = new JobTracker(repository);
		assertTrue(jobTracker.getStatus(jobTrackData).getStatusCode().isRunning());

		Thread.sleep(5000);
		assertTrue(jobTracker.getStatus(jobTrackData).getStatusCode().hasFinished());
	}

	@Test
	public void testReadLog() throws InterruptedException {
		JobCreator jobCreator = new JobCreator(repository);
		String jobName = "TEST_READ_LOG";
		JobCreateData jobData = JobData.newJobCreateData(jobName, username);
		NewJobData dummyJob = jobCreator.createJob(jobData, Collections.singletonList(new StepVariantPair("SHOWCOLO")));

		String jobCount = dummyJob.getJobCount();
		JobRunData jobRunData = JobData.newJobRunData(jobName, username, jobCount);

		AsapJobRunner runner = new AsapJobRunner(repository);
		SapBapiret2 runRet = runner.runJob(jobRunData);
		assertFalse(runRet.hasError());

		Thread.sleep(1000);
		JoblogReader joblogReader = new JoblogReader(repository);
		JoblogReadData jobLogReadData = new JoblogReadData(jobName, jobCount, username);
		;
		joblogReader.readLog(jobLogReadData);
	}

	@AfterClass
	public static void after() {
		xmiSession.logout();
	}
}
