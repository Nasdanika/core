package org.nasdanika.diagram.plantuml.clazz;

public class Implementation extends Relation {

	public Implementation(DiagramElement source, DiagramElement target) {
		super(source, target);
	}

	@Override
	protected String getType() {
		return "..|>";
	}

}
