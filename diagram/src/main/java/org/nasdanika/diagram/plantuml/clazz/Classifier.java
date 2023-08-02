package org.nasdanika.diagram.plantuml.clazz;

import org.nasdanika.diagram.plantuml.Link;

public abstract class Classifier extends DiagramElement {
	
	private String stereotype;
	
	public Classifier(String name) {
		getName().add(new Link(name));	
	}

	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}
	
	
	
	// stereotype

}
