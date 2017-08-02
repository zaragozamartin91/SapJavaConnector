package ast.sap.connector.main;

import com.google.common.base.Optional;

import ast.sap.connector.cmd.CompanycodeGetlistCommand;
import ast.sap.connector.cmd.CreateJobCommand;
import ast.sap.connector.cmd.HelpCommand;
import ast.sap.connector.cmd.RaiseEventCommand;
import ast.sap.connector.cmd.RunJobCommand;
import ast.sap.connector.cmd.SapCommand;
import ast.sap.connector.cmd.StopJobCommand;
import ast.sap.connector.cmd.TrackJobCommand;
import ast.sap.connector.cmd.UserGetDetailCommand;
import ast.sap.connector.cmd.XmiLoginCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.JobRunData;
import ast.sap.connector.main.args.InputArgumentsData;
import ast.sap.connector.xmi.XmiLoginData;

public enum CommandFactory {
	INSTANCE;

	/* TODO TERMINAR */
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
			case "COMPANYCODE_GETLIST":
				return new CompanycodeGetlistCommand(sapRepository);

			default:
				return new HelpCommand();
		}
	}
}
