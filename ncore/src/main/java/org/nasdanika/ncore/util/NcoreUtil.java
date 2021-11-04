package org.nasdanika.ncore.util;

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
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
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
				if (!eObj.eIsSet(eKey)) {
					return position;
				}
				if (pathBuilder.length() > 0) {
					pathBuilder.append("/");
				}
				pathBuilder.append(eObj.eGet(eKey));
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
			
			if (eObj instanceof ModelElement) {
				String uri = ((ModelElement) eObj).getUri();
				if (!Util.isBlank(uri)) {
					return URI.createURI(uri);
				}
			}
			
			BiSupplier<EObject, String> containmentPath = containmentPath(eObj);
			if (containmentPath == null) {
				return null;
			}
			EObject container = containmentPath.getFirst();
			if (container == null) {
	//			Leads to stack overflow during loading.			
	//			for (EObject referrer: getReferrers(eObj, NcorePackage.Literals.REFERENCE__TARGET)) {
	//				return getUri(referrer);
	//			}
				if (eObj instanceof ModelElement) {
					String uuid = ((ModelElement) eObj).getUuid();
					if (!Util.isBlank(uuid)) {
						return URI.createURI("uuid://" + uuid);
					}
				}
				return null;
			}
			
			URI cURI = getUri(container);
			if (cURI == null || !cURI.isHierarchical()) {
				return null;
			}
			String cLastSegment = cURI.lastSegment();
			if (cLastSegment == null || cLastSegment.length() > 0) {
				cURI = cURI.appendSegment("");
			}
			URI cpURI = URI.createURI(containmentPath.getSecond());
			return cpURI.resolve(cURI);
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
	
}
