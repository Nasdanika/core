package org.nasdanika.drawio.model.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
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
import org.nasdanika.drawio.model.Tag;
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
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

/**
 * Base class for classes which map/transform Drawio model to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 *
 * @param <S> Semantic element type
 */
public abstract class AbstractDrawioFactory<S extends EObject> {
	
	public static final String DRAWIO_REPRESENTATION = "drawio";
	public static final String IMAGE_REPRESENTATION = "image";
	
	private static final String DATA_URI_PNG_PREFIX_NO_BASE_64 = "data:image/png,";
	private static final String DATA_URI_JPEG_PREFIX_NO_BASE_64 = "data:image/jpeg,";

	public String getPropertyNamespace() {
		return "";
	}
	
	protected String getTopLevelPageProperty() {
		return getPropertyNamespace() + "top-level-page";
	}	
	
	
	protected String getTagSpecPropertyName() {
		return getPropertyNamespace() + "tag-spec";
	}
	
	protected String getTagSpecRefPropertyName() {
		return getPropertyNamespace() + "feature-map-ref";
	}
	
	protected String getTagSpecStr(EObject source) {
		String tspn = getTagSpecPropertyName();
		if (!Util.isBlank(tspn)) {
			String ts = getProperty(source, tspn);
			if (!Util.isBlank(ts)) {
				return ts;
			}
		}
		
		String tsrpn = getTagSpecRefPropertyName();
		if (!Util.isBlank(tsrpn)) {
			String ref = getProperty(source, tsrpn);
			if (!Util.isBlank(ref)) {
				URI refURI = URI.createURI(ref);
				URI baseURI = getBaseURI(source);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}
				try {
					DefaultConverter converter = DefaultConverter.INSTANCE;
					Reader reader = converter.toReader(refURI);
					return converter.toString(reader);
				} catch (IOException e) {
					throw new ConfigurationException("Error loading tag spec from " + refURI, e, asMarked(source));
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Returns eObject property. This implementation uses ModelElement.getProperties().get() for instances of model element. 
	 * Returns null otherwise.
	 * @param eObj
	 * @return
	 */
	protected String getProperty(EObject eObj, String property) {
		if (eObj instanceof org.nasdanika.drawio.model.ModelElement) {
			return ((org.nasdanika.drawio.model.ModelElement) eObj).getProperties().get(property);
		}
		
		if (eObj instanceof Tag) {
			Page page = (Page) eObj.eContainer();
			Root root = page.getModel().getRoot();
			
			String config = getTagSpecStr(root);
			if (Util.isBlank(config)) {
				return null;
			}
			try {
				Yaml yaml = new Yaml();
				Object tagSpecObj = yaml.load(config);
				if (tagSpecObj instanceof Map) {
					Map<?,?> tagSpecMap = (Map<?,?>) tagSpecObj;
					Object tagConfigObj = tagSpecMap.get(((Tag) eObj).getName());
					if (tagConfigObj == null) {
						return null;
					}
					
					if (tagConfigObj instanceof Map) {
						Map<?,?> tagConfigMap = (Map<?,?>) tagConfigObj;
						Object value = tagConfigMap.get(property);
						if (value instanceof String) {
							return (String) value;
						}
						return yaml.dump(value);
					}
					
					throw new ConfigurationException("Usupported tag configuration type: " + tagConfigObj, asMarked(eObj));
				}
				
				throw new ConfigurationException("Usupported configuration type: " + tagSpecObj, asMarked(eObj));
			} catch (YAMLException yamlException) {
				throw new ConfigurationException(yamlException, asMarked(eObj));
			}
		}
		
		return null;
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
		
	public EClassifier getType(String type, EObject source) {
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
						throw new ConfigurationException("EClassifier " + eClassifierName + " not found in EPackage: " + ePackageNsURI, asMarked(source)); 				
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
			throw new ConfigurationException("Unknown type: " + type, asMarked(source)); 				
		}
		
		Optional<EClassifier> typeOpt = getEPackages()
				.entrySet()
				.stream()
				.filter(pe -> pe.getKey().equals(type.substring(0, dotIdx)))
				.map(Map.Entry::getValue)
				.map(ep -> ep.getEClassifier(type.substring(dotIdx + 1)))
				.filter(Objects::nonNull)
				.findFirst();
			
		if (typeOpt.isPresent()) {
			return typeOpt.get();
		}
		throw new ConfigurationException("Unknown type: " + type, asMarked(source)); 				
	}
	
	/**
	 * Creates an instance of specified type.
	 * This implementation uses flow model EClass names as types to instantiate respective {@link EClass}es using factory
	 * @param kind
	 * @return
	 */
	protected EObject create(String type, EObject source) {
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
	
	/**
	 * Override to return content mappers for different passes. 
	 * This implementation returns {@link PropertySetterFeatureMapper}.
	 * @param pass
	 * @return
	 */
	protected Mapper<EObject, EObject> getMapper(int pass) {
		return new PropertySetterFeatureMapper<EObject, EObject>() {

			@Override
			protected URI getBaseURI(EObject source) {
				return AbstractDrawioFactory.this.getBaseURI(source);
			}
			
			@Override
			protected String getProperty(EObject eObj, String property) {
				return AbstractDrawioFactory.this.getProperty(eObj, property);
			}
			
			@Override
			protected EClassifier getType(String type, EObject context) {
				return AbstractDrawioFactory.this.getType(type, context);
			}
			
			@Override
			protected String getPropertyNamespace() {
				return AbstractDrawioFactory.this.getPropertyNamespace();
			}
			
			@Override
			protected EvaluationContext createEvaluationContext() {
				return AbstractDrawioFactory.this.createEvaluationContext();
			}
			
		};
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
	public final S createDocumentElement(
			org.nasdanika.drawio.model.Document document,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		S documentElement = createDocumentElement(document, elementProvider, registry, progressMonitor);

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
	protected S createDocumentElement(
			org.nasdanika.drawio.model.Document document,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		return null;
	};
	
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
	
	protected Marked asMarked(EObject eObject) {
		if (eObject == null) {
			return null;
		}
		if (eObject instanceof Marked) {
			return (Marked) eObject;
		}
		return asMarked(eObject.eContainer());
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
	public final S createModelElementSemanticElement(
			org.nasdanika.drawio.model.ModelElement modelElement,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {

		return createSemanticElement(
				modelElement,
				elementProvider,
				registry,
				progressMonitor);
	}		
	
	public final S createTagSemanticElement(
			org.nasdanika.drawio.model.Tag tag,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
	
		return createSemanticElement(
				tag,
				elementProvider,
				registry,
				progressMonitor);
	
	}
	
	@SuppressWarnings("unchecked")
	protected S createSemanticElement(
			EObject eObj,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {	
	
		
		String type = getTypeName(eObj);		
		
		S semanticElement;		
		if (Util.isBlank(type)) {
			String constructorPropertyName = getConstructorProperty();
			String constructor = Util.isBlank(constructorPropertyName) ? null : getProperty(eObj, constructorPropertyName);
			if (Util.isBlank(constructor)) {
				return null;
			}
			
			try {			
				ExpressionParser parser = getExpressionParser();
				Expression exp = parser.parseExpression(constructor);
				EvaluationContext evaluationContext = createEvaluationContext();
				configureConstructorEvaluationContext(evaluationContext, registry, progressMonitor);
				semanticElement = (S) exp.getValue(evaluationContext, eObj, EObject.class);
			} catch (ParseException e) {
				throw new ConfigurationException("Error parsing semantic selector: '" + constructor, e, asMarked(eObj));
			} catch (EvaluationException e) {
				throw new ConfigurationException("Error evaluating semantic selector: '" + constructor, e, asMarked(eObj));
			}			
		} else {
			semanticElement = (S) create(type.trim(), eObj);
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
	
	public URI getBaseURI(EObject eObj) {
		EObject logicalParent; 
		
		if (eObj instanceof Connection) {
			Connection connection = (Connection) eObj;
			Node cSource = connection.getSource();
			logicalParent = cSource == null ? connection.eContainer() : cSource;
		} else {		
			logicalParent = eObj.eContainer();
		}
		URI logicalParentBaseURI;
		if (logicalParent instanceof org.nasdanika.drawio.model.ModelElement) {
			logicalParentBaseURI = getBaseURI((org.nasdanika.drawio.model.ModelElement) logicalParent);
		} else {
			logicalParentBaseURI = eObj.eResource().getURI();
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
		
		String baseURIStr = getProperty(eObj, baseURIProperty);
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
	 * @param eObj
	 * @return
	 */
	protected String getTypeName(EObject eObj) {
		String typeProperty = getTypeProperty();
		if (Util.isBlank(typeProperty)) {
			return null;
		}
		
		return getProperty(eObj, typeProperty);
	}
	
	// === Wiring ===
	
	protected String getPageElementProperty() {
		return getPropertyNamespace() + "page-element";
	}
	
	public boolean isPageElement(EObject eObj) {	
		String pageElementProperty = getPageElementProperty();
		if (Util.isBlank(pageElementProperty)) {
			return false;
		}
		
		String pev = getProperty(eObj, pageElementProperty);
		return !Util.isBlank(pev) && "true".equals(pev.trim());
	}
	
	// --- Wiring ---
	
	// --- Phase 0 - semantic elements using ref-id's, page element, selector, prototype, semantic-selector ---	
	
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
	public final boolean wireModelElementRefIds(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wireRefIds(modelElement, registry, pass, progressMonitor);
	}
		
	/**
	 * Wires elements with ref-id property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireTagRefIds(
			org.nasdanika.drawio.model.Tag tag,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wireRefIds(tag, registry, pass, progressMonitor);
	}
	
	protected boolean wireRefIds(
			EObject eObj,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String refIdPropertyName = getRefIdProperty();
		if (Util.isBlank(refIdPropertyName)) {
			return true;
		}
		String refId = getProperty(eObj, refIdPropertyName);
		if (Util.isBlank(refId)) {
			return true;
		}
		
		S refTarget = getByRefId(refId, pass, registry);
		if (refTarget != null) {
			registry.put(eObj, refTarget); // Resolved refId triggers a new wave of wiring
		}
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
	public final boolean wireModelElementSelector(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		return wireSelector(modelElement, registry, pass, progressMonitor);
	}
	
	
	/**
	 * Wires elements with selector property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireTagSelector(
			org.nasdanika.drawio.model.Tag tag,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		return wireSelector(tag, registry, pass, progressMonitor);
	}
	
	protected boolean wireSelector(
			EObject eObj,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String selectorPropertyName = getSelectorProperty();
		if (Util.isBlank(selectorPropertyName)) {
			return true;
		}
		String selector = getProperty(eObj, selectorPropertyName);
		if (Util.isBlank(selector)) {
			return true;
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(selector);
			EvaluationContext evaluationContext = createEvaluationContext();
			configureSelectorEvaluationContext(evaluationContext, registry, pass, progressMonitor);
			Object result = exp.getValue(evaluationContext, eObj, Object.class);
			if (result instanceof EObject) {
				EObject selectedTarget = registry.get(result);
				if (selectedTarget == null) {
					return false; // Not there yet
				}
				registry.put(eObj, selectedTarget); // Тriggers a new wave of wiring
				return true;				
			}
			if (result instanceof Boolean) {
				return (Boolean) result;
			} 
			if (result == null) {
				return true;
			}
			throw new ConfigurationException("Unexpected result type of selector: '" + selector + "': " + result, asMarked(eObj));			
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing selector: '" + selector, e, asMarked(eObj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating selector: '" + selector, e, asMarked(eObj));
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
			EObject eObj,
			ProgressMonitor progressMonitor) {
	
		String prototypePropertyName = getPrototypeProperty();
		if (Util.isBlank(prototypePropertyName)) {
			return null;
		}
		String prototypeExpr = getProperty(eObj, prototypePropertyName);
		if (Util.isBlank(prototypeExpr)) {
			return null;
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(prototypeExpr);
			EvaluationContext evaluationContext = createEvaluationContext();
			configurePrototypeEvaluationContext(evaluationContext, progressMonitor);
			return exp.getValue(evaluationContext, eObj, EObject.class);
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing prototype: '" + prototypeExpr, e, asMarked(eObj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating prototype: '" + prototypeExpr, e, asMarked(eObj));
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
	public final boolean wireModelElementPrototype(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wirePrototype(modelElement, registry, pass, progressMonitor);
	}
		
	/**
	 * Wires elements with prototype property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireTagPrototype(
			org.nasdanika.drawio.model.Tag tag,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wirePrototype(tag, registry, pass, progressMonitor);
	}

	protected boolean wirePrototype(
			EObject eObj,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
	
		String prototypePropertyName = getPrototypeProperty();
		if (Util.isBlank(prototypePropertyName)) {
			return true;
		}
		String prototypeExpr = getProperty(eObj, prototypePropertyName);
		if (Util.isBlank(prototypeExpr)) {
			return true;
		}
		
		try {			
			EObject result = getPrototype(eObj, progressMonitor);
			EObject prototype = registry.get(result);
			if (prototype == null) {
				return false; // Not there yet
			}
			EObject copy = EcoreUtil.copy(prototype);
			if (copy instanceof ModelElement) {
				((ModelElement) copy).setUuid(UUID.randomUUID().toString());
			}
			registry.put(eObj, copy); // Тriggers a new wave of wiring
			return true;				
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing selector: '" + prototypeExpr, e, asMarked(eObj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating selector: '" + prototypeExpr, e, asMarked(eObj));
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
	public final boolean wireModelElementSemanticSelector(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wireSemanticSelector(modelElement, registry, pass, progressMonitor);
	}
	
	/**
	 * Wires elements with semantic selector property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireTagSemanticSelector(
			org.nasdanika.drawio.model.Tag tag,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		return wireSemanticSelector(tag, registry, pass, progressMonitor);
	}	

	protected boolean wireSemanticSelector(
			EObject eObj,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String semanticSelectorPropertyName = getSemanticSelectorProperty();
		if (Util.isBlank(semanticSelectorPropertyName)) {
			return true;
		}
		String semanticSelector = getProperty(eObj, semanticSelectorPropertyName);
		if (Util.isBlank(semanticSelector)) {
			return true;
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(semanticSelector);
			EvaluationContext evaluationContext = createEvaluationContext();
			configureSemanticSelectorEvaluationContext(evaluationContext, registry, pass, progressMonitor);
			Object result = exp.getValue(evaluationContext, eObj, Object.class);
			if (result instanceof EObject) {
				registry.put(eObj, (EObject) result); 
				return true;				
			}
			if (result == null) {
				return true;
			}
			throw new ConfigurationException("Unexpected result type of semantic selector: '" + semanticSelector + "': " + result, asMarked(eObj));			
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing semantic selector: '" + semanticSelector, e, asMarked(eObj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating semantic selector: '" + semanticSelector, e, asMarked(eObj));
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
		
	protected EvaluationContext createEvaluationContext() {
		return new StandardEvaluationContext();
	}

	protected SpelExpressionParser getExpressionParser() {
		return new SpelExpressionParser();
	}	
		
	/**
	 * Wires document to the page element of the top level page.
	 * @param document
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireDocument(
			org.nasdanika.drawio.model.Document document,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		for (Page page: document.getPages()) {
			if (isTopLevelPage(page)) {
				TreeIterator<EObject> cit = page.eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof org.nasdanika.drawio.model.ModelElement) {
						org.nasdanika.drawio.model.ModelElement modelElement = (org.nasdanika.drawio.model.ModelElement) next;
						if (isPageElement(modelElement)) {
							EObject semanticElement = registry.get(modelElement);
							if (semanticElement == null) {
								return false; // Not there yet
							}
							registry.put(document, semanticElement);
							return true;
						}
					}
				}
			}
		}
		
		return true;
	}

	// --- Phase 1: Mapping features and adding representations ---
	
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
			S documentElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<EObject,EObject> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(document, registry, progressMonitor);
		}
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
	public final void addDocumentReprentations(
			org.nasdanika.drawio.model.Document document,
			S documentElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		if (documentElement instanceof org.nasdanika.ncore.ModelElement) {
			org.nasdanika.ncore.ModelElement documentModelElement = (org.nasdanika.ncore.ModelElement) documentElement;
			for (Page page: document.getPages()) {
				if (isTopLevelPage(page)) {
					addRepresentationPage(
							documentModelElement, 
							page, 
							registry, 
							progressMonitor);
					
				}
			}
		}
	}	
	
	/**
	 * Default base URI for the Drawio application to resolve library relative URL's.
	 */
	public static final URI DEFAULT_APP_BASE = URI.createURI("https://app.diagrams.net/");
	
	/**
	 * Application base for resolving relative image URL's. 
	 * This implementation returns DEFAULT_APP_BASE. 
	 * Override to customize for different (e.g. intranet) installations.
	 * App sources - https://github.com/jgraph/drawio/tree/dev/src/main/webapp.
	 * For the purposes of serving images and a diagram editor the web app can be deployed as a static site.
	 * It can also be deployed as a Docker container - https://www.drawio.com/blog/diagrams-docker-app, https://hub.docker.com/r/jgraph/drawio 
	 * @return
	 */
	protected URI getAppBase() {
		return DEFAULT_APP_BASE;
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
		
		if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
			// Page representation
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
			
			// Image
			EMap<String, String> style = drawioModelElement.getStyle();
			String image = style.get("image");
			if (!Util.isBlank(image)) {
				// Drawio does not add ;base64 to the image URL, browsers don't understand. Fixing it here.
				if (image.startsWith(DATA_URI_PNG_PREFIX_NO_BASE_64)) {
					int insertIdx = DATA_URI_PNG_PREFIX_NO_BASE_64.length() - 1;
					image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
				} else if (image.startsWith(DATA_URI_JPEG_PREFIX_NO_BASE_64)) {
					int insertIdx = DATA_URI_JPEG_PREFIX_NO_BASE_64.length() - 1;
					image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
				} else {
					URI imageURI = URI.createURI(image);
					if (imageURI.isRelative()) {
						URI appBase = getAppBase();
						if (appBase != null && !appBase.isRelative()) {
							imageURI = imageURI.resolve(appBase);
						}
					}
					image = imageURI.toString();
				}

				semanticModelElement.getRepresentations().put(IMAGE_REPRESENTATION,	image);					
			}
		}		
	}
	
	/**
	 * NOP method because drawio classes are not available here. Implemented in DocumentFilteringDrawioFactory.
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
	
	// --- Phase 2: mapping features of null semantic elements such as pass-through connections
	
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
	
	// --- Phase 3: Configuration ---
	
	/**
	 * Wires document configuration
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 3)
	public final void wireDocumentConfiguration(
			org.nasdanika.drawio.model.Document document,
			S documentElement,
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
			S documentElement,
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
			EObject eObj,
			ProgressMonitor progressMonitor) {
	
		String configPrototypePropertyName = getConfigPrototypeProperty();
		if (Util.isBlank(configPrototypePropertyName)) {
			return getPrototype(eObj, progressMonitor);
		}
		String configPrototypeExpr = getProperty(eObj, configPrototypePropertyName);
		if (Util.isBlank(configPrototypeExpr)) {
			return getPrototype(eObj, progressMonitor);
		}
		
		try {			
			ExpressionParser parser = getExpressionParser();
			Expression exp = parser.parseExpression(configPrototypeExpr);
			EvaluationContext evaluationContext = createEvaluationContext();
			configurePrototypeEvaluationContext(evaluationContext, progressMonitor);
			return exp.getValue(evaluationContext, eObj, EObject.class);
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing congfig prototype: '" + configPrototypeExpr, e, asMarked(eObj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating config prototype: '" + configPrototypeExpr, e, asMarked(eObj));
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
	
	@org.nasdanika.common.Transformer.Wire(phase = 3)
	public final void wireTagConfiguration(
			org.nasdanika.drawio.model.Tag tag,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		configureSemanticElement(
				tag,
				semanticElement,
				registry,
				false,
				progressMonitor);
	}
	
	/**
	 * Sets semantic identity if semantic element is instance of org.nasdanika.ncore.StringIdentity
	 * @param semanticElement
	 * @param semanticId
	 */
	protected void setSemanticId(S semanticElement, String semanticId, String elementId) {
		if (semanticElement instanceof org.nasdanika.ncore.StringIdentity) {
			org.nasdanika.ncore.StringIdentity semanticStringIdentity = (org.nasdanika.ncore.StringIdentity) semanticElement;
			if (Util.isBlank(semanticId)) {
				if (Util.isBlank(semanticStringIdentity.getId()) && !Util.isBlank(elementId)) {
					semanticStringIdentity.setId(elementId);
				}
			} else {
				semanticStringIdentity.setId(semanticId);
			}
		}
	}
	
	protected void addDocumentation(S semanticElement, EObject documentation) {
		if (semanticElement instanceof org.nasdanika.ncore.Documented) {
			((org.nasdanika.ncore.Documented) semanticElement).getDocumentation().add(documentation);
		}	
	}
	
	/**
	 * Sets label text extracted from the label HTML.
	 * @param semanticElement
	 * @param labelText
	 */
	protected void setLabelText(S semanticElement, String labelText) {
		if (semanticElement instanceof org.nasdanika.ncore.NamedElement) {
			((org.nasdanika.ncore.NamedElement) semanticElement).setName(labelText);
		}
	}

	protected void setTooltip(S semanticElement, String tooltip) {
		if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
			((org.nasdanika.ncore.ModelElement) semanticElement).setDescription(tooltip);
		}
	}	
	
	protected void configureSemanticElement(
			EObject eObj,
			S semanticElement,
			Map<EObject, EObject> registry,
			boolean isPrototype,
			ProgressMonitor progressMonitor) {
		
		EObject configPrototype = getConfigPrototype(eObj, progressMonitor);
		if (configPrototype instanceof org.nasdanika.drawio.model.ModelElement) {
			configureSemanticElement(
					(org.nasdanika.drawio.model.ModelElement) configPrototype, 
					semanticElement, 
					registry, 
					isPrototype, 
					progressMonitor);
		}

		if (semanticElement instanceof org.nasdanika.ncore.Marked && eObj instanceof org.nasdanika.ncore.Marked) {
			for (Marker marker: ((org.nasdanika.ncore.Marked) eObj).getMarkers()) {
				((org.nasdanika.ncore.Marked) semanticElement).getMarkers().add(EcoreUtil.copy(marker));
			}
		}
		
		// Indicates that this element is linked from another element and as such its id shall not be used as default semantic id
		boolean isLinked = eObj instanceof org.nasdanika.drawio.model.ModelElement && isPageElement(eObj) && !isTopLevelPage(((org.nasdanika.drawio.model.ModelElement) eObj).getPage());
		
		if (!isPrototype && !isLinked) {
			String semanticIdProperty = getSemanticIdProperty();
			if (!Util.isBlank(semanticIdProperty)) {
				String semanticId = getProperty(eObj, semanticIdProperty);
				String elementId = null;
				if (eObj instanceof org.nasdanika.drawio.model.ModelElement) {
					elementId = ((org.nasdanika.drawio.model.ModelElement) eObj).getId();
				}
				setSemanticId(semanticElement, semanticId, elementId);
			}
		}
		
		if (eObj instanceof org.nasdanika.drawio.model.ModelElement) {
			String label = ((org.nasdanika.drawio.model.ModelElement) eObj).getLabel();
			if (!Util.isBlank(label)) {
				String labelText = Jsoup.parse(label).text();
				setLabelText(semanticElement, labelText);				
			}
			String tooltip = ((org.nasdanika.drawio.model.ModelElement) eObj).getTooltip();
			if (!Util.isBlank(tooltip)) {
				setTooltip(semanticElement, tooltip);
			}
		}
				
		URI baseUri = getBaseURI(eObj);
		String docProperty = getDocumentationProperty();
		if (!Util.isBlank(docProperty)) {
			String doc = getProperty(eObj, docProperty);
			if (!Util.isBlank(doc)) {
				DocumentationFormat docFormat = DocumentationFormat.markdown;
				String docFormatProperty = getDocFormatProperty();
				if (!Util.isBlank(docFormatProperty)) {
					String docFormatStr = getProperty(eObj, docFormatProperty);
					if (!Util.isBlank(docFormatStr)) {
						docFormat = DocumentationFormat.valueOf(docFormatStr);						
					}					
				}
				switch (docFormat) {
				case html:
					addDocumentation(semanticElement, createHtmlDoc(doc, baseUri, progressMonitor));
					break;
				case markdown:
					addDocumentation(semanticElement, createMarkdownDoc(doc, baseUri, progressMonitor));
					break;
				case text:
					addDocumentation(semanticElement, createTextDoc(doc, baseUri, progressMonitor));
					break;
				default:
					throw new ConfigurationException("Unsupported documentation format: " + docFormat, eObj instanceof Marked ? (Marked) eObj : null);
				
				}
			}
		}
		
		String docRefProperty = getDocRefProperty();
		if (!Util.isBlank(docRefProperty)) {
			String docRefStr = getProperty(eObj, docRefProperty);
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
					String docFormatStr = getProperty(eObj, docFormatProperty);
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
					addDocumentation(semanticElement, createHtmlDoc(docRefURI, progressMonitor));
					break;
				case markdown:
					addDocumentation(semanticElement, createMarkdownDoc(docRefURI, progressMonitor));
					break;
				case text:
					addDocumentation(semanticElement, createTextDoc(docRefURI, progressMonitor));
					break;
				default:
					throw new ConfigurationException("Unsupported documentation format: " + docFormat, eObj instanceof Marked ? (Marked) eObj : null);					
				}
			}
		}
				
		// Root is logically "merged" with the containing page
		if (eObj instanceof Root) {
			Page page = (Page) eObj.eContainer().eContainer();
			if (semanticElement instanceof org.nasdanika.ncore.NamedElement) {
				org.nasdanika.ncore.NamedElement namedSemanticElement = (org.nasdanika.ncore.NamedElement) semanticElement;
				if (Util.isBlank(namedSemanticElement.getName())) {
					namedSemanticElement.setName(page.getName());
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
