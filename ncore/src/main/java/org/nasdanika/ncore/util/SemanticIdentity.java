package org.nasdanika.ncore.util;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Identifier/key of a semantic entity. Two identities are considered equal if at least one URI of one entity is equal to at least one URI of another.
 * @author Pavel
 *
 */
public interface SemanticIdentity {
	
	Collection<URI> getIdentifiers();
	
}
