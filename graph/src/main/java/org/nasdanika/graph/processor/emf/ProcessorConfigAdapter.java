package org.nasdanika.graph.processor.emf;

import java.util.Map;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;

public class ProcessorConfigAdapter<D extends ProcessorConfig> extends AdapterImpl implements ProcessorConfig {

	protected D delegate;

	public ProcessorConfigAdapter(D delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return ProcessorConfig.class == type;
	}

	@Override
	public Element getElement() {
		return delegate.getElement();
	}
	
	@Override
	public Map<Element, ProcessorConfig> getChildProcessorConfigs() {
		return delegate.getChildProcessorConfigs();
	}
	
	@Override
	public ProcessorConfig getParentProcessorConfig() {
		return delegate.getParentProcessorConfig();
	}

	@Override
	public Map<Element, ProcessorConfig> getRegistry() {
		return delegate.getRegistry();
	}
	
}
