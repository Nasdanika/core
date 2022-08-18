package org.nasdanika.graph.processor;

import org.nasdanika.graph.Connection;

public interface ConnectionProcessorConfig<P,H,E> extends ProcessorConfig<P> {
	
	@Override
	Connection getElement();

	E getSourceEndpoint();
	
	void setSourceHandler(H sourceHandler);
	
	E getTargetEndpoint();
	
	void setTargetHandler(H targetHandler);
	
}
