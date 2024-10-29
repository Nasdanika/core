package org.nasdanika.graph.processor;

import org.nasdanika.common.AsyncInvocable;
import org.nasdanika.common.Invocable;

/**
 * Handler wrapper for {@link IncomingHandler} and {@link OutgoingHandler} <code>wrap</code> attribute.
 */
public enum HandlerWrapper {
	
	/**
	 * No wrapping
	 */
	NONE,
	
	/**
	 * Method is wrapped into an instance of {@link Invocable}, field is wrapped into an Invocable which sets the field.
	 */
	INVOCABLE,
	
	/**
	 * Method is wrapped into an instance of {@link AsyncInvocable}
	 */
	ASYNC_INVOCABLE

}
