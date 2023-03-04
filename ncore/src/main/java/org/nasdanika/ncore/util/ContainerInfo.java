package org.nasdanika.ncore.util;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.json.JSONObject;
import org.nasdanika.ncore.NamedElement;

public class ContainerInfo extends SemanticIdentity {

	public static String REFERENCE_KEY = "reference";
	public static String NAME_KEY = "name";
	
	protected String reference;
	protected String name;

	public ContainerInfo(
			String uuid, 
			List<URI> uris, 
			String reference, 
			String name) {
		
		super(uuid, uris);
		this.reference = reference;
		this.name = name;
	}
	
	public ContainerInfo(EObject semanticElement, String reference) {
		super(semanticElement);
		this.reference = reference;
		if (semanticElement instanceof NamedElement) {
			this.name = ((NamedElement) semanticElement).getName();
		}
	}

	public ContainerInfo(JSONObject jsonObj) {
		super(jsonObj);
		if (jsonObj.has(NAME_KEY)) {
			name = jsonObj.getString(NAME_KEY);
		}
		if (jsonObj.has(REFERENCE_KEY)) {
			reference = jsonObj.getString(REFERENCE_KEY);
		}
	}
	
	public String getReference() {
		return reference;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject ret = super.toJSON();
		if (name != null) {
			ret.put(NAME_KEY, uuid);
		}
		if (reference != null) {
			ret.put(REFERENCE_KEY, uuid);
		}
		return ret;
	}

}
	
