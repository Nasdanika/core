package org.nasdanika.diagram.gen;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.End;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Note;
import org.nasdanika.diagram.Start;
import org.nasdanika.exec.content.Text;
import org.nasdanika.ncore.IntegerProperty;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.StringProperty;
import org.nasdanika.ncore.util.NcoreUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;

/**
 * Generates Drawio diagrams.
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
	 * @throws Exception 
	 */
	public String generateDiagram(Diagram diagram) throws Exception {
		return diagramGenerator.generateDrawioDiagram(generateModel(diagram));
	}

	/**
	 * Generates diagram model in .drawio format which can be further converted to HTML or stored to a file for manual editing.
	 * @param diagram
	 * @return
	 */
	public String generateModel(Diagram diagram) throws Exception {
		mxCodec codec = new mxCodec();
		mxIGraphModel model = generateGraph(diagram).getModel();
		Node encodedNode = codec.encode(model);
//		dump((mxCell) model.getRoot(), 0);
		String encodedDiagram = encodeDiagram(encodedNode, diagram.getUuid(), diagram.getName());
		return encodedDiagram;
	}
	
	/**
	 * Encodes diagram node to .drawio format without compression and with indenting to make it more human readable and comparable/mergeable.
	 * @param diagramNode
	 * @return
	 * @throws Exception
	 */
	protected String encodeDiagram(Node diagramNode, String id, String name) throws Exception {
		String templateSource = DefaultConverter.INSTANCE.stringContent(getClass().getResource("template.drawio"));
		Document xmlDocument = mxXmlUtils.parseXml(templateSource);
        Element diagram = (Element) xmlDocument.getElementsByTagName("diagram").item(0);
        if (!Util.isBlank(id)) {
        	diagram.setAttribute("id", id);
        }
        if (!Util.isBlank(name)) {
        	diagram.setAttribute("name", name);
        }
        diagram.appendChild(xmlDocument.importNode(diagramNode, true));
        
		Transformer transformer = TransformerFactory.newInstance().newTransformer();

		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		StreamResult dest = new StreamResult(new StringWriter());
		transformer.transform(new DOMSource(xmlDocument), dest);

		return dest.getWriter().toString();
	}
	
