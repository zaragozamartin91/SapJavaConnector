package ast.sap.connector.job.track;

/**
 * Codigos de status de jobs.
 * 
 * @see https://archive.sap.com/discussions/thread/211601
 * @see https://help.sap.com/saphelp_nw70/helpdata/en/c4/3a8016505211d189550000e829fbbd/frameset.htm
 * 
 * @author martin.zaragoza
 *
 */
public enum StatusCode {
	A("Cancelled"), 
	F("Finished"), 
	P("Scheduled"), 
	R("Active"), 
	S("Released"),
	Y("Ready"),
	Z("Active"),
	I("Intercepted"),
	X("Unknown");

	public final String label;

	private StatusCode(String lbl) {
		this.label = lbl;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
}
