package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

public class ProcessorInfo<H,E,K,P> extends ProcessorConfigFilter<H,E,K,ProcessorConfig<H,E,K>> {
	
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<H,E,K,P> {
		
		ProcessorInfo<H,E,K,P> create(
				ProcessorConfig<H,E,K> config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,K,P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);		
	}
	
	private P processor;

	public ProcessorInfo(ProcessorConfig<H,E,K> config, P processor) {
		super(config);
		this.processor = processor;
	}
	
	public P getProcessor() {
		return processor;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <H,E,K,P> ProcessorInfo<H,E,K,P> of(ProcessorConfig<H,E,K> config, P processor) {
		if (config instanceof ConnectionProcessorConfig) {
			return of((ConnectionProcessorConfig) config, processor);
		}

		if (config instanceof NodeProcessorConfig) {
			return of((NodeProcessorConfig) config, processor);
		}		
		
		return new ProcessorInfo<H,E,K,P>(config, processor);
	}
	
	public static <H,E,K,P> ConnectionProcessorInfo<H,E,K,P> of(ConnectionProcessorConfig<H,E,K> config, P processor) {
		return new ConnectionProcessorInfo<H,E,K,P>(config, processor);
	}
	
	public static <H,E,K,P> NodeProcessorInfo<H,E,K,P> of(NodeProcessorConfig<H,E,K> config, P processor) {
		return new NodeProcessorInfo<H,E,K,P>(config, processor);
	}

}
