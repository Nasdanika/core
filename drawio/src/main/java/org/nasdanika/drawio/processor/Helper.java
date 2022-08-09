package org.nasdanika.drawio.processor;

abstract class Helper<P> {
	
	private ElementProcessorInfo<P> processorInfo;

	Helper(ElementProcessorInfo<P> processorInfo) {
		this.processorInfo = processorInfo;
	}
	
	abstract void setParentProcessorInfo(ElementProcessorInfo<P> parentProcessorInfo);

	ElementProcessorInfo<P> getProcessorInfo() {
		return processorInfo;
	}
	
}
