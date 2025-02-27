package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.URIEncodable;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ContextLoadable;
import org.nasdanika.persistence.DispatchingLoader;
import org.nasdanika.persistence.LoadTracker;
import org.nasdanika.persistence.Loadable;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectLoader;
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
	 * Information about how element is loaded in markdown. For {@link EStructuralFeature}s overrides feature documentation if present.  
	 */
	public static final String LOAD_DOC = "load-doc";
	
	/**
	 * Reference to a resource with information about how element is loaded in markdown. For {@link EStructuralFeature}s overrides feature documentation if present.  
	 */
	public static final String LOAD_DOC_REF = "load-doc-reference";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on {@link EStructuralFeature} or {@link EOperation} and configuration object is not a map
	 * then the configuration object is loaded into this feature. On {@link EClass} annotation value is used as a name of default feature.
	 */
	public static final String IS_DEFAULT_FEATURE = "default-feature";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on {@link EStructuralFeature} or {@link EOperation} and configuration object is not a map
	 * then the configuration object is loaded into this feature. On {@link EClass} annotation value is used as a name of constructor feature.
	 */
	public static final String IS_CONSTRUCTOR_FEATURE = "constructor-feature";
	
	// TODO - constructor operation taking the entire configuration
	
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
	 * This annotation allows to override element types for homogeneous references.
	 * The value shall be a YAML map of reference names to types. If entry value is a string it is treated as a class name from the same
	 * {@link EPackage} as the annotated {@link EClass}. If it is a map then it shall contain <code>ns-uri</code> and <code>name</code> keys.
	 * EClass is looked up in the loader's {@link ResourceSet} package registry.
	 */
	public static final String REFERENCE_TYPES = "reference-types";
	
	/**
	 * This annotation allows to customize element types for homogeneous references based on configuration element type
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
	
	private ThreadLocal<java.util.function.BiFunction<EClass, ProgressMonitor, EObject>> threadConstructor = new ThreadLocal<>();
	
	/**
	 * Sets thread constructor for creation of 
	 * @param constructor
	 */
	public void setThreadConstructor(java.util.function.BiFunction<EClass, ProgressMonitor, EObject> constructor) {
		threadConstructor.set(constructor);
	}
	
	/**
	 * 
	 * @param eClass
	 * @param feature
	 * @return Features in the argument class the argument feature is exclusive with.
	 */
	public static Object[] getExclusiveWith(EClass eClass, ENamedElement feature, BiFunction<EClass,ENamedElement,String> keyProvider) {
		Set<String> ret = new HashSet<>();
		for (EStructuralFeature sf: eClass.getEAllStructuralFeatures()) {
			String exclusiveWithStr = NcoreUtil.getNasdanikaAnnotationDetail(sf, "exclusive-with"); // A space-separated list of load keys which are mutually exclusive with the annotated feature.
			if (!Util.isBlank(exclusiveWithStr)) {
				for (String ew: exclusiveWithStr.split("\\s")) {
					if (sf == feature) {
						ret.add(ew);
					} else if (ew.equals(keyProvider.apply(eClass, feature))) {
						ret.add(keyProvider.apply(eClass, sf));
					}
					
				}
			}
		}

		return ret.toArray();
	}
	
	/**
	 * If true, feature is loaded even if it not changeable, but it is not injected into the object.
	 * In this case the getter can access loaded value using {@link LoadTracker} adapter get() method.
	 */
	public static final String IS_COMPUTED = "computed";
	
	public static boolean isDefaultFeature(EClass eClass, ENamedElement feature) {
		if ("true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, IS_DEFAULT_FEATURE))) {
			return true;
		}
		if (eClass == null) {
			if (feature instanceof EStructuralFeature) {
				eClass = ((EStructuralFeature) feature).getEContainingClass();
			} else if (feature instanceof EOperation) {
				eClass = ((EOperation) feature).getEContainingClass();
			}
		}
		
		if (eClass != null) {
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
		}
		return false;
	}
	
	public static boolean isConstructorFeature(EClass eClass, ENamedElement feature) {
		if ("true".equals(NcoreUtil.getNasdanikaAnnotationDetail(feature, IS_CONSTRUCTOR_FEATURE))) {
			return true;
		}
		String dfName = NcoreUtil.getNasdanikaAnnotationDetail(eClass, IS_CONSTRUCTOR_FEATURE);
		if (!Util.isBlank(dfName)) {
			return feature.getName().equals(dfName);
		}
		for (EClass st: eClass.getEAllSuperTypes()) {
			dfName = NcoreUtil.getNasdanikaAnnotationDetail(st, IS_CONSTRUCTOR_FEATURE);
			if (!Util.isBlank(dfName)) {
				return feature.getName().equals(dfName);
			}			
		}
		return false;
	}
	
	public static String getValueFeature(ETypedElement eTypedElement) {
		return NcoreUtil.getNasdanikaAnnotationDetail(eTypedElement, VALUE_FEATURE);
	}	
	
	/**
	 * If this Nasdanika annotation details is set to "false" on a {@link EStructuralFeature} or {@link EClass} then this feature or class is not loaded from configuration. 
	 */
	public static final String IS_LOADABLE = "loadable";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a {@link EReference} with a concrete element type then reference element(s) are loaded using the reference type
	 * and they shall be a maps of features as opposed of a map of type to a map of features.
	 */
	public static final String IS_HOMOGENEOUS = "homogeneous";
	
	/**
	 * If this Nasdanika annotation details is set to "true" on a homogeneous {@link EReference} then string values are treated as default features, not as object references. 
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
			if (na.getDetails().containsKey(NcoreUtil.LOAD_KEY)) {
				return na.getDetails().get(NcoreUtil.LOAD_KEY);
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
	
	private java.util.function.Supplier<Collection<Map.Entry<String,EPackageEntry>>> registrySupplier = () -> Collections.emptySet();
	
	private Collection<Map.Entry<String,EPackageEntry>> getRegistry() {
		return registrySupplier.get();
	}

	private ResourceSet resourceSet;
	
	public EObjectLoader(ObjectLoader chain) {
		super(chain);
	}

	public EObjectLoader(ObjectLoader chain, BiFunction<EClass,ENamedElement,String> keyProvider, EPackage... ePackages) {
		this(chain);
		this.keyProvider = keyProvider == null ? LOAD_KEY_PROVIDER : keyProvider;
		if (ePackages.length > 0) {
			Map<String,EPackageEntry> registry = new LinkedHashMap<>();
			for (EPackage ePackage: ePackages) {
				Entry<String, EPackageEntry> re = createRegistryEntry(ePackage);
				if (re != null) {
					registry.put(re.getKey(), re.getValue());
				}
			}
			registrySupplier = registry::entrySet;
		}
	}
	
	public MarkerFactory getMarkerFactory() {
		return markerFactory;
	}
	
	public void setMarkerFactory(MarkerFactory markerFactory) {
		this.markerFactory = markerFactory;
	}
	
	private MarkerFactory markerFactory = MarkerFactory.INSTANCE;
	
	public EObjectLoader(EPackage... ePackages) {
		this(null, null, ePackages);
	}

	public EObjectLoader(ObjectLoader chain, BiFunction<EClass,ENamedElement,String> keyProvider, ResourceSet resourceSet) {
		this(chain, keyProvider);
		this.resourceSet = resourceSet;
		
		if (resourceSet != null) {
			registrySupplier = () -> resourceSet.getPackageRegistry().values().stream().map(EPackage.class::cast).map(this::createRegistryEntry).toList();
		}		
	}
	
	public EObjectLoader(ResourceSet resourceSet) {
		this(null, null, resourceSet);
	}
	
	private Map.Entry<String, EPackageEntry> createRegistryEntry(EPackage ePackage, BiFunction<EClass,ENamedElement,String> keyProvider) {
		if (keyProvider == null) {
			keyProvider = this.keyProvider;
		}
		String packageKey = keyProvider.apply(null, ePackage);
		if (Util.isBlank(packageKey)) {
			return null;
		}
		return Map.entry(packageKey + "-", new EPackageEntry(ePackage, keyProvider == null ? this.keyProvider : keyProvider));
	}
	
	private Map.Entry<String, EPackageEntry> createRegistryEntry(EPackage ePackage) {
		return createRegistryEntry(ePackage, null);
	}

	/**
	 * Creates a new EObject from a prototype. 
	 * @param loader
	 * @param type
	 * @param config
	 * @param prototype Prototype EObject, can be null.
	 * @param base
	 * @param progressMonitor
	 * @param marker
	 * @return
	 */
	public <T> T create(
			ObjectLoader loader, 
			String type, 
			Object config, 
			java.util.function.BiFunction<EClass, ProgressMonitor, EObject> constructor, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers, 
			ProgressMonitor progressMonitor) {
		ResolutionResult result = resolveEClass(type);
		if (result != null) {
			return create(
					loader, 
					result.eClass(), 
					config, 
					base,
					resolver,
					markers, 
					progressMonitor, 
					result.keyProvider(), 
					constructor, 
					true);
		}
		
		return super.create(loader, type, config, base, resolver, markers, progressMonitor);
	}
	
	/**
	 * Loading is done by "creating" an object using a "constructor" which returns the target argument
	 */
	@Override
	public void load(
			ObjectLoader loader, 
			Object config, 
			Object target, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		
		Object result = create(
				loader,
				((EObject) target).eClass(),
				config,
				base,
				resolver,
				markers,
				progressMonitor,
				keyProvider,
				(eClass, pm) -> (EObject) target,
				false);
		
		if (result instanceof SupplierFactory) {
			((SupplierFactory<?>) result).create(getContext()).execute(progressMonitor);
		}
	}
	
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}
	
	@Override
	public  <T> T create(
			ObjectLoader loader, 
			String type, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		return create(
				loader, 
				type, 
				config, 
				threadConstructor.get(), 
				base,
				resolver,
				markers,
				progressMonitor);
	}	
	
	public record ResolutionResult(EClass eClass, BiFunction<EClass,ENamedElement,String> keyProvider) {}
	
	/**
	 * Resolves EClass and key provider from type name.
	 * @param type
	 * @return
	 */
	public ResolutionResult resolveEClass(String type) {		
		for (Entry<String, EPackageEntry> re: getRegistry()) {
			EPackageEntry ePackageEntry = re.getValue();
			if (type.startsWith(re.getKey())) {
				for (EClassifier eClassifier: ePackageEntry.ePackage.getEClassifiers()) {
					if (eClassifier instanceof EClass && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eClassifier, IS_LOADABLE, "true"))) {
						String eClassifierKey = ePackageEntry.keyProvider.apply(null, eClassifier);
						if (eClassifierKey != null && type.equals(re.getKey() + eClassifierKey)) {
							return resolveResult(ePackageEntry, eClassifier); 
						}
					}
				}
			} else {
				String exportsAnnotation = NcoreUtil.getNasdanikaAnnotationDetail(ePackageEntry.ePackage, "exports");
				if (!Util.isBlank(exportsAnnotation)) {
					Yaml yaml = new Yaml();
					Map<String,Object> exportsMap = yaml.load(exportsAnnotation);
					for (Entry<String, Object> eme: exportsMap.entrySet()) {
						EClassifier eClassifier = ePackageEntry.ePackage.getEClassifier(eme.getKey());
						if (eClassifier instanceof EClass && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eClassifier, IS_LOADABLE, "true"))) {
							Object val = eme.getValue();
							if (val instanceof Collection) {
								for (Object vale: (Collection<?>) val) {
									if (type.equals(vale)) {
										return resolveResult(ePackageEntry, eClassifier);
									}
								}
							} else if (type.equals(val)) {
								return resolveResult(ePackageEntry, eClassifier);								
							}
						}
					}
				}
			}
		}
		
		// <Ns URI>#//<class name> 
		for (Entry<String, EPackageEntry> re: getRegistry()) {
			EPackageEntry ePackageEntry = re.getValue();
			EPackage ePackage = ePackageEntry.ePackage;
			String nsURI = ePackage.getNsURI();
			for (EClassifier eClassifier: ePackage.getEClassifiers()) {
				String cName = nsURI + "#//" + eClassifier.getName();
				if (cName.equals(type)) {
					return resolveResult(ePackageEntry, eClassifier);
				}
			}
		}
				
		return null;		
	}

	private ResolutionResult resolveResult(EPackageEntry ePackageEntry,	EClassifier eClassifier) {
		return new ResolutionResult((EClass) eClassifier, ePackageEntry.keyProvider);
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
	 */
	@SuppressWarnings("unchecked")
	public <T> T create(
			ObjectLoader loader, 
			EClass eClass, 
			Object config, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers, 
			ProgressMonitor progressMonitor, 
			BiFunction<EClass,ENamedElement,String> keyProvider,
			java.util.function.BiFunction<EClass, ProgressMonitor, EObject> constructor, 
			boolean enforceRequired) {
		
		// Nulls are nulls
		if (config == null) {
			return null;
		}
		
		// Proxy
		EObject proxy = createProxy(eClass, config, base, markers, progressMonitor);
		if (proxy != null) {
			return (T) configure(
					proxy, 
					loader, 
					config, 
					base, 
					null, 
					progressMonitor);
		}
		
		Class<?> instanceClass = eClass.getInstanceClass();
		EFactory factory = eClass.getEPackage().getEFactoryInstance();
		
		if (Loadable.class.isAssignableFrom(instanceClass)) {
			EObject eObject = factory.create(eClass);
			((Loadable) eObject).load(loader, config, base, resolver, markers, progressMonitor);
			return (T) configure(eObject, loader, config, base, null, progressMonitor);
		}
		if (ContextLoadable.class.isAssignableFrom(instanceClass)) {
			return (T) new SupplierFactory<EObject>() {

				@Override
				public Supplier<EObject> create(Context context) {
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
						public EObject execute(ProgressMonitor pm) {
							EObject eObject = factory.create(eClass);
							((ContextLoadable) eObject).load(context, loader, config, base, resolver, markers, pm);
							return configure(eObject, loader, config, base, context, pm);
						}
						
					};
				}									
			};
		}							
		EObjectSupplierFactory eObjectSupplierFactory = createEObjectSupplierFactory(eClass, keyProvider, constructor, enforceRequired);
		eObjectSupplierFactory.load(loader, config, base, resolver, markers, progressMonitor);
		FunctionFactory<EObject, EObject> configureFactory = ctx -> new org.nasdanika.common.Function<EObject, EObject>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Configure";
			}

			@Override
			public EObject execute(EObject eObject, ProgressMonitor pm) {
				return configure(eObject, loader, config, base, ctx, pm);
			}
			
		};
		return (T) eObjectSupplierFactory.then(configureFactory);
	}
	
	/**
	 * Override to configure an object after creation. This method can replace the object.
	 * @param obj Created object to configure.
	 * @param loader Root loader.
	 * @param config Object config.
	 * @param base Base URI.
	 * @param context Context. Not null if created by a supplier.
	 * @param progressMonitor Progress monitor.
	 * @return Configured object. This implementation loads linked resources.
	 */
