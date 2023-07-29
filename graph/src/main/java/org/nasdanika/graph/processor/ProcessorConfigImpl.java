package org.nasdanika.graph.processor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.nasdanika.graph.Element;

class ProcessorConfigImpl implements ProcessorConfig {
	
	private Element element;
	private Map<Element, ProcessorConfig> childConfigs = new ConcurrentHashMap<>();
	private ProcessorConfig parentConfig;
	private Map<Element, ProcessorConfig> registry;

	ProcessorConfigImpl(Element element) {
		this.element = element;
	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public Map<Element, ProcessorConfig> getChildProcessorConfigs() {
		return Collections.unmodifiableMap(childConfigs);
	}

	@Override
	public ProcessorConfig getParentProcessorConfig() {
		return parentConfig;
	}

	@Override
	public Map<Element, ProcessorConfig> getRegistry() {
		return registry;
	}
	
	// --- Wiring methods
	
	void setRegistry(Map<Element, ProcessorConfig> registry) {
		this.registry = registry;
	}
	
	void setParentConfig(ProcessorConfig parentConfig) {
		this.parentConfig = parentConfig;
	}
	
	void putChildConfig(Element child, ProcessorConfig childConfig) {
		if (childConfig != null) {
			childConfigs.put(child, childConfig);
		}
	}
	
}		
