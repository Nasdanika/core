package org.nasdanika.diagram.plantuml.clazz;

public class EnumLiteral extends NamedElement {

	protected String getLinkStringSuffix() {
		return "]]]";
	}
	
	protected String getLinkStringPrefix() {
		return " [[[";
	}
	
}