//	@SuppressWarnings("unchecked")
	protected <T> T configure(
			T obj,
			ObjectLoader loader, 
			Object config, 
			URI base, 
			Context context,
			ProgressMonitor progressMonitor) {
		
		// Loading linked resources if resourceSet is not null.
//		if (resourceSet != null) {
//			if (obj instanceof EObject) {
//				EObject eObj = (EObject) obj;
//				EClass eClass = eObj.eClass();
//				for (EAttribute eAttr: eClass.getEAllAttributes()) {
//					if (isLinkedResourceAttribute(eAttr)) {
//						linkResources(eObj, eObj, eAttr, loader, config, base, context, progressMonitor);
//					}
//				}
//				
//				// EMaps with EString value attributes annotated with content-type = resource-uri.
//				for (EReference eRef: eClass.getEAllReferences()) {
//					EClassifier refType = eRef.getEType();
//					Class<?> refClass = refType.getInstanceClass();
//					if (eRef.isMany() && refType instanceof EClass && refClass != null && Map.Entry.class.isAssignableFrom(refClass)) {
//						EStructuralFeature valueFeature = ((EClass) refType).getEStructuralFeature("value");
//						if (valueFeature instanceof EAttribute && isLinkedResourceAttribute((EAttribute) valueFeature)) {
//							for (EObject entry: (Collection<EObject>) eObj.eGet(eRef)) {
//								linkResources(eObj, entry, (EAttribute) valueFeature, loader, config, base, context, progressMonitor);							
//							}
//						}
//					}
//				}
//			}
//		}
		
		return obj;
	}
	
