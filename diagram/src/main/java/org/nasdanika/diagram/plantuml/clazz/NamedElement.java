package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.plantuml.Link;

public class NamedElement {
	
	private String tooltip;
	private URI location;	
	private List<Link> name = new ArrayList<>();
	
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

	protected String getNameString() {
		return getName().stream().map(Link::toString).reduce("", (a,b) -> a + b);
	}
	
	protected String getLinkString() {
		if (location == null && Util.isBlank(tooltip)) {
			return ""; 
		}
		
		StringBuilder sb = new StringBuilder(" [[");
		if (location != null) {
			sb.append(location);
		}
		if (!Util.isBlank(tooltip)) {
			sb.append("{").append(tooltip).append("}");
		}
		sb.append("]]");
		return sb.toString();
	}
	
}
