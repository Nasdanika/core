package org.nasdanika.diagram.plantuml.clazz;

public class Aggregation extends Relation {

	public Aggregation(DiagramElement source, DiagramElement target) {
		super(source, target);
	}

	@Override
	protected String getType() {
		return "o-->";
	}

}
