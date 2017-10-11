package ast.sap.connector.chain.monitor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.print.attribute.standard.MediaSize.Other;

import org.junit.Test;

import ast.sap.connector.chain.ChainData;
import ast.sap.connector.chain.status.ChainStatusCode;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.OutTableRow;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;

public class ChainMonitorTest {

	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Test
	public void testMonitorChain() throws ParseException {
		SapFunction getChainStatusFunction = mockGetChainStatus();
		SapFunction getChainProcessesFunction = mockChainProcessesReader();
		SapFunction getStatusFunction = mockGetStatusFunction();
		SapFunction getJobLogFunction = mockGetJobLogFunction();

		SapRepository sapRepository = mock(SapRepository.class);
		when(sapRepository.getFunction("RSPC_API_CHAIN_GET_STATUS")).thenReturn(getChainStatusFunction);
		when(sapRepository.getFunction("RSPC_API_CHAIN_GET_PROCESSES")).thenReturn(getChainProcessesFunction);
		when(sapRepository.getFunction("BAPI_XBP_JOB_STATUS_GET")).thenReturn(getStatusFunction);
		when(sapRepository.getFunction("BAPI_XBP_JOB_JOBLOG_READ")).thenReturn(getJobLogFunction);

		/* FIN DE MOCKS ------------------------------------------------------------------------------------------------------------------------ */

		ChainMonitor chainMonitor = new ChainMonitor(sapRepository);
		chainMonitor.doSleep = false;
		String chainName = "Z_CHAIN_S_EVENT";
		String logId = "123456";
		String username = "mzaragoz";
		ChainFullStatus chainFullStatus = chainMonitor.monitorChain(new ChainData(logId, chainName), username);
		
		System.out.println(chainFullStatus);
		assertEquals(ChainStatusCode.G, chainFullStatus.getChainStatus().getStatus());

	}
	
	private String tt(int i) {
		return "Texto de prueba " + i;
	}

	private String randStringNo() {
		return "" + ((int) (Math.random() * 1000));
	}

	private SapFunction mockGetJobLogFunction() throws ParseException {
		SapStruct ret = mock(SapStruct.class);
		when(ret.getValue("TYPE")).thenReturn("");

		OutTableRow outRow = mock(OutTableRow.class);
		when(outRow.getValue("TEXT")).thenReturn(tt(1), tt(2), tt(3), tt(4), tt(5), tt(6), tt(7), tt(8), tt(9), tt(10), tt(11), tt(12), tt(13), tt(14));
		when(outRow.getValue("ENTERDATE")).thenReturn(dateFormat.parse("21/03/1991"), dateFormat.parse("05/12/1964"), dateFormat.parse("18/09/1960"));
		when(outRow.getValue("ENTERTIME")).thenReturn(timeFormat.parse("12:37:48"), timeFormat.parse("12:38:40"), timeFormat.parse("12:40:11"));
		when(outRow.getValue("MSGID")).thenReturn("540", "173", "000", "000", "000", "000", "000", "000", "000", "000");
		when(outRow.getValue("MSGNO")).thenReturn(randStringNo(), randStringNo(), randStringNo(), randStringNo(), randStringNo(), randStringNo());
		when(outRow.getValue("MSGTYPE")).thenReturn("S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S");

		OutTableParam outTable = mock(OutTableParam.class);
		when(outTable.currentRow()).thenReturn(outRow);
		when(outTable.getRowCount()).thenReturn(2, 3, 4);

		SapFunctionResult result = mock(SapFunctionResult.class);
		when(result.getStructure("RETURN")).thenReturn(ret);
		when(result.getOutTableParameter("JOB_PROTOCOL_NEW")).thenReturn(outTable);

		SapFunction function = mock(SapFunction.class);
		when(function.setInParameter(anyString(), any())).thenReturn(function);
		when(function.execute()).thenReturn(result);

		return function;
	}

