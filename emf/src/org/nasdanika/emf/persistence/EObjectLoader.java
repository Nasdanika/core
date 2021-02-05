package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
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
	
	private class EPackageEntry {

		EPackage ePackage;
		
		java.util.function.Function<ENamedElement,String> keyProvider;
				
		EPackageEntry(EPackage ePackage, java.util.function.Function<ENamedElement,String> keyProvider) {
			this.ePackage = ePackage;
			this.keyProvider = keyProvider == null ? LOAD_KEY_PROVIDER : keyProvider;
		}
		
	}
	
	private Map<String,EPackageEntry> registry = new HashMap<>();

	public EObjectLoader(ObjectLoader chain, EPackage... ePackages) {
		this.chain = chain;
		for (EPackage ePackage: ePackages) {
			register(ePackage);
		}
	}
	
	public EObjectLoader(EPackage... ePackages) {
		this(null, ePackages);
	}
	
	public void register(EPackage ePackage, String prefix, java.util.function.Function<ENamedElement,String> keyProvider) {
		registry.put(prefix, new EPackageEntry(ePackage, keyProvider));
	}
	
	public void register(EPackage ePackage) {
		register(ePackage, ePackage.getName() + "-", null);
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
