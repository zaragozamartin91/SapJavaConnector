package ast.sap.connector.conn.func;

/**
 * Representa un parametro de tipo tabla de ENTRADA (parametro de una funcion sap a invocar).
 * La tabla es un componente navegable que permite agregar filas y establecer los valores de las celdas.
 * 
 * @author martin.zaragoza
 *
 */
public interface InTableParam {

	/**
	 * Agrega una fila al parametro tabla.
	 * 
	 * @return this.
	 */
	InTableParam appendRow();

	/**
	 * Establece el valor de un campo/celda de la fila actual.
	 * 
	 * @param column
	 *            - Nombre de columna.
	 * @param value
	 *            - Valor del campo.
	 * @return this.
	 */
	InTableParam setValue(String column, Object value);

}