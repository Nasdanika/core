package org.nasdanika.emf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.DependencyTracer;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Generates PlantUML text from Ecore models.
 * 
 * This code is based on net.sourceforge.plantuml.text.AbstractDiagramTextProvider and 
 * net.sourceforge.plantuml.ecore.AbstractEcoreDiagramTextProvider from 
 * https://github.com/hallvard/plantuml
 * 
 * @author Pavel Vlasov
 *
 */
public class PlantUmlTextGenerator implements DiagramTextGenerator {
	
	private static final String DIAGRAM_STYLE_KEY = "diagram-style";
	private static final String RELATED_BACKGROUND = "#DDDDDD";
	
	// Default background: #FEFECE

	private Appendable collector;
	private Function<EClassifier, String> eClassifierLinkResolver;
	private Function<EModelElement, String> eModelElementFirstDocSentenceProvider;
	private Predicate<EModelElement> elementPredicate;
	private BiFunction<ENamedElement, String, String> labelProvider;

	public PlantUmlTextGenerator(
			Appendable collector, 
			Predicate<EModelElement> elementPredicate,			
			Function<EClassifier, String> eClassifierLinkResolver, 
			Function<EModelElement, String> eModelElementFirstDocSentenceProvider,
			BiFunction<ENamedElement, String, String> labelProvider) {
		this.collector = collector;
		this.elementPredicate = elementPredicate;
		this.eClassifierLinkResolver = eClassifierLinkResolver;
		this.eModelElementFirstDocSentenceProvider = eModelElementFirstDocSentenceProvider;
		this.labelProvider = labelProvider;
	}
	
	/**
	 * Filters the collection retaining only model elements which shall be documented.
	 * @param <T>
	 * @param elements
	 * @return
	 */
	protected <T extends EModelElement> List<T> retainDocumentable(Collection<T> elements) {
		return elements.stream().filter(elementPredicate).toList();
	}	
	
	private static final String EXTENDS_RELATION = "<|--"; 
	private static final String IMPLEMENTS_RELATION = "<|..";
	private static final String DEPENDENCY_RELATION = "..>";	
	private static final String CLASS_COMPARTMENT_SEPARATOR_LINE = "\t__\n";

	protected void appendClassStart(String modifiers, String classType, String name, String link, String style) throws IOException {
		if (modifiers != null) {
			collector.append(modifiers);
			collector.append(" ");
		}
		
		collector
			.append(classType)
			.append(" ")
			.append(name)
			.append(Util.isBlank(link) ? "" : " [[" + link + "]]")
			.append(Util.isBlank(style) ? "" : " " + style)
			.append(" {\n");
	}
	
	protected String getLabel(ENamedElement namedElement) {
		return labelProvider.apply(namedElement, namedElement.getName());
	}
	
	protected void appendClassEnd() throws IOException {
		collector.append("}\n");
	}

	private static String getSimpleName(String name) {
		return name==null ? null : name.substring(name.lastIndexOf('.') + 1);
	}

	protected void appendMember(String modifiers, String visibility, String type, String name, Iterable<String> parameters) throws IOException {
		collector.append("\t");
		if (visibility != null) {
			collector.append(visibility);
		}
		if (modifiers != null) {
			collector.append(modifiers);
			collector.append(" ");
		}
		if (type != null) {
			collector.append(type);
			collector.append(" ");
		}
		if (name != null) {
			collector.append(name);
		}
		if (parameters != null) {
			collector.append("(");
			Iterator<String> it = parameters.iterator();
			while (it.hasNext()) {
				collector.append(it.next());
				if (it.hasNext()) {
					collector.append(", ");
				}
			}
			collector.append(")");
		}
		collector.append("\n");
	}
	
	protected void appendAttribute(String modifiers, String visibility, String type, String name) throws IOException {
		appendMember(modifiers, visibility, type, name, null);
	}

	protected void appendOperation(String modifiers, String visibility, String type, String name, Iterable<String> parameters) throws IOException {
		appendMember(modifiers, visibility, type, name, parameters);
	}
		
	public void appendGeneralization(EClass subClass, EGenericType superType) throws IOException {
		if (elementPredicate.test(superType.getEClassifier())) {
			EClass superClass = (EClass) superType.getEClassifier();
			collector
				.append(qualifiedName(superClass))
				.append(" ")
				.append(superClass.isInterface() && !subClass.isInterface() ? IMPLEMENTS_RELATION : EXTENDS_RELATION)
				.append(" ")
				.append(qualifiedName(subClass));
			
			if (!superType.getETypeArguments().isEmpty()) {
				collector.append(" : <");
				Iterator<EGenericType> it = superType.getETypeArguments().iterator();
				while (it.hasNext()) {
					collector.append(genericName(it.next()));
					if (it.hasNext()) {
						collector.append(",");
					}
				}
				collector.append(">");
			}		
			
			collector.append("\n");
		}
	}
		
