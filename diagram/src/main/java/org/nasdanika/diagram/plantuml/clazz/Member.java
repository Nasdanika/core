package org.nasdanika.diagram.plantuml.clazz;

public abstract class Member extends NamedElement {
	
	private Classifier type;

	public Classifier getType() {
		return type;
	}

	public void setType(Classifier type) {
		this.type = type;
	}
	
	public abstract String generate();
		
}
