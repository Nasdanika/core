package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectFactory;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.emf.EmfUtil;
import org.yaml.snakeyaml.Yaml;

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
	private EClass eClass;
	private Object eReferenceKey;

	/**
	 * 
	 * @param eClass
	 * @param eReference
	 * @param eReferenceKey Reference key for loading type from annotations. If null, defautls to eReference name. Used by EMap's.
	 * @param resolver
	 * @param referenceSupplierFactory
	 * @param keyProvider
	 */
	public ReferenceFactory(
			EClass eClass,
			EReference eReference,
			String eReferenceKey,  
			EObjectLoader resolver,
			boolean referenceSupplierFactory,
			java.util.function.Function<ENamedElement,String> keyProvider) {
		
		this.eClass = eClass;
		this.eReference = eReference;
		this.eReferenceKey = Util.isBlank(eReferenceKey) ? eReference.getName() : eReferenceKey;
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
			Object ret = isHomogenous ? resolver.create(loader, effectiveReferenceType(), element, base, progressMonitor, marker, keyProvider) : loader.load(element, base, progressMonitor);
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
		if (base != null && !ref.startsWith(EObjectLoader.LATE_PROXY_RESOLUTION_URI_PREFIX)) {
			refURI = refURI.resolve(URI.createURI(base.toString()));
		}
		ConfigurationException.pushThreadMarker(marker);
		try {
			EClass eReferenceType = effectiveReferenceType();
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

	/**
	 * @return Effective reference type which defaults to the reference type, but can be overridden by the <code>reference-types</code> annotation. 
	 */
	protected EClass effectiveReferenceType() {
		EGenericType eGenericReferenceType = eClass.getFeatureType(eReference);
		EClass eReferenceType = (EClass) eGenericReferenceType.getEClassifier();
		
		String refTypes = EmfUtil.getNasdanikaAnnotationDetail(eClass, EObjectLoader.REFERENCE_TYPES);
		if (!Util.isBlank(refTypes)) {
			Yaml yaml = new Yaml();
			Map<String,Object> refTypesMap = yaml.load(refTypes);
			Object refType = refTypesMap.get(eReferenceKey);
			if (refType != null) {
				EPackage ePackage;
				String refTypeClassName;
				if (refType instanceof String) {
					ePackage = eClass.getEPackage();
					refTypeClassName = (String) refType;
				} else if (refType instanceof Map) {
					@SuppressWarnings("unchecked")
					Map<String, Object> refTypeMap = (Map<String,Object>) refType;
					Object nsURI = refTypeMap.get("ns-uri");
					if (nsURI instanceof String) {
						ResourceSet resourceSet = resolver.getResourceSet();
						Registry packageRegistry = resourceSet.getPackageRegistry();
						ePackage = packageRegistry.getEPackage((String) nsURI); 
						if (ePackage == null) {
							throw new IllegalArgumentException("EPackage not found: " + nsURI);													
						}
						Object className = refTypeMap.get("name");
						if (className instanceof String) {
							refTypeClassName = (String) className;
						} else {
							throw new IllegalArgumentException("Reference type specification name shall be a String, got: " + className);													
						}
					} else {
						throw new IllegalArgumentException("Reference type specification ns-uri shall be a String, got: " + nsURI);						
					}					
				} else {
					throw new IllegalArgumentException("Unsupported reference type specification: " + refType.getClass() + ": " + refType);
				}					
				
				eReferenceType = (EClass) ePackage.getEClassifier(refTypeClassName);
				if (eReferenceType == null) {
					throw new IllegalArgumentException("Reference type " + refTypeClassName + " not found in EPackage " + ePackage.getNsURI());						
				}
			}				
		}
		return eReferenceType;
	}	
	
}
