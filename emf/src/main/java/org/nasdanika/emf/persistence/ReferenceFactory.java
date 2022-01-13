package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.nasdanika.ncore.util.NcoreUtil;
import org.yaml.snakeyaml.Yaml;

/**
 * Creates reference value/element - {@link EObject} or proxy.
 * @author Pavel
 *
 */
public class ReferenceFactory<T> implements ObjectFactory<T> {
	
	private EObjectLoader resolver;
	private BiFunction<EClass,ENamedElement, String> keyProvider;
	private boolean isStrictContainment;
	private boolean referenceSupplierFactory;
	private boolean resolveProxies;
	private boolean isHomogenous;
	private EReference eReference;
	private EClass eClass;
	private Object eReferenceKey;
	
	public EReference getEReference() {
		return eReference;
	}
	
	public boolean isHomogenous() {
		return isHomogenous;
	}

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
			BiFunction<EClass,ENamedElement,String> keyProvider) {
		
		this.eClass = eClass;
		this.eReference = eReference;
		this.eReferenceKey = Util.isBlank(eReferenceKey) ? eReference.getName() : eReferenceKey;
		this.resolver = resolver;
		this.referenceSupplierFactory = referenceSupplierFactory;
		this.resolveProxies = !eReference.isResolveProxies();
		this.keyProvider = keyProvider;
		
		this.isHomogenous = "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eReference, EObjectLoader.IS_HOMOGENOUS)) || NcoreUtil.getNasdanikaAnnotationDetail(eReference, EObjectLoader.REFERENCE_TYPE) != null;			
		this.isStrictContainment = isHomogenous && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eReference, EObjectLoader.IS_STRICT_CONTAINMENT));			
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
									return (EObject) loadReference(ref, base, marker, progressMonitor);
								}
								
							};
						}
						
					};
				}
				return loadReference((String) element, base, marker, progressMonitor);
			}
			Object ret = isHomogenous ? resolver.create(loader, effectiveReferenceType(element), element, base, progressMonitor, marker, keyProvider) : loader.load(element, base, progressMonitor);
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
			Marker marker, 
			ProgressMonitor progressMonitor) {
		
		URI refURI = URI.createURI(ref);
		if (base != null && !ref.startsWith(EObjectLoader.LATE_PROXY_RESOLUTION_URI_PREFIX)) {
			refURI = refURI.resolve(URI.createURI(base.toString()));
		}
		ConfigurationException.pushThreadMarker(marker);
		try {
			EClass eReferenceType = effectiveReferenceType(ref);
			if (!eReferenceType.isAbstract() && !resolveProxies) {
				// Can create proxy, if possible, instead of loading object
				EObject proxy = resolver.createProxy(eReferenceType, Collections.singletonMap(EObjectLoader.HREF_KEY, refURI), base, marker, progressMonitor);
				if (proxy != null) {
					if ( marker != null) {
						proxy.eAdapters().add(new MarkedAdapter(marker));
					}
					return (T) proxy;
				}
			}
			return (T) resolver.getResourceSet().getEObject(refURI, true);
		} finally {
			ConfigurationException.popThreadMarker();
		}
	}
	
	protected String referenceTypeName(Object element) {
		if (element instanceof String) {
			return "string";
		}
		if (element instanceof Integer) {
			return "integer";
		}
		if (element instanceof Map) {
			return "map";
		}
		if (element instanceof Collection) {
			return "list";
		}
		if (element instanceof Boolean) {
			return "boolean";
		}
		if (element instanceof Date) {
			return "date";
		}
		// More types?
		return null;
	}
		
	/**
	 * @return Effective reference type which defaults to the reference type, but can be overridden by the <code>reference-types</code> annotation. 
	 */
	public EClass effectiveReferenceType(Object element) {
		EGenericType eGenericReferenceType = eClass.getFeatureType(eReference);
		EClass eReferenceType = (EClass) eGenericReferenceType.getEClassifier();
		
		String rTypes = NcoreUtil.getNasdanikaAnnotationDetail(eReference, EObjectLoader.REFERENCE_TYPE);
		if (!Util.isBlank(rTypes)) {
			Yaml yaml = new Yaml();
			Map<String,Object> rTypesMap = yaml.load(rTypes);
			String valueFeature = EObjectLoader.getValueFeature(eReference);
			Object refType;
			if (!Util.isBlank(valueFeature) && element instanceof Map) {
				refType = rTypesMap.get(referenceTypeName(((Map<?,?>) element).get(valueFeature)));
			} else {
				refType = rTypesMap.get(referenceTypeName(element));				
			}
			if (refType != null) {
				return resolveEClass(refType, eReference.getEContainingClass().getEPackage());
			}				
		}
		
		for (EClass ec: NcoreUtil.lineage(eClass)) {
			String refTypes = NcoreUtil.getNasdanikaAnnotationDetail(ec, EObjectLoader.REFERENCE_TYPES);
			if (!Util.isBlank(refTypes)) {
				Yaml yaml = new Yaml();
				Map<String,Object> refTypesMap = yaml.load(refTypes);
				Object refType = refTypesMap.get(eReferenceKey);
				if (refType != null) {
					return resolveEClass(refType, ec.getEPackage());
				}				
			}
		}
		return eReferenceType;
	}	
	
	public EStructuralFeature effectiveDefaultFeature(Object element) { 
		EClass effectiveReferenceType = effectiveReferenceType(element);
		for (EStructuralFeature feature: effectiveReferenceType.getEAllStructuralFeatures()) {
			if (EObjectLoader.isDefaultFeature(effectiveReferenceType, feature)) {
				return feature;
			}
		}
		return null;
	}
	
	protected EClass resolveEClass(Object typeSpec, EPackage ePackage) {
		String typeName;
		if (typeSpec instanceof String) {
			typeName = (String) typeSpec;
		} else if (typeSpec instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> refTypeMap = (Map<String,Object>) typeSpec;
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
					typeName = (String) className;
				} else {
					throw new IllegalArgumentException("Reference type specification name shall be a String, got: " + className);													
				}
			} else {
				throw new IllegalArgumentException("Reference type specification ns-uri shall be a String, got: " + nsURI);						
			}					
		} else {
			throw new IllegalArgumentException("Unsupported reference type specification: " + typeSpec.getClass() + ": " + typeSpec);
		}					
		
		EClass ret = (EClass) ePackage.getEClassifier(typeName);
		if (ret == null) {
			throw new IllegalArgumentException("Reference type " + typeName + " not found in EPackage " + ePackage.getNsURI());						
		}
		return ret;
	}
	
}
