package org.nasdanika.graph.model.util;

import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.NodeProcessor;

/**
 * Service interface for creating flow nodes
 */
public interface NodeProcessorFactory {
	
	/**
	 * Returns true if can create processor for a given transition
	 * @param transition
	 * @return
	 */
	boolean canHandle(EObject node);
	
	/**
	 * Factories are sorted in the natural order of priorities - lower first. 
	 * @return
	 */
	default int priority() {
		return 0;
	}
	
	NodeProcessor<Object, Object, Object, Object> createProcessor(
			ProcessorConfig config, 
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> incomingEndpoints,
			Map<Connection, BiFunction<Object, ProgressMonitor, Object>> outgoingEndpoints,
			Context context,
			ProgressMonitor progressMonitor);

}
