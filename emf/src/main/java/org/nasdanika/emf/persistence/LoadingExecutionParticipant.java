package org.nasdanika.emf.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.ncore.util.NcoreResourceSet;

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
//	protected ClassLoader classLoader;
	
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
		ResourceSet ret = new NcoreResourceSet() {

			@Override
			protected boolean matchURI(EObject eObj, URI uri) {
				return super.matchURI(eObj, uri) || LoadingExecutionParticipant.this.matchURI(eObj, uri);
			}
			
		};

		// XMI as default.
		ret.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
//		// replacing URI and delegating to resource set so it loads using extension factories.
//		ret.getResourceFactoryRegistry().getProtocolToFactoryMap().put(Util.CLASSPATH_SCHEME, new ResourceFactoryImpl() {
//			
//			@Override
//			public Resource createResource(URI uri) {
//				ClassLoader classLoader = getClassLoader();
//				String resourcePath = uri.toString().substring(Util.CLASSPATH_URL_PREFIX.length());
//				URL resource = classLoader.getResource(resourcePath);
//				if (resource == null) {
//					throw new IllegalArgumentException("Classpath resource not found: " + resourcePath);
//				}
//				return ret.createResource(URI.createURI(resource.toString()));
//			}
//			
//		});
		
		ret.getURIConverter().getURIHandlers().add(0, new URIHandlerImpl() {

			@Override
			public boolean canHandle(URI uri) {
				return uri != null && Util.CLASSPATH_SCHEME.equals(uri.scheme());
			}

			@Override
			public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
				return DefaultConverter.INSTANCE.toInputStream(uri);
			}
			
		});
		
		Registry packageRegistry = ret.getPackageRegistry();
		for (EPackage ePackage: getEPackages()) {
			packageRegistry.put(ePackage.getNsURI(), ePackage);
		}		
		
		return ret;
	}
	
	protected abstract Collection<EPackage> getEPackages();
	
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
			return new BasicDiagnostic(getUnresolvedProxyStatus(), "Unresolved proxy: " + source, source, containmentReference, container);
		}
	
		return new BasicDiagnostic(getUnresolvedProxyStatus(), "Unresolved proxy at " + marker + ": " + source, source, marker, containmentReference, container);		
	}

	/**
	 * Override if unresolved proxies are allowed in loaded resources, e.g. they'd be resolvable at some point after the model is loaded. 
	 * This method is called only if isDiagnose() returns true (default). If the diagnostic would result in errors, e.g. due to unresolved
	 * proxies in opposites, then override isDiagnoseModel() instead to return false and then explicitly diagnose the model which is fully loaded and as 
	 * such all proxies are resolvable. 
	 * @return Diagnostic status for unresolved proxies. This method returns FAIL which results in exception during load.
	 */
	protected Status getUnresolvedProxyStatus() {
		return Status.FAIL;
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
		
		// Resolving all proxies and clearing all caches
		EcoreUtil.resolveAll(resourceSet);
		resourceSet.getAllContents().forEachRemaining(notifier -> notifier.eNotify(FeatureCacheAdapter.CLEAR_CACHE));

		if (isDiagnoseModel()) {
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
		
		return ExecutionParticipant.super.diagnose(progressMonitor);
	}
	
	/**
	 * If this method returns <code>true</code> the loaded model is diagnosed by {@link Diagnostician}.
	 * Override to return <code>false</code> if the diagnosis is not required after loading, e.g. the model is knows to contain diagnostic errors. 
	 * E.g. it is a partial model with unresolved proxies.
	 * @return  
	 */
	protected boolean isDiagnoseModel() {
		return true;
	}

	/**
	 * @return Classloader for loading classpath resources (URI's with <code>classpath</code> scheme).
	 * This implementation returns this class classloader.
	 */
	protected ClassLoader getClassLoader() {
		return getClass().getClassLoader();
	}
	
}