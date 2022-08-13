package org.nasdanika.drawio.processor;

import org.nasdanika.drawio.Connection;

public interface ConnectionProcessorConfig<P,H,E> extends ElementProcessorConfig<P> {
	
	@Override
	Connection getElement();

	E getSourceEndpoint();
	
	void setSourceHandler(H sourceHandler);
	
	E getTargetEndpoint();
	
	void setTargetHandler(H targetHandler);
	
}
