package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.SimpleConnection;

public class Relation extends SimpleConnection {
	
	public Relation(DiagramElement source, DiagramElement target) {
		super(source, target, false);
	}

	private String text;
	private String tooltip;
	private URI location;	
	private List<Object> name = new ArrayList<>();
	
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
	public List<Object> getName() {
		return name;
	}
	
	private String sourceDecoration;
	private String targetDecoration;
	
	public String getSourceDecoration() {
		return sourceDecoration;
	}
	public void setSourceDecoration(String sourceDecoration) {
		this.sourceDecoration = sourceDecoration;
	}
	public String getTargetDecoration() {
		return targetDecoration;
	}
	public void setTargetDecoration(String targetDecoration) {
		this.targetDecoration = targetDecoration;
	}

}
