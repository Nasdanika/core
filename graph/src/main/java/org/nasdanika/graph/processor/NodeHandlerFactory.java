package org.nasdanika.graph.processor;

import java.util.function.Supplier;

import org.nasdanika.graph.Connection;

public interface NodeHandlerFactory<H,E> extends ElementHandlerFactory<H,E> {

	H getOutgoingHandler(Connection outgoingConnection, Supplier<E> parentEndpointSupplier);

	H getIncomingHandler(Connection incomingConnection, Supplier<E> parentEndpointSupplier);

}
