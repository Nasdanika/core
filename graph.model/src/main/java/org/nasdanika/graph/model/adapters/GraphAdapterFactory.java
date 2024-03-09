package org.nasdanika.graph.model.adapters;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.model.Connection;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;

/**
 * Reflective factory to create adapters
 */
public class GraphAdapterFactory {
	
	@org.nasdanika.common.Transformer.Factory
	public NodeAdapter createtGraphElementAdapter(
			GraphElement graphElement,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Element>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {

		return new NodeAdapter(graphElement, elementProvider);
	}
	
	@org.nasdanika.common.Transformer.Factory
	public NodeAdapter createtGraphAdapter(
			Graph<?> graph,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Element>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {

		return new NodeAdapter(graph, elementProvider);
	}
	
	@org.nasdanika.common.Transformer.Factory
	public ConnectionAdapter createtConnectionAdapter(
			Connection<?> connection,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Element>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {

		return new ConnectionAdapter(connection, elementProvider);
	}

}
