package org.nasdanika.drawio.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.Mapper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.drawio.model.Node;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.Root;
import org.nasdanika.drawio.model.SemanticMapping;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Base class for classes which map/transform Drawio model to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 *
 * @param <D> Document element type
 * @param <S> Semantic model element type
 */
public abstract class AbstractDrawioFactory<D extends EObject, S extends EObject> {
	
	public static final String TOP_LEVEL_PAGES_ANNOTATION = "top-level-pages";
	public static final String DRAWIO_REPRESENTATION = "drawio";

	public String getPropertyNamespace() {
		return "";
	}
	
	protected String getTopLevelPageProperty() {
		return getPropertyNamespace() + "top-level-page";
	}		
	
	/**
	 * Indicates a top level page which shall be a child of document element
	 * @param page
	 * @return
	 */
	public boolean isTopLevelPage(Page page) {
		String topLevelPageProperty = getTopLevelPageProperty();
		if (!Util.isBlank(topLevelPageProperty)) {
			String topLevelPage = page.getModel().getRoot().getProperties().get(topLevelPageProperty);
			if (!Util.isBlank(topLevelPage)) {
				return "true".equals(topLevelPage.trim());
			}
		}

		return page.getLinks().isEmpty();
	}
	
	/**
	 * Property containing domain class name.
	 * The name can be simple <code>class  name</code>, qualified <code>package name.class name</code>, or be a URI <code>package namespace uri#//class name</code>  
	 * @return
	 */
	protected String getTypeProperty() {
		return getPropertyNamespace() + "type";
	}	
		
	/**
	 * A map of package aliases to {@link EPackage}
	 * @return
	 */
	protected abstract Map<String,EPackage> getEPackages();
		
	public EClassifier getType(String type, Marked source) {
		if (Util.isBlank(type)) {
			return null;
		}
		URI typeURI = URI.createURI(type);
		if (typeURI.hasFragment()) {
			URI ePackageNsURI = typeURI.trimFragment();
			for (EPackage ePackage: getEPackages().values()) {
				if (ePackageNsURI.equals(URI.createURI(ePackage.getNsURI()))) {
					String eClassifierName = typeURI.fragment().substring(2);
					EClassifier eClassifier = ePackage.getEClassifier(eClassifierName);
					if (eClassifier == null) {
						throw new ConfigurationException("EClassifier " + eClassifierName + " not found in EPackage: " + ePackageNsURI, source); 				
					}
					return eClassifier;
				}
			}
			throw new IllegalArgumentException("EPackage not found: " + ePackageNsURI); 
		}
		
		int dotIdx = type.indexOf('.');
		if (dotIdx == -1) {
			Optional<EClassifier> typeOpt = getEPackages()
				.values()
				.stream()
				.map(ep -> ep.getEClassifier(type))
				.filter(Objects::nonNull)
				.findFirst();
			
			if (typeOpt.isPresent()) {
				return typeOpt.get();
			}
			throw new ConfigurationException("Unknown type: " + type, source); 				
		}
		
		Optional<EClassifier> typeOpt = getEPackages()
				.entrySet()
				.stream()
				.filter(pe -> pe.getKey().equals(type.substring(0,dotIdx)))
				.map(Map.Entry::getValue)
				.map(ep -> ep.getEClassifier(type.substring(dotIdx + 1)))
				.filter(Objects::nonNull)
				.findFirst();
			
		if (typeOpt.isPresent()) {
			return typeOpt.get();
		}
		throw new ConfigurationException("Unknown type: " + type, source); 				
	}
	
	/**
	 * Creates an instance of specified type.
	 * This implementation uses flow model EClass names as types to instantiate respective {@link EClass}es using factory
	 * @param kind
	 * @return
	 */
	protected EObject create(String type, Marked source) {
		EClassifier classifier = getType(type, source);
		if (classifier instanceof EClass) {
			return create((EClass) classifier);
		}
		
		throw new IllegalArgumentException("Unsupported element type: " + type);
	}
	
	/**
	 * Creates an instance of argument EClass. This implementation delegates to the EPackage factory.
	 * Override to customize, e.g. use specializations (sub-classes) instead of the argument EClass.
	 * @param type
	 * @return
	 */
	protected EObject create(EClass type) {
		return type.getEPackage().getEFactoryInstance().create(type);
	}
	
	// ---

