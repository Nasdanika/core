package org.nasdanika.capability.requirements;

import org.eclipse.emf.common.util.URI;

public record URIInvocableRequirement(
		URI uri,
		URIHandler uriHandler,
		ClassLoader classLoader,
		ModuleLayer[] parentModuleLayers) {
		
	public URIInvocableRequirement(URI uri) {
		this(
			uri, 
			URIHandler.DEFAULT, 
			Thread.currentThread().getContextClassLoader(),
			null);
	}

}
