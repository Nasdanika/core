package org.nasdanika.graph.processor;

abstract class Helper<P,R> {
	
	private ProcessorInfo<P,R> processorInfo;

	Helper(ProcessorInfo<P,R> processorInfo) {
		this.processorInfo = processorInfo;
	}
	
	abstract void setParentProcessorInfo(ProcessorInfo<P,R> parentProcessorInfo);

	ProcessorInfo<P,R> getProcessorInfo() {
		return processorInfo;
	}
	
	abstract void setRegistry(R registry);	
	
}
