package org.nasdanika.ncore.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Period;
import org.nasdanika.ncore.Temporal;
import org.nasdanika.persistence.LoadTracker;
import org.yaml.snakeyaml.Yaml;

public final class NcoreUtil {
	
	/**
	 * This annotation allows to customize keys for features.
	 * The value shall be a YAML map of feature names to keys. If entry value is a string it is treated as a key name.
	 */
	public static final String FEATURE_KEYS = "feature-keys";
	
	/**
	 * Feature key Nasdanika {@link EAnnotation} to specify a key used to load a feature and to use in URI path construction.
	 */
	public static final String FEATURE_KEY = "feature-key";
	
	public static final String NASDANIKA_ANNOTATION_SOURCE = "urn:org.nasdanika";
	
	/**
	 * Use this annotation to customize load keys. Use feature-key annotation for features to also customize containment path calculation.
	 */
	public static final String LOAD_KEY = "load-key";
	
	private NcoreUtil() {
		throw new UnsupportedOperationException("Utility class");
	}

	public static EAnnotation getNasdanikaAnnotation(EModelElement modelElement) {
		return modelElement == null ? null : modelElement.getEAnnotation(NASDANIKA_ANNOTATION_SOURCE);
	}

	public static String getNasdanikaAnnotationDetail(EModelElement modelElement, String key) {
		return getNasdanikaAnnotationDetail(modelElement, key, null);
	}

	public static String getNasdanikaAnnotationDetail(EModelElement modelElement, String key, String defaultValue) {
		EAnnotation na = getNasdanikaAnnotation(modelElement);
		if (na == null || !na.getDetails().containsKey(key)) {
			return defaultValue;
		}
		return na.getDetails().get(key);
	}
	
	public static void setNasdanikaAnnotationDetail(EModelElement modelElement, String key, String value) {
		EAnnotation na = getNasdanikaAnnotation(modelElement);		
		if (value == null) {
			if (na != null) {
				na.getDetails().removeKey(key);
			}			
		} else {
			if (na == null) {
				na = EcoreFactory.eINSTANCE.createEAnnotation();
				na.setSource(NASDANIKA_ANNOTATION_SOURCE);
				modelElement.getEAnnotations().add(na);
			}
			na.getDetails().put(key, value);
		}
	}

	/**
	 * Flattened inheritance hierarchy from the argument class to all of its supertypes in the order of inheritance.
	 * @param eClass
	 * @return
	 */
	public static List<EClass> lineage(EClass eClass) {
		List<EClass> ret = new ArrayList<>();
		ret.add(eClass);
		for (EClass sc: eClass.getESuperTypes()) {
			for (EClass scLineage: lineage(sc)) {
				if (!ret.contains(scLineage)) {
					ret.add(scLineage);
				}
			}
		}
		return ret;
	}
	
	public static int cmpDistance(EClass eClass, EClass a, EClass b) {
		if (Objects.equals(a, b)) {
			return 0;
		}
		if (a.isSuperTypeOf(b)) {
			return 1;
		}
		if (b.isSuperTypeOf(a)) {
			return -1;
		}
		if (eClass.equals(a)) {
			return -1;
		}
		if (eClass.equals(b)) {
			return 1;
		}
		return distance(eClass, a) - distance(eClass, b);
	}
	
	public static int cmpDistance(EClass eClass, EGenericType a, EGenericType b) {		
		if (Objects.equals(a, b)) {
			return 0;
		}
		EClassifier aClassifier = a.getEClassifier();
		EClassifier bClassifier = b.getEClassifier();
		if (aClassifier instanceof EClass) {
			if (bClassifier instanceof EClass) {
				return cmpDistance(eClass, (EClass) aClassifier, (EClass) bClassifier);
			}
			return -1;
		} else if (bClassifier instanceof EClass) {
			return 1;
		}

		return a.hashCode() - b.hashCode();
	}
	
	/**
	 * Minimal number of steps along the inheritance hierarchy to come from the subclass to superclass
	 * @param sub
	 * @param sup
	 * @return
	 */
	public static int distance(EClass sub, EClass sup) {
		if (sub.equals(sup)) {
			return 0;
		}
		int ret = Integer.MAX_VALUE;
		if (sup.isSuperTypeOf(sub)) {
			for (EClass isup: sub.getESuperTypes()) {
				if (isup.equals(sup)) {
					return 1;
				}
				if (sup.isSuperTypeOf(isup)) {
					ret = Math.min(ret, distance(isup, sup) + 1); 
				}
			}			
		}
		return ret;
	}

