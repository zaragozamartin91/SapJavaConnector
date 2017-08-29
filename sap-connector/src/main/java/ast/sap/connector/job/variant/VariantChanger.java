package ast.sap.connector.job.variant;

import java.util.Collection;

import com.google.common.base.Optional;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.InTableParam;
import ast.sap.connector.func.InTableRow;
import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;

public class VariantChanger {
	private final SapRepository repository;

	public VariantChanger(SapRepository repository) {
		this.repository = repository;
	}

	public SapBapiret2 changeVariant(ChangeVariantData changeVariantData) {
		Object programName = changeVariantData.getProgram();
		Object externalUsername = changeVariantData.getExternalUsername();

		/* OBTENGO LA VARIANTE DEL PROGRAMA RECIBIDO */
		SapFunction function = repository.getFunction("BAPI_XBP_VARINFO")
				.setInParameter("ABAP_PROGRAM_NAME", programName)
				.setInParameter("EXTERNAL_USER_NAME", externalUsername);

		if (changeVariantData.getVariant().isPresent()) {
			function.setInParameter("VARIANT", changeVariantData.getVariant().get());
		}

		SapFunctionResult result = function.execute();
		SapBapiret2 bapiret2 = new SapBapiret2(result.getStructure("RETURN"));
		OutTableParam variantTable = result.getOutTableParameter("VARIANT_INFO");
		if (bapiret2.hasError() || variantTable.isEmpty()) {
			// Retornar SapBapiRet2 en caso de error o en caso de que no tenga variante dado que no se modificaria nada
			return bapiret2;
		}
		Variant variant = new Variant(variantTable);

		System.out.println(variant.toString());
		// TODO: MODIFICAR LA TABLA RECIBIDA (variantData) DE LA FUNCION ANTERIOR Y ACTUALIZANDO LOS CAMPOS NECESARIOS (HAY QUE VER COMO VIENEN ESOS CAMPOS Y
		// CUALES SON)
		// for (VariantInfo variant : variantData.getVariantInfoList()) {
		// //SETEAR EL/LOS VALOR/ES A MODIFICAR
		// }
		// TODO: MODIFICO LA VARIANTE DEL PROGRAMA RECIBIDO
		String variantName = variant.getVariantEntries().get(0).getVariant();
		function = repository.getFunction("BAPI_XBP_VARIANT_CHANGE")
				.setInParameter("ABAP_PROGRAM_NAME", programName)
				.setInParameter("ABAP_VARIANT_NAME", variantName)
				.setInParameter("EXTERNAL_USER_NAME", externalUsername);

		Collection<VariantKeyValuePair> variantValuePairs = changeVariantData.getVariantValuePairs();
		/*
		 * TODO : DEBEMOS TENER MUCHO CUIDADO CON EL SETEO DE LOS CAMPOS DE TIPO FECHA... EL FORMATO DE FECHA DE VARIANT_INFO ES dd.MM.yyyy PERO EL FORMATO DE
		 * FECHA DE BAPI_XBP_VARIANT_CHANGE ES yyyyMMdd
		 */

		InTableParam table = function.setInTableParameter("VARIANT_INFO");
		for (VariantEntry variantEntry : variant.getVariantEntries()) {
			InTableRow newRow = table.appendRow()
					.setValue("REPORT", variantEntry.getReport())
					.setValue("VARIANT", variantEntry.getVariant())
					.setValue("PNAME", variantEntry.getPname())
					.setValue("PKIND", variantEntry.getPkind())
					.setValue("POLEN", variantEntry.getPolen())
					.setValue("PTEXT", variantEntry.getPtext())
					.setValue("PSIGN", variantEntry.getPsign())
					.setValue("POPTION", variantEntry.getPoption())
					.setValue("PLOW", variantEntry.getPlow())
					.setValue("PHIGH", variantEntry.getPhigh());

			Optional<VariantKeyValuePair> matchingEntry = matchingEntry(variantEntry, variantValuePairs);
			if (matchingEntry.isPresent()) {
				newRow.setValue("PLOW", matchingEntry.get().value);
			}
		}
		SapFunctionResult result2 = function.execute();
		return new SapBapiret2(result2.getStructure("RETURN"));
	}

	private Optional<VariantKeyValuePair> matchingEntry(VariantEntry variantEntry, Collection<VariantKeyValuePair> variantValuePairs) {
		for (VariantKeyValuePair variantValuePair : variantValuePairs) {
			if (variantEntry.getPname().equals(variantValuePair.key)) { return Optional.fromNullable(variantValuePair); }
		}
		return Optional.absent();
	}
}