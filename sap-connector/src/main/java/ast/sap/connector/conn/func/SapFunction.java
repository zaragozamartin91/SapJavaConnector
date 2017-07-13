package ast.sap.connector.conn.func;

import com.google.common.base.Preconditions;
import com.sap.conn.jco.ConversionException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRuntimeException;
import com.sap.conn.jco.JCoTable;

/**
 * Funcion ejecutable de sap.
 * 
 * @author martin.zaragoza
 *
 */
public class SapFunction {
	private final JCoFunction jCoFunction;
	private final JCoDestination jCoDestination;

	public SapFunction(JCoFunction jCoFunction, JCoDestination jCoDestination) {
		this.jCoFunction = jCoFunction;
		this.jCoDestination = jCoDestination;
	}

	/**
	 * Establece el valor de un parametro de entrada regular.
	 * 
	 * @param key
	 *            - Nombre del parametro.
	 * @param value
	 *            - Valor del parametro.
	 * @return this.
	 * @throws ImportParameterSetErrorException
	 *             Si el parametro no existe o si el valor asignado no es del tipo correcto.
	 */
	public SapFunction setInParameter(String key, Object value) throws ImportParameterSetErrorException {
		Preconditions.checkNotNull(key, "La clave del parámetro no puede ser nula");
		Preconditions.checkNotNull(value, "El valor del parámetro no puede ser nulo");

		try {
			jCoFunction.getImportParameterList().setValue(key, value);
		} catch (ConversionException e) {
			throw new ImportParameterSetErrorException(
					String.format("El tipo del parametro de tipo IMPORT %s no es correcto en la funcion %s", key, jCoFunction.getName()), e);
		} catch (JCoRuntimeException e) {
			throw new ImportParameterSetErrorException(
					String.format("Ocurrio un error al establecer el valor del parámetro de tipo IMPORT %s de la funcion %s", key, jCoFunction.getName()), e);
		}
		return this;
	}

	public InTableParam setInTableParameter(String tableName) throws NonexistentTableParameterException {
		Preconditions.checkNotNull(tableName, "El nombre del campo tabla no puede ser nulo");

		try {
			JCoTable jcoTable = jCoFunction.getTableParameterList().getTable(tableName);
			return new TableParam(jcoTable);
		} catch (ConversionException e) {
			throw new NonexistentTableParameterException(
					String.format("El parametro %s no es de tipo tabla en la funcion %s", tableName, jCoFunction.getName()), e);
		} catch (JCoRuntimeException e) {
			throw new NonexistentTableParameterException(String.format("No existe el parametro %s en la funcion %s", tableName, jCoFunction.getName()), e);
		}
	}

	public SapFunctionResult execute() {
		try {
			jCoFunction.execute(jCoDestination);
			return new SapFunctionResult(jCoFunction);
		} catch (JCoException e) {
			throw new FunctionExecuteException(
					String.format("Ocurrió un error al ejecutar la función %s del destino %s", jCoFunction.getName(), jCoDestination.toString()), e);
		}
	}
}
