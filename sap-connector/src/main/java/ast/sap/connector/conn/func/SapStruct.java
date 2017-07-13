package ast.sap.connector.conn.func;

import com.sap.conn.jco.JCoStructure;

public class SapStruct {
	private final JCoStructure jCoStructure;

	public SapStruct(JCoStructure structure) {
		this.jCoStructure = structure;
	}

	public SapStruct getStructure(String fieldName) {
		return new SapStruct(jCoStructure.getStructure(fieldName));
	}

	public Object getValue(String fieldName) {
		return jCoStructure.getValue(fieldName);
	}

}
