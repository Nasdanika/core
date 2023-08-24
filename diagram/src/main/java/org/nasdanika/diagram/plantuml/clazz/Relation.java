package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.common.Util;
import org.nasdanika.diagram.plantuml.Link;
import org.nasdanika.graph.SimpleConnection;

public abstract class Relation extends SimpleConnection {
	
	public Relation(DiagramElement source, DiagramElement target) {
		super(source, target, false);
	}

	private String tooltip;
	private String location;	
	private List<Link> name = new ArrayList<>();
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
	
	public List<Link> getName() {
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
	
	protected abstract String getType();
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		DiagramElement sourceDiagramElement = (DiagramElement) getSource();
		String sid = sourceDiagramElement.getId();
		if (Util.isBlank(sid)) {
			ret.append(sourceDiagramElement.getNameString());
		} else {
			ret.append(sourceDiagramElement.getId());
		}
		
		if (!Util.isBlank(sourceDecoration)) {
			ret.append(" \"").append(sourceDecoration).append("\"");
		}
			
		ret.append(" ");			
		ret.append(getType());

		if (!Util.isBlank(targetDecoration)) {
			ret.append(" \"").append(targetDecoration).append("\"");
		}
		
		ret.append(" ");
		
		DiagramElement targetDiagramElement = (DiagramElement) getTarget();
		String tid = targetDiagramElement.getId();
		if (Util.isBlank(tid)) {
			ret.append(targetDiagramElement.getNameString());
		} else {
			ret.append(targetDiagramElement.getId());
		}		
		
		if (location != null || !Util.isBlank(tooltip)) {
			ret.append(" [[");
			if (location != null) {
				ret.append(location);
			}
			if (!Util.isBlank(tooltip)) {
				ret.append("{").append(tooltip).append("}");
			}
			ret.append("]]");
		}		
		
		if (!name.isEmpty()) {
			ret.append(" : ");
			name.forEach(ret::append);
		}
		
		return ret.toString();
	}	

}
