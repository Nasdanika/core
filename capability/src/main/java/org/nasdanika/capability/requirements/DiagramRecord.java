package org.nasdanika.capability.requirements;

import java.util.Map;

public record DiagramRecord(
		
		String location,
		
		/**
		 * Inline diagram
		 */
		String source,
		
		/**
		 * For source - base URI
		 */
		String base,		
		
		/**
		 * properties to pass to the diagram.
		 * Can be specified inline (if map) or loaded from YAML, JSON, or properties URL - format is determined by by extension.
		 * Nested properties can be addressed using "." (dot) separator. For arrays index is used as key. E.g. people.3.name
		 */
		Map<String,?> properties,
		
		/**
		 * Processor property
		 */
		String processor,
		
		/**
		 * Bind property for dynamic proxy
		 */
		String bind,
		
		/**
		 * Interfaces to be implemented by a proxy.
		 * If not provided, no proxy is created and a map of elements to processor info (registry) is used as a result.
		 */
		String[] interfaces) {

}
