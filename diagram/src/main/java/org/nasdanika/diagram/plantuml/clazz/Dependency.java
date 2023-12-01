package org.nasdanika.diagram.plantuml.clazz;

public class Dependency extends Relation {

	public Dependency(DiagramElement source, DiagramElement target) {
		super(source, target);
	}

	@Override
	protected String getType() {
		return "." + getDecorator() + ".>"; 
	}

}
