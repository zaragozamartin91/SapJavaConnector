package ast.sap.connector.dst;

import com.google.common.base.Preconditions;
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoRepository;

import ast.sap.connector.dst.exception.ContextCloseException;
import ast.sap.connector.dst.exception.RepositoryGetFailException;

public class SapDestination {
	private final JCoDestination jcoDestination;

	SapDestination(JCoDestination jcoDestination) throws RepositoryGetFailException {
		Preconditions.checkNotNull(jcoDestination, "El destino no puede ser nulo");
		this.jcoDestination = jcoDestination;
	}

	public SapRepository openContext() throws RepositoryGetFailException {
		JCoContext.begin(jcoDestination);
		return getRepository();
	}

	public SapDestination closeContext() {
		try {
			JCoContext.end(jcoDestination);
			return this;
		} catch (JCoException e) {
			throw new ContextCloseException("Error al cerrar la sesion con sap", e);
		}
	}

	public SapRepository statelessRepository() throws RepositoryGetFailException {
		return getRepository();
	}

	private SapRepository getRepository() throws RepositoryGetFailException {
		try {
			JCoRepository jCoRepository = jcoDestination.getRepository();
			if (jCoRepository == null) {
				throw new RepositoryGetFailException("No es posible obtener el repositorio de " + jcoDestination);
			}

			return new SapRepository(jCoRepository, jcoDestination);
		} catch (JCoException e) {
			throw new RepositoryGetFailException("Error al iniciar sesion de sap u obtener el respositorio del destino " + jcoDestination, e);
		}
	}
}