//	protected boolean isLinkedResourceAttribute(EAttribute eAttribute) {
//		return eAttribute != null && eAttribute.getEType() == EcorePackage.Literals.ESTRING && "resource-uri".equals(NcoreUtil.getNasdanikaAnnotationDetail(eAttribute, "content-type")); 
//	}
	
//	/**
//	 * Loads and links resources specified in the valueObject's linkedResourceAttribute to the semanticElement.
//	 * @param semanticElement Loaded object to link the resource to.
//	 * @param valueObject Value object containing linked resource attribute, can be different from the semanticElement in case of EMap's.
//	 * @param linkedResourceAttribute Attribute containing resource URI.
//	 * @param loader 
//	 * @param config
//	 * @param base
//	 * @param context
//	 * @param progressMonitor
//	 */
//	@SuppressWarnings("unchecked")
//	protected void linkResources(
//			EObject semanticElement, 
//			EObject valueObject, 
//			EAttribute linkedResourceAttribute,
//			ObjectLoader loader, 
//			Object config, 
//			URI base, 
//			Context context,
//			ProgressMonitor progressMonitor) {
//		
//		if (linkedResourceAttribute.isMany()) {
//			ListIterator<String> it = ((List<String>) valueObject.eGet(linkedResourceAttribute)).listIterator();
//			while (it.hasNext()) {
//				it.set(linkResource(semanticElement, it.next(), loader, config, base, context, progressMonitor));
//			}
//		} else {
//			valueObject.eSet(linkedResourceAttribute, linkResource(semanticElement, (String) valueObject.eGet(linkedResourceAttribute), loader, config, base, context, progressMonitor));
//		}		
//	}
	
