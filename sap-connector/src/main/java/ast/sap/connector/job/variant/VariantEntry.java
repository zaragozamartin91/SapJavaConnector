package ast.sap.connector.job.variant;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ast.sap.connector.config.Configuration;
import ast.sap.connector.func.OutTableRow;

/**
 * @author franco.milanese
 *
 *         Estructura de la tabla BAPIVARINFO, utilizada en el manejo de variantes de programa
 */
public class VariantEntry {
	private String report;
	private String variant;
	private String pname;
	private String pkind;
	private Integer polen;
	private String ptext;
	private String psign;
	private String poption;
	private String plow;
	private String phigh;
	private VariantFieldType fieldType = VariantFieldType.TEXT;

	public VariantEntry(OutTableRow outTableRow) {
		this.report = outTableRow.getValue("REPORT").toString();
		this.variant = outTableRow.getValue("VARIANT").toString();
		this.pname = outTableRow.getValue("PNAME").toString();
		this.pkind = outTableRow.getValue("PKIND").toString();
		this.polen = (Integer) outTableRow.getValue("POLEN");
		this.ptext = outTableRow.getValue("PTEXT").toString();
		this.psign = outTableRow.getValue("PSIGN").toString();
		this.poption = outTableRow.getValue("POPTION").toString();

		this.plow = setPlow(outTableRow);

		this.phigh = outTableRow.getValue("PHIGH").toString();
	}

	/**
	 * @param outTableRow
	 * 
	 *            El formato de fecha recibida al consultar la variante en SAP es distinto al formato que se debe enviar para cambiarla, por lo tanto se realiza
	 *            la conversion de dd.MM.yyyy a yyyyMMdd
	 * @return
	 */
	private String setPlow(OutTableRow outTableRow) {
		String plow = outTableRow.getValue("PLOW").toString();
		SimpleDateFormat dateFormat = Configuration.getSapInDateFormat();
		try {
			Date parsed = dateFormat.parse(plow);
			String valueToSet = Configuration.getSapOutDateFormat().format(parsed);
			fieldType = VariantFieldType.DATE;
			return valueToSet;
		} catch (ParseException e) {
			try {
				/* TODO : ENCONTRAR UN PARSER NUMERICO ESTRICTO */
				String regex = "\\d+(\\.|\\,|\\d)*";
				if (plow.trim().matches(regex)) {
					Number parsedNumber = NumberFormat.getInstance(Locale.GERMANY).parse(plow.trim());
					fieldType = VariantFieldType.NUMBER;
					return parsedNumber.toString();
				} else {
					fieldType = VariantFieldType.TEXT;
					return plow;
				}
			} catch (ParseException e1) {
				fieldType = VariantFieldType.TEXT;
				return plow;
			}
		}
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPkind() {
		return pkind;
	}

	public void setPkind(String pkind) {
		this.pkind = pkind;
	}

	public Integer getPolen() {
		return polen;
	}

	public void setPolen(Integer polen) {
		this.polen = polen;
	}

	public String getPtext() {
		return ptext;
	}

	public void setPtext(String ptext) {
		this.ptext = ptext;
	}

	public String getPsign() {
		return psign;
	}

	public void setPsign(String psign) {
		this.psign = psign;
	}

	public String getPoption() {
		return poption;
	}

	public void setPoption(String poption) {
		this.poption = poption;
	}

	public String getPlow() {
		return plow;
	}

	public void setPlow(String plow) {
		this.plow = plow;
	}

	public String getPhigh() {
		return phigh;
	}

	public void setPhigh(String phigh) {
		this.phigh = phigh;
	}

	public VariantFieldType getFieldType() {
		return fieldType;
	}

	@Override
	public String toString() {
		return "VariantEntry [report=" + report + ", variant=" + variant + ", pname=" + pname + ", pkind=" + pkind + ", polen=" + polen + ", ptext=" + ptext
				+ ", psign=" + psign + ", poption=" + poption + ", plow=" + plow + ", phigh=" + phigh + ", fieldType=" + fieldType + "]";
	}
}
