package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ContextLoadable;
import org.nasdanika.common.persistence.DispatchingLoader;
import org.nasdanika.common.persistence.Loadable;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.NcoreFactory;
import org.nasdanika.ncore.util.NcoreUtil;
import org.yaml.snakeyaml.Yaml;

public class EObjectLoader extends DispatchingLoader {
	
	/**
	 * If proxy URI is prefixed with ./ then the URI is not resolved relative to the context resource at the load time.
	 * Rather the prefix is removed and a URI created from the tail is passed to the proxy object to be used
	 * at proxy resolution time. 
	 */
	public static final String LATE_PROXY_RESOLUTION_URI_PREFIX = "./";	
	
	static final String HREF_KEY = "href";

	/**
	 * Use this annotation to customize load keys. Use feature-key annotation for features to also customize containment path calculation.
	 */
	public static final String LOAD_KEY = "load-key";
	
	/**
	 * Information about how element is loaded in markdown. For {@link EStructuralFeature}s overrides feature documentation if present.  
	 */
	public static final String LOAD_DOC = "load-doc";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a {@link EStructuralFeature} and configuration object is not a map
	 * then the configuration object is loaded into this feature. 
	 */
	public static final String IS_DEFAULT_FEATURE = "default-feature";
	
	/**
	 * {@link EReference} annotation with a name of value feature as a value. 
	 * Value feature is loaded with the configuration map value for reference with eKeys. 
	 * If this annotation is not set then the object itself is loaded with the value. 
	 */
	public static final String VALUE_FEATURE = "value-feature";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a {@link EAttribute} and configuration object is a {@link String}
	 * then the configuration object is resolved relative to the model resource {@link URI} (base). 
	 */
	public static final String IS_RESOLVE_URI = "resolve-uri";
	
	/**
	 * This annotation allows to override element types for homogenous references.
	 * The value shall be a YAML map of reference names to types. If entry value is a string it is treated as a class name from the same
	 * {@link EPackage} as the annotated {@link EClass}. If it is a map then it shall contain <code>ns-uri</code> and <code>name</code> keys.
	 * EClass is looked up in the loader's {@link ResourceSet} package registry.
	 */
	public static final String REFERENCE_TYPES = "reference-types";
	
	/**
	 * This annotation allows to customize element types for homogenous references based on configuration element type
	 * The value shall be a YAML map of configuration types (string, map, list, number, boolean) to reference types. 
	 * If entry value is a string it is treated as a class name from the same
	 * {@link EPackage} as the annotated {@link EReference}'s {@link EClass}. 
	 * If it is a map then it shall contain <code>ns-uri</code> and <code>name</code> keys.
	 * EClass is looked up in the loader's {@link ResourceSet} package registry.
	 */
	public static final String REFERENCE_TYPE = "reference-type";
	
	/**
	 * This annotation allows to load keys for features.
	 * The value shall be a YAML map of feature names to keys. If entry value is a string it is treated as a key name.
	 */
	public static final String LOAD_KEYS = "load-keys";
	
	/**
	 * A space-separated list of load keys which are mutually exclusive with the annotated feature.
	 */
	public static final String EXCLUSIVE_WITH = "exclusive-with";
	
	/**
	 * If true, feature is loaded even if it not changeable, but it is not injected into the object.
	 * In this case the getter can access loaded value using {@link LoadTracker} adapter get() method.
	 */
	public static final String IS_COMPUTED = "computed";
	
	public static boolean isDefaultFeature(EClass eClass, EStructuralFeature feature) {
		if ("true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, IS_DEFAULT_FEATURE))) {
			return true;
		}
		String dfName = NcoreUtil.getNasdanikaAnnotationDetail(eClass, IS_DEFAULT_FEATURE);
		if (!Util.isBlank(dfName)) {
			return feature.getName().equals(dfName);
		}
		for (EClass st: eClass.getEAllSuperTypes()) {
			dfName = NcoreUtil.getNasdanikaAnnotationDetail(st, IS_DEFAULT_FEATURE);
			if (!Util.isBlank(dfName)) {
				return feature.getName().equals(dfName);
			}			
		}
		return false;
	}
	
	public static String getValueFeature(EReference eReference) {
		return NcoreUtil.getNasdanikaAnnotationDetail(eReference, VALUE_FEATURE);
	}	
	
	/**
	 * If this Nasdanika annotation details is set to "false" on a {@link EStructuralFeature} or {@link EClass} then this feature or class is not loaded from configuration. 
	 */
	public static final String IS_LOADABLE = "loadable";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a {@link EReference} with a concrete element type then reference element(s) are loaded using the reference type
	 * and they shall be a maps of features as opposed of a map of type to a map of features.
	 */
	public static final String IS_HOMOGENOUS = "homogenous";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a homogenous {@link EReference} then string values are treated as default features, not as object references. 
	 */
	public static final String IS_STRICT_CONTAINMENT = "strict-containment";
	
