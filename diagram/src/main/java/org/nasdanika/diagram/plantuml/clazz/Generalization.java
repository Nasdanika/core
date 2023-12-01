package org.nasdanika.diagram.plantuml.clazz;

public class Generalization extends Relation {

	public Generalization(DiagramElement source, DiagramElement target) {
		super(source, target);
		setDirection(Direction.up);
	}

	@Override
	protected String getType() {
		return "-" + getDecorator() + "-|>";
	}

}
