package org.nasdanika.diagram.gen;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.End;
import org.nasdanika.diagram.Layer;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Note;
import org.nasdanika.diagram.Start;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.exec.content.Text;
import org.nasdanika.ncore.IntegerProperty;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.StringProperty;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Generates Drawio diagrams.
 * For cross-page links use the following format: data:page/id,<diagram uuid>
 * @author Pavel
 *
 */
public class DrawioGenerator {
	
	private static final String HEIGHT_PROPERTY = "height";
	private static final String WIDTH_PROPERTY = "width";

	private DiagramGenerator diagramGenerator;

	public DrawioGenerator(DiagramGenerator diagramGenerator) {
		this.diagramGenerator = diagramGenerator;
	}
	
	/**
	 * Generates diagram HTML using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws IOException 
	 * @throws TransformerException 
	 * @throws ParserConfigurationException 
	 */
	public String generateDiagram(Diagram diagram) throws TransformerException, IOException, ParserConfigurationException {
		Document document = generateModel(diagram);
		return diagramGenerator.generateDrawioDiagram(document.save(true));
	}

	/**
	 * Generates diagram model in .drawio format which can be further converted to HTML or stored to a file for manual editing.
	 * @param diagram
	 * @return
	 * @throws ParserConfigurationException 
	 */
	public Document generateModel(Diagram... diagrams) throws ParserConfigurationException {
		Document ret = Document.create(false, null);
		for (Diagram diagram: diagrams) {
			Page page = ret.createPage();
	        if (!Util.isBlank(diagram.getUuid())) {
	        	page.getElement().setAttribute("id", diagram.getUuid());
	        }
	        if (!Util.isBlank(diagram.getName())) {
	        	page.setName(diagram.getName());
	        }

	        generatePage(diagram, page);
		}
		return ret;
	}
	
    /**
     * {@link Charset} for encoding/decoding.
     * @return This implementation returns UTF-8.
     */
    protected Charset getCharset() {
    	return StandardCharsets.UTF_8;
    }

	protected void generatePage(Diagram diagram, Page page) {
		Root root = page.getModel().getRoot();
		Map<DiagramElement,ModelElement> elementsMap = new HashMap<>();
		Map<DiagramElement, Rectangle> geometry = new HashMap<>();
		layout(diagram, geometry::put);
					
		Collection<Connection> connections = new ArrayList<>();
		org.nasdanika.drawio.Layer backgroundLayer = root.getLayers().get(0);
		if (!diagram.isVisible()) {
			backgroundLayer.setVisible(false);
		}
		for (Note note: diagram.getNotes()) {
			createNote(note, backgroundLayer);				
		}
		for (DiagramElement element: diagram.getElements()) { // Background anonymous layer
			createElement(element, backgroundLayer, geometry::get, elementsMap, connections, 0);
		}
		
		for (Layer layer: diagram.getLayers()) {
			org.nasdanika.drawio.Layer drawioLayer = root.createLayer();
			drawioLayer.setLabel(layer.getName());
			if (!layer.isVisible()) {
				drawioLayer.setVisible(false);
			}
			
			for (DiagramElement element: layer.getElements()) {
				createElement(element, drawioLayer, geometry::get, elementsMap, connections, 0);
			}
		}			
		
		for (Connection connection: connections) {
			ModelElement source = Objects.requireNonNull(elementsMap.get(connection.eContainer()), "Source cell not found");
			ModelElement target = Objects.requireNonNull(elementsMap.get(connection.getTarget()), "Target cell not found");
			EObject commonAncestor = NcoreUtil.commonAncestor(connection.eContainer(), connection.getTarget());
			ModelElement commonParent = elementsMap.get(commonAncestor);		
			(commonParent instanceof org.nasdanika.drawio.Layer ? (org.nasdanika.drawio.Layer) commonParent : backgroundLayer).createConnection((Node) source, (Node) target).getStyle().put("strokeColor", "#000000");
		}
	}

	/**
	 * Computes sizes and positions of diagram elements.
	 * @param diagram
	 * @return
	 */
	protected Map<DiagramElement, Rectangle> layout(Diagram diagram, BiConsumer<DiagramElement, Rectangle> geometryConsumer) {		
		List<DiagramElement> allElements = new ArrayList<>(diagram.getElements());
		for (Layer layer: diagram.getLayers()) {
			allElements.addAll(layer.getElements());
		}
		return layout(allElements, new Point(10,10), geometryConsumer);
	}
	
