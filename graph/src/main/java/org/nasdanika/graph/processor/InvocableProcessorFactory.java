package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

/**
 * Creates processors by loading {@link Invocable}s by {@link URI} and calling them.
 * @param <P>
 */
public abstract class InvocableProcessorFactory<H,E,P> extends ReflectiveWiringProcessorFactory<H,E,P> {
	
	@SuppressWarnings("unchecked")
	@Override
	protected P doCreateProcessor(
			ProcessorConfig<H,E> config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<H,E,P>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		Invocable processorFactory = getProcessorFactory(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
		if (processorFactory == null) {
			return createDefaultProcessor(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);					
		}
		return (P) processorFactory.invoke(config, infoProvider, endpointWiringStageConsumer, progressMonitor);
	}
	
	protected P createDefaultProcessor(
			ProcessorConfig<H,E> config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<H,E,P>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		return null;
	}

	protected abstract Invocable getProcessorFactory(
			ProcessorConfig<H,E> config, 
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<H,E,P>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor);

}
