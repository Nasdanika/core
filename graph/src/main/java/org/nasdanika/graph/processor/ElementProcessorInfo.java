package org.nasdanika.graph.processor;

public interface ElementProcessorInfo<P> {
	
	ElementProcessorConfig<P> getConfig();
	
	P getProcessor();

}
