package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Element;

public interface ProcessorConfig<P> {
	
	Element getElement();
	
	Map<Element, ProcessorInfo<P>> getChildProcessorsInfo();
	
	CompletionStage<ProcessorInfo<P>> getParentProcessorInfo();
	
	CompletionStage<Map<Element, ProcessorInfo<P>>> getRegistry();
	
}