//	/**
//	 * Loads and links resources specified in the valueObject's linkedResourceAttribute to the semanticElement.
//	 * @param semanticElement Loaded object to link the resource to.
//	 * @param resourceUriStr
//	 * @param loader
//	 * @param config
//	 * @param base
//	 * @param context
//	 * @param progressMonitor
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	protected String linkResource(
//			EObject semanticElement,
//			String resourceUriStr,
//			ObjectLoader loader, 
//			Object config, 
//			URI base, 
//			Context context,
//			ProgressMonitor progressMonitor) {
//		
//		URI resourceURI = URI.createURI(resourceUriStr);
//		if (resourceURI.isRelative() && base != null && !base.isRelative() && base.isHierarchical()) {
//			resourceURI = resourceURI.resolve(base);
//		}
//		Resource linkedResource = resourceSet.getResource(resourceURI, true);
//		
//		// Adding to linked resources adapter for setting children in createSemanticElement				
//		LinkedResourcesAdapter linkedResourcesAdapter = (LinkedResourcesAdapter) EcoreUtil.getRegisteredAdapter(semanticElement, LinkedResourcesAdapter.class);
//		if (linkedResourcesAdapter == null) {
//			linkedResourcesAdapter = new LinkedResourcesAdapter();
//			semanticElement.eAdapters().add(linkedResourcesAdapter);
//		}
//		linkedResourcesAdapter.getLinkedResources().add(linkedResource);
//		
//		if (linkedResource instanceof AbstractEObjectFactoryProcessorResource) {
//			((AbstractEObjectFactoryProcessorResource<EObject>) linkedResource).setParent(semanticElement);
//		}
//		
//		return encodeLinkedResource(semanticElement, resourceUriStr, linkedResource, loader, config, base, context, progressMonitor);
//	}
	
	/**
	 * Optionally changes the value of the resourceUriStr by encoding the resource.
	 * This implementation replaces resourceUriStr value with encoded Drawio document so it can be used to generate interactive diagrams in documentation. 
	 * @param semanticElement Loaded object to link the resource to.
	 * @param resourceUriStr
	 * @param linkedResource
	 * @param loader
	 * @param config
	 * @param base
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	protected String encodeLinkedResource(
			EObject semanticElement,
			String resourceUriStr,
			Resource linkedResource,
			ObjectLoader loader, 
			Object config, 
			URI base, 
			Context context,
			ProgressMonitor progressMonitor) {
		
		if (linkedResource instanceof URIEncodable) {
			return ((URIEncodable) linkedResource).encode().toString();
		}
		
		return resourceUriStr;	
	}
		
	/**
	 * Creates a new {@link EObjectSupplierFactory}. This implementation calls EObjectSupplierFactory constructor.
	 * @param eClass
	 * @param keyProvider
	 * @return
	 */
	protected EObjectSupplierFactory createEObjectSupplierFactory(
			EClass eClass,
			BiFunction<EClass, ENamedElement, String> keyProvider,
			java.util.function.BiFunction<EClass, ProgressMonitor, EObject> constructor, 
			boolean enforceRequired) {		
		return new EObjectSupplierFactory(this, eClass, keyProvider, constructor, enforceRequired);
	}
	
	/**
	 * Resolves object reference. This implementation creates a proxy if the type is not abstract and resolveProxies is true.
	 * Otherwise it retrieves the object from the resource set without regard to its type.
	 * Override to customize resolution, e.g. modify the URI taking the loading context into account or load objects on demand from external systems. 
	 * @param uri 
	 * @param type
	 * @param base
	 * @param markers
	 * @param resolveProxies True if client supports proxy resolution so this method may return a proxy object.
	 * @param progressMonitor
	 * @return
	 */
	public List<EObject> resolve(
			URI uri, 
			EClass type, 
			URI base, 
			Collection<? extends Marker> markers,
			boolean resolveProxies,
			ProgressMonitor progressMonitor) {
		if (!type.isAbstract() && !resolveProxies) {
			// Can create proxy, if possible, instead of loading object
			EObject proxy = createProxy(type, Collections.singletonMap(EObjectLoader.HREF_KEY, uri), base, markers, progressMonitor);
			if (proxy != null) {
				if (markers != null && !markers.isEmpty()) {
					proxy.eAdapters().add(new MarkedAdapter(new ArrayList<>(markers)));
				}
				return Collections.singletonList(proxy);
			}
		}
		return Collections.singletonList(getResourceSet().getEObject(uri, true));
	}

	/**
	 * Creates a proxy object if config is a singleton map with "href" key.
	 * @param eClass
	 * @param config
	 * @param base
	 * @return Proxy object or null if config is not a proxy config.
	 */
	public EObject createProxy(EClass eClass, Object config, URI base, Collection<? extends Marker> markers, ProgressMonitor progressMonitor) {
		if (config instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<Object,Object> configMap = (Map<Object,Object>) config;
			if (configMap.size() == 1 && configMap.containsKey(HREF_KEY)) {
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
						proxyURI = proxyURI.resolve(base);
					}
					((MinimalEObjectImpl) eObject).eSetProxyURI(proxyURI);
					mark(eObject, markers, progressMonitor);
					
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
	public void mark(EObject eObject, Collection<? extends Marker> markers, ProgressMonitor progressMonitor) {
		if (markers != null) {
			eObject.eAdapters().add(new MarkedAdapter(new ArrayList<>(markers)));
			if (eObject instanceof Marked) {		
				for (Marker marker: markers) {
					org.nasdanika.ncore.Marker mMarker = markerFactory.createMarker(marker.getLocation(), progressMonitor);
					mMarker.setPosition(marker.getPosition());
					((Marked) eObject).getMarkers().add(0, mMarker);
				}
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
