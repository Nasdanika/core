package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Element;

abstract class Helper<P> {
	
	private ProcessorInfo<P> processorInfo;

	Helper(ProcessorInfo<P> processorInfo) {
		this.processorInfo = processorInfo;
	}
	
	abstract void setParentProcessorInfo(ProcessorInfo<P> parentProcessorInfo);

	ProcessorInfo<P> getProcessorInfo() {
		return processorInfo;
	}
	
	abstract void setRegistry(Map<Element, ProcessorInfo<P>> registry);	
	
}
