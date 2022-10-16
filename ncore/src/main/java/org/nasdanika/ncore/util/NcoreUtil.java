package org.nasdanika.ncore.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Util;
import org.nasdanika.persistence.LoadTracker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Period;
import org.nasdanika.ncore.Temporal;
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

	/**
	 * Containment path relative to the object container. If the container is a map entry its key is added to path and the entry container is skipped. This happens recursively. 
	 * @param eObject
	 * @return null if there is no container. BiSupplier with first returning the first container which is not EMap entry and reference name concatenated with object key for {@link EMap}'s and with object index for many-references.
	 */
	public static BiSupplier<EObject, String> containmentPath(EObject eObj) {
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
		return BiSupplier.of(container, String.join("/", containmentIndex));				
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
		 * Returns {@link ModelElement}.getUri() if the argument is an instance of ModelElement and getUri() returns a non blank string.
		 * Computes URI from the containment reference otherwise.
		 * @param eObj
		 * @return
		 */
		public static URI getUri(EObject eObj) {
			if (eObj == null || isEMapEntry(eObj)) {
				return null; // EMap entries do not have URI's.
			}

			URI ret = null;
			if (eObj instanceof ModelElement) {
				String uri = ((ModelElement) eObj).getUri();
				if (!Util.isBlank(uri)) {
					ret = URI.createURI(uri);
					if (ret.hasAbsolutePath()) {
						return ret;
					}
				}
			}			
			
			BiSupplier<EObject, String> containmentPath = containmentPath(eObj);
			if (containmentPath == null) {
				return ret;
			}
			EObject container = containmentPath.getFirst();
			if (container == null) {
				if (ret == null && eObj instanceof ModelElement) {
					String uuid = ((ModelElement) eObj).getUuid();
					if (!Util.isBlank(uuid)) {
						return URI.createURI("uuid://" + uuid);
					}
				}
				return ret;
			}
			
			URI cURI = getUri(container);
			if (cURI == null) {
				return ret;
			}
			
			URI cpURI = URI.createURI(containmentPath.getSecond());
			
			if (cURI.hasAbsolutePath()) {
				String cLastSegment = cURI.lastSegment();
				if (cLastSegment == null || cLastSegment.length() > 0) {
					cURI = cURI.appendSegment("");
				}
				URI containementURI = cpURI.resolve(cURI);
				return ret == null ? containementURI : ret.resolve(containementURI);
			}
			
			// Resolving containment path URI against container URI by combining all segments and treating the first first segment as scheme, second as authority and the rest as segments.			
			List<String> allParts = new ArrayList<>();
			if (cURI.scheme() != null) {
				allParts.add(cURI.scheme());
			}
			if (cURI.authority() != null) {
				allParts.add(cURI.authority());
			}
			allParts.addAll(cURI.segmentsList());
			
			if (cpURI.scheme() != null) {
				allParts.add(cpURI.scheme());
			}
			if (cpURI.authority() != null) {
				allParts.add(cpURI.authority());
			}
			allParts.addAll(cpURI.segmentsList());
			
			if (allParts.size() == 0) {
				return ret;
			}
			
			if (allParts.size() == 1) {
				return ret == null ? URI.createURI(allParts.get(0)) : ret;
			}

			if (allParts.size() == 2 && allParts.get(1).length() == 0) {
				return URI.createURI(String.join("/", allParts) + (ret == null ? "" : ret));				
			}
			
			URI allPartsURI = URI.createHierarchicalURI(allParts.get(0), allParts.get(1), null, allParts.subList(2, allParts.size()).toArray(new String[allParts.size() - 2]), null, null);
			return ret == null ? allPartsURI : ret.resolve(allPartsURI);
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
	public static String getFeatureKey(EClass eClass, EStructuralFeature feature) {
		String featureKey = NcoreUtil.getNasdanikaAnnotationDetail(feature, FEATURE_KEY);
		if (!Util.isBlank(featureKey)) {
			return featureKey;
		}
		
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
	
}
