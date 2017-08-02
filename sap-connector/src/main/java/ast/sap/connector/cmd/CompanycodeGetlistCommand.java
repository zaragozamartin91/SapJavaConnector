package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.OutTableParam;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;

/**
 * Comando de prueba BAPI_COMPANYCODE_GETLIST.
 * 
 * @see https://www.sapdatasheet.org/abap/func/bapi_companycode_getlist.html
 * 
 * @author martin.zaragoza
 *
 */
public class CompanycodeGetlistCommand extends AbstractSapCommand {
	public CompanycodeGetlistCommand(SapRepository sapRepository) {
		super(sapRepository);
	}

	@Override
	public SapCommandResult execute() {
		SapFunction function = repository().getFunction("BAPI_COMPANYCODE_GETLIST");
		SapFunctionResult result = function.execute();
		OutTableParam companyCodes = result.getOutTableParameter("COMPANYCODE_LIST");

		return companyCodes.isEmpty() ? SapCommandResult.emptyResult() : navigateCompanyCodes(companyCodes);
	}

	private SapCommandResult navigateCompanyCodes(OutTableParam companyCodes) {
		System.out.println("COMPANY CODES:");

		int rowCount = companyCodes.getRowCount();

		StringBuilder message = new StringBuilder("");

		for (int i = 0; i < rowCount; i++) {
			Object companyCode = companyCodes.getValue("COMP_CODE");
			Object companyName = companyCodes.getValue("COMP_NAME");
			String tmsg = String.format("COMPANY CODE: %s | COMPANY NAME: %s", companyCode, companyName);
			System.out.println(tmsg);
			message.append(tmsg);
			companyCode = (i < rowCount - 1) ? companyCodes.nextRow() : companyCode;
		}

		return new SapCommandResult(message.toString());
	}
}
