package ast.sap.connector.job.variant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;
import ast.sap.connector.func.SapStruct;

public class VariantReaderTest {

	@Test
	public void testReadVariant() {
		OutTableParam varinfo = mock(OutTableParam.class);
		when(varinfo.getRowCount()).thenReturn(3);

		SapStruct ret = mock(SapStruct.class);
		when(ret.getValue("TYPE")).thenReturn("");
		when(ret.getValue("MESSAGE")).thenReturn("");

		SapFunctionResult result = mock(SapFunctionResult.class);
		when(result.getStructure("RETURN")).thenReturn(ret);
		when(result.getOutTableParameter("VARIANT_INFO")).thenReturn(varinfo);

		SapFunction function = mock(SapFunction.class);
		when(function.setInParameter(anyString(), any())).thenReturn(function);
		when(function.execute()).thenReturn(result);

		SapRepository repository = mock(SapRepository.class);
		when(repository.getFunction("BAPI_XBP_VARINFO")).thenReturn(function);
	}

}
