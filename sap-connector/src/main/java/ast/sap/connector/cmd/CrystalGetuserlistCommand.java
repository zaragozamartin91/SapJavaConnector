package ast.sap.connector.cmd;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.InTableParam;
import ast.sap.connector.func.SapFunction;
import ast.sap.connector.func.SapFunctionResult;

/**
 * Invoca a una funcion RFC enabled disponible desde AFE500
 * 
 * @author mzaragoz
 *
 */
public class CrystalGetuserlistCommand extends AbstractSapCommand {
	public CrystalGetuserlistCommand(SapRepository sapRepository) {
		super(sapRepository);
	}

	@Override
	public SapCommandResult execute() {
		// SapFunction function = repository().getFunction("/CRYSTAL/asdsad");
		SapFunction function = repository().getFunction("/CRYSTAL/GET_USER_LIST");
		
		SapFunctionResult result = function.execute();
		return new SapCommandResult(result.getOutTableParameter("USERACTGRP"));
	}

}