	// --- ECore ---
	
	private DependencyTracer<EClassifier> OUT_DEPENDENCY_TRACER = new DependencyTracer<EClassifier>() {

		@Override
		protected Iterable<EClassifier> getDependencies(EClassifier obj) {
			Collection<EClassifier> ret = new HashSet<>();
			if (obj instanceof EClass) {
				EClass eClass = (EClass) obj;
				ret.addAll(eClass.getESuperTypes());
				for (EReference ref: retainDocumentable(eClass.getEReferences())) {					
					EClass eReferenceType = ref.getEReferenceType();
					if (elementPredicate.test(eReferenceType)) {
						EClass associationTarget = NcoreUtil.getAssociationTarget(ref);
						if (associationTarget == null) {
							ret.add(eReferenceType);
						} else if (elementPredicate.test(associationTarget)) {
							ret.add(eReferenceType);
							ret.add(associationTarget);							
						}						
					}					
				}
				
				ret.addAll(EmfUtil.collectTypeDependencies(eClass));				
			}
			return retainDocumentable(ret);
		}
		
	};
		
	private DependencyTracer<EClassifier> inDependencyTracer = new DependencyTracer<EClassifier>() {

		@Override
		protected Iterable<EClassifier> getDependencies(EClassifier obj) {
			Collection<EClassifier> ret = new HashSet<>();
			if (obj instanceof EClass) {
				ret.addAll(getSubTypes((EClass) obj));
				ret.addAll(getReferrers((EClass) obj));
			}
			ret.addAll(getUses(obj));
			return retainDocumentable(ret);
		}
		
	};
		
	private DependencyTracer<EClassifier> bothDependencyTracer = new DependencyTracer<EClassifier>() {
	
		@Override
		protected Iterable<EClassifier> getDependencies(EClassifier obj) {
			Collection<EClassifier> ret = new HashSet<>();
			if (obj instanceof EClass) {
				// In
				EClass eClass = (EClass) obj;
				ret.addAll(getSubTypes(eClass));
				ret.addAll(getReferrers(eClass));
				
				// Out
				ret.addAll(eClass.getESuperTypes());
				for (EReference ref: retainDocumentable(eClass.getEReferences())) {
					EClass eReferenceType = ref.getEReferenceType();
					if (elementPredicate.test(eReferenceType)) {
						EClass associationTarget = NcoreUtil.getAssociationTarget(ref);
						if (associationTarget == null) {
							ret.add(eReferenceType);
						} else if (elementPredicate.test(associationTarget)) {
							ret.add(eReferenceType);
							ret.add(associationTarget);							
						}						
					}					
				}
				
				ret.addAll(EmfUtil.collectTypeDependencies(eClass));				
			}

			// In
			ret.addAll(getUses(obj));
			
			return retainDocumentable(ret);
		}
		
	};
		
