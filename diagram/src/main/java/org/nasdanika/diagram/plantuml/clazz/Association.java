package org.nasdanika.diagram.plantuml.clazz;

public class Association extends Relation {

	public Association(DiagramElement source, DiagramElement target) {
		super(source, target);
	}

	@Override
	protected String getType() {
		return "-->";
	}

}