	/**
	 * Default implementation of load key provider - gets load key from the Nasdanika annotation (urn:org.nasdanika) <code>load-key</code> details if it is present.
	 * For features delegates to {@link NcoreUtil} getFeatureKey.
	 * Otherwise converts {@link ENamedElement} element name from camel case to lower-cased kebab case. E.g. firstName is converted first-name.
	 */
	public static final BiFunction<EClass,ENamedElement,String> LOAD_KEY_PROVIDER = (eClass,ne) -> {
		EAnnotation na = NcoreUtil.getNasdanikaAnnotation(ne);
		if (na != null) {
			if (na.getDetails().containsKey(IS_LOADABLE) && "false".equals(na.getDetails().get(IS_LOADABLE))) {
				return null;
			}
			if (na.getDetails().containsKey(LOAD_KEY)) {
				return na.getDetails().get(LOAD_KEY);
			}
		}

		if (eClass != null && ne instanceof EStructuralFeature) {
			EStructuralFeature feature = (EStructuralFeature) ne;
			String loadKey = getLoadKey(eClass, feature);		
			return loadKey == null ? NcoreUtil.getFeatureKey(eClass, feature) : loadKey;
		}
		
		return Util.camelToKebab(ne.getName());
	};
	
	private BiFunction<EClass,ENamedElement,String> keyProvider;
	
	private class EPackageEntry {

		EPackage ePackage;
		
		BiFunction<EClass,ENamedElement,String> keyProvider;
				
		EPackageEntry(EPackage ePackage, BiFunction<EClass,ENamedElement,String> keyProvider) {
			this.ePackage = ePackage;
			this.keyProvider = keyProvider == null ? LOAD_KEY_PROVIDER : keyProvider;
		}
		
	}
	
	private Map<String,EPackageEntry> registry = new HashMap<>();

	private ResourceSet resourceSet;

	public EObjectLoader(ObjectLoader chain, BiFunction<EClass,ENamedElement,String> keyProvider, EPackage... ePackages) {
		super(chain);
		this.keyProvider = keyProvider == null ? LOAD_KEY_PROVIDER : keyProvider;
		for (EPackage ePackage: ePackages) {
			register(ePackage);
		}
	}
	
	public EObjectLoader(EPackage... ePackages) {
		this(null, null, ePackages);
	}

	public EObjectLoader(ObjectLoader chain, BiFunction<EClass,ENamedElement,String> keyProvider, ResourceSet resourceSet) {
		this(chain, keyProvider, resourceSet.getPackageRegistry().values().toArray(new EPackage[0]));
		this.resourceSet = resourceSet;
	}
	
	public EObjectLoader(ResourceSet resourceSet) {
		this(null, null, resourceSet);
	}
	
	public void register(EPackage ePackage, BiFunction<EClass,ENamedElement,String> keyProvider) {
		if (keyProvider == null) {
			keyProvider = this.keyProvider;
		}
		String packageKey = keyProvider.apply(null, ePackage);
		if (packageKey != null) {
			registry.put(packageKey + "-", new EPackageEntry(ePackage, keyProvider == null ? this.keyProvider : keyProvider));
		}
	}
	
	public void register(EPackage ePackage) {
		register(ePackage, null);
	}

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		BiSupplier<EClass,BiFunction<EClass,ENamedElement,String>> result = resolveEClass(type);
		if (result != null) {
			return create(loader, result.getFirst(), config, base, progressMonitor, marker, result.getSecond());
		}
		