	/**
	 * Override to return content mappers for different phases and passes
	 * @param phase
	 * @param pass
	 * @return
	 */
	protected Mapper<EObject, EObject> getMapper(int pass) {
		return null;
	}
	
	/**
	 * Creates a document element from {@link Document}
	 * @param document
	 * @param parallel
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Factory
	public final D createDocumentElement(
			org.nasdanika.drawio.model.Document document,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		D documentElement = createDocumentElement(document, elementProvider, registry, progressMonitor);

		if (documentElement instanceof ModelElement) {
			ModelElement dme = (ModelElement) documentElement;
			if (Util.isBlank(dme.getUuid())) {
				dme.setUuid(UUID.randomUUID().toString());
			}
		}
		
		return documentElement;
	}
	
	/**
	 * 
	 * @param document
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected abstract D createDocumentElement(
			org.nasdanika.drawio.model.Document document,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor);
	
	/**
	 * Semantic ID is used instead of the Drawio element ID if provided. E.g. "my-process" instead of "HP_1aCX3hRniy5izKEYK"
	 * While it is possible to edit Drawio element ID's, it shall be done with care to avoid introduction of duplicate ID's. 
	 * Semantic ID's shall be unique within their containment collection.   
	 * @return
	 */
	protected String getSemanticIdProperty() {
		return getPropertyNamespace() + "semantic-id";
	}	
	
	protected String getDocumentationProperty() {
		return getPropertyNamespace() + "documentation";
	}	
		
	protected String getDocRefProperty() {
		return getPropertyNamespace() + "doc-ref";
	}	
	
	protected enum DocumentationFormat { markdown, text, html }
	
	protected String getDocFormatProperty() {
		return getPropertyNamespace() + "doc-format"; 
	}	
	
	/**
	 * Creates a semantic model element depending on the type
	 * @param page
	 * @param parallel
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Factory
	@SuppressWarnings("unchecked")
	public final S createSemanticElement(
			org.nasdanika.drawio.model.ModelElement modelElement,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
	
		String type = getTypeName(modelElement);		
		
		S semanticElement;		
		if (Util.isBlank(type)) {
			String constructorPropertyName = getConstructorProperty();
			String constructor = Util.isBlank(constructorPropertyName) ? null : modelElement.getProperties().get(constructorPropertyName);
			if (Util.isBlank(constructor)) {
				return null;
			}
			
			try {			
				ExpressionParser parser = getExpressionParser();
				Expression exp = parser.parseExpression(constructor);
				EvaluationContext evaluationContext = getEvaluationContext();
				configureConstructorEvaluationContext(evaluationContext, registry, progressMonitor);
				semanticElement = (S) exp.getValue(evaluationContext, modelElement, EObject.class);
			} catch (ParseException e) {
				throw new ConfigurationException("Error parsing semantic selector: '" + constructor, e, modelElement);
			} catch (EvaluationException e) {
				throw new ConfigurationException("Error evaluating semantic selector: '" + constructor, e, modelElement);
			}			
		} else {
			semanticElement = (S) create(type.trim(), modelElement);
		}
		
		if (semanticElement instanceof ModelElement) {
			ModelElement sme = (ModelElement) semanticElement;
			if (Util.isBlank(sme.getUuid())) {
				sme.setUuid(UUID.randomUUID().toString());
			}
		}
		
		return semanticElement;
	}
	
	/**
	 * Constructor expression
	 * @return
	 */
	protected String getConstructorProperty() {
		return getPropertyNamespace() + "constructor";
	}

	protected void configureConstructorEvaluationContext(
			EvaluationContext evaluationContext,
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry, 
			ProgressMonitor progressMonitor) {
		
		evaluationContext.setVariable("registry", registry);
		evaluationContext.setVariable("progressMonitor", progressMonitor);
	}
	
	/**
	 * Property for setting base {@link URI} for an element relative to its containers for resolving resource URI's 
	 * such as doc-ref, feature-map-ref, spec-ref.
	 * @return
	 */
	protected String getBaseURIProperty() {
		return getPropertyNamespace() + Context.BASE_URI_PROPERTY;
	}		
	
