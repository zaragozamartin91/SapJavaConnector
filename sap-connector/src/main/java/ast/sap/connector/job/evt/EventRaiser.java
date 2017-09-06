package ast.sap.connector.job.evt;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;

/**
 * Disparador de eventos de sap.
 * 
 * @author martin
 *
 */
public class EventRaiser {
	private SapRepository sapRepository;

	public EventRaiser(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}

	/**
	 * Dispara un evento de sap.
	 * 
	 * @see https://www.sapdatasheet.org/abap/func/bapi_xbp_event_raise.html
	 * 
	 * @param externalUsername
	 *            Usuario que disparara el evento.
	 * @param eventId
	 *            Id del evento a disparar.
	 * @return Resultado del disparo del evento.
	 */
	public SapBapiret2 raiseEvent(String externalUsername, String eventId) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_EVENT_RAISE")
				.setInParameter("EVENTID", eventId)
				.setInParameter("EXTERNAL_USER_NAME", externalUsername);

		SapFunctionResult result = function.execute();
		SapStruct ret = result.getStructure("RETURN");
		SapBapiret2 bapiRet = new SapBapiret2(ret);

		return bapiRet;
	}
}