	/**
	 * Computes sizes and positions of a collection of elements.
	 * @param elements
	 * @return
	 */
	protected Map<DiagramElement, Rectangle> layout(Collection<DiagramElement> elements, Point offset, BiConsumer<DiagramElement, Rectangle> geometryConsumer) {
		Map<DiagramElement, Rectangle> ret = new HashMap<>();
		for (DiagramElement element: elements) {
			ret.put(element, createElementRectangle(element, offset, geometryConsumer));
		}
		
		// Sort
		List<Map.Entry<DiagramElement, Rectangle>> remaining = new ArrayList<>(ret.entrySet());
		List<Map.Entry<DiagramElement, Rectangle>> sorted = new ArrayList<>();
		Map<Rectangle,Boolean> downMap = new IdentityHashMap<>();
		while (!remaining.isEmpty()) {
			Entry<DiagramElement, Rectangle> candidate = null;
			int candidateAffinity = 0;
			boolean isCandidateDown = false;
			for (Entry<DiagramElement, Rectangle> e: remaining) {
				Collection<Map.Entry<DiagramElement, Rectangle>> els;
				if (sorted.isEmpty()) {
					els = new ArrayList<>(remaining);
					els.remove(e);
				} else {
					els = sorted;
				}
				int[] eAffinity = affinity(e.getKey(), els.stream().map(Map.Entry::getKey).collect(Collectors.toList()));
				int teAffinity = eAffinity[0] + eAffinity[1];
				if (candidate == null || (candidateAffinity < teAffinity)) {
					candidate = e;
					candidateAffinity = teAffinity;
					isCandidateDown = eAffinity[1] > eAffinity[0];
				}		
			}
			sorted.add(candidate);
			downMap.put(candidate.getValue(), isCandidateDown);
			remaining.remove(candidate);
		}				
		
		position(sorted, new HashMap<>(), offset, downMap::get);
		
		double minX = Integer.MAX_VALUE;
		double minY = Integer.MAX_VALUE;
		for (Rectangle rectangle: ret.values()) {			
			minX = Math.min(rectangle.getX(), minX);
			minY = Math.min(rectangle.getY(), minY);
		}
		
		for (Rectangle rectangle: ret.values()) {
			rectangle.translate((int) (-minX + offset.getX()), (int) (-minY + offset.getY()));
		}		
		
		return ret;
	}
	
	/**
	 * Computes affinity of a given diagram element to a collection of other elements.
	 * Affinity is a two-elements array with the first element containing a total number of 
	 * connections from the first argument element to the second argument elements recursively including child elements on both sides.
	 * The second array element contains a total number of connections from the second argument elements to the first argument element also recursively
	 * including child elements on both sides. Affinity is used to layout elements in a top-down   
	 * @param element
	 * @param elements
	 * @return
	 */
	protected int[] affinity(DiagramElement element, Collection<DiagramElement> elements) {
		int outbound = 0;
		TreeIterator<EObject> oit = element.eAllContents();
		while (oit.hasNext()) {
			EObject next = oit.next();
			if (next instanceof Connection) {
				DiagramElement target = ((Connection) next).getTarget();
				if (elements.contains(target) || EcoreUtil.isAncestor(elements, target)) {
					++outbound;
				}
			}
		}
		int inbound = 0;
		for (EObject el: elements) {
			TreeIterator<EObject> iit = el.eAllContents();
			while (iit.hasNext()) {
				EObject next = iit.next();
				if (next instanceof Connection) {
					DiagramElement target = ((Connection) next).getTarget();
					if (target == element || EcoreUtil.isAncestor(element, target)) {
						++inbound;
					}
				}
			}
		}
		
		return new int[] { outbound, inbound };
	}
	