	/**
	 * Finds objects referencing the argument object (target) via the specified reference.
	 * @param <T>
	 * @param target Referenced object
	 * @param eReference Reference
	 * @return Object referencing this one via the specified reference.
	 */
	public static <T extends EObject> EList<T> getReferrers(EObject target, EReference eReference) {
		EList<T> ret = new BasicInternalEList<>(EObject.class);
		
		Resource res = target.eResource(); 
		TreeIterator<?> cit = null;
		if (res == null) {
			EObject root = target;
			EObject rc;
			while ((rc = root.eContainer()) != null) {
				root = rc;
			}
			if (root != null) {
				NcoreUtil.collect(root, target, eReference, ret);
				cit = root.eAllContents();
			}			
		} else {
			ResourceSet rSet = res.getResourceSet();
			cit = rSet == null ? res.getAllContents() : rSet. getAllContents();
		}
		if (cit != null) {
			while (cit.hasNext()) {
				NcoreUtil.collect(cit.next(), target, eReference, ret);
			}			
		}
		return ret;
	}
	
	/**
	 * Get a list of all objects in the resource set which point to this 
	 * object with the provided {@link EReference}'s opposite.
	 * @param <T>
	 * @param eReference
	 * @return
	 */
	public <T extends EObject> EList<T> getOppositeReferrers(EObject target, EReference reference) {
		return getReferrers(target, Objects.requireNonNull(NcoreUtil.getOpposite(reference), "Opposite is null: " + reference));
	}

	@SuppressWarnings("unchecked")
	private static <T extends EObject> void collect(
			Object source, 
			EObject target, 
			EReference eReference,
			EList<T> accumulator) {
		if (eReference.getEContainingClass().isInstance(source)) {
			Object value = ((EObject) source).eGet(eReference);
			if (eReference.isMany()) {
				if (((Collection<?>) value).contains(target)) {
					accumulator.add((T) source);
				}
			} else if (value == target) {
				accumulator.add((T) source);
			}					
		}
	}

	/**
	 * Creates a proxy of given type with given URI 
	 * @param eClass
	 * @param uri
	 * @return
	 */
	public static EObject createProxy(EClass type, URI uri) {
		EObject proxy = type.getEPackage().getEFactoryInstance().create(type);
		((InternalEObject) proxy).eSetProxyURI(uri);
		if (proxy instanceof NamedElement) {
			((NamedElement) proxy).setName(type.getName() + " proxy for " + uri);
		}
		return proxy;
	}
	
	public static record ContainmentPath(EObject container, String path) {}

	/**
	 * Containment path relative to the object container. If the container is a map entry its key is added to path and the entry container is skipped. This happens recursively. 
	 * @param eObject
	 * @return null if there is no container. BiSupplier with first returning the first container which is not EMap entry and reference name concatenated with object key for {@link EMap}'s and with object index for many-references.
	 * Containment index is constructed taking eKeys into account.
	 */
	public static ContainmentPath containmentPath(EObject eObj) {
		EObject container = eObj.eContainer();
		if (container == null) {
			return null;
		}
		EReference eContainmentFeature = eObj.eContainmentFeature();
		
		LinkedList<String> containmentIndex = new LinkedList<>();
		while (NcoreUtil.isEMapEntry(container)) {
			Object key = ((Map.Entry<?,?>) container).getKey();
			if (key == null) {
				return null;
			}
			String idx = NcoreUtil.containmentIndex(eObj);
			if (idx != null) {
				containmentIndex.addFirst(idx);
			}
			containmentIndex.addFirst(key.toString());
			
			eContainmentFeature = container.eContainmentFeature();
			container = container.eContainer();
		}
		
		if (containmentIndex.isEmpty()) {
			String idx = NcoreUtil.containmentIndex(eObj);
			if (idx != null) {
				containmentIndex.add(idx);
			}
		} 
	
		containmentIndex.addFirst(NcoreUtil.getFeatureKey(container.eClass(), eContainmentFeature));
		return new ContainmentPath(container, String.join("/", containmentIndex));				
	}

