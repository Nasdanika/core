package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Element;

public interface ProcessorConfig<P> {
	
	Element getElement();
	
	Map<Element, ProcessorInfo<P>> getChildProcessorsInfo();
	
	ProcessorInfo<P> getParentProcessorInfo();
	
	Map<Element, ProcessorInfo<P>> getRegistry();
	
}
