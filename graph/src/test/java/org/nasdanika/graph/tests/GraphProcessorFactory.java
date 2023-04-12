package org.nasdanika.graph.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.json.JSONObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class GraphProcessorFactory implements NopEndpointProcessorFactory<Supplier<EClassifierRecord>, Void, Registry> {

	@Override
	public Registry createRegistry(Map<Element, ProcessorInfo<Supplier<EClassifierRecord>, Registry>> registry) {
		return new Registry(registry);
	}
	
	@Override
	public ProcessorInfo<Supplier<EClassifierRecord>, Registry> createProcessor(
			ProcessorConfig<Supplier<EClassifierRecord>, Registry> config,
			ProgressMonitor progressMonitor) {
		
		if (config.getElement() instanceof EObjectNode) {
			EObjectNode eObjectNode = (EObjectNode) config.getElement();
			EObject target = eObjectNode.getTarget();
			if (target instanceof EClassifier) {
				EClassifier eClassifier = (EClassifier) target;
				Supplier<EClassifierRecord> processor = () -> {
					JSONObject jObj = new JSONObject();
					String name = eClassifier.getName();
					jObj.put("name", name);				
					jObj.put("id", name);
					
					List<Connection> allOutgoingConnections = eObjectNode.accept(this::outgoingConnections);
					
					// Filtering out eAll and grouping by target
					List<EReferenceConnection> filteredConnections = allOutgoingConnections
						.stream()
						.filter(EReferenceConnection.class::isInstance)
						.map(EReferenceConnection.class::cast)
						.filter(c -> !c.getReference().getName().startsWith("eAll") && !c.getReference().getName().startsWith("eGeneric"))
						.collect(Collectors.toList());
					
					Map<Node, List<EReferenceConnection>> groupedBy = org.nasdanika.common.Util.groupBy(filteredConnections, Connection::getTarget);
					
					Collection<JSONObject> links = new ArrayList<>();					
					
					groupedBy
						.entrySet()
						.stream()
						.map(e -> Map.entry(((EObjectNode) e.getKey()).getTarget(), e.getValue()))
						.filter(e -> e.getKey() instanceof EClassifier)
						.forEach(e -> {
							JSONObject jLink = new JSONObject();
							jLink.put("source", eClassifier.getName());
							jLink.put("target", ((EClassifier) e.getKey()).getName());
							jLink.put("cardinality", e.getValue().size());
							jLink.put("references", e.getValue().stream().map(c -> c.getReference().getName()).collect(Collectors.toSet()));
							links.add(jLink);
							
						});
					return new EClassifierRecord(jObj, links);
				};								
				
				return ProcessorInfo.of(config, processor, null);
			}
		}
		return NopEndpointProcessorFactory.super.createProcessor(config, progressMonitor);
	}
	
	/**
	 * Collects outgoing collections
	 * @param element
	 * @param childConnections
	 * @return
	 */
	private List<Connection> outgoingConnections(Element element, Map<? extends Element, List<Connection>> childConnections) {
		List<Connection> ret = new ArrayList<>();
		if (element instanceof Node) {
			ret.addAll(((Node) element).getOutgoingConnections());
		}
		for (List<Connection> cc: childConnections.values()) {
			ret.addAll(cc);
		}
		return ret;
	}		

}


//private record ConnectionRecord(String source, String target, int cardinality, List<String> references) {};
//private record NodesAndConnections(List<JSONObject> nodes, List<EReferenceConnection> connections) {};
//
//for (EObjectNode node: nodes) {
////	jNodes.addAll(nodesAndConnections.nodes);
////	connections.addAll(nodesAndConnections.connections);
//}
//JSONArray jConnections = new JSONArray();
//for (ConnectionRecord c: connections) {
//}
//
//
//
////private NodesAndConnections nodesAndConnections(Element element, Map<? extends Element, NodesAndConnections> childNodesAndConnections) {		
////	List<JSONObject> nodes = new ArrayList<>();
////	List<ConnectionRecord> connections = new ArrayList<>();
////	if (element instanceof EObjectNode) {
////		EObject target = ((EObjectNode) element).getTarget();
////		if (target instanceof EClassifier) {
////			JSONObject jObj = new JSONObject();
////			String name = ((ENamedElement) target).getName();
////			jObj.put("name", name);				
////			jObj.put("id", name);			
////			nodes.add(jObj);				
////			@SuppressWarnings({ "rawtypes", "unchecked" })
////			Map<Node, List<Connection>> groupedBy = (Map) org.nasdanika.common.Util.groupBy(((Node) element).getOutgoingConnections(), Connection::getTarget);
////			for (Entry<Node, List<Connection>> oc: groupedBy.entrySet()) {
////				if (oc.getKey() != element && oc.getKey() instanceof EObjectNode && ((EObjectNode) oc.getKey()).getTarget() instanceof EClassifier) {
////					List<String> refNames = oc
////							.getValue()
////							.stream()
////							.filter(EReferenceConnection.class::isInstance)
////							.map(EReferenceConnection.class::cast)
////							.map(EReferenceConnection::getReference)
////							.map(ENamedElement::getName)
////							.collect(Collectors.toList());
////					
////					connections.add(new ConnectionRecord(name, ((EClassifier) ((EObjectNode) oc.getKey()).getTarget()).getName(), refNames.size(), refNames));
////				}
////			}
////		}
////	} else {
//////		connections.addAll(connections)
////	}
////	for (NodesAndConnections cnc: childNodesAndConnections.values()) {
////		nodes.addAll(cnc.nodes);
////		connections.addAll(cnc.connections);
////	}
////	return new NodesAndConnections(nodes, connections);
////}
