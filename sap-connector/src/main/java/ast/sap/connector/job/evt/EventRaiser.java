package ast.sap.connector.job.evt;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;
import ast.sap.connector.job.JobTrackData;

public class EventRaiser {

	private SapRepository sapRepository;

	public EventRaiser(SapRepository sapRepository) {
		this.sapRepository = sapRepository;
	}

	public SapBapiret2 raiseEvent (JobTrackData jobData, String eventId) {
		SapFunction function = sapRepository.getFunction("BAPI_XBP_EVENT_RAISE")
				.setInParameter("EVENTID", eventId)
				.setInParameter("EXTERNAL_USER_NAME", jobData.getExternalUsername());

		SapFunctionResult result = function.execute();
		SapStruct ret = result.getStructure("RETURN");
		SapBapiret2 bapiRet = new SapBapiret2(ret);

		return bapiRet;

	}
}
