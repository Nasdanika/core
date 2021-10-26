package org.nasdanika.emf;

import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.emf.persistence.FeatureCacheAdapter;
import org.nasdanika.ncore.impl.ModelElementImpl;

public class EmfUtil {

	private static final String NASDANIKA_ANNOTATION_SOURCE = "urn:org.nasdanika";
	public static final String ICON_KEY = "icon";
	public static final String LABEL_KEY = "label";
	
	public static final Comparator<ENamedElement> ENAMED_ELEMENT_COMPARATOR = new Comparator<ENamedElement>() {

		@Override
		public int compare(ENamedElement a, ENamedElement b) {
			return a.getName().compareTo(b.getName());
		}
		
	};

	private EmfUtil() {
		// Singleton
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
	
	public static String getCategory(EModelElement modelElement) {
		return getNasdanikaAnnotationDetail(modelElement, "category");
	}

	/**
	 * Collects type dependencies for a given class - attribute types, eoperation return and exception types, eparameter types.
	 * Does not collect reference types and supertypes, as they are reported differently, but collects their generic parameter types.
	 * @param eClass
	 * @return
	 */
	public static Collection<EClassifier> collectTypeDependencies(EClass eClass) {
		Collection<EClassifier> collector = new HashSet<>();
		Predicate<EGenericType> predicate = new HashSet<EGenericType>()::add;
		for (EGenericType st: eClass.getEGenericSuperTypes()) {
			collector.addAll(collectGenericTypeDependencies(st, false, predicate));
		}
		for (EReference ref: eClass.getEReferences()) {
			collector.addAll(collectGenericTypeDependencies(ref.getEGenericType(), false, predicate));
		}		
		
		for (EAttribute attr: eClass.getEAttributes()) {
			collector.addAll(collectGenericTypeDependencies(attr.getEGenericType(), true, predicate));
		}					
		for (EOperation op: eClass.getEOperations()) {
			EGenericType opType = op.getEGenericType();
			if (opType != null) {
				collector.addAll(collectGenericTypeDependencies(opType, true, predicate));
			}
			for (EGenericType ge: op.getEGenericExceptions()) {
				collector.addAll(collectGenericTypeDependencies(ge, true, predicate));
			}
			for (EParameter ep: op.getEParameters()) {
				collector.addAll(collectGenericTypeDependencies(ep.getEGenericType(), true, predicate));
			}
		}					
		return collector;
	}
	
	/**
	 * Collects generic type dependencies, i.e. classifiers used in generic type definition.
	 * @param type Generic type
	 * @param includeEClassifier if true, include EClassifier of the type itself. Otherwise, only EClassifiers of referenced generic types - bounds, type arguments, and type parameter.
	 * @return A set of {@link EClassifier}'s.
	 */
	public static Collection<EClassifier> collectGenericTypeDependencies(EGenericType type, boolean includeEClassifier, Predicate<EGenericType> predicate) {
		Set<EClassifier> ret = new HashSet<>();
		if (type != null && predicate.test(type)) {
			EClassifier eClassifier = type.getEClassifier();
			if (includeEClassifier && eClassifier != null) {
				ret.add(eClassifier);
			}
			
			// Lower bound
			ret.addAll(collectGenericTypeDependencies(type.getELowerBound(), true, predicate));
			
			// Upper bound
			ret.addAll(collectGenericTypeDependencies(type.getEUpperBound(), true, predicate));
			
			// Type arguments
			for (EGenericType ta: type.getETypeArguments()) {
				ret.addAll(collectGenericTypeDependencies(ta, true, predicate));
			}
			
			// Type parameter
			ETypeParameter tp = type.getETypeParameter();
			if (tp != null) {
				for (EGenericType b: tp.getEBounds()) {
					ret.addAll(collectGenericTypeDependencies(b, true, predicate));					
				}
			}
		}
		return ret;
	}
	
	/**
	 * Resolves reference relative to the resource. <code>bundle://</code> protocol can be used to reference resources in OSGi bundles similarly to <code>plugin://</code> protocol.  
	 * @param resource Used to resolve relative references if not null.
	 * @param reference Reference.
	 * @return
	 * @throws Exception
	 */
	public static URL resolveReference(Resource resource, String reference) throws Exception {
		
		if (resource != null) {
			URI resUri = resource.getURI();
			URI refUri = URI.createURI(reference);
			URI resolvedUri = refUri.resolve(resUri);
			return new URL(resolvedUri.toString());			
		}
		
		return new URL(reference);
	}
	
	/**
	 * Loads element documentation from a documentation resource specified in documentation-reference nasdanika (urn:org.nasdanika) annotation resolved relative to the 
	 * model element resource. 
	 * @param modelElement
	 * @return
	 */
	public static String getDocumentation(EModelElement modelElement) {
		EAnnotation nasdanikaAnnotation = getNasdanikaAnnotation(modelElement);
		if (nasdanikaAnnotation != null) {
			String docRef = nasdanikaAnnotation.getDetails().get("documentation-reference");
			if (!Util.isBlank(docRef)) {
				try {
					return DefaultConverter.INSTANCE.toString(resolveReference(modelElement.eResource(), docRef).openStream());
				} catch (Exception e) {
					e.printStackTrace();
					return "Error loading documentation: " + e;
				}
			}
		}
		
		return null;
	}
	
	public static Diagnostic wrap(org.eclipse.emf.common.util.Diagnostic diagnostic) {
		Status status;
		switch (diagnostic.getSeverity()) {
		case org.eclipse.emf.common.util.Diagnostic.CANCEL:
			status = Status.CANCEL;
			break;
		case org.eclipse.emf.common.util.Diagnostic.INFO:
			status = Status.INFO;
			break;
		case org.eclipse.emf.common.util.Diagnostic.OK:
			status = Status.SUCCESS;
			break;
		case org.eclipse.emf.common.util.Diagnostic.WARNING:
			status = Status.WARNING;
			break;
		case org.eclipse.emf.common.util.Diagnostic.ERROR:
		default:
			status = Status.ERROR;
			break;
			
		}
		BasicDiagnostic ret = new BasicDiagnostic(status, diagnostic.getMessage(), diagnostic);
		for (org.eclipse.emf.common.util.Diagnostic child: diagnostic.getChildren()) {
			ret.add(wrap(child));
		}
		return ret;
	}

	public static <K, V extends EObject> Map<K, List<V>> groupBy(Collection<V> elements, EStructuralFeature... keyFeatures) {
		@SuppressWarnings("unchecked")
		Function<EObject,K> classifier = e -> {
			if (e == null) {
				return null;
			}
			if (keyFeatures.length == 1) {
				return (K) e.eGet(keyFeatures[0]);
			}
			List<Object> key = new ArrayList<>();
			for (EStructuralFeature keyFeature: keyFeatures) {
				key.add(e.eGet(keyFeature));
			}
			return (K) key;
		};
		return Util.groupBy(elements, classifier);
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
		return ModelElementImpl.getReferrers(target, eReference);
	}
	
	public static void dumpDiagnostic(org.eclipse.emf.common.util.Diagnostic d, int indent, PrintStream out) {
		for (int i=0; i < indent; ++i) {
			out.print("    ");
		}
		out.println(toString(d));
	    if (d.getChildren() != null) {
	    	d.getChildren().forEach(c -> dumpDiagnostic(c, indent + 1, out));
	    }		
	}
	
	static String toString(org.eclipse.emf.common.util.Diagnostic d) {
		StringBuilder result = new StringBuilder();
		switch (d.getSeverity()) {
		case org.eclipse.emf.common.util.Diagnostic.OK:
			result.append("OK");
			break;
		case org.eclipse.emf.common.util.Diagnostic.INFO:
			result.append("INFO");
			break;
		case org.eclipse.emf.common.util.Diagnostic.WARNING:
			result.append("WARNING");
			break;
		case org.eclipse.emf.common.util.Diagnostic.ERROR:
			result.append("ERROR");
			break;
		case org.eclipse.emf.common.util.Diagnostic.CANCEL:
			result.append("CANCEL");
			break;
		default:
			result.append(Integer.toHexString(d.getSeverity()));
			break;
		}

		result.append(" source=");
		result.append(d.getSource());

		result.append(" code=");
		result.append(d.getCode());

		result.append(' ');
		result.append(d.getMessage());

		if (d.getData() != null) {
			result.append(" data=");
			result.append(d.getData());
		}

		return result.toString();
	}		

	/**
	 * Resolves all proxies, clears feature caches and then diagnoses the resource set.
	 * @param resourceSet
	 * @param context
	 * @return
	 */
	public static org.eclipse.emf.common.util.Diagnostic resolveClearCacheAndDiagnose(ResourceSet resourceSet, Context context) {
		EcoreUtil.resolveAll(resourceSet);
		resourceSet.getAllContents().forEachRemaining(notifier -> notifier.eNotify(FeatureCacheAdapter.CLEAR_CACHE));

		Diagnostician diagnostician = new Diagnostician();
		org.eclipse.emf.common.util.BasicDiagnostic ret = new org.eclipse.emf.common.util.BasicDiagnostic();
		Map<Class<Context>, Context> diagnosticContext = Collections.singletonMap(Context.class, context);
		for (Resource resource: resourceSet.getResources()) {
			org.eclipse.emf.common.util.BasicDiagnostic resourceDiagnostic = new org.eclipse.emf.common.util.BasicDiagnostic(resource.getURI().toString(), 0, "Resource diagnostic", new Object[] {resource});
			for (EObject e: resource.getContents()) {
				org.eclipse.emf.common.util.Diagnostic diagnostic = diagnostician.validate(e, diagnosticContext);
				resourceDiagnostic.add(diagnostic);
			}
			ret.add(resourceDiagnostic);
		}
		return ret;
	}
	
	/**
	 * Resolves URI against the base if base is not null.
	 * @param uri
	 * @param base
	 * @return
	 */
	public static URI resolve(URI uri, URI base) {
		return base == null ? uri : uri.resolve(base);
	}

}