	/**
	 * Appends core classifiers, their related classifiers, and relationships
	 * @param coreClassifiers
	 * @throws IOException 
	 */
	@Override
	public void appendWithRelationships(
			Collection<? extends EClassifier> coreClassifiers,
			RelationshipDirection direction,
			int depth) {
		
		try {
			Set<EClassifier> coreSet = new HashSet<>(retainDocumentable(coreClassifiers));				
			Set<EClassifier> relatedSet = new HashSet<>();
			
			switch (direction) {
			case both:
				relatedSet = bothDependencyTracer.trace(coreSet, depth);
				break;
			case in:
				relatedSet = inDependencyTracer.trace(coreSet, depth);
				break;
			case out:
				relatedSet = OUT_DEPENDENCY_TRACER.trace(coreSet, depth);
				break;
			default:
				break;		
			}
			
			Set<EClassifier> allClassifiers = new HashSet<>(coreSet);
			
			for (EClassifier rc: relatedSet) {
				if (!coreSet.contains(rc) && getEClassifierLink(rc) != null) {
					allClassifiers.add(rc);				
				}
			}
			
			for (EClassifier cc: coreSet) {
				appendEClassifier(cc, allClassifiers);
			}
			
			for (EClassifier rc: relatedSet) {
				if (!coreSet.contains(rc) && getEClassifierLink(rc) != null) {
					String style = NcoreUtil.getNasdanikaAnnotationDetail(rc, DIAGRAM_STYLE_KEY);
					if (style == null) {
						style = RELATED_BACKGROUND;
					} else {
						style = Context.singleton("background", RELATED_BACKGROUND).interpolateToString(style);
					}
					
					appendEClassifier(rc, style, allClassifiers);
				}
			}		
			
			for (EClassifier c: allClassifiers) {
				if (c instanceof EClass) {
					for (EGenericType gsc: ((EClass) c).getEGenericSuperTypes()) {
						if (allClassifiers.contains(gsc.getEClassifier())) {
							appendGeneralization((EClass) c, gsc);
						}
					}
				}
			}
			
			Set<EReference> processedOpposites = new HashSet<>();
			for (EClassifier c: allClassifiers) {
				if (c instanceof EClass) {
					for (EReference ref: ((EClass) c).getEReferences()) {
						EClass eReferenceType = ref.getEReferenceType();
//						EClass associationTarget = getAssociationTarget(ref);
//						if (associationTarget == null) { // Regular relationship
							if (!processedOpposites.contains(ref) && allClassifiers.contains(eReferenceType)) {
								appendEReference(ref);
								EReference opposite = NcoreUtil.getOpposite(ref);
								if (opposite!=null) {
									processedOpposites.add(opposite);
								}
							}
//						} else { // Association class, no handling of opposites right now
//							if (allClassifiers.contains(eReferenceType) && allClassifiers.contains(associationTarget)) {
//								appendAssociation(ref);								
//							}
//						}
					}
				}
			}	
			
			// Type dependencies
			for (EClassifier c: allClassifiers) {
				for (EClass src: getUses(c)) {
					if (allClassifiers.contains(src)) {
						appendTypeDependency(src, c);
					}				
				}
			}
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}
	}
	
	protected static String getMultiplicity(EStructuralFeature feature) {
		if (feature.isMany()) {
			int lowerBound = feature.getLowerBound();
			int upperBound = feature.getUpperBound();
			if (lowerBound == upperBound) {
				return String.valueOf(lowerBound);
			}
			if (lowerBound==0 && upperBound==-1) {
				return "*";
			}
			return String.valueOf(lowerBound)+".."+(upperBound == -1 ? "*" : upperBound);
		}
		
		return "";
	}	
	
	/**
	 * @param ref
	 * @return Empty string if there are no EKeys, a comma separated list of EKeys in parenthesis otherwise.
	 */
	protected String eKeys(EReference ref) {
		StringBuilder ret = new StringBuilder();
		EList<EAttribute> eKeys = ref.getEKeys();
		if (!eKeys.isEmpty()) {
			ret.append("(");		
			ret.append(String.join(",", eKeys.stream().map(this::getLabel).toList()));
			ret.append(")");
		}
		
		return ret.toString();
	}
	
