package org.nasdanika.graph.processor.emf;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.graph.processor.ProcessorInfo;

public class ProcessorInfoAdapter<P> extends AdapterImpl {

	protected ProcessorInfo<P> processorInfo;

	public ProcessorInfoAdapter(ProcessorInfo<P> processorInfo) {
		this.processorInfo = processorInfo;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return ProcessorInfoAdapter.class == type;
	}
	
	public ProcessorInfo<P> getProcessorInfo() {
		return processorInfo;
	}
	
}
