package ast.sap.connector.chain;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import ast.sap.connector.chain.start.ChainStarter;
import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;

/**
 * 
 * @author franco.milanese
 *
 */
public class ChainStarterTest {

	@Test
	public void testStartChain() {
		String logId = "1234567890";

		SapFunctionResult resultMock = mock(SapFunctionResult.class);
		when(resultMock.getOutParameterValue("E_LOGID")).thenReturn(logId);

		SapFunction functionMock = mock(SapFunction.class);
		when(functionMock.setInParameter(anyString(), any())).thenReturn(functionMock);
		when(functionMock.execute()).thenReturn(resultMock);

		SapRepository repoMock = mock(SapRepository.class);
		when(repoMock.getFunction("RSPC_API_CHAIN_START")).thenReturn(functionMock);

		ChainStarter chainStarter = new ChainStarter(repoMock);
		String chainId = "SOME_CHAIN";

		/* FIN MOCKS ----------------------------------------------------------------------- */

		ChainData chainData = chainStarter.startChain(chainId);

		assertEquals(logId, chainData.getLogId());
		assertEquals(chainId, chainData.getChain());

		verify(functionMock, times(1)).execute();
		verify(functionMock, times(1)).setInParameter("I_CHAIN", chainId);
	}

}