	/**
	 * Index part of the containment path. Null for single references. EKeys for containments with EKeys. Position otherwise.
	 * @param eObj
	 * @return
	 */
	private static String containmentIndex(EObject eObj) {
		EObject container = eObj.eContainer();
		if (container == null) {
			return null;
		}
		EReference eContainmentFeature = eObj.eContainmentFeature();
		if (eContainmentFeature.isMany()) {
			EList<EAttribute> eKeys = eContainmentFeature.getEKeys();
			String position = String.valueOf(((List<?>) container.eGet(eContainmentFeature)).indexOf(eObj));
			if (eKeys.isEmpty()) {
				return position;
			}
			StringBuilder pathBuilder = new StringBuilder();
			for (EAttribute eKey: eKeys) {
				Object value = eObj.eGet(eKey);
				if (value == null || (value instanceof String && Util.isBlank((String) value))) {
					return pathBuilder.length() == 0 ? position : pathBuilder.toString();
				}
				if (pathBuilder.length() > 0) {
					pathBuilder.append("/");
				}
				pathBuilder.append(value);
			}
			return pathBuilder.toString();
		}
		return null;
	}

		/**
		 * Uses {@link ModelElement}.getUris() if the argument is an instance of ModelElement to compute URI's.
		 * Computes {@link URI}'s from the containment reference otherwise.
		 * @param eObj
		 * @return
		 */
		public static List<URI> getIdentifiers(EObject eObj) {
			if (eObj == null || isEMapEntry(eObj)) {
				return Collections.emptyList(); // EMap entries do not have URI's.
			}
			
			int ignoredOwnIdentifiers = 0; // Sets to 1 for UUID so containment URI's are resolved despite its presence.

			List<URI> ownIdentifiers = new ArrayList<>();
			if (eObj instanceof ModelElement) {
				for (String uri: ((ModelElement) eObj).getUris()) {
					if (!Util.isBlank(uri)) {
						ownIdentifiers.add(URI.createURI(uri));
					}
				}
				
				String uuid = ((ModelElement) eObj).getUuid();
				if (!Util.isBlank(uuid)) {
					ownIdentifiers.add(URI.createGenericURI("uuid", uuid, null));
					++ignoredOwnIdentifiers;
				}
			}			
			
			if (eObj instanceof SemanticIdentity) {
				ownIdentifiers.addAll(((SemanticIdentity) eObj).getIdentifiers());
			}
			
			if (eObj instanceof EModelElement) {
				// Annotation, fallback to name for named elements
				String identifiersAnnotation = NcoreUtil.getNasdanikaAnnotationDetail((EModelElement) eObj, "identifiers");
				if (Util.isBlank(identifiersAnnotation)) {
					if (eObj instanceof ENamedElement) {
						if (eObj instanceof EOperation) {
							// Need a signature, not yet supported
						} if (eObj instanceof EPackage) {
							ownIdentifiers.add(URI.createURI(((EPackage) eObj).getNsURI()));
						} else {
							EReference containmentFeature = eObj.eContainmentFeature();
							String name = ((ENamedElement) eObj).getName();
							if (!Util.isBlank(name)) {
								if (containmentFeature == null) {
									ownIdentifiers.add(URI.createURI(name));
								} else {
									ownIdentifiers.add(URI.createURI(containmentFeature.getName() + "/" + name));								
								}
							}
						}
					}
				} else {
					Yaml yaml = new Yaml();
					Object identifiersObj = yaml.load(identifiersAnnotation);
					if (identifiersObj instanceof Iterable) {
						for (Object e: (Iterable<?>) identifiersObj) {
							if (e instanceof String) {
								ownIdentifiers.add(URI.createURI((String) e));								
							}
						} 
					} else if (identifiersObj instanceof String) {
						ownIdentifiers.add(URI.createURI((String) identifiersObj));														
					}
				}
			}
			
			ContainmentPath containmentPath = containmentPath(eObj);
			if (containmentPath == null) {
				return ownIdentifiers;
			}
			
			EObject container = containmentPath.container();
			
			// Computing all permutations
			Collection<URI> ret = new HashSet<>();
			
			List<URI> relativeOwnIdentifiers = new ArrayList<>();
			for (URI ownURI: ownIdentifiers) {
				if (ownURI.isRelative()) {
					relativeOwnIdentifiers.add(ownURI);
				} else {
					ret.add(ownURI); // Absolute or opaque
				}
			}
			
			// Resolving containment URI's
			for (URI cURI: getIdentifiers(container)) {
				if (!cURI.isRelative() && cURI.isHierarchical()) {
					String cLastSegment = cURI.lastSegment();
					if (cLastSegment == null || cLastSegment.length() > 0) {
						cURI = cURI.appendSegment("");
					}
					for (URI relativeOwnURI: relativeOwnIdentifiers) {
						URI resolved = resolve(relativeOwnURI,cURI);
						if (resolved != null) {
							ret.add(resolved);
						}						
					}
				}

				if (ownIdentifiers.size() == ignoredOwnIdentifiers) { // Resorting to containment URI's only if there are no own URI's which are not UUID.
					URI containmentURI = URI.createURI(containmentPath.path());				
					URI resolved = resolve(containmentURI,cURI);
					if (resolved != null) {
						ret.add(resolved);
					}
				}
			}
			
			return ret.stream().distinct().toList();
		}
		
