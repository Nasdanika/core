package org.nasdanika.capability.requirements;

import java.io.InputStream;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;

public record DiagramRequirement(
		
		URI uri,
		
		String source,
		
		URI base,
		
		/**
		 * Optional selector of an element from a document.
		 */
		Function<Object, Object> selector,
		
		Function<String, String> propertySource,
		
		Function<URI, InputStream> uriHandler,
		
		/**
		 * Processor property
		 */
		String processor,
		
		/**
		 * Bind property for dynamic proxy
		 */
		String bind,
		
		ClassLoader classLoader,
		
//		Function<ProcessorInfo<Invocable>,Invocable> processorFilter, Not supported yet		
		
		/**
		 * Interfaces to be implemented by a proxy.
		 * If not provided, no proxy is created and a map of elements to processor info (registry) is used as a result.
		 */
		Class<?>[] interfaces) {

}
