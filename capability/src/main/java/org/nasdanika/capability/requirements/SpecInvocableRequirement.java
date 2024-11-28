package org.nasdanika.capability.requirements;

import java.util.Map;

/**
 * Loading invocable from a map, which can be loaded from YAML or JSON
 */
public record SpecInvocableRequirement(
		Map<?,?> spec,
		URIHandler uriHandler,
		ClassLoader classLoader,
		ModuleLayer[] parentModuleLayers) {
		
	public SpecInvocableRequirement(Map<?,?> spec) {
		this(
			spec, 
			URIHandler.DEFAULT, 
			Thread.currentThread().getContextClassLoader(),
			null);
	}

}
