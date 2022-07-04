package org.nasdanika.drawio;

/**
 * Base (logical parent) for connections to use, for example, to resolve URI's and also during visiting.
 * @author Pavel
 *
 */
public enum ConnectionBase {
	
	/**
	 * Connection is a logical child of its parent 
	 */
	PARENT,
	
	/**
	 * Connection is a logical child of its source
	 */
	SOURCE,
	
	/**
	 * Connection is a logical child of its target
	 */
	TARGET,

}