	/**
	 * Sorts rectangles entry set by size and affinity to positioned offset rectangles.
	 * Then positions the first element so it doesn't overlap with the offset rectangles. 
	 * Then passes the remaining elements for positioning.
	 * @param rectangles
	 * @param offsetRectangles
	 */
	protected void position(
			List<Map.Entry<DiagramElement, Rectangle>> rectangles, 
			Map<DiagramElement, Rectangle> offsetRectangles,
			Point offset,
			Predicate<Rectangle> downPredicate) {
		if (rectangles.isEmpty()) {
			return;
		}
		
		Entry<DiagramElement, Rectangle> firstEntry = rectangles.get(0);
		Rectangle firstRectangle = firstEntry.getValue();
		position(firstRectangle, offsetRectangles.values(), downPredicate.test(firstRectangle)); 
		Rectangle offsetRectangle = new Rectangle(firstRectangle);
		offsetRectangle.translate((int) -offset.getX(), (int) - offset.getY());
		offsetRectangle.grow(2 * (int) offset.getX(), 2 * (int) offset.getY());
		offsetRectangles.put(firstEntry.getKey(), offsetRectangle);			
		
		position(rectangles.subList(1, rectangles.size()), offsetRectangles, offset, downPredicate);
	}

	/**
	 * Positions the rectangle so it doesn't overlap with existing rectangles.
	 * @param element
	 * @param geometry
	 * @param rectangle
	 * @param rectangles
	 */
	protected void position(
			Rectangle rectangle, 
			Collection<Rectangle> offsetRectangles,
			boolean down) {
		
		if (offsetRectangles.isEmpty()) {
			return;
		}
		
		// Start at the center of offset rectangles union
		Rectangle union = null;
		for (Rectangle offsetRectangle: offsetRectangles) {
			if (union == null) {
				union = new Rectangle(offsetRectangle);
			} else {
				union.add(offsetRectangle); 
			}
		}
		double unionCenter = (union.getX() + union.getWidth())/2;
		double unionMiddle = (union.getY() + union.getHeight())/2;

		double rectangleCenter = (rectangle.getX() + rectangle.getWidth())/2;
		double rectangleMiddle = (rectangle.getY() + rectangle.getHeight())/2;
		
		rectangle.translate((int) (unionCenter - rectangleCenter), (int) (unionMiddle - rectangleMiddle));
	
		Supplier<Point> offsetGenerator = org.nasdanika.drawio.Util.createOffsetGenerator(10, down);
		Z: for (Point offset = offsetGenerator.get(); offset != null; offset = offsetGenerator.get()) {
			rectangle.translate((int) offset.getX(), (int) offset.getY());
			for (Rectangle offsetRectangle: offsetRectangles) {
				if (!rectangle.intersection(offsetRectangle).isEmpty()) {
					continue Z;
				}
			}
			break; // No intersection.
		}
	}

	/**
	 * Creates a rectangle for the diagram element and its children at position 0, 0.
	 * @param element
	 * @param offset
	 * @return
	 */
	private Rectangle createElementRectangle(DiagramElement element, Point offset, BiConsumer<DiagramElement, Rectangle> geometryConsumer) {
		
		Rectangle rectangle = new Rectangle(0, 0, elementWidth(element), elementHeight(element));
		EList<DiagramElement> children = element.getElements();
		if (!children.isEmpty()) {
			Point childOffset = new Point((int) offset.getX(), (int) offset.getY() + 30);
			Map<DiagramElement, Rectangle> childGeometry = layout(children, childOffset, geometryConsumer);
			for (DiagramElement child: children) {
				Rectangle childRectangle = childGeometry.get(child);
				rectangle.add(childRectangle);
			}
			rectangle.grow((int) offset.getX(), (int) offset.getY());
		}
		geometryConsumer.accept(element, rectangle);
		return rectangle;
	}

	private int elementWidth(DiagramElement element) {
		for (Property property: element.getProperties()) {
			if ("drawio".equals(property.getName()) && property instanceof MapProperty) {
				for (Property drawioProperty: ((MapProperty) property).getValue()) {
					if (WIDTH_PROPERTY.equals(drawioProperty.getName()) && drawioProperty instanceof IntegerProperty) {
						return ((IntegerProperty) drawioProperty).getValue();
					}
				}
			}
		}
		
		return (renderName(element).length() + 2) * 7;
	}

	private int elementHeight(DiagramElement element) {
		for (Property property: element.getProperties()) {
			if ("drawio".equals(property.getName()) && property instanceof MapProperty) {
				for (Property drawioProperty: ((MapProperty) property).getValue()) {
					if (HEIGHT_PROPERTY.equals(drawioProperty.getName()) && drawioProperty instanceof IntegerProperty) {
						return ((IntegerProperty) drawioProperty).getValue();
					}
				}
			}
		}
		return 30;
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
		
		for (Note note: connection.getNotes()) {
			ret.append("note on link").append(System.lineSeparator());
			ret.append(renderNote(note));
			ret.append("end note").append(System.lineSeparator());
		}
		
		return ret.toString();
	}	
		
