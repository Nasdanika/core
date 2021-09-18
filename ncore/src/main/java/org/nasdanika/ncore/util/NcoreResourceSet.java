package org.nasdanika.ncore.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.impl.ModelElementImpl;

public class NcoreResourceSet extends ResourceSetImpl {

	Map<URI, EObject> cache = new HashMap<>();
	
	@Override
	public EObject getEObject(URI uri, boolean loadOnDemand) {
		EObject ret = cache.get(uri);
		if (ret != null) {
			return ret;
		}
		
		TreeIterator<Notifier> cit = getAllContents();
		while (cit.hasNext()) {
			Notifier next = cit.next();
			if (next instanceof EObject) {
				EObject nextEObject = (EObject) next;
				if (matchURI(nextEObject, uri)) {
					cache.put(uri, nextEObject);
					ret = nextEObject;
				}
			}
		}

		if (ret != null) {
			return ret;
		}
		
		return super.getEObject(uri, loadOnDemand);
	}
	
	/**
	 * Matches {@link EObject} to {@link URI}. This implementation uses ModelElement.getUri() to 
	 * match containment path URI's and ModelElement.getUuid() to match URI's with <code>uuid</code> scheme.  
	 * uses  
	 * @param eObj
	 * @param uri
	 * @return
	 */
	protected boolean matchURI(EObject eObj, URI uri) {
		URI objUri = ModelElementImpl.getUri(eObj);
		if (objUri != null && objUri.equals(uri)) {
			return true;
		}
		if (eObj instanceof ModelElement) {
			String uuid = ((ModelElement) eObj).getUuid();
			if (!Util.isBlank(uuid)) {
				URI uuidUri = URI.createURI("uuid:" + uuid);
				if (uuidUri.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}

}
