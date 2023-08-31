package org.nasdanika.diagram.plantuml.clazz;

public class Composition extends Relation {

	private boolean biDirectional;

	public Composition(DiagramElement source, DiagramElement target, boolean biDirectional) {
		super(source, target);
		this.biDirectional = biDirectional;
	}

	@Override
	protected String getType() {
		return biDirectional ? "*--" : "*-->";
	}

}
