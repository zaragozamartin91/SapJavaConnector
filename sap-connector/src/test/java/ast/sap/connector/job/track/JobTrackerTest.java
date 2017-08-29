package ast.sap.connector.job.track;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ast.sap.connector.dst.SapDestination;
import ast.sap.connector.dst.SapDestinationFactory;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.dst.exception.RepositoryGetFailException;
import ast.sap.connector.job.JobCreateData;
import ast.sap.connector.job.JobData;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.JobTrackData;
import ast.sap.connector.job.create.JobCreator;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.job.create.StepVariantPair;
import ast.sap.connector.job.run.AsapJobRunner;
import ast.sap.connector.util.DestinationConfigBuilder;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSessionData;
import ast.sap.connector.xmi.XmiSessionManager;

import com.sap.conn.jco.JCoException;

public class JobTrackerTest {

	private static String username;
	private static final String JOB_NAME = "TEST_JOB_TRACKER";
	private static final String DESTINATION_NAME = "testDestination";
	private static XmiSessionData xmiSessionData;
	private static SapRepository repository;
	private static Properties destinationProperties;

	@BeforeClass
	public static void before() throws JCoException, RepositoryGetFailException, FileNotFoundException, IOException {
		SapDestination destination = SapDestinationFactory.INSTANCE.getDestination(DESTINATION_NAME);
		destination.getAttributes();
		repository = destination.openContext();
		XmiLoginData xmiLoginData = new XmiLoginData();
		xmiSessionData = XmiSessionManager.INSTANCE.login(repository, xmiLoginData);		
		destinationProperties = DestinationConfigBuilder.INSTANCE.getJcoDestinationProperties(DESTINATION_NAME);
		username = destinationProperties.getProperty("jco.client.user");
	}

	@Test
	public void testGetStatus() throws InterruptedException {
		JobCreator jobCreator = new JobCreator(repository);
		JobCreateData jobData = JobData.newJobCreateData(JOB_NAME, username);
		NewJobData dummyJob = jobCreator.createJob(jobData, Collections.singletonList(new StepVariantPair("SHOWCOLO")));
		String jobCount = dummyJob.getJobCount();
		JobRunData jobRunData = JobData.newJobRunData(JOB_NAME, username, jobCount);
		AsapJobRunner runner = new AsapJobRunner(repository);
		runner.runJob(jobRunData);
		JobTrackData jobTrackData = JobData.newJobTrackData(JOB_NAME, username, jobCount);
		JobTracker jobTracker = new JobTracker(repository);
		System.out.println("Status before sleep: "+jobTracker.getStatus(jobTrackData));
		Thread.sleep(1000);
		JobStatus jobStatus = jobTracker.getStatus(jobTrackData);
		System.out.println("Status after sleep: "+jobTracker.getStatus(jobTrackData));
		Assert.assertTrue(jobStatus.getStatusCode().hasFinished());
		
		
	}

	
	@AfterClass
	public static void after(){
		XmiSessionManager.INSTANCE.logout(repository, xmiSessionData);
	}
}