		private static URI resolve(URI uri, URI base) {		
			if (uri.isRelative() && base.isHierarchical()) {
				if (!base.isRelative()) { 
					return uri.resolve(base);
				}
				// Resolving containment path URI against container URI by combining all segments and treating the first first segment as scheme, second as authority and the rest as segments.			
				List<String> allParts = new ArrayList<>();
				if (base.scheme() != null) {
					allParts.add(base.scheme());
				}
				if (base.authority() != null) {
					allParts.add(base.authority());
				}
				allParts.addAll(base.segmentsList());
				
				if (uri.scheme() != null) {
					allParts.add(uri.scheme());
				}
				if (uri.authority() != null) {
					allParts.add(uri.authority());
				}
				allParts.addAll(uri.segmentsList());
				
				if (allParts.size() == 0) {
					return uri;
				}
				
				if (allParts.size() == 1) {
					return URI.createURI(allParts.get(0));
				}

				if (allParts.size() == 2 && allParts.get(1).length() == 0) {
					return URI.createURI(String.join("/", allParts) + (uri == null ? "" : uri));				
				}
				
				URI allPartsURI = URI.createHierarchicalURI(allParts.get(0), allParts.get(1), null, allParts.subList(2, allParts.size()).toArray(new String[allParts.size() - 2]), null, null);
				return uri == null ? allPartsURI : uri.resolve(allPartsURI);
			}

			return null;
		}

	/**
	 * @param eObj
	 * @return true if the argument is an entry for EMap
	 */
	public static boolean isEMapEntry(EObject eObj) {
		if (eObj instanceof Map.Entry) {
			EReference cf = eObj.eContainmentFeature();
			return cf != null && cf.isMany() && cf.getEType().getInstanceClass() == Map.Entry.class;
		}
		return false;
	}
		
	public static EObject commonAncestor(EObject a, EObject b) {
		if (a == null || b == null) {
			return null;
		}
		if (EcoreUtil.isAncestor(a, b)) {
			return a.eContainer();
		}
		if (EcoreUtil.isAncestor(b, a)) {
			b.eContainer();
		}
		for (EObject ancestor = a.eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
			if (EcoreUtil.isAncestor(ancestor, b)) {
				return ancestor;
			}
		}
		return null;
	}		
	
//	public static String relativeContainmentPath(EObject obj, EObject base) {
//		if (obj == null || base == null) {
//			return null;
//		}
//		if (EcoreUtil.isAncestor(obj, base)) {
//			// TODO
//		}
//		if (EcoreUtil.isAncestor(base, obj)) {
//			// TODO
//		}
//		for (EObject ancestor = obj.eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
//			if (EcoreUtil.isAncestor(ancestor, obj)) {
//				// TODO
//			}
//		}
//		return null;
//	}
	
//	private static String toString(URI uri) {
//		return uri == null ? null : uri.toString();
//	}
	
	/**
	 * Feature key is used for feature loading from YAML and other key-value sources and also in construction of containment path by containmentPath() method and, as such, EObject {@link URI} by getUri() method.
	 * This method traverses the class inheritance hierarchy and uses feature-keys class-level annotations to retrieve feature key. If such an annotation is not found then feature-level feature-key annotation is used.
	 * If none is found in the entire hierarchy then feature name is converted to kebab case is returned. 
	 * @param eClass Context class for which feature key is retrieved.
	 * @param feature Feature
	 * @return
	 */
	public static String getFeatureKey(EClass eClass, ETypedElement feature) {
		String featureKey = NcoreUtil.getNasdanikaAnnotationDetail(feature, FEATURE_KEY);
		if (!Util.isBlank(featureKey)) {
			return featureKey;
		}
		
		if (eClass == null) {
			if (feature instanceof EStructuralFeature) {
				eClass = ((EStructuralFeature) feature).getEContainingClass();
			} else if (feature instanceof EOperation) {
				eClass = ((EOperation) feature).getEContainingClass();
			}
		}
		
		if (eClass != null) {
			for (EClass ec: NcoreUtil.lineage(eClass)) {
				String featureKeys = NcoreUtil.getNasdanikaAnnotationDetail(ec, FEATURE_KEYS);
				if (!Util.isBlank(featureKeys)) {
					Yaml yaml = new Yaml();
					Map<String,Object> featureKeyMap = yaml.load(featureKeys);
					Object fKey = featureKeyMap.get(feature.getName());
					if (fKey instanceof String) {
						return (String) fKey;
					}
				}
			}
		}
		return Util.camelToKebab(feature.getName());
	}

