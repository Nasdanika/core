package org.nasdanika.emf.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectFactory;
import org.nasdanika.persistence.ObjectLoader;
import org.yaml.snakeyaml.Yaml;

import io.github.azagniotov.matcher.AntPathMatcher;
import io.github.azagniotov.matcher.AntPathMatcher.Builder;

/**
 * Creates reference value/element - {@link EObject} or proxy.
 * May create zero or more elements for filesets and resourcesets.
 * @author Pavel
 *
 */
public class TypedElementFactory implements ObjectFactory<List<?>> {
	
	protected static final String FILESET_EXCLUDE_KEY = "exclude";
	protected static final String FILESET_INCLUDE_KEY = "include";
	protected static final String FILESET_BASE_KEY = "base";
	protected static final String FILESET_SCHEME = "fileset:";
	public static final String FACTORY_SCHEME = "factory:";
	
	protected EObjectLoader eObjectLoader;
	protected BiFunction<EClass,ENamedElement, String> keyProvider;
	protected boolean isStrictContainment;
	protected boolean referenceSupplierFactory;
	protected boolean resolveProxies;
	protected boolean isHomogeneous;
	protected ETypedElement eTypedElement;
	protected EClass eClass;
	protected Object elementKey;
	
	public ETypedElement getETypedElement() {
		return eTypedElement;
	}
	
	public boolean isHomogeneous() {
		return isHomogeneous;
	}

