package ast.sap.connector.main.args.parse;

import java.util.regex.Pattern;

import ast.sap.connector.main.args.InputArgumentsData;

public abstract class AbstractArgumentParser implements ArgumentParser {
	private final Pattern pattern;
	private final String prefix;

	AbstractArgumentParser(String prefix, String search) {
		pattern = Pattern.compile(prefix + search);
		this.prefix = prefix;
	}

	@Override
	public boolean matches(String arg) {
		return pattern.matcher(arg).matches();
	}

	@Override
	public String getArgValue(String arg) {
		return arg.split(prefix)[1];
	}

	@Override
	public InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg) {
		return setValue(inputArgumentsData, getArgValue(arg));
	}

	/**
	 * Establece el valor de un campo del componente de argumentos de programa.
	 * 
	 * @param inputArgumentsData
	 *            - Componente de argumentos de programa.
	 * @param value
	 *            - Valor a establecer en el componente.
	 * @return componente modificado.
	 */
	protected abstract InputArgumentsData setValue(InputArgumentsData inputArgumentsData, String value);
}
