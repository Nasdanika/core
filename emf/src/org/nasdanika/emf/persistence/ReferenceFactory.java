package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.Collections;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectFactory;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.emf.EmfUtil;

/**
 * Creates reference value/element - {@link EObject} or proxy.
 * @author Pavel
 *
 */
public class ReferenceFactory<T> implements ObjectFactory<T> {
	
	private EObjectLoader resolver;
	private Function<ENamedElement, String> keyProvider;
	private boolean isStrictContainment;
	private boolean referenceSupplierFactory;
	private boolean resolveProxies;
	private boolean isHomogenous;
	private EReference eReference;

	/**
	 * 
	 * @param eReference {@link EReference} to which objects are loaded.
	 * @param resolver Resolver of objects from configurations.
	 * @param referenceSupplierFactory If true, string references are wrapped into supplier factories which allows token interpolation.
	 * @param keyProvider
	 */
	public ReferenceFactory(
			EReference eReference,
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			java.util.function.Function<ENamedElement,String> keyProvider) {
		
		this.eReference = eReference;
		this.resolver = resolver;
		this.referenceSupplierFactory = referenceSupplierFactory;
		this.resolveProxies = !eReference.isResolveProxies();
		this.keyProvider = keyProvider;
		
		this.isHomogenous = "true".equals(EmfUtil.getNasdanikaAnnotationDetail(eReference, EObjectLoader.IS_HOMOGENOUS));			
		this.isStrictContainment = isHomogenous && "true".equals(EmfUtil.getNasdanikaAnnotationDetail(eReference, EObjectLoader.IS_STRICT_CONTAINMENT));			
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T create(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try {
			// Strings are references if not strict containment.
			if (element instanceof String && !isStrictContainment) {
				if (referenceSupplierFactory) {
					return (T) new SupplierFactory<EObject>() {
	
						@Override
						public Supplier<EObject> create(Context context) throws Exception {
							String ref = context.interpolateToString((String) element);
							return new Supplier<EObject>() {
	
								@Override
								public double size() {
									return 1;
								}
	
								@Override
								public String name() {
									return "Loading " + ref;
								}
	
								@Override
								public EObject execute(ProgressMonitor progressMonitor) throws Exception {
									return (EObject) loadReference(ref, base, marker);
								}
								
							};
						}
						
					};
				}
				return loadReference((String) element, base, marker);
			}
			Object ret = isHomogenous ? resolver.create(loader, eReference.getEReferenceType(), element, base, progressMonitor, marker, keyProvider) : loader.load(element, base, progressMonitor);
			if (resolveProxies && ret instanceof EObject && ((EObject) ret).eIsProxy()) {
				return (T) resolver.resolve((EObject) ret);
			}
			return (T) ret;
		} catch (ConfigurationException e) {
			throw e;
		} catch (Exception e) {
			if (marker == null) {
				throw e;
			}
			throw new ConfigurationException("Error loading reference: " + e, e, marker);
		}
	}
	
	/**
	 * Loads reference. Creates a proxy if reference type is not abstract and resolve proxies is true.
	 * @param ref
	 * @param base
	 * @param marker
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T loadReference(
			String ref, 
			URL base,
			Marker marker) {
		
		URI refURI = URI.createURI(ref);
		if (base != null) {
			refURI = refURI.resolve(URI.createURI(base.toString()));
		}
		ConfigurationException.pushThreadMarker(marker);
		try {
			EClass eReferenceType = eReference.getEReferenceType();
			if (!eReferenceType.isAbstract() && !resolveProxies) {
				// Can create proxy instead of loading object
				EObject proxy = EObjectLoader.createProxy(eReferenceType, Collections.singletonMap(EObjectLoader.HREF_KEY, refURI), base);
				if (marker != null) {
					proxy.eAdapters().add(new MarkedAdapter(marker));
				}
				return (T) proxy;
			}
			return (T) resolver.getResourceSet().getEObject(refURI, true);
		} finally {
			ConfigurationException.popThreadMarker();
		}
	}	
	
}
