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
	
	public boolean isRunning() {
		return this.equals(R) || this.equals(Z);
	}
	
	public boolean isReleased() {
		return this.equals(S);
	}
	
	public boolean hasFinished() {
		return this.equals(F);
	}
	
	public boolean notFinished() {
		return !hasFinished();
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", this.name(), this.label);
	}
	
	public static void main(String[] args) {
		System.out.println(StatusCode.A);
	}
}
