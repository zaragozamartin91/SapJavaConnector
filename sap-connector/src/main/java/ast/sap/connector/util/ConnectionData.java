package ast.sap.connector.util;

public final class ConnectionData {
	private final String sapClient;
	private final String userId;
	private final String password;
	private final String language;
	private final String host;
	private final String systemNumber;

	public String getSapClient() {
		return sapClient;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getLanguage() {
		return language;
	}

	public String getHost() {
		return host;
	}

	public String getSystemNumber() {
		return systemNumber;
	}

	public ConnectionData(String sapClient, String userId, String password, String language, String host, String systemNumber) {
		this.sapClient = sapClient;
		this.userId = userId;
		this.password = password;
		this.language = language;
		this.host = host;
		this.systemNumber = systemNumber;
	}
}
