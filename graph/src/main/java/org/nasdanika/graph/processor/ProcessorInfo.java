package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.Objects;

import org.nasdanika.graph.Element;

public class ProcessorInfo<P> implements ProcessorConfig {
	
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
