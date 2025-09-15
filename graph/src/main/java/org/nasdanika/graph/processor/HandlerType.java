package org.nasdanika.graph.processor;

/**
 * Type of a handler and its corresponding endpoint.
 * @author Pavel
 *
 */
public enum HandlerType {
	
	/**
	 * Connection's target handler to invoke source node outgoing endpoint for this connection.
	 */
	SOURCE,

	/**
	 * Connection's source handler to invoke target node incoming endpoint for this connection.
	 */
	TARGET,
	
	/**
	 * Node's outgoing handler to invoke outgoing connection's source endpoint or, if the connection is passthrough, target node's incoming endpoint.
	 */
	OUTGOING,
	
	/**
	 * Node's incoming handler to invoke incoming connection's target endpoint or, if the connection is passthrough, source node's outgoing endpoint.
	 */
	INCOMING	

}
