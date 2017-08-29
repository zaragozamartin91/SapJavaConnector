package ast.sap.connector.main;

import org.junit.Assert;
import org.junit.Test;

public class ErrorCodeTest {

	@Test 
	public void testFromCodeWithNonFatalError() {
		int code = 701;
		ErrorCode error = ErrorCode.fromCode(code);
		Assert.assertEquals(code, error.getCode());
	}
	
	
	@Test
	public void testFromCodeWithUknownCode() {
		int code = 20000;
		ErrorCode error = ErrorCode.fromCode(code);
		Assert.assertNotEquals(code, error.getCode());
	}
	
	@Test
	public void testFromCodeWithFatalError() {
		int code = 102;
		ErrorCode error = ErrorCode.fromCode(code);
		Assert.assertEquals(code+ErrorCode.JCO_ERROR_OFFSET, error.getCode());
	}
	
	@Test
	public void testFromName(){
		String key = "JCO_ERROR_COMMUNICATION";
		ErrorCode error = ErrorCode.fromName(key);
		Assert.assertEquals(ErrorCode.JCO_ERROR_COMMUNICATION.getCode(), error.getCode());
	}

}
