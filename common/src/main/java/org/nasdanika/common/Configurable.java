package org.nasdanika.common;

import java.util.function.Function;

/**
 * Something which can be configured with String to Object function, e.g. Map::get.
 * @author Pavel
 *
 */
public interface Configurable {
	
	/**
	 * Configures object (loads data).
	 * @param configuration
	 */
	void configure(Function<String,Object> configuration);

}