	protected void appendEReference(EReference ref) throws IOException {
		if (elementPredicate.test(ref)) {
			collector.append(qualifiedName(ref.getEContainingClass()));
			collector.append(" ");
			EReference opposite = NcoreUtil.getOpposite(ref);
			if (ref.isContainment()) {
				collector.append("*");
			} else if (opposite!=null) {
				collector.append("\"");
				collector.append(getLabel(opposite));
				collector.append(eKeys(opposite));
				String multiplicity = getMultiplicity(opposite);
				if (!multiplicity.isEmpty()) {
					collector.append("["+multiplicity+"]");
				}
				collector.append("\" ");
			}
			
			String diagramStyle = NcoreUtil.getNasdanikaAnnotationDetail(ref, DIAGRAM_STYLE_KEY);
			String relationLine = Util.isBlank(diagramStyle) ? "--" : "-[" + diagramStyle + "]-";
			String associationRelation = relationLine + ">";
			
			if (opposite == null) {
				collector.append(associationRelation);
			} else {
				collector.append(relationLine);
				if (opposite.isContainment()) {
					collector.append("*");
				}
			}		
			collector.append(" ");
			
			String multiplicity = getMultiplicity(ref);
			if (opposite == null) {
				if (!multiplicity.isEmpty()) {
					collector
						.append("\"")
						.append(multiplicity)
						.append("\" ");						
				}
			} else {
				collector.append("\"");
				collector.append(getLabel(ref));			
				collector.append(eKeys(ref));
				if (!multiplicity.isEmpty()) {
					collector.append("["+multiplicity+"]");
				}
				collector.append("\" ");			
			}		
			
			collector.append(qualifiedName(ref.getEReferenceType()));		
			
			if (opposite == null) {
				collector
					.append(" : ")
					.append(getLabel(ref));
	
				collector.append(eKeys(ref));			
				collector.append(genericTypeArguments(ref.getEGenericType()));
			}
			
			collector.append(System.lineSeparator());
		}
	}
	
//	protected void appendAssociation(EReference ref) throws IOException {
//		if (elementPredicate.test(ref)) {
//			String sourceQualifiedName = qualifiedName(ref.getEContainingClass());
//			collector.append(sourceQualifiedName);
//			collector.append(" ");
//			if (ref.isContainment()) {
//				collector.append("*");
//			}
//			
//			String diagramStyle = NcoreUtil.getNasdanikaAnnotationDetail(ref, DIAGRAM_STYLE_KEY);
//			String relationLine = Util.isBlank(diagramStyle) ? "--" : "-[" + diagramStyle + "]-";
//			String associationRelation = relationLine + ">";
//			
//			collector.append(associationRelation);
//			collector.append(" ");
//			
//			String multiplicity = getMultiplicity(ref);
//			if (!multiplicity.isEmpty()) {
//				collector
//					.append("\"")
//					.append(multiplicity)
//					.append("\" ");						
//			}
//			
//			String associationTargetQualifiedName = qualifiedName(getAssociationTarget(ref));
//			collector.append(associationTargetQualifiedName);		
//			
//			collector
//				.append(" : ")
//				.append(getLabel(ref));
//
//			collector.append(eKeys(ref));			
//			collector.append(genericTypeArguments(ref.getEGenericType()));
//			
//			collector.append(System.lineSeparator());
//			
//			collector
//				.append("(")
//				.append(sourceQualifiedName)
//				.append(", ")
//				.append(associationTargetQualifiedName)
//				.append(") .. ")
//				.append(qualifiedName(ref.getEReferenceType()))
//				.append(System.lineSeparator());				
//		}
//	}
		
	protected void appendTypeDependency(EClass source, EClassifier target) throws IOException {
		if (elementPredicate.test(source) && elementPredicate.test(target)) {
			collector.append(qualifiedName(source));
			collector.append(" ");
			collector.append(DEPENDENCY_RELATION);
			collector.append(" ");
			collector.append(qualifiedName(target));		
			collector.append(System.lineSeparator());
		}
	}
	
