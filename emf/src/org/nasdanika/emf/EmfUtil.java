package org.nasdanika.emf;

import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Util;

public class EmfUtil {

	private static final String NASDANIKA_ANNOTATION_SOURCE = "urn:org.nasdanika";

	private EmfUtil() {
		// Singleton
	}
		
	public static EAnnotation getNasdanikaAnnotation(EModelElement modelElement) {
		return modelElement == null ? null : modelElement.getEAnnotation(NASDANIKA_ANNOTATION_SOURCE);
	}
	
	public static String getCategory(EModelElement modelElement) {
		if (modelElement == null) {
			return null;
		}
		EAnnotation na = getNasdanikaAnnotation(modelElement);
		return na == null ? null : na.getDetails().get("category");		
	}

	/**
	 * Collects type dependencies for a given class - attribute types, eoperation return and exception types, eparameter types.
	 * Does not collect reference types and supertypes, as they are reported differently, but collects their generic parameter types.
	 * @param eClass
	 * @return
	 */
	public static Collection<EClassifier> collectTypeDependencies(EClass eClass) {
		Collection<EClassifier> collector = new HashSet<>();
		for (EGenericType st: eClass.getEGenericSuperTypes()) {
			collector.addAll(collectGenericTypeDependencies(st, false));
		}
		for (EReference ref: eClass.getEReferences()) {
			collector.addAll(collectGenericTypeDependencies(ref.getEGenericType(), false));
		}		
		
		for (EAttribute attr: eClass.getEAttributes()) {
			collector.addAll(collectGenericTypeDependencies(attr.getEGenericType(), true));
		}					
		for (EOperation op: eClass.getEOperations()) {
			EGenericType opType = op.getEGenericType();
			if (opType != null) {
				collector.addAll(collectGenericTypeDependencies(opType, true));
			}
			for (EGenericType ge: op.getEGenericExceptions()) {
				collector.addAll(collectGenericTypeDependencies(ge, true));
			}
			for (EParameter ep: op.getEParameters()) {
				collector.addAll(collectGenericTypeDependencies(ep.getEGenericType(), true));
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
	public static Collection<EClassifier> collectGenericTypeDependencies(EGenericType type, boolean includeEClassifier) {
		Set<EClassifier> ret = new HashSet<>();
		if (type != null) {
			EClassifier eClassifier = type.getEClassifier();
			if (includeEClassifier && eClassifier != null) {
				ret.add(eClassifier);
			}
			
			// Lower bound
			ret.addAll(collectGenericTypeDependencies(type.getELowerBound(), true));
			
			// Upper bound
			ret.addAll(collectGenericTypeDependencies(type.getEUpperBound(), true));
			
			// Type arguments
			for (EGenericType ta: type.getETypeArguments()) {
				ret.addAll(collectGenericTypeDependencies(ta, true));
			}
			
			// Type parameter
			ETypeParameter tp = type.getETypeParameter();
			if (tp != null) {
				for (EGenericType b: tp.getEBounds()) {
					ret.addAll(collectGenericTypeDependencies(b, true));					
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

}
