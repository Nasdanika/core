package org.nasdanika.diagram.plantuml.clazz;

public class EnumLiteral extends NamedElement {

	@Override
	protected String getLinkStringSuffix() {
		return "]]]";
	}
	
	@Override
	protected String getLinkStringPrefix() {
		return " [[[";
	}
	
}
