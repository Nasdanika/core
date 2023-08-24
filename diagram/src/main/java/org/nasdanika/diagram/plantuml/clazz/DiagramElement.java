package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.nasdanika.common.Util;
import org.nasdanika.diagram.plantuml.Link;
import org.nasdanika.graph.SimpleNode;

public class DiagramElement extends SimpleNode {
	
	public static final String INDENT = "  ";
	
	
//	public record Result(List<String> lines, List<String> relations) {};
	
	private static AtomicInteger counter = new AtomicInteger();
	
	private String id = "DE_" + Integer.toString(counter.incrementAndGet(), Character.MAX_RADIX);
	private String tooltip;
	private String location;
	private List<Link> name = new ArrayList<>();
	private String style;
	
	public List<Link> getName() {
		return name;
	}

	public String getNameString() {
		return getName().stream().map(Link::toString).reduce("", (a,b) -> a + b);
	}
	
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
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
	
	protected String getLinkString() {
		if (Util.isBlank(location) && Util.isBlank(tooltip)) {
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
