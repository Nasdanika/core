package org.nasdanika.capability.requirements;

import java.util.Map;

public record ScriptRecord(
		
		/**
		 * Script location
		 */
		String location,
		
		/**
		 * Script source mutually exclusive with location
		 */
		String source,		
		
		/**
		 * Script language (mime type) for source. For location is language is not specified it is derived from extension. 
		 */
		String language,
		
		/**
		 * Fully qualified name of a script engine factory implementation.
		 * Use if the engine is loaded from dependencies and therefore is not visible
		 * to the script engine manager.
		 */
		String engineFactory,
		
		/**
		 * Bindings for the script.
		 * Values are treated as invocable URI's. 
		 */
		Map<String,String> bindings) {

}
