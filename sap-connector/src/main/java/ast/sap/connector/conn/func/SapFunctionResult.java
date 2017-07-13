package ast.sap.connector.conn.func;

import com.google.common.base.Preconditions;
import com.sap.conn.jco.ConversionException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRuntimeException;
import com.sap.conn.jco.JCoTable;

public class SapFunctionResult {
	private final JCoFunction jCoFunction;

	public SapFunctionResult(JCoFunction jCoFunction) {
		this.jCoFunction = jCoFunction;
	}

	public Object getOutParameterValue(String paramKey) {
		return jCoFunction.getExportParameterList().getValue(paramKey);
	}
	
	public OutTableParam getOutTableParameter(String tableName) {
		Preconditions.checkNotNull(tableName, "El nombre del campo tabla no puede ser nulo");

		try {
			JCoTable jcoTable = jCoFunction.getTableParameterList().getTable(tableName);
			return new TableParam(jcoTable);
		} catch (ConversionException e) {
			throw new NonexistentTableParameterException(
					String.format("El parametro %s no es de tipo tabla en la funcion %s", tableName, jCoFunction.getName()), e);
		} catch (JCoRuntimeException e) {
			throw new NonexistentTableParameterException(String.format("No existe el parámetro %s en la funcion %s", tableName, jCoFunction.getName()), e);
		}
	}
}
