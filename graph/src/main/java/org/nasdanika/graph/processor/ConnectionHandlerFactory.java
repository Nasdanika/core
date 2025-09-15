package org.nasdanika.graph.processor;

import java.util.function.Supplier;

public interface ConnectionHandlerFactory<H,E> extends ElementHandlerFactory<H,E> {
	
	H getSourceHandler(Supplier<E> parentEndpointSupplier); 

	H getTargetHandler(Supplier<E> parentEndpointSupplier); 

}
