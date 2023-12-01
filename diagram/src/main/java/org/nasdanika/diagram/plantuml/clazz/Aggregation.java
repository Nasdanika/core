package org.nasdanika.diagram.plantuml.clazz;

public class Aggregation extends Relation {

	private boolean biDirectional;

	public Aggregation(DiagramElement source, DiagramElement target, boolean biDirectional) {
		super(source, target);
		this.biDirectional = biDirectional;
	}

	@Override
	protected String getType() {
		return biDirectional ? "o-" + getDecorator() + "-" : "o-" + getDecorator() + "->";
	}

}
