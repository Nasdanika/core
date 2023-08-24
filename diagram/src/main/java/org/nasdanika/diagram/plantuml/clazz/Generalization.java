package org.nasdanika.diagram.plantuml.clazz;

public class Generalization extends Relation {

	public Generalization(DiagramElement source, DiagramElement target) {
		super(source, target);
	}

	@Override
	protected String getType() {
		return "-u-|>";
	}

}
