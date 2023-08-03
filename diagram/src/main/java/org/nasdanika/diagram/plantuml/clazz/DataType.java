package org.nasdanika.diagram.plantuml.clazz;

import org.nasdanika.common.Util;

public class DataType extends Classifier {

	public DataType(String name) {
		super(name);
	}
	
	@Override
	protected String getType() {
		return "class";
	}
	
	@Override
	public String getStereotype() {
		String ret = super.getStereotype();
		return Util.isBlank(ret) ? "<< (D,orchid) >>" : ret;
	}

}
