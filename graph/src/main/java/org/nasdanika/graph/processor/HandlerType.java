package org.nasdanika.graph.processor;

/**
 * Type of a handler and its corresponding endpoint.
 * @author Pavel
 *
 */
public enum HandlerType {
	
	/**
	 * Handler to invoke connection source.
	 */
	SOURCE,

	/**
	 * Handler to invoke connection target.
	 */
	TARGET,
	
	/**
	 * Handler to invoke a node via an outgoing connection .
	 */
	OUTBOUND,
	
	/**
	 * Handler to invoke a node via an incoming connection.
	 */
	INBOUND	

}
