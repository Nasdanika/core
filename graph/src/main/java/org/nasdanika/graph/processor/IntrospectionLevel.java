package org.nasdanika.graph.processor;

/**
 * Level of introspection.
 * @author Pavel
 *
 */
public enum IntrospectionLevel {
	
	/**
	 * No introspection.
	 */
	NONE,
	
	/**
	 * Accessible members
	 */
	ACCESSIBLE,
	
	/**
	 * All declared members including not accessible.
	 * To access no accessible members <code>{@link java.lang.reflect.AcessibleObject}.setAccessible()</code> is called, which may result in {@link SecurityException}.
	 */
	DECLARED

}
