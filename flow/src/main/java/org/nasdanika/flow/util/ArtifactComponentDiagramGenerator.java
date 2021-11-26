package org.nasdanika.flow.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Style;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.flow.Relationship;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.Property;

/**
 * Generates component diagrams for a collection of artifacts and an artifact context diagram. 
 * @author Pavel
 *
 */
public class ArtifactComponentDiagramGenerator {
	
	DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;
	
	public void generateDiagram(Artifact artifact, Diagram diagram) {
		// Collecting all elements to be included
		Map<Artifact,Integer> semanticElements = new HashMap<>();
		int context = diagram.getContext();
		if (context == -1) {
			// Only child elements
			for (Artifact child: artifact.getChildren().values()) {
				collectRelatedElements(child, semanticElements, 0);				
			}
		} else {
			collectRelatedElements(artifact, semanticElements, context);
		}
		
		// Selecting top-level elements
		Collection<Artifact> topLevelElements = new HashSet<>();
		semanticElements.keySet().stream()
			.filter(se -> !EcoreUtil.isAncestor(topLevelElements, se))
			.forEach(se -> {
				topLevelElements.removeIf(topLevelElement -> EcoreUtil.isAncestor(se, topLevelElement));
				topLevelElements.add(se);
			});
		
		Map<Artifact,DiagramElement> semanticMap = new HashMap<>();
		diagram.getElements().addAll(createDiagramElements(topLevelElements, semanticMap, context == 0 ? null : artifact, diagram.getDepth()));
		
		for (Artifact se: topLevelElements) {
			wire(se, semanticMap);
		}
	}

	private void collectRelatedElements(Artifact semanticElement, Map<Artifact, Integer> accumulator, int depth) {
		if (semanticElement == null) {
			return;
		}
		Integer sDepth = accumulator.get(semanticElement);
		if (sDepth != null && (sDepth < 0 || depth <= sDepth)) {
			return; // Do not traverse if existing depth is negative or depth is less than already traversed depth.
		}
		accumulator.put(semanticElement, depth);
		if (depth != 0) {
			for (Relationship inboundRelationship: semanticElement.getInboundRelationships()) {
				collectRelatedElements((Artifact) inboundRelationship.eContainer().eContainer(), accumulator, depth - 1);
			}

			for (Relationship ouboundRelationship: semanticElement.getOutboundRelationships().values()) {
				collectRelatedElements(ouboundRelationship.getTarget(), accumulator, depth - 1);
			}
		}
		
		for (Artifact child: semanticElement.getChildren().values()) {
			collectRelatedElements(child, accumulator, depth);
		}
	}
	
	protected DiagramElement createDiagramElement(
			Artifact semanticElement, 
			Map<Artifact, DiagramElement> semanticMap, 
			Artifact contextElement,
			int depth) {

		DiagramElement template = semanticElement.getStyle();		
		org.nasdanika.diagram.DiagramElement ret = template == null ? diagramFactory.createDiagramElement() : EcoreUtil.copy(template);
		semanticMap.put(semanticElement, ret);
		if (Util.isBlank(ret.getType())) {
			ret.setType("component");
		}
		
		ret.setText(semanticElement.getName());
		ret.setLocation(getArtifactLocation(semanticElement));
		ret.setTooltip(getArtifactTooltip(semanticElement));
			
		if (depth != 0 && semanticElement.isPartition()) {
			ret.getElements().addAll(createDiagramElements(semanticElement.getChildren().values(), semanticMap, contextElement, depth - 1));
		}
	
		if (template == null) {
			EList<String> modifiers = semanticElement.getModifiers();
			if (modifiers.contains("final")) {
				ret.setBold(true);
			} else if (modifiers.contains("abstract")) {
				ret.setDashed(true);
			}
			
			if (modifiers.contains("optional")) {
				ret.setBorder("grey");
			}
		}
		
		if (contextElement == null || semanticElement == contextElement || EcoreUtil.isAncestor(contextElement, semanticElement)) {
			if (Util.isBlank(ret.getColor())) {
				ret.setColor("FEFECE"); 				
			}
		} else {
			ret.setColor("DDDDDD");
		}
		
		setProperties(semanticElement, ret);
		return ret;
	}
	
