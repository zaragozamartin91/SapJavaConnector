package ast.sap.connector.func;

/**
 * Representa un parametro de tipo tabla de SALIDA (tabla resultado de una ejecucion de funcion de sap).
 * 
 * @author martin.zaragoza
 *
 */
public interface OutTableParam {

	OutTableParam nextRow();

	Object getValue(String colName);

	int getRowCount();

}