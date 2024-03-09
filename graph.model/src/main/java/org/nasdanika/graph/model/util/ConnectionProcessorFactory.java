package org.nasdanika.graph.model.util;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.model.Connection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;

/**
 * Service interface for creating connection processors
 */
public interface ConnectionProcessorFactory {
	
	/**
	 * Returns true if can create processor for a given connection
	 * @param transition
	 * @return
	 */
	boolean canHandle(Connection<?> connection);
	
	/**
	 * Factories are sorted in the natural order of priorities - lower first. 
	 * @return
	 */
	default int priority() {
		return 0;
	}
	
	ConnectionProcessor<Object, Object, Object, Object> createProcessor(
			ConnectionProcessorConfig<BiFunction<Object,ProgressMonitor,Object>, BiFunction<Object,ProgressMonitor,Object>> config,
			BiConsumer<Element, BiConsumer<ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			Context context,
			ProgressMonitor progressMonitor);	

}