	/**
	 * Finds all subtypes in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getSubTypes(EClass eClass) {
		TreeIterator<?> acit;
		Resource eResource = eClass.eResource();
		if (eResource == null) {
			EPackage ePackage = eClass.getEPackage();
			if (ePackage == null) {
				return Collections.emptySet();
			}
			acit = ePackage.eAllContents();
		} else {
			ResourceSet resourceSet = eResource.getResourceSet();
			acit = resourceSet == null ? eResource.getAllContents() : resourceSet.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EClass && ((EClass) obj).getESuperTypes().contains(eClass)) {
				ret.add((EClass) obj);
			}
		});
		return retainDocumentable(ret);
	}
	
	protected Collection<EClass> getReferrers(EClass eClass) {
		return getReferrers(eClass, true);
	}
	
	/**
	 * Finds all referrers in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getReferrers(EClass eClass, boolean includeAssociations) {
		TreeIterator<?> acit;
		Resource eResource = eClass.eResource();
		if (eResource == null) {
			EPackage ePackage = eClass.getEPackage();
			if (ePackage == null) {
				return Collections.emptySet();
			}
			acit = ePackage.eAllContents();
		} else {
			ResourceSet resourceSet = eResource.getResourceSet();
			acit = resourceSet == null ? eResource.getAllContents() : resourceSet.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EReference && ((EReference) obj).getEReferenceType() == eClass) {
				// Finding association sources. TODO - handle a case when association target does not pass the predicate test, but the association class does.
				EClass referrer = ((EReference) obj).getEContainingClass();
				if (includeAssociations) {
					for (EClass superReferrer: getReferrers(referrer, false)) {
						for (EReference superReference: superReferrer.getEReferences()) {
							if (superReference.getEReferenceType() == referrer) {
								EClass associationTarget = NcoreUtil.getAssociationTarget(superReference);
								if (associationTarget == eClass) {
									ret.add(superReferrer);
								}
							}
						}
					}
				}
				ret.add(referrer);
			}
		});
		return retainDocumentable(ret);
	}
	
	/**
	 * Finds all type uses in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getUses(EClassifier eClassifier) {
		TreeIterator<?> acit;
		Resource eResource = eClassifier.eResource();
		if (eResource == null) {
			EPackage ePackage = eClassifier.getEPackage();
			if (ePackage == null) {
				return Collections.emptySet();
			}
			acit = ePackage.eAllContents();
		} else {
			ResourceSet resourceSet = eResource.getResourceSet();
			acit = resourceSet == null ? eResource.getAllContents() : resourceSet.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EClass && EmfUtil.collectTypeDependencies((EClass) obj).contains(eClassifier)) {
				ret.add((EClass) obj);
			}
		});
		return retainDocumentable(ret);
	}
		
	public void appendEClassifier(EClassifier eClassifier, Set<EClassifier> allClassifiers) throws IOException {
		String style = Context.EMPTY_CONTEXT.interpolateToString(NcoreUtil.getNasdanikaAnnotationDetail(eClassifier, DIAGRAM_STYLE_KEY));
		appendEClassifier(eClassifier, style, allClassifiers);
	}
	
	public void appendEClassifier(EClassifier eClassifier, String style, Set<EClassifier> allClassifiers) throws IOException {
		if (elementPredicate.test(eClassifier)) {
			if (eClassifier instanceof EClass) {
				appendEClass((EClass) eClassifier, style, allClassifiers);
			} else if (eClassifier instanceof EEnum) {
				appendEEnum((EEnum) eClassifier, style, allClassifiers);			
			} else if (eClassifier instanceof EDataType) {
				appendEDataType((EDataType) eClassifier, style, allClassifiers);
			}
		}
	}
	
	protected String qualifiedName(EClassifier eClassifier) {
		Class<?> ic = eClassifier.getInstanceClass();
		if (ic != null) {
			return ic.getName();
		}
		EPackage ePackage = eClassifier.getEPackage();
		if (ePackage == null) {
			return getLabel(eClassifier);				
		}
		return "\""+getLabel(ePackage)+" ("+ePackage.getNsURI()+")."+getLabel(eClassifier)+"\"";
	}	
	
	protected String typeParameters(EClassifier eClassifier) {
		if (eClassifier.getETypeParameters().isEmpty()) {
			return "";
		}
		StringBuilder typeParameters = new StringBuilder();
		for (ETypeParameter typeParameter: eClassifier.getETypeParameters()) {
			if (typeParameters.length() > 0) {
				typeParameters.append(",");
			}
			typeParameters.append(genericName(typeParameter));
		}		
		
		return "<" + typeParameters +">";
	}	
	
	protected String genericName(ETypeParameter typeParameter) {
		StringBuilder ret = new StringBuilder(getLabel(typeParameter));
		for (EGenericType bound : typeParameter.getEBounds()) {
			if (bound.getEUpperBound() != null) {
				ret.append(" extends ").append(genericName(bound.getEUpperBound()));
			}
			if (bound.getELowerBound() != null) {
				ret.append(" super ").append(genericName(bound.getELowerBound()));
			}
		}
		
		return ret.toString();
	}
	
	protected String genericName(EGenericType eGenericType) {
		if (eGenericType == null) {
			return "void";
		}
		StringBuilder ret = new StringBuilder();		
		if (eGenericType.getETypeParameter() != null) {
			ret.append(getLabel(eGenericType.getETypeParameter()));
		} else if (eGenericType.getEClassifier() != null) {
			ret.append(getLabel(eGenericType.getEClassifier()));			
		}
		ret.append(genericTypeArguments(eGenericType));
		return ret.toString();
	}

	protected String genericTypeArguments(EGenericType eGenericType) {
		StringBuilder ret = new StringBuilder();
		Iterator<EGenericType> it = eGenericType.getETypeArguments().iterator();
		if (it.hasNext()) {
			ret.append("<");
			while (it.hasNext()) {
				ret.append(genericName(it.next()));
				if (it.hasNext()) {
					ret.append(",");
				}
			}
			ret.append(">");
		}
		return ret.toString();
	}

	public void appendEClass(EClass eClass, Set<EClassifier> allClassifiers) throws IOException {
		appendEClass(eClass, null, allClassifiers);
	}
	
	protected Comparator<ENamedElement> eNamedElementComparator = (a,b) -> labelProvider.apply(a, a.getName()).compareTo(labelProvider.apply(b, b.getName()));
	
	public void appendEClass(EClass eClass, String style, Set<EClassifier> allClassifiers) throws IOException {
		String modifiers = eClass.isAbstract() && !eClass.isInterface() ? "abstract" : null;
		appendClassStart(modifiers, eClass.isInterface() ? "interface" : "class", qualifiedName(eClass) + typeParameters(eClass), getEClassifierLink(eClass), style);
		if (isAppendAttributes(eClass)) {
			for (EAttribute attribute: eClass.getEAttributes().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList()) {			
				EGenericType eGenericType = attribute.getEGenericType();
				if (eGenericType != null) {
					appendAttribute(null, null, genericName(eGenericType) + (attribute.isMany() ? "[]" : ""), getLabel(attribute));
				}						
			}
		}
		collector.append(CLASS_COMPARTMENT_SEPARATOR_LINE);
		for (EReference reference: eClass.getEReferences().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList()) {
			EGenericType eGenericType = reference.getEGenericType();			
			if (eGenericType == null || !allClassifiers.contains(reference.getEReferenceType())) {
				appendAttribute(null, null, genericName(eGenericType) + (reference.isMany() ? "[]" : ""), getLabel(reference));
			}						
		}
		
		collector.append(CLASS_COMPARTMENT_SEPARATOR_LINE);
		if (isAppendOperations(eClass)) {
			for (EOperation op : eClass.getEOperations().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList()) {
				Collection<String> parameters = new ArrayList<String>();
				for (EParameter parameter : op.getEParameters()) {
					String paramString = getLabel(parameter);
					if (parameter.getEGenericType() != null) {
						paramString = genericName(parameter.getEGenericType()) + " " + paramString;
					}
					parameters.add(paramString);
				}
				appendOperation(null, null, genericName(op.getEGenericType()), getLabel(op), parameters);
			}
		}
		appendClassEnd();
	}

	/**
	 * Constructs {@link EClassifier} with the first documentation sentence as a tooltip. 
	 * @param eClassifier
	 * @return
	 */
	protected String getEClassifierLink(EClassifier eClassifier) {
		String link = null;
		if (eClassifier != null && eClassifierLinkResolver != null) {
			link = eClassifierLinkResolver.apply(eClassifier);
			if (link != null && eModelElementFirstDocSentenceProvider != null) {
				String firstDocSentence = eModelElementFirstDocSentenceProvider.apply(eClassifier);
				if (!org.nasdanika.common.Util.isBlank(firstDocSentence)) {
					link += "{" + firstDocSentence + "}";
				}
			}
		}
		return link;
	}
	