	/**
	 * 
	 * @param eReference
	 * @return "Logical" opposite - either the "physical" opposite returned by {@link EReference}.getEOpposite() or a reference with a name specified in "opposite" Nasdanika annotation
	 * or a reference which specifies this reference in its "opposite" Nasdanika annotation.
	 */
	public static EReference getOpposite(EReference eReference) {
		EReference opposite = eReference.getEOpposite();
		if (opposite != null) {
			return opposite;
		}
		String oName = getNasdanikaAnnotationDetail(eReference, "opposite");
		EClass refType = eReference.getEReferenceType();
		for (EReference ref: refType.getEAllReferences()) {
			if (ref.getName().equals(oName)) {
				return ref;
			}
			if (ref.getEReferenceType() == eReference.getEContainingClass() && eReference.getName().equals(getNasdanikaAnnotationDetail(ref, "opposite"))) {
				return ref;
			}
		}
		return null;
	}

	/**
	 * Computes end and duration from start and previously loaded things.
	 * @param period
	 * @param newStart
	 */
	public static void onStart(Period period, Temporal newStart) {
		if (newStart != null) {
			LoadTracker loadTracker = (LoadTracker) EcoreUtil.getRegisteredAdapter(period, LoadTracker.class);
			if (loadTracker != null && loadTracker.isLoading(NcorePackage.Literals.PERIOD__START)) {
				if (loadTracker.isLoaded(NcorePackage.Literals.PERIOD__END)) {
					if (!loadTracker.isLoaded(NcorePackage.Literals.PERIOD__DURATION)) {
						period.setDuration(period.getEnd().minus(newStart));
					}
				} else if (loadTracker.isLoaded(NcorePackage.Literals.PERIOD__DURATION)) {
					period.setEnd(newStart.plus(period.getDuration()));
				}
			}
		}
	}

	public static void onEnd(Period period, Temporal newEnd) {
		if (newEnd != null) {
			LoadTracker loadTracker = (LoadTracker) EcoreUtil.getRegisteredAdapter(period, LoadTracker.class);
			if (loadTracker != null && loadTracker.isLoading(NcorePackage.Literals.PERIOD__END)) {
				if (loadTracker.isLoaded(NcorePackage.Literals.PERIOD__START)) {
					if (!loadTracker.isLoaded(NcorePackage.Literals.PERIOD__DURATION)) {
						period.setDuration(newEnd.minus(period.getStart()));
					}
				} else if (loadTracker.isLoaded(NcorePackage.Literals.PERIOD__DURATION)) {
					period.setStart(newEnd.minus(period.getDuration()));
				}
			}
		}
	}

	public static void onDuration(Period period, Duration newDuration) {
		if (newDuration != null) {
			LoadTracker loadTracker = (LoadTracker) EcoreUtil.getRegisteredAdapter(period, LoadTracker.class);
			if (loadTracker != null && loadTracker.isLoading(NcorePackage.Literals.PERIOD__DURATION)) {
				if (loadTracker.isLoaded(NcorePackage.Literals.PERIOD__START)) {
					if (!loadTracker.isLoaded(NcorePackage.Literals.PERIOD__END)) {
						period.setEnd(period.getStart().plus(newDuration));
					}
				} else if (loadTracker.isLoaded(NcorePackage.Literals.PERIOD__END)) {
					period.setStart(period.getEnd().minus(newDuration));
				}
			}
		}
	}
	
	/**
	 * @param eReference
	 * @return Target EClass of association class reference.
	 */
	public static EClass getAssociationTarget(EReference eReference) {
		String association = NcoreUtil.getNasdanikaAnnotationDetail(eReference, "association");
		if (Util.isBlank(association)) {
			return null;
		}
		EClass refType = eReference.getEReferenceType();
		EStructuralFeature associationFeature = refType.getEStructuralFeature(association);
		if (associationFeature instanceof EReference) {
			return ((EReference) associationFeature).getEReferenceType();
		}
		return null;		
	}
	
