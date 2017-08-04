package ast.sap.connector.cmd;

import ast.sap.connector.cmd.impl.CreateJobCommand;
import ast.sap.connector.cmd.impl.CrystalGetuserlistCommand;
import ast.sap.connector.cmd.impl.GetJobOutputCommand;
import ast.sap.connector.cmd.impl.HelpCommand;
import ast.sap.connector.cmd.impl.RaiseEventCommand;
import ast.sap.connector.cmd.impl.RunJobCommand;
import ast.sap.connector.cmd.impl.StopJobCommand;
import ast.sap.connector.cmd.impl.TrackJobCommand;
import ast.sap.connector.cmd.impl.UserGetDetailCommand;
import ast.sap.connector.cmd.impl.XmiLoginCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.main.args.InputArgumentsData;
import ast.sap.connector.xmi.XmiLoginData;

import com.google.common.base.Optional;

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

		String strCmd = Optional.fromNullable(inputArguments.getCommand()).or("HELP");

		switch (strCmd) {
			case "XMI_LOGIN":
				return new XmiLoginCommand(sapRepository, xmiLoginData);
			case "TRACK_JOB":
				return new TrackJobCommand(sapRepository, xmiLoginData, jobData);
			case "RUN_JOB":
				return new RunJobCommand(sapRepository, xmiLoginData, jobData);
			case "CREATE_JOB":
				return new CreateJobCommand(sapRepository, xmiLoginData, jobData);
			case "USER_GET_DETAIL":
				return new UserGetDetailCommand(sapRepository, jobData.getExternalUsername());
			case "STOP_JOB":
				return new StopJobCommand(sapRepository, xmiLoginData, jobData);
			case "RAISE_EVENT":
				return new RaiseEventCommand(sapRepository, xmiLoginData, inputArguments.getEventId(), inputArguments.getUser());
			case "CRYSTAL_GETUSERLIST":
				return new CrystalGetuserlistCommand(sapRepository);
			case "GET_JOB_OUTPUT":
				return new GetJobOutputCommand(sapRepository, xmiLoginData, jobData);

			default:
				return new HelpCommand();
		}
	}
}