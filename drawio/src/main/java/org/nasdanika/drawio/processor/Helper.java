package org.nasdanika.drawio.processor;

import org.nasdanika.common.Reference;

class Helper<P> {
	
	private ElementProcessorInfo<P> processorInfo;
	private Reference<ElementProcessorInfo<P>> parentProcessorInfoReference;

	Helper(ElementProcessorInfo<P> processorInfo, Reference<ElementProcessorInfo<P>> parentProcessorInfoReference) {
		this.processorInfo = processorInfo;
		this.parentProcessorInfoReference = parentProcessorInfoReference;
	}
	
	void setParentProcessorInfo(ElementProcessorInfo<P> parentProcessorInfo) {
		parentProcessorInfoReference.set(parentProcessorInfo);
	}

	ElementProcessorInfo<P> getProcessorInfo() {
		return processorInfo;
	}
	
}
