package org.nasdanika.diagram.plantuml.clazz;

public class Interface extends Type {

	public Interface(String name) {
		super(name);
	}
	
	@Override
	protected String getType() {
		return "interface";
	}

}