	/**
	 * Creates a cell for diagram element and puts element to cell entry to the elements map for creating connections.
	 * @param element
	 * @param graph
	 * @param parent
	 * @param userObjectFactory
	 * @param elementsMap
	 */
	protected Node createElement(
			DiagramElement element, 
			org.nasdanika.drawio.Layer layer, 
			Function<DiagramElement, Rectangle> geometry,
			Map<DiagramElement, ModelElement> elementsMap,
			Collection<Connection> connections,
			int depth) {
		
		Node elementNode = layer.createNode();
		
		String renderedName = renderName(element);
		if (!Util.isBlank(renderedName)) {
			elementNode.setLabel(renderedName);
		}
				
		Map<String,String> style = elementNode.getStyle();

		String location = element.getLocation();
		if (!Util.isBlank(location)) {
			elementNode.setLink(location);
		}
		String tooltip = element.getTooltip();
		if (!Util.isBlank(tooltip)) {
			elementNode.setTooltip(tooltip);
		}

		style.put("recursiveResize", "0");
//		
//		ret.append(diagramElement.getId());
//		
//		String stereotype = diagramElement.getStereotype();
//		if (!Util.isBlank(stereotype)) {
//			ret.append(" <<").append(stereotype).append(">>");
//		}
//		
//		String location = diagramElement.getLocation();
//		if (!Util.isBlank(location)) {
//			ret.append(" ").append(renderLink(null, location, diagramElement.getTooltip()));
//		}
//		
		String color = element.getColor();
		if (Util.isBlank(color) && element.getElements().isEmpty()) {
			color = "#fefece";
		}
		if (!Util.isBlank(color)) {
			try {
				Integer.parseInt(color, 16);
				style.put("fillColor", "#" + color.toLowerCase());				
			} catch (NumberFormatException e) {
				style.put("fillColor", color);
			}
		}
		
		if (!element.getElements().isEmpty()) {
			style.put("verticalAlign", "top");
		}
//		
//		String borderColor = diagramElement.getBorder();
//		if (!Util.isBlank(borderColor)) {
//			styleBuilder.append(styleBuilder.length() == 0 ? " #" : ";");
//			styleBuilder.append("line:").append(borderColor);
//		}
//				
//		if (diagramElement.isDashed()) {
//			styleBuilder.append(styleBuilder.length() == 0 ? " #" : ";");
//			styleBuilder.append("line.dashed");
//		} else if (diagramElement.isDotted()) {
//			styleBuilder.append(styleBuilder.length() == 0 ? " #" : ";");
//			styleBuilder.append("line.dotted");
//		} else if (diagramElement.isBold()) {
//			styleBuilder.append(styleBuilder.length() == 0 ? " #" : ";");
//			styleBuilder.append("line.bold");
//		}
//		
//		ret.append(styleBuilder);
//		
//		// TODO - styling like background
//		
//		EList<EObject> description = diagramElement.getDescription();
//		if (!description.isEmpty()) {
//			ret.append(" : ");
//			ret.append(render(description));
//		}
//
		StringBuilder styleBuilder = new StringBuilder();
		for (Property property: element.getProperties()) {
			if ("drawio".equals(property.getName()) && property instanceof MapProperty) {
				for (Property drawioProperty: ((MapProperty) property).getValue()) {
					if ("style".equals(drawioProperty.getName())) {
						if (drawioProperty instanceof StringProperty) {
							styleBuilder.append(((StringProperty) drawioProperty).getName());
						} else if (drawioProperty instanceof MapProperty) {
							for (Property styleProperty: ((MapProperty) drawioProperty).getValue()) {
								if (styleProperty instanceof StringProperty) {
									style.put(styleProperty.getName(), ((StringProperty) styleProperty).getValue());
								}
								// TODO - Integer and boolean properties.
							}
						}
					} else if (!HEIGHT_PROPERTY.equals(drawioProperty.getName()) && !WIDTH_PROPERTY.equals(drawioProperty.getName())) {
						if (drawioProperty instanceof StringProperty) {
							elementNode.setProperty(drawioProperty.getName(), ((StringProperty) drawioProperty).getValue());
						}
					}
				}
			}
		}
		
		if (!style.isEmpty()) {
			for (Entry<String, String> se: style.entrySet()) {
				if (styleBuilder.length() > 0) {
					styleBuilder.append(";");
				}
				styleBuilder.append(se.getKey()).append("=").append(se.getValue());
			}
		}		
		Rectangle elementGeometry = Objects.requireNonNull(geometry.apply(element), "Element geometry not found");
		org.nasdanika.drawio.Rectangle nodeGeometry = elementNode.getGeometry();
		nodeGeometry.setBounds((int) elementGeometry.getX(), (int) elementGeometry.getY(), (int) elementGeometry.getWidth(), (int) elementGeometry.getHeight()); 
		elementsMap.put(element, elementNode);
		
		EList<DiagramElement> elements = element.getElements();		
		if (!elements.isEmpty()) {
			List<Node> childNodes = new ArrayList<>();
			for (DiagramElement child: elements) {
				childNodes.add(createElement(child, elementNode, geometry, elementsMap, connections, depth + 1));
			}
		}
		
//		
//		for (Note note: diagramElement.getNotes()) {
//			ret
//				.append("note ")
//				.append(note.getPlacement().name().toLowerCase())
//				.append(" of ")
//				.append(diagramElement.getId())
//				.append(System.lineSeparator());
//			ret.append(renderNote(note));
//			ret.append("end note").append(System.lineSeparator());
//		}
//		
//		return ret.toString();
				
		connections.addAll(element.getConnections());	
		
		return elementNode;
	}

