package org.nasdanika.emf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

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
import org.nasdanika.common.DependencyTracer;

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
public class PlantUmlTextGenerator {
	
	public enum RelationshipDirection { in, out, both } 
	
	// TODO - support of packages and fully qualified names -> get rid of Logical name?
	
	private Appendable collector;
	private Function<EClassifier, String> eClassifierLinkResolver;
	private Function<EModelElement, String> eModelElementFirstDocSentenceProvider;

	public PlantUmlTextGenerator(
			Appendable collector, 
			Function<EClassifier, String> eClassifierLinkResolver, 
			Function<EModelElement, String> eModelElementFirstDocSentenceProvider) {
		this.collector = collector;
		this.eClassifierLinkResolver = eClassifierLinkResolver;
		this.eModelElementFirstDocSentenceProvider = eModelElementFirstDocSentenceProvider;
	}
	
	private static final String RELATION_LINE = "--";
	private static final String ASSOCIATION_RELATION = RELATION_LINE + ">";
	private static final String EXTENDS_RELATION = "<|" + RELATION_LINE, IMPLEMENTS_RELATION = "<|..";

	protected void appendClassStart(String modifiers, String classType, String name, String link, String background) throws IOException {
		if (modifiers != null) {
			collector.append(modifiers);
			collector.append(" ");
		}
		collector
			.append(classType)
			.append(" ")
			.append(name)
			.append(link == null ? "" : " [[" + link + "]]")
			.append(background == null ? "" : " " + background)
			.append(" {\n");
	}
	
	protected String getLocalizedName(ENamedElement namedElement) {
		return EObjectAdaptable.getResourceContext(namedElement).getString("label", namedElement.getName());
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
		
	// --- ECore ---
	
	
	private static DependencyTracer<EClassifier> OUT_DEPENDENCY_TRACER = new DependencyTracer<EClassifier>() {

		@Override
		protected Iterable<EClassifier> getDependencies(EClassifier obj) {
			Collection<EClassifier> ret = new HashSet<>();
			if (obj instanceof EClass) {
				ret.addAll(((EClass) obj).getESuperTypes());
				for (EReference ref: ((EClass) obj).getEReferences()) {
					ret.add(ref.getEReferenceType());
				}
			}
			return ret;
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
			return ret;
		}
		
	};
		
	private DependencyTracer<EClassifier> bothDependencyTracer = new DependencyTracer<EClassifier>() {
	
		@Override
		protected Iterable<EClassifier> getDependencies(EClassifier obj) {
			Collection<EClassifier> ret = new HashSet<>();
			if (obj instanceof EClass) {
				// In
				ret.addAll(getSubTypes((EClass) obj));
				ret.addAll(getReferrers((EClass) obj));
				// Out
				ret.addAll(((EClass) obj).getESuperTypes());
				for (EReference ref: ((EClass) obj).getEReferences()) {
					ret.add(ref.getEReferenceType());
				}
			}
			return ret;
		}
		
	};
	
	
	/**
	 * Appends core classifiers, their related classifiers, and relationships
	 * @param coreClassifiers
	 * @throws IOException 
	 */
	public void appendWithRelationships(
			Iterable<EClassifier> coreClassifiers,
			RelationshipDirection direction,
			int depth) throws IOException {
		Set<EClassifier> coreSet = new HashSet<>();
		for (EClassifier cc: coreClassifiers) {
			if (coreSet.add(cc)) {
				append(cc);
			}
		}
				
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
		
		for (EClassifier rc: relatedSet) {
			if (!coreSet.contains(rc)) {
				append(rc, "#DDDDDD");
			}
		}
		
		Set<EClassifier> allClassifiers = new HashSet<>(coreSet);
		allClassifiers.addAll(relatedSet);
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
					if (!processedOpposites.contains(ref) && allClassifiers.contains(ref.getEReferenceType())) {
						append(ref);
						EReference opposite = ref.getEOpposite();
						if (opposite!=null) {
							processedOpposites.add(opposite);
						}
					} 
				}
			}
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
	
