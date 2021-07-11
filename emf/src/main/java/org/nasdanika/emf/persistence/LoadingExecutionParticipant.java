package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;

/**
 * {@link ExecutionParticipant} which loads resources using {@link EObjectLoader}, diagnoses them
 * and stores in a {@link ResourceSet} for use in execute() methods of subclasses.
 * 
 * @author Pavel
 *
 */
public abstract class LoadingExecutionParticipant implements ExecutionParticipant {
	
	protected Context context;
	protected List<EObject> roots;
	protected ResourceSet resourceSet;
	protected Map<EObject, org.eclipse.emf.common.util.Diagnostic> diagnosticMap = new LinkedHashMap<>();
	
	public LoadingExecutionParticipant(Context context) {
		this.context = context;
	}
	
	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return "Loading resources";
	}
	
	protected boolean matchURI(EObject obj, URI uri) {
		return false;
	}
	
	protected abstract Collection<URI> getResources();
	
	protected ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
		ResourceSetImpl ret = new ResourceSetImpl() {
			
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
			
		};
		
		return ret;
	}
	
	/**
	 * Diagnoses the {@link ResourceSet}. This implementation finds unresolved proxies and reports them
	 * with {@link Status} FAIL by calling unresolvedProxyDiagnostic().
	 * @param resourceSet
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected BasicDiagnostic diagnose() {
		BasicDiagnostic ret = new BasicDiagnostic(Status.SUCCESS, "Diagnostic of " + resourceSet, resourceSet);
		
		TreeIterator<Notifier> cit = resourceSet.getAllContents();
		while (cit.hasNext()) {
			Notifier next = cit.next();
			if (next instanceof EObject) {
				EObject nextEObject = (EObject) next;
				if (nextEObject.eIsProxy()) {							
					ret.add(unresolvedProxyDiagnostic(nextEObject, null, null));
				} else {
					for (EReference ref: nextEObject.eClass().getEAllReferences()) {
						Object val = nextEObject.eGet(ref);
						if (val instanceof EObject) {
							if (((EObject) val).eIsProxy()) {
								ret.add(unresolvedProxyDiagnostic((EObject) val, ref, nextEObject));
							}
						} else if (val instanceof Collection) {
							for (EObject ve: (Collection<EObject>) val) {
								if (ve.eIsProxy()) {
									ret.add(unresolvedProxyDiagnostic((EObject) ve, ref, nextEObject));
								}								
							}
						}
					}
				}
			}	
		}
		return ret;
	}
	
	/**
	 * Reports unresolved proxies
	 * @param source Unresolved proxy
	 * @param containmentReference Reference containing the proxy. Can be null.
	 * @param container Container of unresolved proxy. Can be null. 
	 * @return
	 */
	protected Diagnostic unresolvedProxyDiagnostic(EObject source, EReference containmentReference, EObject container) {
		Marked marked = EObjectAdaptable.adaptTo(source, Marked.class);
		Marker marker = marked == null ? null : marked.getMarker();
		if (marker == null) {
			return new BasicDiagnostic(Status.FAIL, "Unresolved proxy: " + source, source, containmentReference, container);
		}
	
		return new BasicDiagnostic(Status.FAIL, "Unresolved proxy at " + marker + ": " + source, source, marker, containmentReference, container);		
	}

	/**
	 * Loads resources, checks for unresolved proxies and diagnoses.
	 */
	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
		resourceSet = createResourceSet(progressMonitor);
		
		// Pre-loading
		for (URI uri: getResources()) {
			resourceSet.getResource(uri, true);
		}
		
		EcoreUtil.resolveAll(resourceSet);		
		BasicDiagnostic ret = diagnose();
		
		Diagnostician diagnostician = new Diagnostician();
		if (ret.getStatus() != Status.FAIL) {				
			roots = new ArrayList<>();
			Map<Class<Context>, Context> diagnosticContext = Collections.singletonMap(Context.class, context);
			for (Resource resource: resourceSet.getResources()) {
				for (EObject e: resource.getContents()) {
					org.eclipse.emf.common.util.Diagnostic diagnostic = diagnostician.validate(e, diagnosticContext);
					diagnosticMap.put(e, diagnostic);
					ret.add(EmfUtil.wrap(diagnostic));
					roots.add(e);
				}
			}
		}
		
		return ret;
	}
	
}