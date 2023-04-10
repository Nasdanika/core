package org.nasdanika.graph.processor.emf;

import java.util.Map;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class ProcessorConfigAdapter<P, R, D extends ProcessorConfig<P, R>> extends AdapterImpl implements ProcessorConfig<P, R> {

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
	public Map<Element, ProcessorInfo<P, R>> getChildProcessorsInfo() {
		return delegate.getChildProcessorsInfo();
	}

	@Override
	public CompletionStage<ProcessorInfo<P, R>> getParentProcessorInfo() {
		return delegate.getParentProcessorInfo();
	}

	@Override
	public CompletionStage<R> getRegistry() {
		return delegate.getRegistry();
	}	
	
}
