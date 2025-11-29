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
public abstract class ReflectiveWiringProcessorFactory<H,E,K,P> extends ProcessorFactory<H,E,K,P> {
	
	ReflectiveProcessorWirer<H,E,K,P> wirerer = new ReflectiveProcessorWirer<>();

	@Override
	protected ProcessorInfo<H,E,K,P> createProcessor(
			ProcessorConfig<H,E,K> config, 
			boolean parallel,
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,K,P>,ProgressMonitor>> infoProvider,
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
			ProcessorConfig<H,E,K> config, 
			boolean parallel, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,K,P>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor progressMonitor); 
		
}
