package org.nasdanika.diagram.plantuml.clazz;

public class Reference extends Member {

	@Override
	public String generate() {
		return getText();
	}	
	
}