	protected void append(EReference ref) throws IOException {
		collector.append(qualifiedName(ref.getEContainingClass()));
		collector.append(" ");
		EReference opposite = ref.getEOpposite();
		if (ref.isContainment()) {
			collector.append("*");
		} else if (opposite!=null) {
			collector.append("\"");
			collector.append(getLocalizedName(opposite));			
			String multiplicity = getMultiplicity(opposite);
			if (!multiplicity.isEmpty()) {
				collector.append("["+multiplicity+"]");
			}
			collector.append("\" ");
		}
		
		if (opposite == null) {
			collector.append(ASSOCIATION_RELATION);
		} else {
			collector.append(RELATION_LINE);
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
			collector.append(getLocalizedName(ref));			
			if (!multiplicity.isEmpty()) {
				collector.append("["+multiplicity+"]");
			}
			collector.append("\" ");			
		}		
		
		collector.append(qualifiedName(ref.getEReferenceType()));		
		
		if (opposite == null) {
			collector
				.append(" : ")
				.append(getLocalizedName(ref));
			
			collector.append(genericTypeArguments(ref.getEGenericType()));
		}
		
		collector.append(System.lineSeparator());
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
			acit = resourceSet == null ? eResource.getAllContents() : eResource.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EClass && ((EClass) obj).getESuperTypes().contains(eClass)) {
				ret.add((EClass) obj);
			}
		});
		return ret;
	}
	
	/**
	 * Finds all referrers in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getReferrers(EClass eClass) {
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
			acit = resourceSet == null ? eResource.getAllContents() : eResource.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EReference && ((EReference) obj).getEReferenceType() == eClass) {
				ret.add(((EReference) obj).getEContainingClass());
			}
		});
		return ret;
	}
	
	public void append(EClassifier eClassifier) throws IOException {
		append(eClassifier, null);
	}
	
	public void append(EClassifier eClassifier, String background) throws IOException {
		if (eClassifier instanceof EClass) {
			append((EClass) eClassifier, background);
		} else if (eClassifier instanceof EEnum) {
			append((EEnum) eClassifier, background);			
		} else if (eClassifier instanceof EDataType) {
			append((EDataType) eClassifier, background);
		}
	}
	
	protected String qualifiedName(EClassifier eClassifier) {
		EPackage ePackage = eClassifier.getEPackage();
		if (ePackage == null) {
			return getLocalizedName(eClassifier);				
		}
		return "\""+getLocalizedName(ePackage)+" ("+ePackage.getNsURI()+")."+getLocalizedName(eClassifier)+"\"";
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
		StringBuilder ret = new StringBuilder(typeParameter.getName());
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
			ret.append(eGenericType.getETypeParameter().getName());
		} else if (eGenericType.getEClassifier() != null) {
			ret.append(eGenericType.getEClassifier().getName());			
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

	public void append(EClass eClass) throws IOException {
		append(eClass, null);
	}
	
	public void append(EClass eClass, String background) throws IOException {
		String modifiers = eClass.isAbstract() && !eClass.isInterface() ? "abstract" : null;
		appendClassStart(modifiers, eClass.isInterface() ? "interface" : "class", qualifiedName(eClass) + typeParameters(eClass), getEClassifierLink(eClass), background);
		if (isAppendAttributes(eClass)) {
			for (EAttribute attribute: eClass.getEAttributes()) {			
				EGenericType eGenericType = attribute.getEGenericType();
				if (eGenericType != null) {
					appendAttribute(null, null, genericName(eGenericType) + (attribute.isMany() ? "[]" : ""), getLocalizedName(attribute));
				}						
			}
		}
		if (isAppendOperations(eClass)) {
			for (EOperation op : eClass.getEOperations()) {
				Collection<String> parameters = new ArrayList<String>();
				for (EParameter parameter : op.getEParameters()) {
					String paramString = getLocalizedName(parameter);
					if (parameter.getEGenericType() != null) {
						paramString = genericName(parameter.getEGenericType()) + " " + paramString;
					}
					parameters.add(paramString);
				}
				appendOperation(null, null, genericName(op.getEGenericType()), getLocalizedName(op), parameters);
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
		if (eClassifierLinkResolver != null) {
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

	public void append(EDataType dataType) throws IOException {
		append(dataType, null);
	}	
	
	public void append(EDataType dataType, String background) throws IOException {
		appendClassStart(null, "class", qualifiedName(dataType)+" << (D,orchid) >>", getEClassifierLink(dataType), background);
		if (dataType.getInstanceClassName() != null) {
			appendAttribute(null, null, null, dataType.getInstanceClassName());
		}
		appendClassEnd();
	}

	public void append(EEnum eEnum) throws IOException {
		append(eEnum, null);
	}
	
	public void append(EEnum eEnum, String background) throws IOException {
		appendClassStart(null, "enum", qualifiedName(eEnum), getEClassifierLink(eEnum), background);
		for (EEnumLiteral literal : eEnum.getELiterals()) {
			appendAttribute(String.valueOf(literal.getValue()), null, getLocalizedName(literal), literal.getName().equals(literal.getLiteral()) ? "" : literal.getLiteral());
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
				typeName = getLocalizedName(type);
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
