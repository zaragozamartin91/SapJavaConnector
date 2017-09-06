package ast.sap.connector.cmd;

import java.util.Collection;

import ast.sap.connector.dst.SapRepository;
import ast.sap.connector.func.SapBapiret2;
import ast.sap.connector.job.JobCreateData;
import ast.sap.connector.job.create.StepVariantValuesTuple;
import ast.sap.connector.job.variant.ChangeVariantData;
import ast.sap.connector.job.variant.VariantChanger;
import ast.sap.connector.job.variant.VariantKeyValuePair;

public enum VariantChangerHelper {
	INSTANCE;
	
	public SapBapiret2 changeVariant(SapRepository repository,JobCreateData jobData, StepVariantValuesTuple stepVariantTuple) {
		VariantChanger variantChanger = new VariantChanger(repository);
		String program = stepVariantTuple.getProgram();
		String variant = stepVariantTuple.getVariant().get();
		String externalUsername = jobData.getExternalUsername();
		Collection<VariantKeyValuePair> variantValuePairs = stepVariantTuple.getVariantValuePairs().get();
		ChangeVariantData changeVariantData = new ChangeVariantData(program, variant, externalUsername, variantValuePairs);

		SapBapiret2 changeVariantRet = variantChanger.changeVariant(changeVariantData);
		return changeVariantRet;
	}
}
