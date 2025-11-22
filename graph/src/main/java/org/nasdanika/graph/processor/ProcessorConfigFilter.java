package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

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
	public Map<Element, Synapse<H, E>> getChildSynapses() {
		return config.getChildSynapses();
	}

	@Override
	public Synapse<H, E> getParentSynapse() {
		return config.getParentSynapse();
	}

	@Override
	public Synapse<H, E> getClientSynapse(Object clientKey) {
		return config.getClientSynapse(clientKey);
	}
	
	@Override
	public void setProcessorSynapseConsumer(BiConsumer<Object, Synapse<H, E>> processorSynapseConsumer) {
		config.setProcessorSynapseConsumer(processorSynapseConsumer);
		
	}

}
