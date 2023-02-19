package org.nasdanika.ncore.util;

import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * Interface to find EObjects. 
 * Can be implemented by resources which facade systems with a large number of objects where iteration is inefficient.
 * @author Pavel
 *
 */
public interface EObjectLocator {
	
	/**
	 * Finds EObject by URI.
	 * @param uri
	 * @return Optional if this locator has an authoritative answer regarding existence or non-existence of an EObject matching the URI.
	 * Null if this locator doesn't have an authoritative answer - it doesn't know, but the EObject is not in the locator for sure, so the iterator can be pruned. 
	 */
	Optional<EObject> get(URI uri);

}
