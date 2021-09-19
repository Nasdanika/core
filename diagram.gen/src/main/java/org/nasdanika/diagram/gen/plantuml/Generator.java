package org.nasdanika.diagram.gen.plantuml;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.End;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Start;
import org.nasdanika.exec.content.Text;

/**
 * Generates PlantUML (http://www.plantuml.com/) text from diagrams and images with image maps.
 * @author Pavel
 *
 */
public class Generator {
	
//	public Generator(DiagramGenerator diagramGenerator) {
//		this.diagramGenerator
//	}
	
	public String generateSpec(Diagram diagram) {
		StringBuilder ret = new StringBuilder();
		
		// Modifiers
		if (!diagram.isVertical()) {
			ret.append("left to right direction").append(System.lineSeparator());
		}
		if (diagram.isHideEmptyDescription()) {
			ret.append("hide empty description").append(System.lineSeparator());
		}
		
		List<Connection> connections = new ArrayList<>();
		for (DiagramElement de: diagram.getElements()) {
			ret.append(generate(de, connections, 0));
		}
		
		for (Connection c: connections) {
			ret.append(generate(c));
		}
		
		return ret.toString();
	}
	
	protected String renderLink(Link link) {
		return renderLink(link.getText(), link.getLocation(), link.getTooltip());
	}

	protected String renderLink(String text, String location, String tooltip) {
		if (Util.isBlank(location)) {
			return text;
		}
		StringBuilder ret = new StringBuilder("[[");
		ret.append(location);
		if (!Util.isBlank(tooltip)) {
			ret.append("{").append(tooltip).append("}");
		}
		if (!Util.isBlank(text)) {
			ret.append(" ").append(text);
		}
		ret.append("]]");
		return ret.toString();
	}
	
	protected String render(List<EObject> elements) {
		StringBuilder ret = new StringBuilder();
		for (EObject e: elements) {
			if (e instanceof Link) {
				ret.append(renderLink((Link) e));
			} else if (e instanceof Text) {
				ret.append(((Text) e).getContent());
			} else {
				throw new IllegalArgumentException("Unsupported element type: " + e);
			}
		}
		return ret.toString();
	}
	
	/**
	 * @param diagramElement
	 * @param connections
	 * @param depth
	 * @return Diagram element spec with a line separator.
	 */
	protected String generate(DiagramElement diagramElement, List<Connection> connections, int depth) {
		connections.addAll(diagramElement.getConnections());
		if (diagramElement instanceof Start) {
			return "";
		}
		if (diagramElement instanceof End) {
			return "";
		}
		
		StringBuilder ret = new StringBuilder();
		
		for (int i = 0; i < depth; ++i) {
			ret.append("  ");
		}
		
		ret.append(diagramElement.getType()).append(" \"");
		String text = diagramElement.getText();
		EList<EObject> name = diagramElement.getName();
		if (!Util.isBlank(text)) {
			ret.append(text);
			if (!name.isEmpty()) {
				ret.append(" ");
			}
		}
		
		ret.append(render(name));
		ret.append("\"");
		
		ret.append(" as ").append(diagramElement.getId());
		
		String location = diagramElement.getLocation();
		if (!Util.isBlank(location)) {
			ret.append(" ").append(renderLink(null, location, diagramElement.getTooltip()));
		}
		
		String stereotype = diagramElement.getStereotype();
		if (!Util.isBlank(stereotype)) {
			ret.append(" <<").append(stereotype).append(">>");
		}
		
		String color = diagramElement.getColor();
		if (!Util.isBlank(color)) {
			ret.append(" #").append(color);
			String gradient = diagramElement.getGradient();
			if (!Util.isBlank(gradient)) {
				ret.append("|").append(gradient);
			}
		}
		
		StringBuilder borderStyleBuilder = new StringBuilder();
		if (diagramElement.isDashed()) {
			borderStyleBuilder.append("[dashed]");
		} else if (diagramElement.isDotted()) {
			borderStyleBuilder.append("[dotted]");
		} else if (diagramElement.isBold()) {
			borderStyleBuilder.append("[bold]");
		}
		String borderColor = diagramElement.getBorder();
		if (!Util.isBlank(borderColor)) {
			borderStyleBuilder.append(borderColor);
		}
		if (borderStyleBuilder.length() > 0) {
			ret.append(" ##").append(borderStyleBuilder.toString());
		}
		
		// TODO - styling like background
		
		EList<EObject> description = diagramElement.getDescription();
		if (!description.isEmpty()) {
			ret.append(" : ");
			ret.append(render(description));
		}
		
		EList<DiagramElement> elements = diagramElement.getElements();
		if (!elements.isEmpty()) {
			ret.append(" {").append(System.lineSeparator());
			
			for (DiagramElement element: elements) {
				ret.append(generate(element, connections, depth + 1));
			}
			
			for (int i = 0; i < depth; ++i) {
				ret.append("  ");
			}
			ret.append("}");
		}
		ret.append(System.lineSeparator());
		return ret.toString();
	}
	
