package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Element;

public interface ProcessorConfig {
	
	Element getElement();
	
	Map<Element, ProcessorConfig> getChildProcessorsConfig();
	
	ProcessorConfig getParentProcessorConfig();
	
	Map<Element,ProcessorConfig> getRegistry();
	
}
