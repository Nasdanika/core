package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.diagram.plantuml.Link;

public class NamedElement {
	
	private String text;
	private String tooltip;
	private URI location;	
	private List<Link> name = new ArrayList<>();
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public URI getLocation() {
		return location;
	}
	public void setLocation(URI location) {
		this.location = location;
	}	
	public List<Link> getName() {
		return name;
	}	

}
