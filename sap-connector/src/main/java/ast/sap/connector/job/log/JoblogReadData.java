package ast.sap.connector.job.log;

import ast.sap.connector.job.BaseJobData;

public class JoblogReadData extends BaseJobData {
	public static enum Direction {
		BEGINNING("B"), END("E");

		public final String code;

		private Direction(String code) {
			this.code = code;
		}
	}

	private int lines = 0;
	private Direction direction = Direction.END;

	public JoblogReadData(String jobName, String jobId, String externalUsername, int lines, Direction direction) {
		super(jobName, jobId, externalUsername);
		this.lines = lines;
		this.direction = direction;
	}

	public JoblogReadData(String jobName, String jobId, String externalUsername, Direction direction) {
		super(jobName, jobId, externalUsername);
		this.direction = direction;
	}

	public JoblogReadData(String jobName, String jobId, String externalUsername, int lines) {
		super(jobName, jobId, externalUsername);
		this.lines = lines;
	}

	public JoblogReadData(String jobName, String jobId, String externalUsername) {
		super(jobName, jobId, externalUsername);
	}

	public int getLines() {
		return lines;
	}

	public Direction getDirection() {
		return direction;
	}
}
