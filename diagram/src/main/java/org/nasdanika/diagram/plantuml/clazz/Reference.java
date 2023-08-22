package org.nasdanika.diagram.plantuml.clazz;

public class Reference extends Member {

	@Override
	public String toString() {
		return (getType().isEmpty() ? getNameString() : getNameString() + " : " + getTypeString()) + getLinkString();
	}
	
}
