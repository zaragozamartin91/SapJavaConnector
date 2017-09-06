package ast.sap.connector.job.variant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ast.sap.connector.config.Configuration;

public enum VariantFieldType {
	DATE, NUMBER, TEXT;

	public String transform(String value) throws ParseException {
		switch (this) {
			case DATE:
				SimpleDateFormat dateFormat = Configuration.getArgsInDateFormat();
				Date date = dateFormat.parse(value);
				return Configuration.getSapOutDateFormat().format(date);

			default:
				return value;
		}
	}
}
