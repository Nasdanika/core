package org.nasdanika.ncore.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nasdanika.ncore.ModelElement;

public class NcoreResourceSet extends ResourceSetImpl {
	
	private Map<URI, Set<ModelElement>> uriToModelElementtMap;
	private Map<ModelElement, List<URI>> modelElementToURIMap;
	
	
	public EList<ModelElement> getAliases(ModelElement modelElement) {
		if (uriToModelElementtMap == null) {
			uriToModelElementtMap = new HashMap<>();
			modelElementToURIMap = new HashMap<>();
			TreeIterator<Notifier> cit = getAllContents();
			while (cit.hasNext()) {
				Notifier next = cit.next();				
				if (next instanceof ModelElement) {
					ModelElement nextModelElement = (ModelElement) next;
					List<URI> identifiers = NcoreUtil.getIdentifiers(nextModelElement);
					modelElementToURIMap.put(nextModelElement, identifiers);
					for (URI identifier: identifiers) {
						uriToModelElementtMap.computeIfAbsent(identifier, uri -> new HashSet<>()).add(nextModelElement);
					}
				}
			}	
									
			// Removing single (no alias) uri -> Model Element mappings
			uriToModelElementtMap.entrySet().removeIf(e -> e.getValue().size() == 1);
		}
		Set<ModelElement> collector = new HashSet<>();
		collectAliases(modelElement, collector);
		collector.remove(modelElement);
		return ECollections.unmodifiableEList(ECollections.toEList(collector));
	}
	
	private void collectAliases(ModelElement modelElement, Set<ModelElement> collector) {
		if (modelElement != null && collector.add(modelElement)) {
			for (URI identifier: modelElementToURIMap.get(modelElement)) {				
				Set<ModelElement> modelElements = uriToModelElementtMap.get(identifier);
				if (modelElements != null) {
					for (ModelElement me: modelElements) {
						collectAliases(me, collector);
					}
				}
			}
		}
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