	public static EClassifier getType(
			String type,
			ResourceSet resourceSet,
			java.util.function.Function<String,RuntimeException> errorFactory) {
		
		return getType(
				type,
				resourceSet.getPackageRegistry().values(),
				errorFactory);
	}
	
	public static EClassifier getType(
			String type,
			Iterable<Object> ePackages,
			java.util.function.Function<String,RuntimeException> errorFactory) {
		
		Map<String, EPackage> ePackageMap = new LinkedHashMap<>();
		for (Object ep: ePackages) {
			EPackage ePackage = (EPackage) ep;
			ePackageMap.put(getEPackageName(ePackage), ePackage);
		}
		return getType(type, ePackageMap, errorFactory);
	}
	
	public static EClassifier getType(
			String type,
			Map<String,EPackage> ePackages,
			java.util.function.Function<String,RuntimeException> errorFactory) {
		if (Util.isBlank(type)) {
			return null;
		}
		URI typeURI = URI.createURI(type);
		if (typeURI.hasFragment()) {
			URI ePackageNsURI = typeURI.trimFragment();
			for (EPackage ePackage: ePackages.values()) {
				if (ePackageNsURI.equals(URI.createURI(ePackage.getNsURI()))) {
					String fragment = typeURI.fragment();
					if (fragment.startsWith("//")) {
						String eClassifierName = fragment.substring(2);
						EClassifier eClassifier = ePackage.getEClassifier(eClassifierName);
						if (eClassifier == null) {
							throw errorFactory.apply("EClassifier " + eClassifierName + " not found in EPackage: " + ePackageNsURI); 				
						}
						return eClassifier;
					} 
					throw errorFactory.apply("Unsuppported fragment, expected '//<class name>': " + fragment); 
				}
			}
			throw errorFactory.apply("EPackage not found: " + ePackageNsURI); 
		}
		
		int dotIdx = type.indexOf('.');
		if (dotIdx == -1) {
			Optional<EClassifier> typeOpt = ePackages
				.values()
				.stream()
				.flatMap(NcoreUtil::withSubPackages)
				.map(ep -> ep.getEClassifier(type))
				.filter(Objects::nonNull)
				.findFirst();
			
			if (typeOpt.isPresent()) {
				return typeOpt.get();
			}
			throw errorFactory.apply("Unknown type: " + type); 				
		}
		
		Optional<EClassifier> typeOpt = ePackages
				.entrySet()
				.stream()
				.filter(pe -> pe.getKey().equals(type.substring(0, dotIdx)))
				.map(Map.Entry::getValue)
				.map(ep -> findEClassifier(ep, type.substring(dotIdx + 1)))
				.filter(Objects::nonNull)
				.sorted((a, b) -> a.getEPackage().getNsURI().compareTo(b.getEPackage().getNsURI()))
				.findFirst();
			
		if (typeOpt.isPresent()) {
			return typeOpt.get();
		}
		throw errorFactory.apply("Unknown type: " + type); 				
	}

	
	private static Stream<EPackage> withSubPackages(EPackage ePackage) {
		Stream<EPackage> subPackagesStream = ePackage
			.getESubpackages()
			.stream()
			.flatMap(NcoreUtil::withSubPackages);
		
		return Stream.concat(Stream.of(ePackage), subPackagesStream);
	}
	
	/**
	 * Override to de-dup epackages if needed.
	 * @param ePackage
	 * @return
	 */
	protected static String getEPackageName(EPackage ePackage) {
		return getNasdanikaAnnotationDetail(ePackage, LOAD_KEY, ePackage.getName());
	}
	
	/**
	 * Finds eClassifier in the package and sub-packages
	 * @param ePackage
	 * @param name dot-separated classifier name relative to the argument package. e.g. MyClassifier for a classifier in the package, and mySubPackage.MyClassifier
	 * for a classfier in a sub-package.
	 * @return
	 */
	private static EClassifier findEClassifier(EPackage ePackage, String name) {
		int dotIdx = name.indexOf('.');
		if (dotIdx == -1) {
			return ePackage.getEClassifier(name);
		}
		String spName = name.substring(0, dotIdx);
		Optional<EPackage> subPackageOpt = ePackage
				.getESubpackages()
				.stream()
				.filter(sp -> spName.equals(sp.getName()))
				.findFirst();
		
		return subPackageOpt.isPresent() ? findEClassifier(subPackageOpt.get(), name.substring(dotIdx + 1)) : null;		
	}
	
	
}
