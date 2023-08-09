package org.nasdanika.graph;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.nasdanika.common.ProgressMonitor;

/**
 * Creates a graph from JGraphT {@link Graph}
 */
public class JGraphTFactory<V,E> {
	
	public Element createGraph(Graph<V,E> graph, boolean parallel, ProgressMonitor progressMonitor) {
		Stream<V> vStream = parallel ? graph.vertexSet().parallelStream() : graph.vertexSet().stream();
		Map<V, Node> vMap = vStream
			.map(v -> new AbstractMap.SimpleEntry<>(v, createNode(v, progressMonitor)))
			.filter(e -> e.getValue() != null)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		Stream<E> eStream = parallel ? graph.edgeSet().parallelStream() : graph.edgeSet().stream();
		eStream.forEach(e -> createConnection(e, vMap.get(graph.getEdgeSource(e)), vMap.get(graph.getEdgeTarget(e)), progressMonitor));
		
		return new Element() {
			
			public java.util.Collection<? extends Element> getChildren() {				
				return vMap.values();				
			};
			
		};
		
	}
	
	protected Node createNode(V vertex, ProgressMonitor progressMonitor) {
		return new ObjectNode<>(vertex);
	}
	
	protected Connection createConnection(E edge, Node source, Node target, ProgressMonitor progressMonitor) {
		return new ObjectConnection<>(source, target, false, edge);
	}

}
