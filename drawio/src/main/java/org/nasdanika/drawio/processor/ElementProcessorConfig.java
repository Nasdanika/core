package org.nasdanika.drawio.processor;

import java.util.Map;

import org.nasdanika.drawio.Element;

public interface ElementProcessorConfig<P> {
	
	Element getElement();
	
	Map<Element, ElementProcessorInfo<P>> getChildProcessorsInfo();
	
	ElementProcessorInfo<P> getParentProcessorInfo();
	
	Map<Element, ElementProcessorInfo<P>> getRegistry();
	
}
