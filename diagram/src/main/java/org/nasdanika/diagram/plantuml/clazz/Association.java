package org.nasdanika.diagram.plantuml.clazz;

public class Association extends Relation {

	private boolean biDirectional;

	public Association(DiagramElement source, DiagramElement target, boolean biDirectional) {
		super(source, target);
		this.biDirectional = biDirectional;
	}

	@Override
	protected String getType() {
		return biDirectional ? "-" + getDecorator() + "-" : "-" + getDecorator() + "->";
	}

}