	public URI getBaseURI(org.nasdanika.drawio.model.ModelElement modelElement) {
		EObject logicalParent; 
		
		if (modelElement instanceof Connection) {
			Connection connection = (Connection) modelElement;
			Node cSource = connection.getSource();
			logicalParent = cSource == null ? connection.eContainer() : cSource;
		} else {		
			logicalParent = modelElement.eContainer();
		}
		URI logicalParentBaseURI;
		if (logicalParent instanceof org.nasdanika.drawio.model.ModelElement) {
			logicalParentBaseURI = getBaseURI((org.nasdanika.drawio.model.ModelElement) logicalParent);
		} else {
			logicalParentBaseURI = modelElement.eResource().getURI();
			if (logicalParent instanceof org.nasdanika.drawio.model.Model) {
				Page page = (Page) logicalParent.eContainer();
				for (org.nasdanika.drawio.model.ModelElement link: page.getLinks()) {
					logicalParentBaseURI = getBaseURI(link);
					break;
				}
			}
		}
		String baseURIProperty = getBaseURIProperty();
		if (Util.isBlank(baseURIProperty)) {
			return logicalParentBaseURI;
		}
		
		String baseURIStr = modelElement.getProperties().get(baseURIProperty);
		if (Util.isBlank(baseURIStr)) {
			return logicalParentBaseURI;
		}
		
		URI baseURI = URI.createURI(baseURIStr);
		return logicalParentBaseURI == null || logicalParentBaseURI.isRelative() ? baseURI : baseURI.resolve(logicalParentBaseURI);		
	}
	
	/**
	 * Returns semantic element type name. This implementation gets it from type property.
	 * Override to customize. E.g. read <code>c4Type</code> property and map c4 type names such as "Software System" to model types.
	 * Mapping of type names can also be done by overriding <code>getType</code> method
	 * @param modelElement
	 * @return
	 */
	protected String getTypeName(org.nasdanika.drawio.model.ModelElement modelElement) {
		String typeProperty = getTypeProperty();
		if (Util.isBlank(typeProperty)) {
			return null;
		}
		
		return modelElement.getProperties().get(typeProperty);
	}
	
	// === Wiring ===
	
	protected String getPageElementProperty() {
		return getPropertyNamespace() + "page-element";
	}
	
	public boolean isPageElement(org.nasdanika.drawio.model.ModelElement drawioModelElement) {	
		String pageElementProperty = getPageElementProperty();
		if (Util.isBlank(pageElementProperty)) {
			return false;
		}
		
		String pev = drawioModelElement.getProperties().get(pageElementProperty);
		return !Util.isBlank(pev) && "true".equals(pev.trim());
	}

	/**
	 * Adds contained elements into containment references.
	 * Traverses page links.
	 * 
	 * @param document
	 * @param pkg
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 1)
	public final void mapDocument(
			org.nasdanika.drawio.model.Document document,
			D documentElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<EObject,EObject> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(document, registry, progressMonitor);
		}
		
		String source = document.getSource();
		if (!Util.isBlank(source) && documentElement instanceof org.nasdanika.ncore.ModelElement) {
			org.nasdanika.ncore.ModelElement documentModelElement = (org.nasdanika.ncore.ModelElement) documentElement;
			String filteredSource = filterSourceDocument(source, document, registry, progressMonitor);
			documentModelElement.getRepresentations().put(DRAWIO_REPRESENTATION, filteredSource);
			Collection<String> topLevelPages = new ArrayList<>();
			for (Page page: document.getPages()) {
				if (isTopLevelPage(page)) {
					topLevelPages.add(page.getId());
				}
			}
			if (!topLevelPages.isEmpty()) {
				documentModelElement.setAnnotation(TOP_LEVEL_PAGES_ANNOTATION, topLevelPages);
			}
		}
	}
	
	/**
	 * Override to manipulate the source document. E.g. inject semantic UUID's from the registry
	 * @param source
	 * @param document
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected String filterSourceDocument(
			String source,
			org.nasdanika.drawio.model.Document document,
			Map<EObject, EObject> registry,
			ProgressMonitor progressMonitor) {
		
		return source;
	}
	
	@org.nasdanika.common.Transformer.Wire(phase = 1)
	public final void mapModelElement(
			org.nasdanika.drawio.model.ModelElement drawioModelElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
						
		Mapper<EObject,EObject> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(drawioModelElement, registry, progressMonitor);
		}
		
		boolean isPageElement = isPageElement(drawioModelElement);
		
		// Semantic mapping
		if (semanticElement instanceof org.nasdanika.drawio.model.SemanticElement) {
			SemanticMapping semanticMapping = createSemanticMapping();
			for (EObject eContainer = drawioModelElement.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
				if (eContainer instanceof Page) {
					semanticMapping.setPageID(((Page) eContainer).getId());						
				} else if (eContainer instanceof org.nasdanika.drawio.model.Document) {
					semanticMapping.setDocumentURI(((org.nasdanika.drawio.model.Document) eContainer).getUri());
				}
			}
			semanticMapping.setModelElementID(drawioModelElement.getId());
			semanticMapping.setPageElement(isPageElement);
			((org.nasdanika.drawio.model.SemanticElement) semanticElement).getSemanticMappings().add(semanticMapping);
		}		
		
		// Page representation
		if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
			org.nasdanika.ncore.ModelElement semanticModelElement = (org.nasdanika.ncore.ModelElement) semanticElement;
			Page linkedPage = drawioModelElement.getLinkedPage();
			if (linkedPage != null) {
				addRepresentationPage(
						semanticModelElement, 
						linkedPage, 
						registry, 
						progressMonitor);
				
			}
			for (EObject eContainer = drawioModelElement.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
				if (eContainer instanceof Page) {
					if (isPageElement) {
						addRepresentationPage(
								semanticModelElement, 
								(Page) eContainer, 
								registry, 
								progressMonitor);
					}
				}
			}
		}
	}
	
	/**
	 * NOP method because drawio classes are not available here. Implemented in GraphDrawioFactory.
	 * @param semanticElement
	 * @param page
	 */
	protected void addRepresentationPage(
			org.nasdanika.ncore.ModelElement semanticModelElement, 
			Page page,
			Map<EObject, EObject> registry,
			ProgressMonitor progressMonitor) {
		// NOP
	}

