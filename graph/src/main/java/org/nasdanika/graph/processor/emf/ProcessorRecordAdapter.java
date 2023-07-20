package org.nasdanika.graph.processor.emf;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.graph.processor.ProcessorRecord;

public class ProcessorRecordAdapter<P> extends AdapterImpl {

	protected ProcessorRecord<P> processorRecord;

	public ProcessorRecordAdapter(ProcessorRecord<P> processorRecord) {
		this.processorRecord = processorRecord;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return ProcessorRecordAdapter.class == type;
	}
	
	public ProcessorRecord<P> getProcessorRecord() {
		return processorRecord;
	}
	
}
