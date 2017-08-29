package ast.sap.connector.main;

/**
 * @author franco.milanese
 *
 *         Contiene los codigos de error
 */
public enum ErrorCode {
	/**
	 * 0 - Ejecucion exitosa del conector
	 */
	SUCCESS(0, "", false),
	JCO_ERROR_PROGRAM(101, "", true),
	JCO_ERROR_CONFIGURATION(101, "", true),
	/**
	 * 102 - JCO_ERROR_COMMUNICATION - Initialization of destination mainDestination failed: Connect to SAP gateway failed
	 */
	JCO_ERROR_COMMUNICATION(102, "Error al intentar comunicarse con Sap.", true),
	/**
	 * 103 - JCO_ERROR_LOGON_FAILURE - Initialization of destination mainDestination failed: Name or password is incorrect (repeat logon)
	 */
	JCO_ERROR_LOGON_FAILURE(103, "Error al intentar logearse. Verificar nombre de usuario y password.", true), 
	JCO_ERROR_SYSTEM_FAILURE(104, "", true), 
	JCO_ERROR_APPLICATION_EXCEPTION(105, "", true), 
	JCO_ERROR_RESOURCE(106, "", true),
	JCO_ERROR_PROTOCOL(107, "", true), 
	JCO_ERROR_INTERNAL(108, "", true), 
	JCO_ERROR_CANCELLED(109, "", true),
	JCO_ERROR_STATE_BUSY(110, "", true),
	JCO_ERROR_ABAP_CLASS_EXCEPTION(111, "", true),
	JCO_ERROR_REQUEST_CANCELLED(112, "", true),
	JCO_ERROR_REGISTRATION_DENIED(113, "", true),
	JCO_ERROR_EXTENSION(120, "", true),
	JCO_ERROR_NULL_HANDLE(121, "", true),
	JCO_ERROR_CONVERSION(122, "", true),
	JCO_ERROR_FUNCTION_NOT_FOUND(123, "", true),
	JCO_ERROR_ILLEGAL_TID(124, "", true), 
	JCO_ERROR_UNSUPPORTED_CODEPAGE(125, "", true), 
	JCO_ERROR_ABAP_EXCEPTION(126, "", true), 
	JCO_ERROR_FIELD_NOT_FOUND(127, "", true), 
	JCO_ERROR_NOT_SUPPORTED(128, "", true), 
	JCO_ERROR_SERVER_STARTUP(129, "", true), 
	JCO_ERROR_XML_PARSER(130, "", true), 
	JCO_ERROR_ILLEGAL_ARGUMENT(131, "", true), 
	JCO_ERROR_CONCURRENT_CALL(132, "", true), 
	JCO_ERROR_INVALID_HANDLE(133, "", true), 
	JCO_ERROR_INITIALIZATION(134, "", true), 
	JCO_ERROR_TIMEOUT(135, "", true), 
	JCO_ERROR_ILLEGAL_STATE(136, "", true), 
	JCO_ERROR_CONTEXT_NOT_FOUND(137, "", true), 
	JCO_ERROR_PASSWORD_CHANGE_REQUIRED(138, "", true), 
	JCO_ERROR_DSR_LOAD_ERROR(150, "", true), 
	JCO_ERROR_DSR_PASSPORT_NOT_RECEIVED(151, "", true), 
	JCO_ERROR_DSR_PASSPORT_NOT_VALID(152, "", true), 
	JCO_ERROR_SESSION_REF_NOT_VALID(153, "", true), 
	JCO_ERROR_JARM_LOAD_ERROR(155, "", true),
	JCO_ERROR_DATA_PROVIDER_ERROR(156, "", true),
	JCO_ERROR_SHAREABLE_CONTAINER(160, "", true),
	JCO_ERROR_CREATE_SESSION(170, "", true), 
	JCO_ERROR_PASSIVATE_SESSION(171, "", true),
	JCO_ERROR_RESTORE_SESSION(172, "", true), 
	JCO_ERROR_DESTROY_SESSION(173, "", true), 
	JCO_ERROR_DESTINATION_DATA_INVALID(180, "", true),
	JCO_ERROR_SERVER_DATA_INVALID(181, "", true), 
	JCO_ERROR_INVALID_REPOSITORY_CACHE(190, "", true),
	JCO_ERROR_REPOSITORY_SERIALIZATION(191, "", true),
	/**
	 * 700 - BAPI_XMI_LOGON - Error al iniciar sesion con XMI
	 */
	XMI_LOGIN_EXCEPTION(700, "Error al iniciar sesion con XMI.", false),
	/**
	 * 701 - Error al iniciar sesion de sap u obtener el respositorio del destino
	 */
	REPOSITORY_GET_FAIL(701, "Error al iniciar sesion de sap u obtener el respositorio del destino.", false),
	/**
	 * 999 - UNKNOWN - Error no desconocido
	 */
	ERROR_JOB_STATUS(702, "Ocurrio un error interno de sap al ejecutar la funcion", false),
	UNKNOWN(999, "Error desconocido o no configurado.", false);

	public static final int JCO_ERROR_OFFSET = 1000;
	private final int code;
	public final String message;
	private final Boolean isFatal;

	private ErrorCode(int code, String message, Boolean isFatal) {
		this.code = code;
		this.message = message;
		this.isFatal = isFatal;

	}

	/**
	 * @param code
	 * @return Error a partir del codigo recibido
	 */
	public static ErrorCode fromCode(int code) {
		ErrorCode[] errorCodes = ErrorCode.values();
		for (ErrorCode errorCode : errorCodes) {
			if (errorCode.code == code) {
				return errorCode;
			}
		}

		return UNKNOWN;
	}

	/**
	 * @param code
	 * @return Error a partir del nombre de la excepcion
	 */
	public static ErrorCode fromName(String key) {
		ErrorCode[] errorCodes = ErrorCode.values();
		for (ErrorCode errorCode : errorCodes) {
			if (errorCode.name().equals(key)) {
				return errorCode;
			}
		}
		return UNKNOWN;
	}

	public int getCode() {
		if (isFatal) {
			return JCO_ERROR_OFFSET + code;
		} else {
			return code;
		}
	}

}