	protected String generate(Connection connection) {
		StringBuilder ret = new StringBuilder();
		
		DiagramElement source = (DiagramElement) connection.eContainer();
		
		String sourceId;
		if (source instanceof Start) {
			sourceId = "[*]";
		} else {
			sourceId = source.getId();
		}
		DiagramElement target = connection.getTarget();
		String targetId;
		if (target instanceof End) {
			targetId = "[*]";
		} else {
			targetId = target.getId();
		}
		
		StringBuilder styleBuilder = new StringBuilder();
		String color = connection.getColor();
		if (!Util.isBlank(color)) {
			styleBuilder.append("#").append(color);
		}		
		if (connection.isDashed()) {
			if (styleBuilder.length() > 0) {
				styleBuilder.append(",");
			}
			styleBuilder.append("dashed");
		} else if (connection.isDotted()) {
			if (styleBuilder.length() > 0) {
				styleBuilder.append(",");
			}
			styleBuilder.append("dotted");
		} else if (connection.isBold()) {
			if (styleBuilder.length() > 0) {
				styleBuilder.append(",");
			}
			styleBuilder.append("bold");
		}
		int thickness = connection.getThickness();
		if (thickness > 0) {
			if (styleBuilder.length() > 0) {
				styleBuilder.append(",");
			}
			styleBuilder.append("thickness=").append(thickness);			
		}
		String style = styleBuilder.length() > 0 ? "[" + styleBuilder + "]" : "";
		
		ret
			.append(sourceId)
			.append(" ")
			.append(Context.singleton("style", style).interpolateToString(connection.getType()))
			.append(" ")
			.append(targetId);			
		
		EList<EObject> description = connection.getDescription();
		if (!description.isEmpty()) {
			ret.append(" : ");
			ret.append(render(description));
		}
		
		ret.append(System.lineSeparator());
		
		return ret.toString();
	}	
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateDiagram(Diagram diagram, DiagramGenerator.Dialect dialect, DiagramGenerator diagramGenerator) throws Exception {
		return diagramGenerator.generateDiagram(generateSpec(diagram), dialect);
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateDiagram(Diagram diagram, DiagramGenerator.Dialect dialect) throws Exception {
		return DiagramGenerator.INSTANCE.generateDiagram(generateSpec(diagram), dialect);
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateUmlDiagram(Diagram diagram, DiagramGenerator diagramGenerator) throws Exception {
		return diagramGenerator.generateUmlDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateUmlDiagram(Diagram diagram) throws Exception {
		return DiagramGenerator.INSTANCE.generateUmlDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateWireframeDiagram(Diagram diagram, DiagramGenerator diagramGenerator) throws Exception {
		return diagramGenerator.generateWireframeDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateWireframeDiagram(Diagram diagram) throws Exception {
		return DiagramGenerator.INSTANCE.generateWireframeDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateGanttDiagram(Diagram diagram, DiagramGenerator diagramGenerator) throws Exception {
		return diagramGenerator.generateGanttDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateGanttDiagram(Diagram diagram) throws Exception {
		return DiagramGenerator.INSTANCE.generateGanttDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateMindmapDiagram(Diagram diagram, DiagramGenerator diagramGenerator) throws Exception {
		return diagramGenerator.generateMindmapDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateMindmapDiagram(Diagram diagram) throws Exception {
		return DiagramGenerator.INSTANCE.generateMindmapDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateWbsDiagram(Diagram diagram, DiagramGenerator diagramGenerator) throws Exception {
		return diagramGenerator.generateWbsDiagram(generateSpec(diagram));
	}
	
	/**
	 * Generates a diagram image using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateWbsDiagram(Diagram diagram) throws Exception {
		return DiagramGenerator.INSTANCE.generateWbsDiagram(generateSpec(diagram));
	}
	

}
