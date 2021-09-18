package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
import org.nasdanika.emf.EmfUtil;

public class EObjectLoader extends DispatchingLoader {
	
	/**
	 * If proxy URI is prefixed with ./ then the URI is not resolved relative to the context resource at the load time.
	 * Rather the prefix is removed and a URI created from the tail is passed to the proxy object to be used
	 * at proxy resolution time. 
	 */
	public static final String LATE_PROXY_RESOLUTION_URI_PREFIX = "./";	
	
	static final String HREF_KEY = "href";

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
	 * If this Nasdanika annotation details is set to "true" on a {@link EAttribute} and configuration object is a {@link String}
	 * then the configuration object is resolved relative to the model resource {@link URI} (base). 
	 */
	public static final String IS_RESOLVE_URI = "resolve-uri";
	
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
		if ("true".equals(EmfUtil.getNasdanikaAnnotationDetail(feature, IS_DEFAULT_FEATURE))) {
			return true;
		}
		String dfName = EmfUtil.getNasdanikaAnnotationDetail(eClass, IS_DEFAULT_FEATURE);
		if (!Util.isBlank(dfName)) {
			return feature.getName().equals(dfName);
		}
		for (EClass st: eClass.getEAllSuperTypes()) {
			dfName = EmfUtil.getNasdanikaAnnotationDetail(st, IS_DEFAULT_FEATURE);
			if (!Util.isBlank(dfName)) {
				return feature.getName().equals(dfName);
			}			
		}
		return false;
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
	 * Otherwise converts {@link ENamedElement} element name from camel case to lower-cased kebab case. E.g. firstName is converted first-name.
	 */
	public static final java.util.function.Function<ENamedElement,String> LOAD_KEY_PROVIDER = ne -> {
		EAnnotation na = EmfUtil.getNasdanikaAnnotation(ne);
		if (na != null) {
			if (na.getDetails().containsKey(IS_LOADABLE) && "false".equals(na.getDetails().get(IS_LOADABLE))) {
				return null;
			}
			if (na.getDetails().containsKey(LOAD_KEY)) {
				return na.getDetails().get(LOAD_KEY);
			}
		}
		
		return Util.camelToKebab(ne.getName());
	};
	
	private java.util.function.Function<ENamedElement,String> keyProvider;
	
	private class EPackageEntry {

		EPackage ePackage;
		
		java.util.function.Function<ENamedElement,String> keyProvider;
				
		EPackageEntry(EPackage ePackage, java.util.function.Function<ENamedElement,String> keyProvider) {
			this.ePackage = ePackage;
			this.keyProvider = keyProvider == null ? LOAD_KEY_PROVIDER : keyProvider;
		}
		
	}
	
	private Map<String,EPackageEntry> registry = new HashMap<>();

	private ResourceSet resourceSet;

	public EObjectLoader(ObjectLoader chain, java.util.function.Function<ENamedElement,String> keyProvider, EPackage... ePackages) {
		super(chain);
		this.keyProvider = keyProvider == null ? LOAD_KEY_PROVIDER : keyProvider;
		for (EPackage ePackage: ePackages) {
			register(ePackage);
		}
	}
	
	public EObjectLoader(EPackage... ePackages) {
		this(null, null, ePackages);
	}

	public EObjectLoader(ObjectLoader chain, java.util.function.Function<ENamedElement,String> keyProvider, ResourceSet resourceSet) {
		this(chain, keyProvider, resourceSet.getPackageRegistry().values().toArray(new EPackage[0]));
		this.resourceSet = resourceSet;
	}
	
	public EObjectLoader(ResourceSet resourceSet) {
		this(null, null, resourceSet);
	}
	
	public void register(EPackage ePackage, java.util.function.Function<ENamedElement,String> keyProvider) {
		if (keyProvider == null) {
			keyProvider = this.keyProvider;
		}
		String packageKey = keyProvider.apply(ePackage);
		if (packageKey != null) {
			registry.put(packageKey + "-", new EPackageEntry(ePackage, keyProvider == null ? this.keyProvider : keyProvider));
		}
	}
	
	public void register(EPackage ePackage) {
		register(ePackage, null);
	}

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		BiSupplier<EClass,java.util.function.Function<ENamedElement,String>> result = resolveEClass(type);
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
	public BiSupplier<EClass,java.util.function.Function<ENamedElement,String>> resolveEClass(String type) {		
		for (Entry<String, EPackageEntry> re: registry.entrySet()) {
			if (type.startsWith(re.getKey())) {
				EPackageEntry ePackageEntry = re.getValue();
				for (EClassifier eClassifier: ePackageEntry.ePackage.getEClassifiers()) {
					if (eClassifier instanceof EClass && "true".equals(EmfUtil.getNasdanikaAnnotationDetail(eClassifier, IS_LOADABLE, "true"))) {
						String eClassifierKey = ePackageEntry.keyProvider.apply(eClassifier);
						if (eClassifierKey != null && type.equals(re.getKey() + eClassifierKey)) {
							return new BiSupplier<EClass, java.util.function.Function<ENamedElement,String>>() {

								@Override
								public EClass getFirst() {
									return (EClass) eClassifier;
								}

								@Override
								public java.util.function.Function<ENamedElement, String> getSecond() {
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
			java.util.function.Function<ENamedElement,String> keyProvider) throws Exception {
		
		// Nulls are nulls
		if (config == null) {
			return null;
		}
		
		// Proxy
		Object proxy = createProxy(eClass, config, base);
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
	public static EObject createProxy(EClass eClass, Object config, URL base) {
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
					return eObject;
				}
			}
		}
		return null;
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
}
