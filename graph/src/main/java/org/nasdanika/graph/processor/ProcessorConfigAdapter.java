package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.graph.Element;

public class ProcessorConfigAdapter<P, D extends ProcessorConfig<P>> extends AdapterImpl implements ProcessorConfig<P> {

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
	public Map<Element, ProcessorInfo<P>> getChildProcessorsInfo() {
		return delegate.getChildProcessorsInfo();
	}

	@Override
	public CompletionStage<ProcessorInfo<P>> getParentProcessorInfo() {
		return delegate.getParentProcessorInfo();
	}

	@Override
	public CompletionStage<Map<Element, ProcessorInfo<P>>> getRegistry() {
		return delegate.getRegistry();
	}	
	
}
