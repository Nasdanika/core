package org.nasdanika.graph.processor;

public interface ProcessorInfo<P> {
	
	ProcessorConfig<P> getConfig();
	
	P getProcessor();

}
