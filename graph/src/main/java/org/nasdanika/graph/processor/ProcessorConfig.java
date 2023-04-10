package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.CompletionStage;

import org.nasdanika.graph.Element;

public interface ProcessorConfig<P,R> {
	
	Element getElement();
	
	Map<Element, ProcessorInfo<P,R>> getChildProcessorsInfo();
	
	CompletionStage<ProcessorInfo<P,R>> getParentProcessorInfo();
	
	CompletionStage<R> getRegistry();
	
}