	protected SemanticMapping createSemanticMapping() {
		return ModelFactory.eINSTANCE.createSemanticMapping();
	}
	
	protected String getRefIdProperty() {
		return getPropertyNamespace() + "ref-id";
	}
	
	/**
	 * Resolves semantic element by reference id. 
	 * @param refId
	 * @param wiring pass
	 * @return Null if element cannot be resolved yet
	 * @throws IllegalArgumentException If refId does not resolve to a semantic element
	 */
	protected abstract S getByRefId(String refId, int pass, Map<EObject, EObject> registry);
	
	/**
	 * Wires elements with ref-id property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireRefIds(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String refIdPropertyName = getRefIdProperty();
		if (Util.isBlank(refIdPropertyName)) {
			return true;
		}
		String refId = modelElement.getProperties().get(refIdPropertyName);
		if (Util.isBlank(refId)) {
			return true;
		}
		
		S refTarget = getByRefId(refId, pass, registry);
		if (refTarget == null) {
			return false;
		}
		
		registry.put(modelElement, refTarget); // Resolved refId triggers a new wave of wiring
		return true;
	}
		
	protected String getSelectorProperty() {
		return getPropertyNamespace() + "selector";
	}
	
	/**
	 * Wires page elements without semantic element
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wirePageElement(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		if (!isPageElement(modelElement)) {
			return true;
		}
		Page page = modelElement.getPage();
		if (isTopLevelPage(page) || page.getLinks().isEmpty()) {
			return true;
		}
		Optional<EObject> linkSemanticElementOpt = page
			.getLinks()
			.stream()
			.map(registry::get)
			.filter(Objects::nonNull)
			.findFirst();
		
		if (linkSemanticElementOpt.isEmpty()) {
			return false; // Not there yet
		}
		registry.put(modelElement, linkSemanticElementOpt.get());
		return true;
	}
	
	/**
	 * Wires elements with selector property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireSelector(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String selectorPropertyName = getSelectorProperty();
		if (Util.isBlank(selectorPropertyName)) {
			return true;
		}
		String selector = modelElement.getProperties().get(selectorPropertyName);
		if (Util.isBlank(selector)) {
			return true;
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(selector);
			EvaluationContext evaluationContext = getEvaluationContext();
			configureSelectorEvaluationContext(evaluationContext, registry, pass, progressMonitor);
			Object result = exp.getValue(evaluationContext, modelElement, Object.class);
			if (result instanceof EObject) {
				EObject selectedTarget = registry.get(result);
				if (selectedTarget == null) {
					return false; // Not there yet
				}
				registry.put(modelElement, selectedTarget); // Тriggers a new wave of wiring
				return true;				
			}
			if (result instanceof Boolean) {
				return (Boolean) result;
			} 
			if (result == null) {
				return true;
			}
			throw new ConfigurationException("Unexpected result type of selector: '" + selector + "': " + result, modelElement);			
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing selector: '" + selector, e, modelElement);
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating selector: '" + selector, e, modelElement);
		}
	}

	protected void configureSelectorEvaluationContext(
			EvaluationContext evaluationContext,
			Map<EObject, EObject> registry, 
			int pass,
			ProgressMonitor progressMonitor) {
		
		evaluationContext.setVariable("registry", registry);
		evaluationContext.setVariable("pass", pass);
		evaluationContext.setVariable("progressMonitor", progressMonitor);
	}
	
	protected String getPrototypeProperty() {
		return getPropertyNamespace() + "prototype";
	}
	
	protected EObject getPrototype(
			org.nasdanika.drawio.model.ModelElement modelElement,
			ProgressMonitor progressMonitor) {
	
		String prototypePropertyName = getPrototypeProperty();
		if (Util.isBlank(prototypePropertyName)) {
			return null;
		}
		String prototypeExpr = modelElement.getProperties().get(prototypePropertyName);
		if (Util.isBlank(prototypeExpr)) {
			return null;
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(prototypeExpr);
			EvaluationContext evaluationContext = getEvaluationContext();
			configurePrototypeEvaluationContext(evaluationContext, progressMonitor);
			return exp.getValue(evaluationContext, modelElement, EObject.class);
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing prototype: '" + prototypeExpr, e, modelElement);
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating prototype: '" + prototypeExpr, e, modelElement);
		}
	}	
	
	/**
	 * Wires elements with prototype property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wirePrototype(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
	
		String prototypePropertyName = getPrototypeProperty();
		if (Util.isBlank(prototypePropertyName)) {
			return true;
		}
		String prototypeExpr = modelElement.getProperties().get(prototypePropertyName);
		if (Util.isBlank(prototypeExpr)) {
			return true;
		}
		
		try {			
			EObject result = getPrototype(modelElement, progressMonitor);
			EObject prototype = registry.get(result);
			if (prototype == null) {
				return false; // Not there yet
			}
			EObject copy = EcoreUtil.copy(prototype);
			if (copy instanceof ModelElement) {
				((ModelElement) copy).setUuid(UUID.randomUUID().toString());
			}
			registry.put(modelElement, copy); // Тriggers a new wave of wiring
			return true;				
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing selector: '" + prototypeExpr, e, modelElement);
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating selector: '" + prototypeExpr, e, modelElement);
		}
	}

	protected void configurePrototypeEvaluationContext(
			EvaluationContext evaluationContext,
			ProgressMonitor progressMonitor) {
		
		evaluationContext.setVariable("progressMonitor", progressMonitor);
	}
	
	protected String getSemanticSelectorProperty() {
		return getPropertyNamespace() + "semantic-selector";
	}
	
	/**
	 * Wires elements with semantic selector property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireSemanticSelector(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String semanticSelectorPropertyName = getSemanticSelectorProperty();
		if (Util.isBlank(semanticSelectorPropertyName)) {
			return true;
		}
		String semanticSelector = modelElement.getProperties().get(semanticSelectorPropertyName);
		if (Util.isBlank(semanticSelector)) {
			return true;
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(semanticSelector);
			EvaluationContext evaluationContext = getEvaluationContext();
			configureSemanticSelectorEvaluationContext(evaluationContext, registry, pass, progressMonitor);
			Object result = exp.getValue(evaluationContext, modelElement, Object.class);
			if (result instanceof EObject) {
				registry.put(modelElement, (EObject) result); 
				return true;				
			}
			if (result == null) {
				return true;
			}
			throw new ConfigurationException("Unexpected result type of semantic selector: '" + semanticSelector + "': " + result, modelElement);			
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing semantic selector: '" + semanticSelector, e, modelElement);
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating semantic selector: '" + semanticSelector, e, modelElement);
		}
	}

	protected void configureSemanticSelectorEvaluationContext(
			EvaluationContext evaluationContext,
			Map<EObject, EObject> registry, 
			int pass,
			ProgressMonitor progressMonitor) {
		
		evaluationContext.setVariable("registry", registry);
		evaluationContext.setVariable("pass", pass);
		evaluationContext.setVariable("progressMonitor", progressMonitor);
	}
		
	protected EvaluationContext getEvaluationContext() {
		return new StandardEvaluationContext();
	}

	protected SpelExpressionParser getExpressionParser() {
		return new SpelExpressionParser();
	}	
	
	/**
	 * Feature maps null semantic elements, which is needed for connections as they might be pass-through.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 2)
	public final boolean featurMapNulls(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<EObject,EObject> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(modelElement, registry, progressMonitor);
		}		
		return true;
	}
	
	/**
	 * Wires document configuration
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 3)
	public final void wireDocumentConfiguration(
			org.nasdanika.drawio.model.Document document,
			D documentElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		
		configureDocumentElement(
				document,
				documentElement,
				registry,
				progressMonitor);
	}
	
	protected void configureDocumentElement(
			org.nasdanika.drawio.model.Document document,
			D documentElement,
			Map<EObject, EObject> registry,
			ProgressMonitor progressMonitor) {
		
		if (documentElement instanceof org.nasdanika.ncore.Marked) {
			for (Marker marker: document.getMarkers()) {
				((org.nasdanika.ncore.Marked) documentElement).getMarkers().add(EcoreUtil.copy(marker));
			}
		}		
	}
	
	protected String getConfigPrototypeProperty() {
		return getPropertyNamespace() + "config-prototype";
	}
	
	protected EObject getConfigPrototype(
			org.nasdanika.drawio.model.ModelElement modelElement,
			ProgressMonitor progressMonitor) {
	
		String configPrototypePropertyName = getConfigPrototypeProperty();
		if (Util.isBlank(configPrototypePropertyName)) {
			return getPrototype(modelElement, progressMonitor);
		}
		String configPrototypeExpr = modelElement.getProperties().get(configPrototypePropertyName);
		if (Util.isBlank(configPrototypeExpr)) {
			return getPrototype(modelElement, progressMonitor);
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(configPrototypeExpr);
			EvaluationContext evaluationContext = getEvaluationContext();
			configurePrototypeEvaluationContext(evaluationContext, progressMonitor);
			return exp.getValue(evaluationContext, modelElement, EObject.class);
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing congfig prototype: '" + configPrototypeExpr, e, modelElement);
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating config prototype: '" + configPrototypeExpr, e, modelElement);
		}
	}		
	
	@org.nasdanika.common.Transformer.Wire(phase = 3)
	public final void wireModelElementConfiguration(
			org.nasdanika.drawio.model.ModelElement drawioModelElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		configureSemanticElement(
				drawioModelElement,
				semanticElement,
				registry,
				false,
				progressMonitor);
	}
	
	protected void configureSemanticElement(
			org.nasdanika.drawio.model.ModelElement modelElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			boolean isPrototype,
			ProgressMonitor progressMonitor) {
		
		EObject configPrototype = getConfigPrototype(modelElement, progressMonitor);
		if (configPrototype instanceof org.nasdanika.drawio.model.ModelElement) {
			configureSemanticElement(
					(org.nasdanika.drawio.model.ModelElement) configPrototype, 
					semanticElement, 
					registry, 
					isPrototype, 
					progressMonitor);
		}

		if (semanticElement instanceof org.nasdanika.ncore.Marked) {
			for (Marker marker: modelElement.getMarkers()) {
				((org.nasdanika.ncore.Marked) semanticElement).getMarkers().add(EcoreUtil.copy(marker));
			}
		}
						
		// Root is logically "merged" with the containing page
		if (modelElement instanceof Root) {
			Page page = (Page) modelElement.eContainer().eContainer();
			if (semanticElement instanceof org.nasdanika.ncore.NamedElement) {
				org.nasdanika.ncore.NamedElement namedSemanticElement = (org.nasdanika.ncore.NamedElement) semanticElement;
				if (Util.isBlank(namedSemanticElement.getName())) {
					namedSemanticElement.setName(page.getName());
				}
			}
		}
		
		if (!isPrototype && semanticElement instanceof org.nasdanika.ncore.StringIdentity) {
			String semanticIdProperty = getSemanticIdProperty();
			if (!Util.isBlank(semanticIdProperty)) {
				String semanticId = modelElement.getProperties().get(semanticIdProperty);
				org.nasdanika.ncore.StringIdentity semanticStringIdentity = (org.nasdanika.ncore.StringIdentity) semanticElement;
				if (Util.isBlank(semanticId)) {
					if (Util.isBlank(semanticStringIdentity.getId())) {
						semanticStringIdentity.setId(modelElement.getId());
					}
				} else {
					semanticStringIdentity.setId(semanticId);
				}
			}
		}
		
		String label = modelElement.getLabel();
		if (!Util.isBlank(label) && semanticElement instanceof org.nasdanika.ncore.NamedElement) {
			String labelText = Jsoup.parse(label).text();
			((org.nasdanika.ncore.NamedElement) semanticElement).setName(labelText);
		}
		String tooltip = modelElement.getTooltip();
		if (!Util.isBlank(tooltip) && semanticElement instanceof org.nasdanika.ncore.ModelElement) {
			((org.nasdanika.ncore.ModelElement) semanticElement).setDescription(tooltip);
		}
				
		if (semanticElement instanceof org.nasdanika.ncore.Documented) {
			URI baseUri = getBaseURI(modelElement);
			org.nasdanika.ncore.Documented documented = (org.nasdanika.ncore.Documented) semanticElement;
			String docProperty = getDocumentationProperty();
			if (!Util.isBlank(docProperty)) {
				String doc = modelElement.getProperties().get(docProperty);
				if (!Util.isBlank(doc)) {
					DocumentationFormat docFormat = DocumentationFormat.markdown;
					String docFormatProperty = getDocFormatProperty();
					if (!Util.isBlank(docFormatProperty)) {
						String docFormatStr = modelElement.getProperties().get(docFormatProperty);
						if (!Util.isBlank(docFormatStr)) {
							docFormat = DocumentationFormat.valueOf(docFormatStr);						
						}					
					}
					switch (docFormat) {
					case html:
						documented.getDocumentation().add(createHtmlDoc(doc, baseUri, progressMonitor));
						break;
					case markdown:
						documented.getDocumentation().add(createMarkdownDoc(doc, baseUri, progressMonitor));
						break;
					case text:
						documented.getDocumentation().add(createTextDoc(doc, baseUri, progressMonitor));
						break;
					default:
						throw new ConfigurationException("Unsupported documentation format: " + docFormat, modelElement instanceof Marked ? (Marked) modelElement : null);
					
					}
				}
			}
			
			String docRefProperty = getDocRefProperty();
			if (!Util.isBlank(docRefProperty)) {
				String docRefStr = modelElement.getProperties().get(docRefProperty);
				if (!Util.isBlank(docRefStr)) {					
					DocumentationFormat docFormat = null;
					if (docRefStr.toLowerCase().endsWith(".html") || docRefStr.toLowerCase().endsWith(".htm")) {
						docFormat = DocumentationFormat.html;
					} else if (docRefStr.toLowerCase().endsWith(".txt")) {
						docFormat = DocumentationFormat.text;
					} else {					
						docFormat = DocumentationFormat.markdown; // Default
					}
					String docFormatProperty = getDocFormatProperty();
					if (!Util.isBlank(docFormatProperty)) {
						String docFormatStr = modelElement.getProperties().get(docFormatProperty);
						if (!Util.isBlank(docFormatStr)) {
							docFormat = DocumentationFormat.valueOf(docFormatStr);						
						}					
					}
					URI docRefURI = URI.createURI(docRefStr);
					if (baseUri != null && !baseUri.isRelative()) {
						docRefURI = docRefURI.resolve(baseUri);
					}
					switch (docFormat) {
					case html:
						documented.getDocumentation().add(createHtmlDoc(docRefURI, progressMonitor));
						break;
					case markdown:
						documented.getDocumentation().add(createMarkdownDoc(docRefURI, progressMonitor));
						break;
					case text:
						documented.getDocumentation().add(createTextDoc(docRefURI, progressMonitor));
						break;
					default:
						throw new ConfigurationException("Unsupported documentation format: " + docFormat, modelElement instanceof Marked ? (Marked) modelElement : null);					
					}
				}
			}
		}				
	}
		
	protected abstract EObject createHtmlDoc(String doc, URI baseUri, ProgressMonitor progressMonitor);
	
	protected abstract EObject createTextDoc(String doc, URI baseUri, ProgressMonitor progressMonitor);
	
	protected abstract EObject createMarkdownDoc(String doc, URI baseUri, ProgressMonitor progressMonitor);
	
	protected abstract EObject createHtmlDoc(URI docRef, ProgressMonitor progressMonitor);
	
	protected abstract EObject createTextDoc(URI docRef, ProgressMonitor progressMonitor);
	
	protected abstract EObject createMarkdownDoc(URI docRef, ProgressMonitor progressMonitor);	
	
}
