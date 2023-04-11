package org.nasdanika.graph.tests;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.emf.Util;


public class TestGraph {
	
	private record ConnectionRecord(String source, String target, int cardinality, List<String> references) {};
	private record NodesAndConnections(List<JSONObject> nodes, List<ConnectionRecord> connections) {};
		
	@Test
	public void testECoreGraph() {
		List<EObjectNode> nodes = Util.load(Collections.singleton(EcorePackage.eINSTANCE));
		List<JSONObject> jNodes = new ArrayList<>();
		List<ConnectionRecord> connections = new ArrayList<>();
		for (EObjectNode node: nodes) {
			NodesAndConnections nodesAndConnections = node.accept(this::nodesAndConnections);
			jNodes.addAll(nodesAndConnections.nodes);
			connections.addAll(nodesAndConnections.connections);
		}
		JSONObject result = new JSONObject();
		result.put("nodes", jNodes);
		JSONArray jConnections = new JSONArray();
		for (ConnectionRecord c: connections) {
			JSONObject jConnection = new JSONObject();
			jConnection.put("source", c.source());
			jConnection.put("target", c.target());
			jConnection.put("cardinality", c.cardinality());
			jConnection.put("references", c.references());
			jConnections.put(jConnection);
		}
		result.put("links", jConnections);
		System.out.println(result.toString(4));
	}
	
	private NodesAndConnections nodesAndConnections(Element element, Map<? extends Element, NodesAndConnections> childNodesAndConnections) {		
		List<JSONObject> nodes = new ArrayList<>();
		List<ConnectionRecord> connections = new ArrayList<>();
		if (element instanceof EObjectNode) {
			EObject target = ((EObjectNode) element).getTarget();
			if (target instanceof EClassifier) {
				JSONObject jObj = new JSONObject();
				String name = ((ENamedElement) target).getName();
				jObj.put("name", name);				
				jObj.put("id", name);			
				nodes.add(jObj);				
				@SuppressWarnings({ "rawtypes", "unchecked" })
				Map<Node, List<Connection>> groupedBy = (Map) org.nasdanika.common.Util.groupBy(((Node) element).getOutgoingConnections(), Connection::getTarget);
				for (Entry<Node, List<Connection>> oc: groupedBy.entrySet()) {
					if (oc.getKey() != element && oc.getKey() instanceof EObjectNode && ((EObjectNode) oc.getKey()).getTarget() instanceof EClassifier) {
						List<String> refNames = oc
								.getValue()
								.stream()
								.filter(EReferenceConnection.class::isInstance)
								.map(EReferenceConnection.class::cast)
								.map(EReferenceConnection::getReference)
								.map(ENamedElement::getName)
								.collect(Collectors.toList());
						
						connections.add(new ConnectionRecord(name, ((EClassifier) ((EObjectNode) oc.getKey()).getTarget()).getName(), refNames.size(), refNames));
					}
				}
			}
		} else {
//			connections.addAll(connections)
		}
		for (NodesAndConnections cnc: childNodesAndConnections.values()) {
			nodes.addAll(cnc.nodes);
			connections.addAll(cnc.connections);
		}
		return new NodesAndConnections(nodes, connections);
	}
	
}
