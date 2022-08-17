package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Element;

abstract class Helper<P> {
	
	private ElementProcessorInfo<P> processorInfo;

	Helper(ElementProcessorInfo<P> processorInfo) {
		this.processorInfo = processorInfo;
	}
	
	abstract void setParentProcessorInfo(ElementProcessorInfo<P> parentProcessorInfo);

	ElementProcessorInfo<P> getProcessorInfo() {
		return processorInfo;
	}
	
	abstract void setRegistry(Map<Element, ElementProcessorInfo<P>> registry);	
	
}
