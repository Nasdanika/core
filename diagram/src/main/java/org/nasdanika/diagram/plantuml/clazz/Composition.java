package org.nasdanika.diagram.plantuml.clazz;

public class Composition extends Relation {

	public Composition(DiagramElement source, DiagramElement target) {
		super(source, target);
	}

	@Override
	protected String getType() {
		return "*--";
	}

}
