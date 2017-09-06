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
	SUCCESS(0, ""),
	JCO_ERROR_PROGRAM(1101, "Ocurrio un error general del programa."),
	JCO_ERROR_CONFIGURATION(1101, ""),
	/**
	 * 102 - JCO_ERROR_COMMUNICATION - Initialization of destination mainDestination failed: Connect to SAP gateway failed
	 */
	JCO_ERROR_COMMUNICATION(1102, "Error al intentar comunicarse con el sistema Sap."),
	/**
	 * 103 - JCO_ERROR_LOGON_FAILURE - Initialization of destination mainDestination failed: Name or password is incorrect (repeat logon)
	 */
	JCO_ERROR_LOGON_FAILURE(1103, "Error al intentar logearse. Verificar nombre de usuario y password."),
	JCO_ERROR_SYSTEM_FAILURE(1104, "Occurio una falla en el sistema Sap."),
	JCO_ERROR_APPLICATION_EXCEPTION(1105, "Ocurrió una excepcion de la aplicacion en el sistema Sap."),
	JCO_ERROR_RESOURCE(1106, "Jco se ha quedado sin recursos para realizar la ejecucion."),
	JCO_ERROR_PROTOCOL(1107, "Se ha detectado un error en el protocolo interno de comunicacion."),
	JCO_ERROR_INTERNAL(1108, "Ocurrio una excepcion interna de Jco."),
	JCO_ERROR_CANCELLED(1109, "Un servidor Jco registrado ha sido cancelado."),
	JCO_ERROR_STATE_BUSY(1110, "El sistema Sap esta ocupado. Intente nuevamente."),
	JCO_ERROR_ABAP_CLASS_EXCEPTION(1111, ""),
	JCO_ERROR_REQUEST_CANCELLED(1112, ""),
	JCO_ERROR_REGISTRATION_DENIED(1113, ""),
	JCO_ERROR_EXTENSION(1120, ""),
	JCO_ERROR_NULL_HANDLE(1121, ""),
	JCO_ERROR_CONVERSION(1122, "Fallo una conversion entre dos representaciones de un parametro, estructura o tabla."),
	JCO_ERROR_FUNCTION_NOT_FOUND(1123, "Una funcion o una de sus estructuras no ha podido ser obtenida del repositorio."),
	JCO_ERROR_ILLEGAL_TID(1124, "Se ha detectado un Id de transaccion invalida. Tiene una longitud mayor a 24 o contiene caracteres invalidos."),
	JCO_ERROR_UNSUPPORTED_CODEPAGE(1125, "El codepage del sistema Sap o el local, no es soportado por Jco."),
	JCO_ERROR_ABAP_EXCEPTION(1126, "Una excepcion ha sido lanzada por un RFM."),
	JCO_ERROR_FIELD_NOT_FOUND(1127, "Una referencia a un parametro, estructura o tabla no existe."),
	JCO_ERROR_NOT_SUPPORTED(1128, "La caracteristica no es soportada por la actual version de Jco."),
	JCO_ERROR_SERVER_STARTUP(1129, "Fallo el inicio del servidor Jco."),
	JCO_ERROR_XML_PARSER(1130, "Error de parseo debido a un documento XML invalido."),
	JCO_ERROR_ILLEGAL_ARGUMENT(1131, ""),
	JCO_ERROR_CONCURRENT_CALL(1132, ""),
	JCO_ERROR_INVALID_HANDLE(1133, ""),
	JCO_ERROR_INITIALIZATION(1134, ""),
	JCO_ERROR_TIMEOUT(1135, ""),
	JCO_ERROR_ILLEGAL_STATE(1136, ""),
	JCO_ERROR_CONTEXT_NOT_FOUND(1137, ""),
	JCO_ERROR_PASSWORD_CHANGE_REQUIRED(1138, ""),
	JCO_ERROR_DSR_LOAD_ERROR(1150, ""),
	JCO_ERROR_DSR_PASSPORT_NOT_RECEIVED(1151, ""),
	JCO_ERROR_DSR_PASSPORT_NOT_VALID(1152, ""),
	JCO_ERROR_SESSION_REF_NOT_VALID(1153, ""),
	JCO_ERROR_JARM_LOAD_ERROR(1155, ""),
	JCO_ERROR_DATA_PROVIDER_ERROR(1156, ""),
	JCO_ERROR_SHAREABLE_CONTAINER(1160, ""),
	JCO_ERROR_CREATE_SESSION(1170, ""),
	JCO_ERROR_PASSIVATE_SESSION(1171, ""),
	JCO_ERROR_RESTORE_SESSION(1172, ""),
	JCO_ERROR_DESTROY_SESSION(1173, ""),
	JCO_ERROR_DESTINATION_DATA_INVALID(1180, ""),
	JCO_ERROR_SERVER_DATA_INVALID(1181, ""),
	JCO_ERROR_INVALID_REPOSITORY_CACHE(1190, ""),
	JCO_ERROR_REPOSITORY_SERIALIZATION(1191, ""),
	/**
	 * 700 - BAPI_XMI_LOGON - Error al iniciar sesion con XMI
	 */
	XMI_LOGIN_EXCEPTION(700, "Error al iniciar sesion con XMI."),
	/**
	 * 701 - Error al iniciar sesion de sap u obtener el respositorio del destino
	 */
	REPOSITORY_GET_FAIL(701, "Error al iniciar sesion de sap u obtener el respositorio del destino."),
	ERROR_JOB_STATUS(702, "Ocurrio un error interno de sap al ejecutar la funcion."),
	INSUFFICIENT_CREDENTIALS(703, "Las credenciales de conexion son insuficientes."),
	/**
	 * 999 - UNKNOWN - Error no desconocido
	 */
	UNKNOWN(999, "Error desconocido o no configurado.");

	public final int code;
	public final String message;

	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @param code
	 * @return Error a partir del codigo recibido
	 */
	public static ErrorCode getError(int code) {
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
	public static ErrorCode getError(String key) {
		ErrorCode[] errorCodes = ErrorCode.values();
		for (ErrorCode errorCode : errorCodes) {
			if (errorCode.name().equals(key)) {
				return errorCode;
			}
		}
		return UNKNOWN;
	}

	/*
	 * public int getCode() { if (isFatal) { return JCO_ERROR_OFFSET + code; } else { return code; } }
	 */

}
