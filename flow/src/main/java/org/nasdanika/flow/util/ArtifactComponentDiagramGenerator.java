package org.nasdanika.flow.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.Link;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Relationship;

/**
 * Generates component diagrams for a collection of artifacts and an artifact context diagram. 
 * @author Pavel
 *
 */
public class ArtifactComponentDiagramGenerator {
	
	DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;

	public void generateDiagram(Collection<Artifact> semanticElements, Diagram diagram) {
		Map<Artifact,DiagramElement> semanticMap = new HashMap<>();
		diagram.getElements().addAll(createDiagramElements(semanticElements, semanticMap, null));
		
		for (Artifact se: semanticMap.keySet()) {
			wire(se, semanticMap);
		}
	}
	
	// TODO - single method - depth, context.
	public void generateContextDiagram(Artifact artifact, Diagram diagram) {
		Collection<Artifact> semanticElements = new HashSet<>();
		collectRelatedElements(artifact, semanticElements);
		
		Map<Artifact,DiagramElement> semanticMap = new HashMap<>();
		diagram.getElements().addAll(createDiagramElements(semanticElements, semanticMap, artifact));
		
		for (Artifact se: semanticElements) {
			wire(se, semanticMap);
		}
	}

	private void collectRelatedElements(Artifact semanticElement, Collection<Artifact> accumulator) { 
		if (semanticElement != null && accumulator.add(semanticElement)) {
			for (Relationship inboundRelationship: semanticElement.getInboundRelationships()) {
				accumulator.add((Artifact) inboundRelationship.eContainer().eContainer());
			}

			for (Relationship ouboundRelationship: semanticElement.getOutboundRelationships().values()) {
				accumulator.add(ouboundRelationship.getTarget());
			}
			
//			if (semanticElement.isPartition()) {
//				for (Artifact child: semanticElement.getChildren().values()) {
//					collectRelatedElements(child, accumulator);
//				}
//			}
		}
	}
	
	protected DiagramElement createDiagramElement(
			Artifact semanticElement, 
			Map<Artifact, DiagramElement> semanticMap, 
			Artifact contextElement) {

		DiagramElement template = semanticElement.getStyle();		
		org.nasdanika.diagram.DiagramElement ret = template == null ? diagramFactory.createDiagramElement() : EcoreUtil.copy(template);
		semanticMap.put(semanticElement, ret);
		if (Util.isBlank(ret.getType())) {
			ret.setType("component");
		}
		
		ret.setText(semanticElement.getName());
		ret.setLocation(getArtifactLocation(semanticElement));
		ret.setTooltip(getArtifactTooltip(semanticElement));
			
		if (semanticElement.isPartition()) {
			ret.getElements().addAll(createDiagramElements(semanticElement.getChildren().values(), semanticMap, contextElement));
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
		
		if (contextElement == null || semanticElement == contextElement) {
			if (Util.isBlank(ret.getColor())) {
				ret.setColor("FEFECE"); 				
			}
		} else {
			ret.setColor("DDDDDD");
		}
		
		return ret;
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
			Artifact contextElement) {		
		List<DiagramElement> ret = new ArrayList<>();
		for (Artifact semanticElement: semanticElements) {
			ret.add(createDiagramElement(semanticElement, semanticMap, contextElement));
		}
		return ret;
	}
	
	protected String getArtifactLocation(Artifact semanticElement) {
		return null;
	}
	
	protected String getArtifactTooltip(Artifact semanticElement) {
		return null;
	}
	
	protected void wire(Artifact semanticElement, Map<Artifact, DiagramElement> semanticMap) {
		EList<Connection> connections = semanticMap.get(semanticElement).getConnections();
		
		for (Relationship outboundRelationship: semanticElement.getOutboundRelationships().values()) {
			DiagramElement connectionTarget = semanticMap.get(outboundRelationship.getTarget());
			if (connectionTarget != null) {
				Connection template = outboundRelationship.getStyle();
				Connection connection = template == null ? diagramFactory.createConnection() : EcoreUtil.copy(template);
				connections.add(connection);
				connection.setTarget(connectionTarget);
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
			}
		}
	}
		
	protected String getRelationshipLocation(Relationship relationship) {
		return null;
	}
	
	protected String getRelationshipTooltip(Relationship relationship) {
		return null;
	}
	
}
