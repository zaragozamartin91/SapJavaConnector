package ast.sap.connector.job.track;

public enum StatusCode {
	/*
	 * A - Cancelled
	 * 
	 * F - Completed
	 * 
	 * P - Scheduled
	 * 
	 * R - Active
	 * 
	 * S - Released
	 */
	A("Cancelled"), F("Completed"), P("Scheduled"), R("Active"), S("Released");

	public final String label;

	private StatusCode(String lbl) {
		this.label = lbl;
	}
}
