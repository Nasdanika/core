package org.nasdanika.diagram.plantuml.clazz;

public class Attribute extends Member {

	@Override
	public String toString() {
		return (getType().isEmpty() ? getNameString() : getNameString() + " : " + getTypeString()) + getLinkString();
	}

}
