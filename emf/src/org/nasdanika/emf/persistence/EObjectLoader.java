package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.ContextLoadable;
import org.nasdanika.common.persistence.Loadable;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.emf.EmfUtil;

public class EObjectLoader implements ObjectLoader {
	
	static final String HREF_KEY = "href";

	public static final String LOAD_KEY = "load-key";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a {@link EStructuralFeature} and configuration object is not a map
	 * then the configuration object is loaded into this feature. 
	 */
	public static final String IS_DEFAULT_FEATURE = "default-feature";
	
	/**
	 * If this Nasdanika annotation details is set to "false" on a {@link EStructuralFeature} then this feature is not loaded from configuration. 
	 */
	public static final String IS_LOADABLE = "loadable";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a {@link EReference} with a concrete element type then reference element(s) are loaded using the reference type
	 * and they shall be a maps of features as opposed of a map of type to a map of features.. 
	 */
	public static final String IS_HOMOGEONUS = "homogenous";
	
	/**
	 * Default implementation of load key provider - gets load key from the Nasdanika annotation (urn:org.nasdanika) <code>load-key</code> details if it is present.
	 * If it is not converts {@link ENamedElement} element name from camel case to lower-cased kebab case. E.g. firstName is converted first-name.
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
		
		String[] ns = StringUtils.splitByCharacterTypeCamelCase(ne.getName());
		for (int i = 0; i < ns.length; ++i) {
			ns[i] = ns[i].toLowerCase();
		}
		return String.join("-", ns);
	};
	
	private org.nasdanika.common.persistence.ObjectLoader chain;
	
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

	public EObjectLoader(ObjectLoader chain, java.util.function.Function<ENamedElement,String> keyProvider, EPackage... ePackages) {
		this.chain = chain;
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
		for (Entry<String, EPackageEntry> re: registry.entrySet()) {
			if (type.startsWith(re.getKey())) {
				EPackageEntry ePackageEntry = re.getValue();
				for (EClassifier eClassifier: ePackageEntry.ePackage.getEClassifiers()) {
					if (eClassifier instanceof EClass) {
						String eClassifierKey = ePackageEntry.keyProvider.apply(eClassifier);
						if (eClassifierKey != null && type.equals(re.getKey() + eClassifierKey)) {
							return create(loader, (EClass) eClassifier, config, base, progressMonitor, marker, ePackageEntry.keyProvider);
						}
					}
				}
			}
		}
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
		}
		if (chain == null) {
			throw new ConfigurationException("Unsupported type: " + type, marker);
		}
		
		return chain.create(loader, type, config, base, progressMonitor, marker);		
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
	private Object create(
			ObjectLoader loader, 
			EClass eClass, 
			Object config, 
			URL base, 
			ProgressMonitor progressMonitor, 
			Marker marker, 
			java.util.function.Function<ENamedElement,String> keyProvider) throws Exception {
		
		Class<?> instanceClass = eClass.getInstanceClass();
		EFactory factory = eClass.getEPackage().getEFactoryInstance();
		
		// Proxy
		if (config instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<Object,Object> configMap = (Map<Object,Object>) config;
			if (configMap.size() == 1 
					&& configMap.containsKey(HREF_KEY) 
					&& configMap.get(HREF_KEY) instanceof String) {
				EObject eObject = factory.create(eClass);
				if (eObject instanceof MinimalEObjectImpl) {
					URI proxyURI = URI.createURI((String) configMap.get(HREF_KEY));
					if (base != null) {
						URI baseURI = URI.createURI(base.toString());
						proxyURI = proxyURI.resolve(baseURI);
					}
					((MinimalEObjectImpl) eObject).eSetProxyURI(proxyURI);
					return eObject;
				}
			}
		}			
		
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

}
