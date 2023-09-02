package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

public class ProcessorInfo<P> implements ProcessorConfig {
	
	/**
	 * Functional interface for processor/info creation.
	 * @param <P>
	 */
	public interface Factory<P> {
		
		ProcessorInfo<P> create(
				ProcessorConfig config, 
				boolean parallel, 
				BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
				Consumer<CompletionStage<?>> endpointWiringStageConsumer,
				Context context,
				ProgressMonitor progressMonitor);		
	}
	
	protected ProcessorConfig config;
	private P processor;

	public ProcessorInfo(ProcessorConfig config, P processor) {
		this.config = Objects.requireNonNull(config);
		this.processor = processor;
	}
	
	public P getProcessor() {
		return processor;
	}

	@Override
	public Element getElement() {
		return config.getElement();
	}

	@Override
	public Map<Element, ProcessorConfig> getChildProcessorConfigs() {
		return config.getChildProcessorConfigs();
	}

	@Override
	public ProcessorConfig getParentProcessorConfig() {
		return config.getParentProcessorConfig();
	}

	@Override
	public Map<Element, ProcessorConfig> getRegistry() {
		return config.getRegistry();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <P> ProcessorInfo<P> of(ProcessorConfig config, P processor) {
		if (config instanceof ConnectionProcessorConfig) {
			return of((ConnectionProcessorConfig) config, processor);
		}

		if (config instanceof NodeProcessorConfig) {
			return of((NodeProcessorConfig) config, processor);
		}		
		
		return new ProcessorInfo<P>(config, processor);
	}
	
	public static <P,H,E> ConnectionProcessorInfo<P,H,E> of(ConnectionProcessorConfig<H,E> config, P processor) {
		return new ConnectionProcessorInfo<P,H,E>(config, processor);
	}
	
	public static <P,H,E> NodeProcessorInfo<P,H,E> of(NodeProcessorConfig<H,E> config, P processor) {
		return new NodeProcessorInfo<P,H,E>(config, processor);
	}

}
