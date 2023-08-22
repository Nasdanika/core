package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.diagram.plantuml.Link;

public abstract class Member extends NamedElement {
	
	private List<Link> type = new ArrayList<>();

	public List<Link> getType() {
		return type;
	}

	protected String getTypeString() {
		return getType().stream().map(Link::toString).reduce("", (a,b) -> a + b);
	}
		
}