	/**
	 * 
	 * @param eClass
	 * @param eTypedElement
	 * @param elementKey Reference key for loading type from annotations. If null, defautls to eReference name. Used by EMap's.
	 * @param eObjectLoader
	 * @param referenceSupplierFactory
	 * @param keyProvider
	 */
	public TypedElementFactory(
			EClass eClass,
			ETypedElement eTypedElement,
			String elementKey,  
			EObjectLoader eObjectLoader,
			boolean referenceSupplierFactory,
			BiFunction<EClass,ENamedElement,String> keyProvider) {
		
		this.eClass = eClass;
		this.eTypedElement = eTypedElement;
		this.elementKey = Util.isBlank(elementKey) ? eTypedElement.getName() : elementKey;
		this.eObjectLoader = eObjectLoader;
		this.referenceSupplierFactory = referenceSupplierFactory;
		this.keyProvider = keyProvider;
		
		this.isHomogeneous = "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eTypedElement, EObjectLoader.IS_HOMOGENEOUS)) || NcoreUtil.getNasdanikaAnnotationDetail(eTypedElement, EObjectLoader.REFERENCE_TYPE) != null;			
		this.isStrictContainment = isHomogeneous && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eTypedElement, EObjectLoader.IS_STRICT_CONTAINMENT));			
	}
	
	@Override
	public List<?> create(
			ObjectLoader loader, 
			Object element, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		try {
			// Strings are references if not strict containment.
			if (element instanceof String && !isStrictContainment) {
				if (referenceSupplierFactory) {
					return Collections.singletonList(new SupplierFactory<List<EObject>>() {  
	
						@Override
						public Supplier<List<EObject>> create(Context context) {
							String ref = context.interpolateToString((String) element);
							return new Supplier<List<EObject>>() {
	
								@Override
								public double size() {
									return 1;
								}
	
								@Override
								public String name() {
									return "Loading " + ref;
								}
	
								@Override
								public List<EObject> execute(ProgressMonitor progressMonitor) {
									return loadReference(ref, base, resolver, markers, progressMonitor);
								}
								
							};
						}
						
					});
				}
				return loadReference((String) element, base, resolver, markers, progressMonitor);
			}
			Object ret = isHomogeneous ? eObjectLoader.create(
					loader, 
					effectiveReferenceType(element), 
					element, 
					base,
					resolver,
					markers, 
					progressMonitor, 
					keyProvider,
					null)
				: 
					loader.load(element, base, resolver, progressMonitor);
			if (resolveProxies && ret instanceof EObject && ((EObject) ret).eIsProxy()) {
				return Collections.singletonList(eObjectLoader.resolve((EObject) ret));
			}
			return Collections.singletonList(ret);
		} catch (ConfigurationException e) {
			throw e;
		} catch (Exception e) {
			if (markers == null || markers.isEmpty()) {
				throw e;
			}
			throw new ConfigurationException("Error loading reference: " + e, e, markers);
		}
	}
		
	/**
	 * Loads reference. Creates a proxy if reference type is not abstract and resolve proxies is false.
	 * @param ref
	 * @param base
	 * @param marker
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<EObject> loadReference(
			String ref, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers, 
			ProgressMonitor progressMonitor) {
		
		if (ref.startsWith(FILESET_SCHEME)) {
			// Includes, excludes, factory, prototype
			String spec = ref.substring(FILESET_SCHEME.length());
			Yaml yaml = new Yaml();
			Object filesetSpec = yaml.load(spec);			
			if (filesetSpec instanceof String) {				
				return loadMatches(
						base, 
						Collections.singleton((String) filesetSpec),
						null, 
						base,
						resolver,
						markers,
						progressMonitor);
			} 
			
			if (filesetSpec instanceof List) {
				return loadMatches(
						base, 
						(List<String>) filesetSpec,
						null,
						base,
						resolver,
						markers,
						progressMonitor);				
			}
			
			if (filesetSpec instanceof Map) {
				Map<String,Object> specMap = (Map<String,Object>) filesetSpec;
				org.nasdanika.persistence.Util.checkUnsupportedKeys(specMap, FILESET_BASE_KEY, FILESET_INCLUDE_KEY, FILESET_EXCLUDE_KEY);
				Object fsBase = specMap.get(FILESET_BASE_KEY);
				if (fsBase == null || fsBase instanceof String) {
					URI fsBaseURI = fsBase == null ? base : URI.createFileURI((String) fsBase).resolve(base);
					java.util.function.Function<Object, Collection<String>> asCollection = obj -> {
						if (obj == null) {
							return null;
						}
						if (obj instanceof String) {
							return Collections.singletonList((String) obj);
						}
						if (obj instanceof Collection) {
							return (Collection<String>) obj;
						}
						throw new ConfigurationException("Expected a string or an array of strings, got " + obj.getClass() + ": " + obj, markers);
					};
					return loadMatches(
							fsBaseURI, 
							asCollection.apply(specMap.get(FILESET_INCLUDE_KEY)), 
							asCollection.apply(specMap.get(FILESET_EXCLUDE_KEY)),
							base,
							resolver,
							markers, 
							progressMonitor);				
				} else if (fsBase != null) {
					throw new ConfigurationException("FileSet base shall be a string: " + fsBase.getClass() + ": " + fsBase, markers);			
				}
			}
			
			throw new ConfigurationException("Unsupported FileSet specification type: " + filesetSpec.getClass() + ": " + filesetSpec, markers);			
		}
		
		if (ref.startsWith(FACTORY_SCHEME)) {
			// Factory
		
			throw new UnsupportedOperationException("Factories are not supported yet");
		}		
		
		// Single value
		URI refURI = URI.createURI(ref);
		if (base != null && !ref.startsWith(EObjectLoader.LATE_PROXY_RESOLUTION_URI_PREFIX)) {
			refURI = refURI.resolve(base);
		}
		ConfigurationException.pushThreadMarker(markers);
		try {
			EClass eReferenceType = effectiveReferenceType(ref);
			if (!eReferenceType.isAbstract() && !resolveProxies) {
				// Can create proxy, if possible, instead of loading object
				EObject proxy = eObjectLoader.createProxy(eReferenceType, Collections.singletonMap(EObjectLoader.HREF_KEY, refURI), base, markers, progressMonitor);
				if (proxy != null) {
					if (markers != null && !markers.isEmpty()) {
						proxy.eAdapters().add(new MarkedAdapter(new ArrayList<>(markers)));
					}
					return Collections.singletonList(proxy);
				}
			}
			return Collections.singletonList(eObjectLoader.getResourceSet().getEObject(refURI, true));
		} finally {
			ConfigurationException.popThreadMarker();
		}	
	}

	private List<EObject> loadMatches(
			URI fileSetBase,
			Collection<String> includes, 
			Collection<String> excludes, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		if (!fileSetBase.isFile()) {
			throw new ConfigurationException("Base URI for a fileset is not a file URI: " + fileSetBase, markers);
		}
		String baseFileStr = fileSetBase.toFileString();				
		File baseDir = new File(baseFileStr);
		if (baseDir.isFile()) {
			baseDir = baseDir.getParentFile();
		}
		return match(baseDir, includes, excludes).stream().map(f -> loadReference(f.toURI().toString(), base, resolver, markers, progressMonitor)).flatMap(objs -> objs.stream()).toList();		
	}
	
	private static Collection<File> match(File baseDir, Collection<String> includes, Collection<String> excludes) {
		Builder builder = new AntPathMatcher.Builder();
		AntPathMatcher matcher = builder.build();
		Map<String,File> collector = new HashMap<>();
		if (includes == null || includes.isEmpty()) {
			include(baseDir, (file,path) -> true, collector);
		} else {
			for (String include: includes) {
				include(baseDir, (file,path) -> matcher.isMatch(peel(include), path), collector);
			}
		}
		if (excludes != null) {
			collector.keySet().removeIf(path -> excludes.stream().filter(exclude -> matcher.isMatch(peel(exclude), path)).findAny().isPresent());
		}				
		return collector.values();
	}
	
	/**
	 * Removes leading ./ if it is present
	 * @param baseDir
	 * @param pattern
	 * @return
	 */
	private static String peel(String pattern) {
		return pattern != null && pattern.startsWith("./") ? peel(pattern.substring(2)) : pattern;
	}
	
	private static void include(File baseDir, BiPredicate<File,String> predicate, Map<String, File> collector) {
		BiConsumer<File, String> listener = new BiConsumer<File, String>() {
			
			@Override
			public void accept(File file, String path) {
				if (predicate.test(file, path)) {
					collector.put(path, file);
				}						
			}
		};
		org.nasdanika.common.Util.walk(null, listener, baseDir.listFiles());		
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
		EGenericType eGenericReferenceType = eTypedElement instanceof EStructuralFeature ? eClass.getFeatureType((EStructuralFeature) eTypedElement) : EcoreUtil.getReifiedType(eClass, eTypedElement.getEGenericType());
		EClass eReferenceType = (EClass) eGenericReferenceType.getEClassifier();
		
		String rTypes = NcoreUtil.getNasdanikaAnnotationDetail(eTypedElement, EObjectLoader.REFERENCE_TYPE);
		if (!Util.isBlank(rTypes)) {
			Yaml yaml = new Yaml();
			Map<String,Object> rTypesMap = yaml.load(rTypes);
			String valueFeature = EObjectLoader.getValueFeature(eTypedElement);
			Object refType;
			if (!Util.isBlank(valueFeature) && element instanceof Map) {
				refType = rTypesMap.get(referenceTypeName(((Map<?,?>) element).get(valueFeature)));
			} else {
				refType = rTypesMap.get(referenceTypeName(element));				
			}
			if (refType != null) {
				EClass eContainingClass = null;
				if (eTypedElement instanceof EStructuralFeature) {
					eContainingClass = ((EStructuralFeature) eTypedElement).getEContainingClass();
				} else if (eTypedElement instanceof EParameter) {
					eContainingClass = ((EParameter) eTypedElement).getEOperation().getEContainingClass();
				}
				if (eContainingClass != null) {
					return resolveEClass(refType, eContainingClass.getEPackage());
				}
			}				
		}
		
		for (EClass ec: NcoreUtil.lineage(eClass)) {
			String refTypes = NcoreUtil.getNasdanikaAnnotationDetail(ec, EObjectLoader.REFERENCE_TYPES);
			if (!Util.isBlank(refTypes)) {
				Yaml yaml = new Yaml();
				Map<String,Object> refTypesMap = yaml.load(refTypes);
				Object refType = refTypesMap.get(elementKey);
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
				ResourceSet resourceSet = eObjectLoader.getResourceSet();
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
