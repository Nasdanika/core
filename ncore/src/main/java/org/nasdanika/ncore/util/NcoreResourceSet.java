package org.nasdanika.ncore.util;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nasdanika.ncore.ModelElement;

public class NcoreResourceSet extends ResourceSetImpl {
	
	/**
	 * 
	 * @param modelElement
	 * @param cache cache with fall-back. E.g.Map.computeIfAbsent
	 * @return
	 */
	public EList<ModelElement> getAliases(
			ModelElement modelElement, 
			BiFunction<ModelElement,Function<? super ModelElement,? extends Collection<URI>>,Collection<URI>> cache) {
		
		if (cache == null) {
			cache = (m,f) -> f.apply(m);
		}
		
		Collection<URI> modelElementIdentifiers = cache.apply(modelElement, NcoreUtil::getIdentifiers);
		if (modelElementIdentifiers.isEmpty()) {
			return ECollections.emptyEList();
		}

		EList<ModelElement> aliases = ECollections.newBasicEList();		
		TreeIterator<Notifier> cit = getAllContents();
		while (cit.hasNext()) {
			Notifier next = cit.next();				
			if (next != modelElement && next instanceof ModelElement) {
				ModelElement nextModelElement = (ModelElement) next;
				for (URI nextModelElementidentifier: cache.apply(nextModelElement, NcoreUtil::getIdentifiers)) {
					if (modelElementIdentifiers.contains(nextModelElementidentifier)) {
						aliases.add(nextModelElement);
						break;
					}
				}
			}
		}	
		return aliases;
	}

	@Override
	public EObject getEObject(URI uri, boolean loadOnDemand) {		
		TreeIterator<Notifier> cit = getAllContents();
		while (cit.hasNext()) {
			Notifier next = cit.next();
			if (next instanceof EObjectLocator) {
				cit.prune();
				Optional<EObject> ret = ((EObjectLocator) next).get(uri);
				if (ret != null) {
					return ret.orElse(null);
				}
			}
			if (next instanceof EObject) {
				EObject nextEObject = (EObject) next;
				if (matchURI(nextEObject, uri)) {
					return nextEObject;
				}
			}
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
		if (NcoreUtil.isEMapEntry(eObj)) {
			return false; // Ignoring EMap entries.
		}
		for (URI objUri: NcoreUtil.getIdentifiers(eObj)) {
			if (objUri != null && objUri.equals(uri)) {
				return true;
			}
		}
		return false;
	}

}
