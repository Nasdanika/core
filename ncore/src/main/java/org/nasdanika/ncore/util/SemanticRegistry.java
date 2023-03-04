package org.nasdanika.ncore.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.emf.common.util.URI;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Registry of semantic identities with methods to find by another identity, UUID, and URI
 * @author Pavel
 *
 */
public class SemanticRegistry extends ArrayList<SemanticIdentity> {
	
// Override these methods to implement indexing by uuid and uri		
//	@Override
//	public boolean addAll(Collection<? extends SemanticIdentity> c) {
//		return super.addAll(c);
//	}
//	
//	@Override
//	public org.nasdanika.ncore.util.SemanticIdentity set(int index, org.nasdanika.ncore.util.SemanticIdentity element) {
//		return super.set(index, element);
//	}
//	
//	@Override
//	public void add(int index, org.nasdanika.ncore.util.SemanticIdentity element) {
//		// TODO Auto-generated method stub
//		super.add(index, element);
//	}
//	
//	@Override
//	public boolean remove(Object o) {
//		// TODO Auto-generated method stub
//		return super.remove(o);
//	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Find identity in the registry by another identity as a key 
	 * @param key
	 * @return
	 */
	public SemanticIdentity find(SemanticIdentity key) {
		if (key == null) {
			return null;
		}
		for (URI identifier: key.getIdentifiers()) {
			SemanticIdentity ret = find(identifier);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}
	
	/**
	 * Find identity in the registry by UUID
	 * @param key
	 * @return
	 */
	public SemanticIdentity find(URI identifier) {
		if (identifier != null) {
			for (SemanticIdentity e: this) {
				if (e.getIdentifiers().contains(identifier)) {
					return e;
				}
			}
		}
		return null;		
	}
	
	/**
	 * Loads semantic identities from {@link JSONArray}. 
	 * Array elements are loaded as {@link SemanticIdentity} if they have only uris and uuid keys.
	 * If they have any other keys, they are loaded as {@link SemanticInfo} 
	 * @param jsonData
	 * @param Base {@link URI} to resolve locations in semantic info
	 */
	public void load(JSONArray jsonData, URI base) {
		for (Object je: jsonData) {
			if (je instanceof JSONObject) {
				JSONObject jo = (JSONObject) je;
				HashSet<String> keySet = new HashSet<>(jo.keySet());
				keySet.remove(SemanticIdentityImpl.IDENTIFIERS_KEY);
				if (keySet.isEmpty()) {
					add(new SemanticIdentityImpl(jo));
				} else {
					add(new SemanticInfo(jo, base));
				}
			}
		}
	}
		
	/**
	 * Parses a string a JSONArray and loads
	 * @param str JSON array string
	 * @param Base {@link URI} to resolve locations in semantic info
	 * @return
	 */
	public void load(String str, URI base) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(str));
		load(jsonArray, base);
	}
	
	public void load(InputStream in, URI base) throws IOException {
		try (Reader reader = new InputStreamReader(in, getCharset())) {
			load(reader,  base);
		}
	}
	
	public void load(Reader reader, URI base) {
		JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
		load(jsonArray, base);
	}
	
	public void load(URL url) throws IOException {
		load(url.openStream(), URI.createURI(url.toString()));
	}	
	
	public JSONArray toJSON(int indent) {
		JSONArray ret = new JSONArray();
		for (SemanticIdentity e: this) {
			if (e instanceof SemanticIdentityImpl) {
				ret.put(((SemanticIdentityImpl) e).toJSON());
			}
		}
		return ret;
	}	
	
	/**
	 * Override to customize charset.
	 * @return
	 */
	protected Charset getCharset() {
		return StandardCharsets.UTF_8;
	}
	

}