		return super.create(loader, type, config, base, progressMonitor, marker);
	}
	
	/**
	 * Resolves EClass and key provider from type name.
	 * @param type
	 * @return
	 */
	public BiSupplier<EClass,BiFunction<EClass,ENamedElement,String>> resolveEClass(String type) {		
		for (Entry<String, EPackageEntry> re: registry.entrySet()) {
			if (type.startsWith(re.getKey())) {
				EPackageEntry ePackageEntry = re.getValue();
				for (EClassifier eClassifier: ePackageEntry.ePackage.getEClassifiers()) {
					if (eClassifier instanceof EClass && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eClassifier, IS_LOADABLE, "true"))) {
						String eClassifierKey = ePackageEntry.keyProvider.apply(null, eClassifier);
						if (eClassifierKey != null && type.equals(re.getKey() + eClassifierKey)) {
							return new BiSupplier<EClass, BiFunction<EClass,ENamedElement,String>>() {

								@Override
								public EClass getFirst() {
									return (EClass) eClassifier;
								}

								@Override
								public BiFunction<EClass,ENamedElement, String> getSecond() {
									return ePackageEntry.keyProvider;
								}
							}; 
						}
					}
				}
			}
		}
		return null;		
	}
	
	/**
	 * Creates an instance of EClass or a supplier factory
	 * @param loader
	 * @param eClass
	 * @param config
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @param ePackageEntry
	 * @param eClassifier
	 * @return
	 * @throws Exception
	 */
	public Object create(
			ObjectLoader loader, 
			EClass eClass, 
			Object config, 
			URL base, 
			ProgressMonitor progressMonitor, 
			Marker marker, 
			BiFunction<EClass,ENamedElement,String> keyProvider) throws Exception {
		
		// Nulls are nulls
		if (config == null) {
			return null;
		}
		
		// Proxy
		Object proxy = createProxy(eClass, config, base, marker);
		if (proxy != null) {
			return proxy;
		}
		
		Class<?> instanceClass = eClass.getInstanceClass();
		EFactory factory = eClass.getEPackage().getEFactoryInstance();
		
		if (Loadable.class.isAssignableFrom(instanceClass)) {
			EObject eObject = factory.create(eClass);
			((Loadable) eObject).load(loader, config, base, progressMonitor, marker);
			return eObject;
		}
		if (ContextLoadable.class.isAssignableFrom(instanceClass)) {
			return new SupplierFactory<EObject>() {

				@Override
				public Supplier<EObject> create(Context context) throws Exception {
					return new Supplier<EObject>() {

						@Override
						public double size() {
							return 1;
						}

						@Override
						public String name() {
							return "Creating " + eClass.getName();
						}

						@Override
						public EObject execute(ProgressMonitor pm) throws Exception {
							EObject eObject = factory.create(eClass);
							((ContextLoadable) eObject).load(context, loader, config, base, pm, marker);
							return eObject;
						}
						
					};
				}									
			};
		}							
		EObjectSupplierFactory eObjectSupplierFactory = new EObjectSupplierFactory(this, eClass, keyProvider);
		eObjectSupplierFactory.load(loader, config, base, progressMonitor, marker);
		return eObjectSupplierFactory;
	}

	/**
	 * Creates a proxy object if config is a singleton map with "href" key.
	 * @param eClass
	 * @param config
	 * @param base
	 * @return Proxy object or null if config is not a proxy config.
	 */
	public static EObject createProxy(EClass eClass, Object config, URL base, Marker marker) {
		if (config instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<Object,Object> configMap = (Map<Object,Object>) config;
			if (configMap.size() == 1 
					&& configMap.containsKey(HREF_KEY)) {
				EObject eObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
				if (eObject instanceof MinimalEObjectImpl) {
					Object href = configMap.get(HREF_KEY);					
					URI proxyURI;
					if (href instanceof URI) {
						proxyURI = (URI) href;
					} else if (href instanceof String) {
						proxyURI = URI.createURI((String) configMap.get(HREF_KEY));
					} else {
						return null;
					}
					
					String proxyURIStr = proxyURI.toString();
					if (proxyURIStr.startsWith(LATE_PROXY_RESOLUTION_URI_PREFIX)) {
						proxyURI = URI.createURI(proxyURIStr.substring(LATE_PROXY_RESOLUTION_URI_PREFIX.length()));
					} else if (base != null) {
						URI baseURI = URI.createURI(base.toString());
						proxyURI = proxyURI.resolve(baseURI);
					}
					((MinimalEObjectImpl) eObject).eSetProxyURI(proxyURI);
					mark(eObject, marker);
					
					return eObject;
				}
			}
		}
		return null;
	}

	/**
	 * If marker is not null adds marked adapter. If eObject is instance of {@link Marked} creates and sets {@link org.nasdanika.ncore.Marker}.
	 * @param eObject
	 * @param marker
	 */
	public static void mark(EObject eObject, Marker marker) {
		if (marker != null) {
			eObject.eAdapters().add(new MarkedAdapter(marker));
			if (eObject instanceof Marked) {
				org.nasdanika.ncore.Marker mMarker = NcoreFactory.eINSTANCE.createMarker();
				mMarker.setLocation(marker.getLocation());
				mMarker.setLine(marker.getLine());
				mMarker.setColumn(marker.getColumn());
				((Marked) eObject).setMarker(mMarker);
			}
		}
	}
	
	/**
	 * Resolves proxy using the loader's resource set.
	 * Does not suppress exceptions. 
	 */
	public EObject resolve(EObject proxy) {
		if (!proxy.eIsProxy()) {
			return proxy;
		}

		URI proxyURI = ((InternalEObject) proxy).eProxyURI();
		EObject ret = resourceSet.getEObject(proxyURI, true);
		return ret == null || ret == proxy ? ret : resolve(ret);
	}	

	public ResourceSet getResourceSet() {
		return resourceSet;
	}
	
	/**
	 * @return Load key from <code>load-keys</code> annotation. 
	 */
	public static String getLoadKey(EClass eClass, EStructuralFeature feature) {
		for (EClass ec: NcoreUtil.lineage(eClass)) {
			String loadKeys = NcoreUtil.getNasdanikaAnnotationDetail(ec, LOAD_KEYS);
			if (!Util.isBlank(loadKeys)) {
				Yaml yaml = new Yaml();
				Map<String,Object> loadKeyMap = yaml.load(loadKeys);
				Object loadKey = loadKeyMap.get(feature.getName());
				if (loadKey instanceof String) {
					return (String) loadKey;
				}
			}
		}
		return null;
	}	
	
}
