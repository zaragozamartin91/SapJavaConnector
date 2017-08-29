package ast.sap.connector.main;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sap.conn.jco.JCoException;

import ast.sap.connector.cmd.SapCommandResult;
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
import ast.sap.connector.job.run.AsapJobRunner;
import ast.sap.connector.xmi.XmiLoginData;
import ast.sap.connector.xmi.XmiSession;

public class OutputParserTest {

	private static SapRepository repository;
	private static XmiSession xmiSession;
	private SapCommandResult sapCommandResult;

	@Before
	public void before() throws JCoException, RepositoryGetFailException {
		SapDestination destination = SapDestinationFactory.INSTANCE.getDestination("testDestination");
		destination.getAttributes();
		repository = destination.openContext();
		XmiLoginData loginData = new XmiLoginData();
		xmiSession = new XmiSession(repository, loginData);
		JobCreator jobDummy = new JobCreator(repository);
		String jobName = "TEST_JOB_RUNNER";
		String externalUsername = "mzaragoz";
		JobCreateData jobCreateData = JobData.newJobCreateData(jobName, externalUsername);
		NewJobData newJob = jobDummy.createJob(jobCreateData, Collections.singletonList(new StepVariantPair("SHOWCOLO")));
		String jobId = newJob.getJobCount();
		JobRunData jobRunData = JobData.newJobRunData(jobName, externalUsername, jobId);
		AsapJobRunner runner = new AsapJobRunner(repository);
		SapBapiret2 bapiRet2 = runner.runJob(jobRunData);
		this.sapCommandResult = new SapCommandResult(bapiRet2);
	}

	@Test
	public void testParseOutput() {
		OutputError parsedOutput = OutputParser.INSTANCE.parseOutput(sapCommandResult);
		Integer expected = 0;
		Integer code = parsedOutput.getCode();
		Assert.assertEquals(expected, code);

	}

	@AfterClass
	public static void after() {
		xmiSession.logout();
	}
}
