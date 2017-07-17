package ast.sap.connector.dst;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoRepository;

import ast.sap.connector.func.SapFunction;

/**
 * Representa un repositorio de funciones de sap.
 * 
 * @author martin.zaragoza
 *
 */
public class SapRepository {
	private JCoRepository jcoRepository;
	private JCoDestination jcoDestination;

	SapRepository(JCoRepository jCoRepository, JCoDestination jcoDestination) {
		this.jcoRepository = jCoRepository;
		this.jcoDestination = jcoDestination;
	}

	/**
	 * Obtiene una funcion ejecutable de sap.
	 * 
	 * @param functionName
	 *            - Nombre de la funcion.
	 * @return Funcion ejecutable.
	 * @throws FunctionGetFailException
	 *             Si ocurrio un error al obtener la funcion.
	 * @throws NonexistentFunctionException
	 *             Si la funcion requerida no existe.
	 */
	public SapFunction getFunction(String functionName) throws FunctionGetFailException, NonexistentFunctionException {
		try {
			JCoFunctionTemplate jCoFunctionTemplate = jcoRepository.getFunctionTemplate(functionName);
			JCoFunction jcoFunction = jCoFunctionTemplate.getFunction();
			if (jcoFunction == null) {
				throw new NonexistentFunctionException(String.format("La funcion %s no existe en el destino %s", functionName, jcoDestination.toString()));
			}
			return new SapFunction(jcoFunction, jcoDestination);
		} catch (JCoException e) {
			throw new FunctionGetFailException(
					String.format("Ocurrio un error al obtener la funcion %s del destino %s", functionName, jcoDestination.toString()),
					e);
		}
	}
}
