package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.nasdanika.graph.Element;

public class ProcessorConfigFilter<H, E, C extends ProcessorConfig<H,E>> implements ProcessorConfig<H,E> {
	
	protected C config;

	public ProcessorConfigFilter(C config) {
		this.config = Objects.requireNonNull(config);
	}

	@Override
	public Element getElement() {
		return config.getElement();
	}

	@Override
	public Map<Element, ProcessorConfig<H,E>> getChildProcessorConfigs() {
		return config.getChildProcessorConfigs();
	}

	@Override
	public ProcessorConfig<H,E> getParentProcessorConfig() {
		return config.getParentProcessorConfig();
	}

	@Override
	public Map<Element, ProcessorConfig<H,E>> getRegistry() {
		return config.getRegistry();
	}

	@Override
	public Map<Element, CompletionStage<E>> getChildEndpoints() {
		return config.getChildEndpoints();
	}

	@Override
	public Map<Element, Consumer<H>> getChildHandlerConsumers() {
		return config.getChildHandlerConsumers();
	}

	@Override
	public CompletionStage<E> getParentEndpoint() {
		return config.getParentEndpoint();
	}

	@Override
	public void setParentHandler(H parentHandler) {
		config.setParentHandler(parentHandler);		
	}

	@Override
	public CompletionStage<E> getClientEndpoint() {
		return config.getClientEndpoint();
	}

	@Override
	public void setClientHandler(H clientHandler) {
		config.setClientHandler(clientHandler);		
	}

}
