package org.nasdanika.drawio.processor;

import java.util.Map;

import org.nasdanika.drawio.Element;

public interface ElementProcessorConfig<E extends Element,P> {
	
	E getElement();
	
	Map<Element, P> getChildProcessors();
	
	P getParentProcessor();
	
	Map<Element, P> getRegistry();
	
}
