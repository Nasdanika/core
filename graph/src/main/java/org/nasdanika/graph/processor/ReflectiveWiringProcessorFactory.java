package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

/**
 * Base clsss for processor factories reflectively wiring processors.
 * @param <P>
 */
public abstract class ReflectiveWiringProcessorFactory<P> extends ProcessorFactory<P> {
	
	ReflectiveProcessorWirer<P,?,?> wirerer = new ReflectiveProcessorWirer<>();

	@Override
	protected ProcessorInfo<P> createProcessor(
			ProcessorConfig config, 
			boolean parallel,
			BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor progressMonitor) {
		
		P processor = doCreateProcessor(
				config, 
				parallel, 
				infoProvider, 
				endpointWiringStageConsumer, 
				progressMonitor);
		
		if (processor != null) {
			wirerer.wireProcessor(
					config, 
					processor, 
					true, 
					parallel, 
					infoProvider, 
					endpointWiringStageConsumer, 
					progressMonitor);
		}
		
		
		return ProcessorInfo.of(config, processor);
	}

	protected abstract P doCreateProcessor(
			ProcessorConfig config, 
			boolean parallel, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor progressMonitor); 
		
}