//	/**
//	 * Decodes compressed text content of the diagram element.
//	 * @param diagramSource
//	 * @return
//	 * @throws Exception
//	 */
//	protected String decodeDiagram(String diagramSource) throws Exception {
//		Document xmlDocument = mxXmlUtils.parseXml(diagramSource);
//        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
//        String textContent = diagram.getTextContent();
//
//        byte[] compressed = Base64.getDecoder().decode(textContent);
//        byte[] decompressed = inflate(compressed);
//        String decompressedStr = new String(decompressed, getCharset());
//        return URLDecoder.decode(decompressedStr, getCharset().name());
//	}
//		
//    private byte[] inflate(byte[] content) throws Exception {
//    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    	try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new InflaterOutputStream(baos, new Inflater(true))) {
//            byte[] buf = new byte[8192];
//            int length;
//            while ((length = source.read(buf)) > 0) {
//                target.write(buf, 0, length);
//            }
//    	}
//    	
//    	return baos.toByteArray();
//    }
    
    /**
     * {@link Charset} for encoding/decoding.
     * @return This implementation returns UTF-8.
     */
    protected Charset getCharset() {
    	return StandardCharsets.UTF_8;
    }

	protected mxGraph generateGraph(Diagram diagram) throws Exception {
		mxGraph graph = new mxGraph(/* graphModel */);
		
		mxIGraphModel graphModel = graph.getModel();
		graphModel.beginUpdate();
		Object parent = graph.getDefaultParent();	
		try	{
			Document userObjectFactory = mxDomUtils.createDocument();
			for (Note note: diagram.getNotes()) {
				createNote(note, graph, parent, userObjectFactory);				
			}
			Map<DiagramElement,mxCell> elementsMap = new HashMap<>();
			Map<DiagramElement, Rectangle> geometry = new HashMap<>();
			layout(diagram, geometry::put);
			
			Collection<Connection> connections = new ArrayList<>(); 
			for (DiagramElement element: diagram.getElements()) {
				createElement(element, graph, parent, userObjectFactory, geometry::get, elementsMap, connections, 0);
			}
			
			for (Connection connection: connections) {
				mxCell source = Objects.requireNonNull(elementsMap.get(connection.eContainer()), "Source cell not found");
				mxCell target = Objects.requireNonNull(elementsMap.get(connection.getTarget()), "Target cell not found");
				EObject commonAncestor = NcoreUtil.commonAncestor(connection.eContainer(), connection.getTarget());
				mxCell commonParent = elementsMap.get(commonAncestor);
				createConnection(connection, graph, commonParent == null ? parent : commonParent, source, target, userObjectFactory);
			}
		} finally {
			graphModel.endUpdate();
		}		
		return graph;	
	}

	/**
	 * Computes sizes and positions of diagram elements.
	 * @param diagram
	 * @return
	 */
	protected Map<DiagramElement, Rectangle> layout(Diagram diagram, BiConsumer<DiagramElement, Rectangle> geometryConsumer) {
		return layout(diagram.getElements(), new Point(10,10), geometryConsumer);
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
	
		Supplier<Point> offsetGenerator = createOffsetGenerator(down);
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
	 * @return generator of a sequence of points which are offset from the initial point for positioning of a rectangle. 
	 * This implementation returns a sequence of offsets which form concentric half-circles up or down.
	 * The generator returns null after some large number of invocations to prevent infinite positioning.   
	 */
	protected Supplier<Point> createOffsetGenerator(boolean down) {
		return new Supplier<Point>() {
			
			private int counter;
			
			private double lastX;
			private double lastY;
			
			private int radiusIncrement = 10;
			
			private int angleSteps = 18;
			
			private double initialAngle = down ? Math.PI * 0.75 : Math.PI * 0.25;

			@Override
			public Point get() {
				++counter;

				int radius = radiusIncrement * (1 + counter / angleSteps);
				double angle = initialAngle;
				double angleDelta = Math.PI * (double) (counter / 2) / angleSteps; 
				if (angleSteps % 2 == 0) {
					angle += angleDelta;
				} else {
					angle -= angleDelta;
				}
				
				double x = radius * Math.sin(angle);
				double y = radius * Math.cos(angle);
				Point point = new Point((int) (x - lastX), (int) (y - lastY));
				lastX = x;
				lastY = y;
				return point; 
			}
			
		};
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

	protected void createConnection(
			Connection connection,
			mxGraph graph,
			Object parent, 
			mxCell source, 
			mxCell target,
			Document userObjectFactory) {
		
		Element userObject = userObjectFactory.createElement("UserObject");
//		String dataLink = "data:action/json,{\"actions\":[{\"open\":\"javascript:alert(\\\"Hello World\\\")\"}]}";
//		userObject.setAttribute("link", dataLink);
//		userObject.setAttribute("linkTarget", "self");
//		userObject.setAttribute("label", renderConconnection.get); TODO
				
		mxCell edge = (mxCell) graph.insertEdge(parent, null, userObject, source, target); // TODO - ID
		edge.setStyle("strokeColor=#000000");
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
	protected mxCell createElement(
			DiagramElement element, 
			mxGraph graph, 
			Object parent, 
			Document userObjectFactory,
			Function<DiagramElement, Rectangle> geometry,
			Map<DiagramElement, mxCell> elementsMap,
			Collection<Connection> connections,
			int depth) {
		
//		Element v1uo = doc.createElement("UserObject");
//		v1uo.setAttribute("label", "Hren\nsobachya");
//		v1uo.setAttribute("link", "https://nasdanika.org");
//		v1uo.setAttribute("uri", "nasdanika://hmmm");
//		mxCell v1 = (mxCell) graph.insertVertex(parent, null, v1uo, 40, 40, 160, 60);
//		v1.setStyle("verticalAlign=top;fillColor=#fefece");
//		Object v11 = graph.insertVertex(v1, null, "Purum", 20, 20, 80, 30);
//		Object v2 = graph.insertVertex(parent, null, "World!", 280, 250, 160, 60);
//		mxCell v21 = (mxCell) graph.insertVertex(parent, null, "Govno", 20, 20, 80, 30);
//		v21.setStyle("fillColor=#fefece");
//		graph.insertEdge(parent, null, "Edge", v1, v2);
//		graph.insertEdge(parent, null, "Cross Edge", v11, v21);
		
		
//		if (diagramElement instanceof Start) {
//			return "";
//		}
//		if (diagramElement instanceof End) {
//			return "";
//		}
//		
//		StringBuilder ret = new StringBuilder();
//		
//		for (int i = 0; i < depth; ++i) {
//			ret.append("  ");
//		}
//		
//		ret.append(diagramElement.getType()).append(" ");		
//		
		Element userObject = userObjectFactory.createElement("UserObject");
				
		String renderedName = renderName(element);
		if (!Util.isBlank(renderedName)) {
			userObject.setAttribute("label", renderedName);
		}
				
		Map<String,String> style = new LinkedHashMap<>();

		String location = element.getLocation();
		if (!Util.isBlank(location)) {
			userObject.setAttribute("link", location);
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
							userObject.setAttribute(drawioProperty.getName(), ((StringProperty) drawioProperty).getValue());
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
		Rectangle cellGeometry = Objects.requireNonNull(geometry.apply(element), "Element geometry not found");
		mxCell cell = (mxCell) graph.insertVertex(
				parent, 
				element.getId(), 
				userObject, 
				cellGeometry.getX(), 
				cellGeometry.getY(), 
				cellGeometry.getWidth(), 
				cellGeometry.getHeight(), 
				styleBuilder.length() == 0 ? null : styleBuilder.toString());
		
		elementsMap.put(element, cell);
		
		EList<DiagramElement> elements = element.getElements();		
		if (elements.isEmpty()) {
//			graph.updateCellSize(cell, true);
		} else {
			List<mxCell> childrenCells = new ArrayList<>();
			for (DiagramElement child: elements) {
				childrenCells.add(createElement(child, graph, cell, userObjectFactory, geometry, elementsMap, connections, depth + 1));
			}

			mxGeometry geo = (mxGeometry) cell.getGeometry().clone();
			for (mxCell childCell: childrenCells) {
				mxGeometry childGeometry = childCell.getGeometry();
				geo.setWidth(Math.max(geo.getWidth(), childGeometry.getX() + childGeometry.getWidth() + 10));
				geo.setHeight(Math.max(geo.getHeight(), childGeometry.getY() + childGeometry.getHeight() + 10));
			}
			graph.resizeCell(cell, geo);
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
		
		return cell;
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
	protected mxCell createNote(Note note, mxGraph graph, Object parent, Document userObjectFactory) {
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
		
		Element noteUserObject = userObjectFactory.createElement("UserObject");
		noteUserObject.setAttribute("label", content.toString());
		mxCell noteCell = (mxCell) graph.insertVertex(parent, null, noteUserObject, 40, 40, 160, 60); // TODO - compute position and size, relative?
		noteCell.setStyle("shape=note;align=left;fillColor=#e3c800");		
		return noteCell;
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
	
	public static void dump(mxICell cell, int indent) {
		for (int i = 0; i < indent; ++i) {
			System.out.print("  ");
		}
		Object cellValue = cell.getValue();
		if (cellValue instanceof Element) {
			String label = ((Element) cellValue).getAttribute("label");
			if (!Util.isBlank(label)) {
				System.out.print(label + " ");
			}
		}
		System.out.print(cell);
		System.out.println();
		for (int i = 0; i < cell.getChildCount(); ++i) {
        	mxICell child = cell.getChildAt(i);
			dump(child, indent + 1);
        }
	}		

}
