package org.nasdanika.graph.processor;

import java.util.function.Supplier;

import org.nasdanika.graph.Element;

public interface ElementHandlerFactory<H,E> {
	
	H getChildHandler(Element child, Supplier<E> parentEndpointSupplier);

	H getParentHandler(E parentEndpoint);

	H getHandler(E parentEndpoint);

}
