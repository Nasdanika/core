package org.nasdanika.drawio.processor;

import org.nasdanika.common.Reference;

class Helper<P> {
	
	private P processor;
	private Reference<P> parentProcessorReference;

	Helper(P processor, Reference<P> parentProcessorReference) {
		this.processor = processor;
		this.parentProcessorReference = parentProcessorReference;
	}
	
	void setParentProcessor(P parentProcessor) {
		parentProcessorReference.set(parentProcessor);
	}

	P getProcessor() {
		return processor;
	}
	
}
