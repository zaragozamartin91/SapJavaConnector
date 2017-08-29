package ast.sap.connector.job.variant;

import java.util.Collections;
import java.util.Collection;

import com.google.common.base.Optional;

public class ChangeVariantData {
	/**
	 * El nombre de la variante es opcional. De esta manera, si el nombre no se
	 * indica, entonces la variante a cambiar sera la primera encontrada
	 * relacionada con el programa.
	 */
	private Optional<String> variant = Optional.absent();
	private String program;
	private String externalUsername;
	private Collection<VariantKeyValuePair> variantValuePairs;

	public ChangeVariantData(String program, String externalUsername,
			Collection<VariantKeyValuePair> variantValuePairs) {
		this.program = program;
		this.externalUsername = externalUsername;
		this.variantValuePairs = Collections.unmodifiableCollection(variantValuePairs);
	}

	public ChangeVariantData(String program, String variant, String externalUsername,
			Collection<VariantKeyValuePair> variantValuePairs) {
		this.program = program;
		this.variant = Optional.fromNullable(variant);
		this.externalUsername = externalUsername;
		this.variantValuePairs = Collections.unmodifiableCollection(variantValuePairs);
	}

	public String getProgram() {
		return program;
	}

	public Optional<String> getVariant() {
		return variant;
	}

	public String getExternalUsername() {
		return externalUsername;
	}

	/**
	 * Obtiene los pares clave->valor para la modificacion de las variantes.
	 * 
	 * @return pares clave->valor para la modificacion de las variantes.
	 */
	public Collection<VariantKeyValuePair> getVariantValuePairs() {
		return variantValuePairs;
	}
}
