package org.nasdanika.diagram.plantuml;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Util;

public class Link {
	
	private String label;
	private String tooltip;
	private URI location;
	
	public Link(String label) {
		this.label = label;
	}
	
	public Link() {
	}

	public Link(String label, URI location, String tooltip) {
		super();
		this.label = label;
		this.location = location;
		this.tooltip = tooltip;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
	
	public String toString(URI base) {
		if (location == null && Util.isBlank(tooltip)) {
			return label;
		}
		StringBuilder ret = new StringBuilder("[[");
		URI uri = location;
		if (uri != null) {
			if (base != null && !uri.isRelative() && !base.isRelative()) {
				uri = uri.deresolve(base, true, true, true);
			}
			ret.append(uri);
		}
		if (!Util.isBlank(tooltip)) {
			ret.append("{").append(tooltip).append("}");
		}
		if (!Util.isBlank(label)) {
			ret.append(" ").append(label);
		}
		ret.append("]]");
		return ret.toString();
	}
		
}