	protected boolean isAppendAttributes(EClass eClass) {
		return true;
	}
	
	protected boolean isAppendOperations(EClass eClass) {
		return true;
	}

	public void appendEDataType(EDataType dataType, Set<EClassifier> allClassifiers) throws IOException {
		appendEDataType(dataType, null, allClassifiers);
	}	
	
	public void appendEDataType(EDataType dataType, String background, Set<EClassifier> allClassifiers) throws IOException {
		appendClassStart(null, "class", qualifiedName(dataType)+" << (D,orchid) >>", getEClassifierLink(dataType), background);
		if (dataType.getInstanceClassName() != null) {
			appendAttribute(null, null, null, dataType.getInstanceClassName());
		}
		appendClassEnd();
	}

	public void appendEEnum(EEnum eEnum, Set<EClassifier> allClassifiers) throws IOException {
		appendEEnum(eEnum, null, allClassifiers);
	}
	
	public void appendEEnum(EEnum eEnum, String background, Set<EClassifier> allClassifiers) throws IOException {
		appendClassStart(null, "enum", qualifiedName(eEnum), getEClassifierLink(eEnum), background);
		for (EEnumLiteral literal : eEnum.getELiterals()) {
			appendAttribute(String.valueOf(literal.getValue()), null, getLabel(literal), literal.getName().equals(literal.getLiteral()) ? "" : literal.getLiteral());
		}
		appendClassEnd();
	}

	protected String getTypeName(EClassifier type) {
		String typeName = null;
		if (type != null) {
			if (type instanceof EDataType) {
				typeName = type.getInstanceClassName();
			}
			if (typeName == null) {
				typeName = getLabel(type);
			}
		}
		return getSimpleName(typeName);
	}
	
	public void appendStartUml() throws IOException {
		collector.append("@startuml").append(System.lineSeparator());
	}
	
	public void appendEndUml() throws IOException {
		collector.append("@enduml").append(System.lineSeparator());
	}
	
}
