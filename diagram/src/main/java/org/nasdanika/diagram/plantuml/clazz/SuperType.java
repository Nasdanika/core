package org.nasdanika.diagram.plantuml.clazz;

/**
 * Supertype element for a class if a supertype is not present as a diagram element.
 */
public class SuperType extends Member {

	@Override
	public String toString() {
		return getNameString() + getLinkString();
	}
	
}
