package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.Objects;

import org.nasdanika.graph.Element;

public class ProcessorConfigFilter<C extends ProcessorConfig> implements ProcessorConfig {
	
	protected C config;

	public ProcessorConfigFilter(C config) {
		this.config = Objects.requireNonNull(config);
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

}
