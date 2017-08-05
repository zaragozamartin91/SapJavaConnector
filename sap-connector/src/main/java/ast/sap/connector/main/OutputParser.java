package ast.sap.connector.main;

import ast.sap.connector.cmd.SapCommandResult;


public enum OutputParser {
	INSTANCE;
	
	/* TODO: SE DEBE ANALIZAR LA SALIDA DEL COMANDO Y DETERMINAR QUE HACER (QUE COSAS IMPRIMIR, QUE VARIABLES DE ENTORNO
	 * SE DEBEN ESTABLECER, SI SE DEBE ACASO SETEAR EL %ERRORLEVEL%, ETC.)
	 * SE DEBE ANALIZAR LOS CODIGOS DE ERROR DEL SapBapiRet (parametros type, id, number y message) PARA CONVERTIRLOS EN 
	 * NUESTROS PROPIOS CODIGOS DE ERROR (ej: E es para error). */
	public void parseOutput(SapCommandResult commandResult) {
		// HACE COSAS CON EL OUTPUT DE UN COMANDO SAP.
	}
}
