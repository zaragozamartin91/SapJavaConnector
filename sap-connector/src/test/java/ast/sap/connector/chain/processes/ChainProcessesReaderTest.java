package ast.sap.connector.chain.processes;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import ast.sap.connector.chain.ChainData;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.OutTableRow;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;

public class ChainProcessesReaderTest {

	@Test
	public void testReadProcesses() {
		OutTableRow row1 = mock(OutTableRow.class);
		String type1 = "TRIGGER";
		String count1 = "123456";
		when(row1.getValue("TYPE")).thenReturn(type1);
		when(row1.getValue("JOB_COUNT")).thenReturn(count1);

		OutTableRow row2 = mock(OutTableRow.class);
		String type2 = "ABAP";
		String count2 = "789123";
		when(row2.getValue("TYPE")).thenReturn(type2);
		when(row2.getValue("JOB_COUNT")).thenReturn(count2);

		OutTableParam table = mock(OutTableParam.class);
		when(table.currentRow()).thenReturn(row1, row2);
		when(table.getRowCount()).thenReturn(2);
		when(table.nextRow()).thenReturn(row2);

		SapFunctionResult result = mock(SapFunctionResult.class);
		when(result.getOutTableParameter("E_T_PROCESSLIST")).thenReturn(table);

		SapFunction function = mock(SapFunction.class);
		when(function.setInParameter(anyString(), any())).thenReturn(function);
		when(function.execute()).thenReturn(result);

		SapRepository sapRepository = mock(SapRepository.class);
		when(sapRepository.getFunction("RSPC_API_CHAIN_GET_PROCESSES")).thenReturn(function);

		/* FIN DE MOCKS -------------------------------------------------------------------------- */

		ChainProcessesReader chainProcessesReader = new ChainProcessesReader(sapRepository);
		String logId = "654231";
		String chain = "SOME_CHAIN";
		ChainData chainData = new ChainData(logId, chain);
		ChainProcessBundle chainProcessBundle = chainProcessesReader.readProcesses(chainData);

		List<ProcessEntry> processes = chainProcessBundle.getProcesses();
		assertEquals(2, processes.size());
		
		ProcessEntry process1 = processes.get(0);
		assertEquals(type1, process1.getType());
		assertEquals(count1, process1.getJobCount());
		
		ProcessEntry process2 = processes.get(1);
		assertEquals(type2, process2.getType());
		assertEquals(count2, process2.getJobCount());
	}

}
