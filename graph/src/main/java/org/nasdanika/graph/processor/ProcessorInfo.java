package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

public class ProcessorInfo<H,E,P> extends ProcessorConfigFilter<H,E,ProcessorConfig<H,E>> {
	
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<H,E,P> {
		
		ProcessorInfo<H,E,P> create(
				ProcessorConfig<H,E> config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);		
	}
	
	private P processor;

	public ProcessorInfo(ProcessorConfig<H,E> config, P processor) {
		super(config);
		this.processor = processor;
	}
	
	public P getProcessor() {
		return processor;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <H,E,P> ProcessorInfo<H,E,P> of(ProcessorConfig<H,E> config, P processor) {
		if (config instanceof ConnectionProcessorConfig) {
			return of((ConnectionProcessorConfig) config, processor);
		}

		if (config instanceof NodeProcessorConfig) {
			return of((NodeProcessorConfig) config, processor);
		}		
		
		return new ProcessorInfo<H,E,P>(config, processor);
	}
	
	public static <H,E,P> ConnectionProcessorInfo<H,E,P> of(ConnectionProcessorConfig<H,E> config, P processor) {
		return new ConnectionProcessorInfo<H,E,P>(config, processor);
	}
	
	public static <H,E,P> NodeProcessorInfo<H,E,P> of(NodeProcessorConfig<H,E> config, P processor) {
		return new NodeProcessorInfo<H,E,P>(config, processor);
	}

}
