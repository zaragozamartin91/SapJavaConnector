package ast.sap.connector.cmd.impl;

import java.util.Collection;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.config.Configuration;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.JobCreateData;
import ast.sap.connector.job.JobData;
import ast.sap.connector.job.create.JobCreator;
import ast.sap.connector.job.create.NewJobData;
import ast.sap.connector.job.create.StepVariantPair;
import ast.sap.connector.job.create.StepVariantValuesTuple;
import ast.sap.connector.job.run.JobRunner;
import ast.sap.connector.job.run.SmartJobRunner;
import ast.sap.connector.job.track.JobFullStatus;
import ast.sap.connector.job.track.JobMonitor;
import ast.sap.connector.job.variant.ChangeVariantData;
import ast.sap.connector.job.variant.VariantChanger;
import ast.sap.connector.job.variant.VariantKeyValuePair;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando para la creacion y corrida y monitoreo de jobs.
 * 
 * @author martin.zaragoza
 *
 */
public class CreateMonitorJobCommand extends SapXmiCommand {
	private final JobCreateData jobData;
	private final StepVariantValuesTuple stepVariantValue;

	public CreateMonitorJobCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, JobCreateData jobData, StepVariantValuesTuple stepVariantValue) {
		super(sapRepository, xmiLoginData);
		this.jobData = jobData;
		this.stepVariantValue = stepVariantValue;
	}

	@Override
	protected SapCommandResult perform() {
		SapRepository repository = repository();

		/* Modifico los valores de una variante */
		StepVariantPair stepVariantPair = stepVariantValue;
		if (stepVariantValue.getVariant().isPresent() && stepVariantValue.getVariantValuePairs().isPresent()) {
			VariantChanger variantChanger = new VariantChanger(repository);
			String program = stepVariantPair.getProgram();
			String variant = stepVariantPair.getVariant().get();
			String externalUsername = jobData.getExternalUsername();
			Collection<VariantKeyValuePair> variantValuePairs = stepVariantValue.getVariantValuePairs().get();
			ChangeVariantData changeVariantData = new ChangeVariantData(program , variant, externalUsername, variantValuePairs);
			
			SapBapiret2 changeVariantRet = variantChanger.changeVariant(changeVariantData);
			if(changeVariantRet.hasError()) return new SapCommandResult(changeVariantRet);
		}

		/* Creo un job */
		JobCreator jobCreator = new JobCreator(repository);
		NewJobData newJobData = jobCreator.createJob(jobData, stepVariantPair);
		if (newJobData.hasError()) return new SapCommandResult(newJobData);
		
		/* Corro el job creado */
		String jobCount = newJobData.getJobCount();
		JobRunner jobRunner = new SmartJobRunner(repository);
		JobData runJobData = JobData.newJobRunData(jobData.getJobName(), jobData.getExternalUsername(), jobCount);
		SapBapiret2 runRet = jobRunner.runJob(runJobData);
		if(runRet.hasError()) return new SapCommandResult(runRet);

		/* Monitoreo el job corriendo */
		JobMonitor jobMonitor = new JobMonitor(repository);
		JobFullStatus monitorRet = jobMonitor.monitorJob(runJobData, Configuration.printContinuously());
		return new SapCommandResult(monitorRet);
	}
}