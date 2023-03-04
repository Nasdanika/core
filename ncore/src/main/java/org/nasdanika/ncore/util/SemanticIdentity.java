package org.nasdanika.ncore.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.ncore.ModelElement;

/**
 * Identifier/key of a semantic entity. Two identities are considered equal if they have equal UUID's or at least one URI of one entity is equal to at least one URI of another.
 * This class shall not be used with {@link HashMap} because two identities may be equal but have different hash codes. Use {@link SemanticMap} instead.
 * @author Pavel
 *
 */
public class SemanticIdentity {
	
	public static String UUID_KEY = "uuid";
	public static String URIS_KEY = "uris";	
	
	protected String uuid;
	protected List<URI> uris;
	
	public SemanticIdentity(String uuid, List<URI> uris) {
		this.uuid = uuid;
		if (uris != null) {
			this.uris = Collections.unmodifiableList(new ArrayList<>(uris));
		} else {
			this.uris = Collections.emptyList();
		}
	}
	
	public SemanticIdentity(EObject semanticElement) {
		List<URI> semanticURIs = new ArrayList<>();
		for (URI uri: NcoreUtil.getUris(semanticElement)) {
			if (!uri.isRelative()) {
				semanticURIs.add(uri);
			}
		}
		if (semanticElement instanceof ModelElement) {
			uuid = ((ModelElement) semanticElement).getUuid();
		}
	}
	
	public JSONObject toJSON() {
		JSONObject ret = new JSONObject();
		if (uuid != null) {
			ret.put(UUID_KEY, uuid);
		}
		
		if (!getURIs().isEmpty()) {
			JSONArray jURIs = new JSONArray();
			for (URI uri: getURIs()) {
				jURIs.put(uri.toString());
			}
			ret.put(URIS_KEY, jURIs);
		}
		
		return ret;
	}
	
	public SemanticIdentity(JSONObject jsonObj) {
		if (jsonObj.has(UUID_KEY)) {
			uuid = jsonObj.getString(UUID_KEY);
		}
		if (jsonObj.has(URIS_KEY)) {
			JSONArray jURIs = jsonObj.getJSONArray(URIS_KEY);
			ArrayList<URI> aURIs = new ArrayList<>();
			for (Object el: jURIs) {
				aURIs.add(URI.createURI((String) el));
			}
			uris = Collections.unmodifiableList(aURIs);
		}
	}
		
	public List<URI> getURIs() {
		return uris;
	}
	
	public String getUUID() {
		return uuid;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof SemanticIdentity) {
			SemanticIdentity other = (SemanticIdentity) obj;
			String firstUUID = getUUID();
			if (firstUUID != null && firstUUID.equals(other.getUUID())) {
				return true;
			}
			List<URI> thisURIs = getURIs();
			if (thisURIs != null && !thisURIs.isEmpty()) {		
				List<URI> otherURIs = getURIs();
				if (otherURIs != null && !otherURIs.isEmpty()) {					
					for (URI firstURI: getURIs()) {
						if (otherURIs.contains(firstURI)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public int hashCode() {
		return Objects.hash(getUUID(), getURIs());
	}	
	
}
