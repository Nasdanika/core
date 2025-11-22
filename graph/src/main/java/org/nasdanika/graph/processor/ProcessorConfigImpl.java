package org.nasdanika.graph.processor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.nasdanika.graph.Element;

class ProcessorConfigImpl<H,E> implements ProcessorConfig<H,E> {
	
	private Element element;
	private Map<Element, ProcessorConfig<H,E>> childConfigs = new ConcurrentHashMap<>();
	private Map<Element, Synapse<H,E>> childSynapses = new ConcurrentHashMap<>();
	
	private Map<Object, Pipe<H,E>> clientPipes = new ConcurrentHashMap<>(); // client source, processor target
	private ProcessorConfig<H,E> parentConfig;
	private Synapse<H,E> parentSynapse;
	private Map<Element, ProcessorConfig<H,E>> registry;
	protected Function<H, E> clientEndpointFactory;
	protected Function<H, E> processorEndpointFactory;
	protected BiConsumer<Object, Synapse<H, E>> processorSynapseConsumer;

	ProcessorConfigImpl(Element element, Function<H,E> clientEndpointFactory, Function<H,E> processorEndpointFactory) {
		this.element = element;
		this.clientEndpointFactory = clientEndpointFactory;
		this.processorEndpointFactory = processorEndpointFactory;
	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public Map<Element, ProcessorConfig<H,E>> getChildProcessorConfigs() {
		return Collections.unmodifiableMap(childConfigs);
	}

	@Override
	public ProcessorConfig<H,E> getParentProcessorConfig() {
		return parentConfig;
	}

	@Override
	public Map<Element, ProcessorConfig<H,E>> getRegistry() {
		return registry;
	}
	

	@Override
	public Synapse<H, E> getParentSynapse() {
		return parentSynapse;
	}

	@Override
	public Synapse<H, E> getClientSynapse(Object clientKey) {
		return clientPipes.computeIfAbsent(
				clientKey, 
				cs -> {
					Pipe<H,E> pipe = Pipe.create(clientEndpointFactory, processorEndpointFactory);
					if (processorSynapseConsumer != null) {
						processorSynapseConsumer.accept(cs, pipe.getTarget());
					}
					return pipe;
				}).getSource();
	}
	
	@Override
	public void setProcessorSynapseConsumer(BiConsumer<Object, Synapse<H, E>> processorSynapseConsumer) {
		this.processorSynapseConsumer = processorSynapseConsumer;
		if (this.processorSynapseConsumer != null) {
			clientPipes.forEach((k,p) -> this.processorSynapseConsumer.accept(k, p.getTarget()));
		}
		
	}

	@Override
	public Map<Element, Synapse<H, E>> getChildSynapses() {
		return childSynapses;
	}
	
	// --- Wiring methods
	
	void setRegistry(Map<Element, ProcessorConfig<H,E>> registry) {
		this.registry = registry;
	}
	
	void setParentConfigAndSynapse(ProcessorConfig<H,E> parentConfig, Synapse<H,E> parentSynapse) {
		this.parentConfig = parentConfig;
		this.parentSynapse = parentSynapse;
	}
	
	void putChildConfigAndSynapse(Element child, ProcessorConfig<H,E> childConfig, Synapse<H,E> childSynapse) {
		if (childConfig != null) {
			childConfigs.put(child, childConfig);
		}
		if (childSynapse != null) {
			childSynapses.put(child, childSynapse);
		}
	}
	
}		
