package org.nasdanika.diagram.plantuml.clazz;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.common.Util;
import org.nasdanika.diagram.plantuml.Link;
import org.nasdanika.graph.SimpleConnection;

public abstract class Relation extends SimpleConnection {
	
	public enum Direction { up, down, left, right }
	
	public enum Style { plain, dotted, dashed }
	
	private Direction direction;
	private Style style;
	private List<String> colors = new ArrayList<>();
	private int thickness = 1;
	
	public Relation(DiagramElement source, DiagramElement target) {
		super(source, target, false);
	}

	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public List<String> getColors() {
		return colors;
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
		
	protected String getDecorator() {
		StringBuilder sb = new StringBuilder();
		for (String color: colors) {
			if (!sb.isEmpty()) {
				sb.append(";");
			}
			sb.append("#").append(color);
		}
		
		if (style != null) {
			if (!sb.isEmpty()) {
				sb.append(",");
			}
			sb.append(style.name());			
		}
		if (thickness != 1) {
			if (!sb.isEmpty()) {
				sb.append(",");
			}
			sb.append("thickness=").append(thickness);						
		}
		
		if (direction == null) {
			return sb.isEmpty() ? sb.toString() : "[" + sb + "]";
		}
		
		if (sb.isEmpty()) {			
			return direction.name();
		}
		
		return "[" +sb + "]" + direction.name();
	}

}
