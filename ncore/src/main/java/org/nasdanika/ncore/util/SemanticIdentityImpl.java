package org.nasdanika.ncore.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class shall not be used with {@link HashMap} because two identities may be equal but have different hash codes. Use {@link SemanticMap} instead.
 * @author Pavel
 *
 */
public class SemanticIdentityImpl implements SemanticIdentity {
	
	public static String IDENTIFIERS_KEY = "identifiers";
	
	protected Collection<URI> identifiers;
	
	public SemanticIdentityImpl(Collection<URI> identifiers) {
		if (identifiers != null) {
			this.identifiers = Collections.unmodifiableList(new ArrayList<>(identifiers));
		} else {
			this.identifiers = Collections.emptyList();
		}
	}
	
	public SemanticIdentityImpl(EObject semanticElement) {
		List<URI> semanticURIs = new ArrayList<>();
		for (URI uri: NcoreUtil.getIdentifiers(semanticElement)) {
			if (!uri.isRelative()) {
				semanticURIs.add(uri);
			}
		}
		this.identifiers = Collections.unmodifiableList(semanticURIs);
	}
	
	public JSONObject toJSON() {
		JSONObject ret = new JSONObject();
		if (!getIdentifiers().isEmpty()) {
			JSONArray jIdentifiers = new JSONArray();
			for (URI identifier: getIdentifiers()) {
				jIdentifiers.put(identifier.toString());
			}
			ret.put(IDENTIFIERS_KEY, jIdentifiers);
		}
		
		return ret;
	}
	
	public SemanticIdentityImpl(JSONObject jsonObj) {
		if (jsonObj.has(IDENTIFIERS_KEY)) {
			JSONArray jIdentifiers = jsonObj.getJSONArray(IDENTIFIERS_KEY);
			ArrayList<URI> aIdentifiers = new ArrayList<>();
			for (Object el: jIdentifiers) {
				aIdentifiers.add(URI.createURI((String) el));
			}
			identifiers = Collections.unmodifiableList(aIdentifiers);
		} else {
			identifiers = Collections.emptyList();
		}
	}
		
	@Override
	public Collection<URI> getIdentifiers() {
		return identifiers;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof SemanticIdentity) {
			SemanticIdentity other = (SemanticIdentity) obj;
			Collection<URI> thisIdentifiers = getIdentifiers();
			if (thisIdentifiers != null && !thisIdentifiers.isEmpty()) {		
				Collection<URI> otherIdentifiers = other.getIdentifiers();
				if (otherIdentifiers != null && !otherIdentifiers.isEmpty()) {					
					for (URI thisIdentifier: getIdentifiers()) {
						if (otherIdentifiers.contains(thisIdentifier)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public int hashCode() {
		return Objects.hash(getIdentifiers());
	}	
	
}
