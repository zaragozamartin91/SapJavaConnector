package ast.sap.connector.conn.dst;

import com.google.common.base.Preconditions;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoRepository;

import ast.sap.connector.conn.func.SapFunction;

public class SapDestination {
	private final JCoDestination jcoDestination;
	private final JCoRepository jCoRepository;

	public SapDestination(JCoDestination jcoDestination) throws RepositoryGetFailException {
		Preconditions.checkNotNull(jcoDestination, "El destino no puede ser nulo");

		this.jcoDestination = jcoDestination;
		try {
			this.jCoRepository = jcoDestination.getRepository();
			if (jCoRepository == null) {
				throw new RepositoryGetFailException("No es posible obtener el repositorio de " + jcoDestination);
			}
		} catch (JCoException e) {
			throw new RepositoryGetFailException("Error al obtener el respositorio del destino " + jcoDestination, e);
		}
	}

	public SapFunction getFunction(String functionName) throws FunctionGetFailException, NonexistentFunctionException {
		try {
			JCoFunctionTemplate jCoFunctionTemplate = jCoRepository.getFunctionTemplate(functionName);
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
