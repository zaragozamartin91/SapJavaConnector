package ast.sap.connector.main.args.parse;

import ast.sap.connector.main.args.InputArgumentsData;

/**
 * Parsea un argumento de entrada.
 */
public interface ArgumentParser {
    /**
     * Determina si el argumento coincide con el patron establecido.
     *
     * @param arg - Argumento a analizar.
     * @return True si el argumento matchea el formato de patron establecido.
     */
    boolean matches(String arg);

    /**
     * Obtiene el valor del argumento.
     *
     * @param arg - Argumento completo.
     * @return Valor del argumento.
     */
    String getArgValue(String arg);

    /**
     * Establece el valor de un argumento
     * @param inputArgumentsData
     * @param arg
     * @return
     */
    InputArgumentsData setArgValue(InputArgumentsData inputArgumentsData, String arg);
}
