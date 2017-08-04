package ast.sap.connector.cmd.impl;

import ast.sap.connector.cmd.SapCommandResult;
import ast.sap.connector.cmd.SapXmiCommand;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.xmi.XmiLoginData;

/**
 * Comando de prueba BAPI_XBP_EVENT_RAISE.
 * 
 * @see http://www.sapdatasheet.org/abap/func/bapi_xbp_event_raise.html
 * 
 * @author martin.zaragoza
 *
 */
public class RaiseEventCommand extends SapXmiCommand {
	private final String eventId;
	private final String externalUsername;

	public RaiseEventCommand(SapRepository sapRepository, XmiLoginData xmiLoginData, String eventId, String externalUsername) {
		super(sapRepository, xmiLoginData);
		this.eventId = eventId;
		this.externalUsername = externalUsername;
	}

	@Override
	protected SapCommandResult perform() {
		SapFunction function = repository().getFunction("BAPI_XBP_EVENT_RAISE")
				.setInParameter("EVENTID", eventId)
				.setInParameter("EXTERNAL_USER_NAME", externalUsername);

		SapFunctionResult result = function.execute();
		SapStruct ret = result.getStructure("RETURN");
		SapBapiret2 bapiRet = new SapBapiret2(ret);

		return new SapCommandResult(bapiRet);
	}
}
