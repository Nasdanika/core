package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.diagram.plantuml.Link;
import org.nasdanika.graph.SimpleNode;

public class DiagramElement extends SimpleNode {
	
	public static final String INDENT = "  ";
	
	
//	public record Result(List<String> lines, List<String> relations) {};
	
	private String id;
	private String tooltip;
	private URI location;
	private List<Link> name = new ArrayList<>();
	private String style;
	
	public List<Link> getName() {
		return name;
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
	public String getId() {
		return id;
	}	
	public void setId(String id) {
		this.id = id;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	/**
	 * @return A list of spec lines.
	 */
	public List<String> generate() {
		return Collections.emptyList();
	}

}
