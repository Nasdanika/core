package org.nasdanika.drawio.processor;

public interface ElementProcessorInfo<P> {
	
	ElementProcessorConfig<P> getConfig();
	
	P getProcessor();

}
