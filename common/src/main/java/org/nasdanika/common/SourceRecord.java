package org.nasdanika.common;

import org.eclipse.emf.common.util.URI;

/**
 * String with a URI to resolve references
 */
public record SourceRecord(URI uri, String source) {};	
