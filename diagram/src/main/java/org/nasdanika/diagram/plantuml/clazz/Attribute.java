package org.nasdanika.diagram.plantuml.clazz;

public class Attribute extends Member {

	@Override
	public String generate() {
		return getText();
	}

}