	private String renderName(DiagramElement element) {
		StringBuilder nameBuilder = new StringBuilder();
		String text = element.getText();
		EList<EObject> name = element.getName();
		if (!Util.isBlank(text)) {
			nameBuilder.append(text);
			if (!name.isEmpty()) {
				nameBuilder.append(" ");
			}
		}
		
		nameBuilder.append(render(name));
				
		String renderedName = nameBuilder.toString();
		return renderedName;
	}
	
	/**
	 * Computes element size. For elements without children uses name length. 
	 * For element with children lays out the children. 
	 * @param element
	 * @return
	 */
	protected Point getElementSize(DiagramElement element) {		
		int width = renderName(element).length() * 10 + 20;
		int height = 30;
		
		return new Point(width, height);
	}
		
	/**
	 * Creates a note cell.
	 * @param note
	 * @param graph
	 * @param userObjectFactory
	 * @return Note cell
	 */
	protected Node createNote(Note note, org.nasdanika.drawio.Layer layer) {
		StringBuilder content = new StringBuilder();
		String noteText = note.getText();
		EList<EObject> noteContent = note.getContent();
		if (!Util.isBlank(noteText)) {
			content.append(noteText);
			if (!noteContent.isEmpty()) {
				content.append(" ");
			}
		}
		content.append(render(noteContent));
		content.append(System.lineSeparator());
		
		Node noteNode = layer.createNode();
		noteNode.setLabel(content.toString());
		noteNode.getGeometry().setBounds(0, 0, 160, 60);
		Map<String, String> style = noteNode.getStyle();
		style.put("shape", "note");
		style.put("align", "left");
		style.put("fillColor", "#e3c800");
		style.put("whiteSpace", "wrap");
		style.put("html", "1");
		return noteNode;
	}
	
	// TODO - merging of existing drawio model
	// a) Generate a model from the diagram and then copy geometry (layout) from the existing diagram.
	// b) Modify the existing diagram - drop removed elements, add new, update existing - label, link.
	
	protected String renderLink(Link link) {
		return renderLink(link.getText(), link.getLocation(), link.getTooltip());
	}

	protected String renderLink(String text, String location, String tooltip) {
		if (Util.isBlank(location)) {
			return text;
		}
		StringBuilder ret = new StringBuilder("< a href=\"");
		ret.append(location).append("\"");
		if (!Util.isBlank(tooltip)) {
			ret.append(" title=\"").append(tooltip).append("\"");
		}
		ret.append(">");
		if (!Util.isBlank(text)) {
			ret.append(text);
		}
		ret.append("</a>");
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

	protected String renderNote(Note note) {
		StringBuilder ret = new StringBuilder();
		String noteText = note.getText();
		EList<EObject> noteContent = note.getContent();
		if (!Util.isBlank(noteText)) {
			ret.append(noteText);
			if (!noteContent.isEmpty()) {
				ret.append(" ");
			}
		}
		ret.append(render(noteContent));
		ret.append(System.lineSeparator());
		return ret.toString();
	}

}
