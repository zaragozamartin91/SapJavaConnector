package ast.sap.connector.main;

import com.google.common.base.Optional;

import ast.sap.connector.cmd.CreateJobCommand;
import ast.sap.connector.cmd.HelpCommand;
import ast.sap.connector.cmd.RunJobCommand;
import ast.sap.connector.cmd.SapCommand;
import ast.sap.connector.cmd.TrackJobCommand;
import ast.sap.connector.cmd.XmiLoginCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.job.RunJobData;
import ast.sap.connector.main.args.InputArgumentsData;
import ast.sap.connector.xmi.XmiLoginData;

public enum CommandFactory {
	INSTANCE;

	/* TODO TERMINAR */
	public SapCommand getCommand(InputArgumentsData inputArguments, SapRepository sapRepository) {
		XmiLoginData xmiLoginData = inputArguments.newXmiLoginData();
		RunJobData jobData = inputArguments.newFullJobData();

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

			default:
				return new HelpCommand();
		}
	}
}