	private SapFunction mockGetStatusFunction() {
		SapStruct ret = mock(SapStruct.class);
		when(ret.getValue("TYPE")).thenReturn("");

		SapFunctionResult result = mock(SapFunctionResult.class);
		when(result.getOutParameterValue("STATUS")).thenReturn("S").thenReturn("R").thenReturn("R").thenReturn("F");
		when(result.getStructure("RETURN")).thenReturn(ret);

		SapFunction function = mock(SapFunction.class);
		when(function.setInParameter(anyString(), any())).thenReturn(function);
		when(function.execute()).thenReturn(result);

		return function;
	}

	private SapFunction mockGetChainStatus() {
		SapFunctionResult result = mock(SapFunctionResult.class);
		when(result.getOutParameterValue("E_STATUS")).thenReturn("G");
		when(result.getOutParameterValue("E_MANUAL_ABORT")).thenReturn("");
		when(result.getOutParameterValue("E_MESSAGE")).thenReturn("");
		
		SapFunction function = mock(SapFunction.class);
		when(function.setInParameter(anyString(), any())).thenReturn(function);
		when(function.execute()).thenReturn(result);

		return function;
	}

	private SapFunction mockChainProcessesReader() throws ParseException {
		OutTableRow outRow = mock(OutTableRow.class);
		when(outRow.getValue("CHAIN_ID")).thenReturn(tt(1), tt(2));
		when(outRow.getValue("EVENT_START")).thenReturn("000", "000");
		when(outRow.getValue("EVENTP_START")).thenReturn("000", "000");
		when(outRow.getValue("EVENTNO_START")).thenReturn("000", "000");
		when(outRow.getValue("BACKLINK_START")).thenReturn("000", "000");
		when(outRow.getValue("TYPE")).thenReturn("TRIGGER", "ABAP");
		when(outRow.getValue("VARIANTE")).thenReturn("000", "000");
		when(outRow.getValue("PREDECESSOR")).thenReturn("000", "000");
		when(outRow.getValue("INSTANCE")).thenReturn("000", "000");
		when(outRow.getValue("STATE")).thenReturn("000", "000");
		when(outRow.getValue("EVENT_END")).thenReturn("000", "000");
		when(outRow.getValue("EVENTP_END")).thenReturn("000", "000");
		when(outRow.getValue("BACKLINK_END")).thenReturn("000", "000");
		when(outRow.getValue("ACTUAL_STATE")).thenReturn("000", "000");
		when(outRow.getValue("EVENT_GREEN")).thenReturn("000", "000");
		when(outRow.getValue("EVENTP_GREEN")).thenReturn("000", "000");
		when(outRow.getValue("BACKLINK_GREEN")).thenReturn("000", "000");
		when(outRow.getValue("EVENT_RED")).thenReturn("000", "000");
		when(outRow.getValue("EVENTP_RED")).thenReturn("000", "000");
		when(outRow.getValue("BACKLINK_RED")).thenReturn("000", "000");
		when(outRow.getValue("GREEN_EQ_RED")).thenReturn("000", "000");
		when(outRow.getValue("WAIT")).thenReturn("000", "000");
		when(outRow.getValue("STARTTIMESTAMP")).thenReturn("000", "000");
		when(outRow.getValue("ENDTIMESTAMP")).thenReturn("000", "000");
		when(outRow.getValue("JOB_COUNT")).thenReturn("1", "2");
		
		OutTableParam outTable = mock(OutTableParam.class);
		when(outTable.currentRow()).thenReturn(outRow, outRow);
		when(outTable.getRowCount()).thenReturn(2);

		SapFunctionResult result = mock(SapFunctionResult.class);
		when(result.getOutTableParameter("E_T_PROCESSLIST")).thenReturn(outTable);

		SapFunction function = mock(SapFunction.class);
		when(function.setInParameter(anyString(), any())).thenReturn(function);
		when(function.execute()).thenReturn(result);

		return function;
	}

}
