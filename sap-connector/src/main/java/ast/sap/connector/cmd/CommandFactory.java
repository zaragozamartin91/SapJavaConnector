package ast.sap.connector.cmd;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Optional;

import ast.sap.connector.cmd.impl.ChangeVariantCommand;
import ast.sap.connector.cmd.impl.CreateJobCommand;
import ast.sap.connector.cmd.impl.CreateMonitorJobCommand;
import ast.sap.connector.cmd.impl.CreateRunJobCommand;
import ast.sap.connector.cmd.impl.CrystalGetuserlistCommand;
import ast.sap.connector.cmd.impl.GetChainErrorsCommand;
import ast.sap.connector.cmd.impl.GetChainLogCommand;
import ast.sap.connector.cmd.impl.GetJobOutputCommand;
import ast.sap.connector.cmd.impl.HelpCommand;
import ast.sap.connector.cmd.impl.JobCountCommand;
import ast.sap.connector.cmd.impl.MonitorJobCommand;
import ast.sap.connector.cmd.impl.RaiseEventCommand;
import ast.sap.connector.cmd.impl.ReadJobCommand;
import ast.sap.connector.cmd.impl.ReadJobDefinitionCommand;
import ast.sap.connector.cmd.impl.ReadSpoolCommand;
import ast.sap.connector.cmd.impl.RunAndStopJobCommand;
import ast.sap.connector.cmd.impl.RunJobCommand;
import ast.sap.connector.cmd.impl.StartChainCommand;
import ast.sap.connector.cmd.impl.StopJobCommand;
import ast.sap.connector.cmd.impl.TrackJobCommand;
import ast.sap.connector.cmd.impl.UserGetDetailCommand;
import ast.sap.connector.cmd.impl.XmiLoginCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.job.create.StepVariantPair;
import ast.sap.connector.job.create.StepVariantValuesTuple;
import ast.sap.connector.job.variant.VariantKeyValuePair;
import ast.sap.connector.main.args.InputArgumentsData;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Generador de comandos de sap ejecutables.
 * 
 * @author martin.zaragoza
 *
 */
public enum CommandFactory {
	INSTANCE;

	/**
	 * Obtiene un comando de sap a ejecutar a partir de los argumentos de entrada del programa y el repositorio de funciones de sap.
	 * 
	 * @param inputArguments
	 *            - Argumentos de entrada del conector sap.
	 * @param sapRepository
	 *            - Repositorio de funciones de sap inicializado.
	 * @return Comando de sap listo para ser ejecutado.
	 */
	public SapCommand getCommand(InputArgumentsData inputArguments, SapRepository sapRepository) {
		XmiLoginData xmiLoginData = inputArguments.newXmiLoginData();
		JobRunData jobData = inputArguments.newJobRunData();

		String program = inputArguments.getSingleStep();
		String variant = inputArguments.getSingleVariant();
		List<VariantKeyValuePair> variantValuePairs = inputArguments.getVariantKeyValuePairs();

		String strCmd = Optional.fromNullable(inputArguments.getCommand()).or("HELP");

		switch (strCmd) {
			case "XMI_LOGIN":
				return new XmiLoginCommand(sapRepository, xmiLoginData);
			case "TRACK_JOB":
				return new TrackJobCommand(sapRepository, xmiLoginData, jobData);
			case "RUN_JOB":
				return new RunJobCommand(sapRepository, xmiLoginData, jobData);
			case "CREATE_JOB":
				StepVariantPair step = new StepVariantPair(program, variant);
				return new CreateJobCommand(sapRepository, xmiLoginData, jobData, Collections.singletonList(step));
			case "USER_GET_DETAIL":
				return new UserGetDetailCommand(sapRepository, jobData.getExternalUsername());
			case "STOP_JOB":
				return new StopJobCommand(sapRepository, xmiLoginData, jobData);
			case "RAISE_EVENT":
				return new RaiseEventCommand(sapRepository, xmiLoginData, jobData, inputArguments.getEventId());
			case "CRYSTAL_GETUSERLIST":
				return new CrystalGetuserlistCommand(sapRepository);
			case "GET_JOB_OUTPUT":
				return new GetJobOutputCommand(sapRepository, xmiLoginData, jobData);
			case "MONITOR_JOB":
				return new MonitorJobCommand(sapRepository, xmiLoginData, jobData);
			case "RUN_STOP_JOB":
				return new RunAndStopJobCommand(sapRepository, xmiLoginData, jobData);
			case "READ_SPOOL":
				return new ReadSpoolCommand(sapRepository, xmiLoginData, jobData);
			case "READ_JOB_DEFINITION":
				return new ReadJobDefinitionCommand(sapRepository, xmiLoginData, jobData);
			case "JOB_COUNT":
				return new JobCountCommand(sapRepository, xmiLoginData, jobData);
			case "READ_JOB":
				return new ReadJobCommand(sapRepository, xmiLoginData, jobData);
			// case "MODIFY_HEADER":
			// return new ModifyHeaderCommand(sapRepository, xmiLoginData, jobData);
			case "CHANGE_VARIANT":
				return new ChangeVariantCommand(sapRepository, xmiLoginData, jobData, inputArguments.getSingleStep());
			case "CREATE_RUN_JOB":
				StepVariantValuesTuple stepVariantValuesTuple = new StepVariantValuesTuple(program, variant, variantValuePairs);
				return new CreateRunJobCommand(sapRepository, xmiLoginData, jobData, stepVariantValuesTuple);
			case "CREATE_MONITOR_JOB":
				StepVariantValuesTuple stepVariantValuesTuple2 = new StepVariantValuesTuple(program, variant, variantValuePairs);
				return new CreateMonitorJobCommand(sapRepository, xmiLoginData, jobData, stepVariantValuesTuple2);
			case "START_CHAIN":
				return new StartChainCommand(sapRepository, xmiLoginData, inputArguments.getJobName());
			case "GET_CHAIN_LOG":
				return new GetChainLogCommand(sapRepository, xmiLoginData, inputArguments.getJobName(), "5AXYTHRJH6U17VCATZEL3BR3U");
			case "GET_CHAIN_ERRORS":
				return new GetChainErrorsCommand(sapRepository, xmiLoginData, inputArguments.getJobName(), "5AXYTHRJH6U17VCATZEL3BR3U");
			default:
				return new HelpCommand();
		}
	}
}