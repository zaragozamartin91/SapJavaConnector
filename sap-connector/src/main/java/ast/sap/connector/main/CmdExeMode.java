package ast.sap.connector.main;

public enum CmdExeMode {
	EXECUTE("X"), MONITOR("M");

	public final String code;

	private CmdExeMode(String code) {
		this.code = code;
	}

	public static CmdExeMode fromCode(final String code) {
		for (CmdExeMode mode : CmdExeMode.values()) {
			if(mode.code.equalsIgnoreCase(code)) {
				return mode;
			}
		}
		
		return MONITOR;
	}
}
