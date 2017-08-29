package ast.sap.connector.util;

/**
 * Datos de conexion para la creacion de archivos de destino de JCO3 con sap.
 * 
 * @see DestinationConfigBuilder
 * 
 * @author martin.zaragoza
 *
 */
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

	/**
	 * Construye un nuevo set de datos de conexion con SAP.
	 * 
	 * @param sapClient
	 *            - Numero de cliente (ej: 500)
	 * @param userId
	 *            - Nombre de usuario (ej: mzaragoz)
	 * @param password
	 *            - Password
	 * @param language
	 *            - Lenguaje del sistema (ej: "EN" para ingles)
	 * @param host
	 *            - Ip del server sap o nombre del host (ej: saphanatest)
	 * @param systemNumber
	 *            - Codigo de numero de sistema o numero de instancia (ej: 01)
	 */
	public ConnectionData(String sapClient, String userId, String password, String language, String host, String systemNumber) {
		this.sapClient = sapClient;
		this.userId = userId;
		this.password = password;
		this.language = language;
		this.host = host;
		this.systemNumber = systemNumber;
	}
}