	/**
	 * Copies semantic element properties under "representation" property, if it is present, to style properties. 
	 * @param semanticElement
	 * @param style
	 */
	public static void setProperties(PackageElement<?> semanticElement, Style style) {
		for (Property property: semanticElement.getProperties()) {
			if ("representation".equals(property.getName()) && property instanceof MapProperty) {
				style.getProperties().addAll(EcoreUtil.copyAll(((MapProperty) property).getValue()));
			}
		}
	}

	/**
	 * Creates diagram for flow elements potentially grouping them.
	 * @param semanticElements 
	 * @param semanticMap
	 * @param contextElement
	 * @return
	 */
	protected List<DiagramElement> createDiagramElements(
			Collection<Artifact> semanticElements,
			Map<Artifact, DiagramElement> semanticMap, 
			Artifact contextElement,
			int depth) {
		List<DiagramElement> ret = new ArrayList<>();
		for (Artifact semanticElement: semanticElements) {
			ret.add(createDiagramElement(semanticElement, semanticMap, contextElement, depth));
		}
		return ret;
	}
	
	protected String getArtifactLocation(Artifact semanticElement) {
		return null;
	}
	
	protected String getArtifactTooltip(Artifact semanticElement) {
		return null;
	}
	
	/**
	 * Collects relationships from the argument and unmapped children.
	 * @param semanticElement
	 * @param semanticMap
	 * @return
	 */
	protected Collection<Relationship> collectAllRelationships(Artifact semanticElement, Map<Artifact, DiagramElement> semanticMap) {
		Collection<Relationship> ret = new ArrayList<>();
		ret.addAll(semanticElement.getOutboundRelationships().values());
		for (Artifact child: semanticElement.getChildren().values()) {
			if (!semanticMap.containsKey(child)) {
				ret.addAll(collectAllRelationships(child, semanticMap));
			}
		}
		return ret;
	}
	
	protected void wire(Artifact semanticElement, Map<Artifact, DiagramElement> semanticMap) {
		EList<Connection> connections = semanticMap.get(semanticElement).getConnections();
		
		Function<Relationship,Artifact> groupByMappedTarget = relationship -> {
			Artifact target = relationship.getTarget(); 
			while (!semanticMap.containsKey(target)) {
				EObject superContainer = target.eContainer().eContainer();
				if (superContainer instanceof Artifact) {
					target = (Artifact) superContainer;
				} else {
					return null;
				}
			}
			return target;		
		};
		
		for (Entry<Artifact, List<Relationship>> outboundRelationshipEntry: Util.groupBy(collectAllRelationships(semanticElement, semanticMap), groupByMappedTarget).entrySet()) {			
			Artifact target = outboundRelationshipEntry.getKey();
			if (target != null && target != semanticElement) {
				DiagramElement connectionTarget = semanticMap.get(target);
				if (connectionTarget != null) {
					List<Relationship> relationships = outboundRelationshipEntry.getValue();
					Connection connection;
					if (relationships.size() == 1) {
						// Single relationship
						Relationship outboundRelationship = relationships.get(0);
						Connection template = outboundRelationship.getStyle();
						connection = template == null ? diagramFactory.createConnection() : EcoreUtil.copy(template);
						if (Util.isBlank(connection.getType())) {
							connection.setType("->");
						}
						String name = outboundRelationship.getName();
						if (!Util.isBlank(name)) {
							Link link = diagramFactory.createLink();
							link.setText(name);
							link.setLocation(getRelationshipLocation(outboundRelationship));
							link.setTooltip(getRelationshipTooltip(outboundRelationship));
							connection.getDescription().add(link);
						}
					} else {
						// Grouped relationship
						connection = createGroupConnection(semanticElement, target, relationships);
					}
					connections.add(connection);
					connection.setTarget(connectionTarget);
				}
			}
		}
		
		for (Artifact child: semanticElement.getChildren().values()) {
			if (semanticMap.containsKey(child)) {
				wire(child, semanticMap);
			}
		}
	}
	
	/**
	 * Creates a connection between to elements from a group of relationships. 
	 * @param source
	 * @param target
	 * @param relationships
	 * @return
	 */
	protected Connection createGroupConnection(Artifact source, Artifact target, List<Relationship> relationships) {
		Connection connection = diagramFactory.createConnection();
		connection.setThickness(3);
		// ports # and o to indicate connections to internal things?
		return connection;
	}
		
	protected String getRelationshipLocation(Relationship relationship) {
		return null;
	}
	
	protected String getRelationshipTooltip(Relationship relationship) {
		return null;
	}
	
}
