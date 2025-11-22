package org.nasdanika.graph.processor;

import java.util.concurrent.CompletionStage;

/**
 * This interface is used for establishing bi-directional communication between
 * graph element processors.
 * @param <H> Handler type
 * @param <E> Endpoint type 
 */
public interface Synapse<H,E> {
	
	/**
	 * Endpoint is used to invoke the other processor - outbound communication.
	 * @return
	 */
	CompletionStage<E> getEndpoint();
	
	/**
	 * Handler is used for the other processor to invoke this one - inbound communication.
	 * @param handler
	 * @return <code>true</code> if the handler was set, <code>false</code> if the handler was already set before and this invocation had no effect.
	 */
	boolean setHandler(H handler);

}
