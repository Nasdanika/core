package org.nasdanika.mapping;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
//import org.jsoup.Jsoup;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DocumentationFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SourceRecord;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.util.NcoreUtil;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeLocator;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.ConfigurationException;

/**
 * Base class for classes which map/transform Drawio diagrams to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 * @param <S> Semantic element type
 */
public abstract class AbstractMappingFactory<S> {
	
	private static final String DOC_FORMAT_PROPERTY = "doc-format";
	private static final String DOC_REF_PROPERTY = "doc-ref";
	private static final String DOCUMENTATION_PROPERTY = "documentation";
	private static final String SEMANTIC_ID_PROPERTY = "semantic-id";
	private static final String REF_SUFFIX = "-ref";
	private static final String TYPE_PROPERTY = "type";
	private static final String TAG_SPEC_PROPERTY = "tag-spec";
	private static final String TOP_LEVEL_PAGE_PROPERTY = "top-level-page";
	private static final String SEMANTIC_MAPPING_PROPERTY = "semantic-mapping";
	private static final String PASS_KEY = "pass";
	private static final String ARGUMENTS_KEY = "arguments";
	private static final String ITERATOR_KEY = "iterator";
	private static final String SELECTOR_KEY = "selector";
	public static final String DRAWIO_REPRESENTATION = "drawio";
	public static final String IMAGE_REPRESENTATION = "image";
	
	private static final String DATA_URI_PNG_PREFIX_NO_BASE_64 = "data:image/png,";
	private static final String DATA_URI_JPEG_PREFIX_NO_BASE_64 = "data:image/jpeg,";
	
	protected CapabilityLoader capabilityLoader;
	
	public AbstractMappingFactory() {
		this(new CapabilityLoader());
	}
	
	public AbstractMappingFactory(CapabilityLoader capabilityLoader) {
		this.capabilityLoader = capabilityLoader;
	}
		
	protected CapabilityLoader getCapabilityLoader() {
		return capabilityLoader;
	}	

	public String getPropertyNamespace() {
		return "";
	}
	
	protected String getTopLevelPageProperty() {
		return getPropertyNamespace() + TOP_LEVEL_PAGE_PROPERTY;
	}	
	
	protected String getTagSpecPropertyName() {
		return getPropertyNamespace() + TAG_SPEC_PROPERTY;
	}

	protected String getRefSuffix() {
		return REF_SUFFIX;
	}
	
	/**
	 * Semantic ID is used instead of the Drawio element ID if provided. E.g. "my-process" instead of "HP_1aCX3hRniy5izKEYK"
	 * While it is possible to edit Drawio element ID's, it shall be done with care to avoid introduction of duplicate ID's. 
	 * Semantic ID's shall be unique within their containment collection.   
	 * @return
	 */
	protected String getSemanticIdProperty() {
		return getPropertyNamespace() + SEMANTIC_ID_PROPERTY;
	}	
	
	protected String getDocumentationProperty() {
		return getPropertyNamespace() + DOCUMENTATION_PROPERTY;
	}	
		
	protected String getDocRefProperty() {
		return getPropertyNamespace() + DOC_REF_PROPERTY;
	}	
	
	protected String getDocFormatProperty() {
		return getPropertyNamespace() + DOC_FORMAT_PROPERTY; 
	}	
	
	protected abstract Marked asMarked(S obj);
	
	/**
	 * Property containing domain class name.
	 * The name can be simple <code>class  name</code>, qualified <code>package name.class name</code>, or be a URI <code>package namespace uri#//class name</code>  
	 * @return
	 */
	protected String getTypeProperty() {
		return getPropertyNamespace() + TYPE_PROPERTY;
	}	
	
	public abstract URI getBaseURI(S obj);
	
	/**
	 * Returns object property. This implementation uses ModelElement.getProperties().get() for instances of model element. 
	 * Returns null otherwise.
	 * @param eObj
	 * @return
	 */
	protected abstract String getProperty(S obj, String property);
	
	/**
	 * Loads source from a property or from a URL specified by refProperty
	 * @param source
	 * @param property
	 * @param refProperty
	 * @return
	 */
	protected SourceRecord loadSource(S obj, String property, String refProperty) {
		URI baseURI = getBaseURI(obj);
		if (!Util.isBlank(property)) {
			String source = getProperty(obj, property);
			if (!Util.isBlank(source)) {
				return new SourceRecord(baseURI, source);
			}
		}
		
		if (!Util.isBlank(refProperty)) {
			String ref = getProperty(obj, refProperty);
			if (!Util.isBlank(ref)) {
				URI refURI = URI.createURI(ref);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}
				try {
					DefaultConverter converter = DefaultConverter.INSTANCE;
					Reader reader = converter.toReader(refURI);
					return new SourceRecord(refURI, converter.toString(reader));
				} catch (IOException e) {
					throw new ConfigurationException("Error loading source from " + refURI, e, asMarked(obj));
				}
			}
		}
		return null;
	}
	
	protected SourceRecord loadSource(S obj, String property) {
		if (Util.isBlank(property)) {
			return null;
		}
		return loadSource(obj, property, property + getRefSuffix());
	}
		
	/**
	 * A map of top level package aliases to {@link EPackage}
	 * @return
	 */
	protected abstract Map<String,EPackage> getEPackages();
		
	public EClassifier getType(String type, S source) {
		return NcoreUtil.getType(type, getEPackages(), msg -> new ConfigurationException(msg, asMarked(source)));
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
	
	protected SetterFeatureMapper<S, EObject> mapper = new PropertySetterFeatureMapper<S, EObject>() {

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
		protected EvaluationContext createEvaluationContext(EObject context) {
			return AbstractDrawioFactory.this.createEvaluationContext(context);
		}
		
		@Override
		protected SpelExpressionParser createExpressionParser(EObject context) {
			return AbstractDrawioFactory.this.createExpressionParser(context);
		}
		
		@Override
		protected ClassLoader getClassLoader(EObject source) {
			return AbstractDrawioFactory.this.getClassLoader(source);
		}
		
		@Override
		protected Iterable<Entry<String, Object>> getVariables(EObject context) {
			return AbstractDrawioFactory.this.getVariables(context);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public Iterable<EObject> select(EObject source, Map<EObject, EObject> registry, ProgressMonitor progressMonitor) {
			String referenceProperty = getReferenceProperty();
			if (!Util.isBlank(referenceProperty)) {		
				String referenceSpec = getProperty(source, referenceProperty);			
				if (!Util.isBlank(referenceSpec)) {
					List<EObject> ret = new ArrayList<>();
					ReferenceMapper referenceMapper = new ReferenceMapper(referenceSpec, source);
					List<EObject> logicalAncestorsPath = new ArrayList<>();
					for (EObject logicalAncestor = getLogicalParent(source); logicalAncestor != null; logicalAncestor = getLogicalParent(logicalAncestor)) {
						logicalAncestorsPath.add(logicalAncestor);					
						for (EObject logicalAncestorSemanticElement: mapper.select(logicalAncestor, registry, progressMonitor)) {						
							if (referenceMapper.matchLogicalAncestorSemanticelement(logicalAncestorSemanticElement, logicalAncestorsPath, registry, source)) {
								EObject refObj = referenceMapper.getLogicalAncestorSemanticElementRefObj(logicalAncestorSemanticElement, logicalAncestorsPath, registry, source); 
								if (refObj != null) {
									EClass eClass = refObj.eClass();
									String referenceName = referenceMapper.getReferenceName();
									EStructuralFeature feature = eClass.getEStructuralFeature(referenceName);
									if (feature == null) {
										throw new ConfigurationException("Feature " + referenceName + " not found in " + eClass.getName(), asMarked(source)); 
									} else if (feature instanceof EReference) {
										Object featureValue = refObj.eGet(feature);
										if (feature.isMany()) {
											ret.addAll((Collection<EObject>) featureValue);
										} else if (featureValue instanceof EObject) {
											ret.add((EObject) featureValue);
										}
									} else {
										throw new ConfigurationException("Not a reference: " + referenceName + " in " + eClass.getName(), null, asMarked(source)); 									
									}
								}
							}
							Comparator<Object> comparator = referenceMapper.getComparator(logicalAncestorSemanticElement, registry);
							if (comparator != null) {
								ret.sort(comparator);
							}
						}
					}					
					return ret;
				}
			}
			
			return super.select(source, registry, progressMonitor);
		}
		
	};
	
	/**
	 * Override to return content mappers for different passes. 
	 * This implementation returns {@link PropertySetterFeatureMapper}.
	 * @param pass
	 * @return
	 */
	protected Mapper<EObject, EObject> getMapper(int pass) {
		return mapper;
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

	@org.nasdanika.common.Transformer.Factory
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
	
		S semanticElement = null;		
		String initializerPropertyName = getInitializerProperty();
		String initializer = Util.isBlank(initializerPropertyName) ? null : getProperty(eObj, initializerPropertyName);
		if (!Util.isBlank(initializer)) {
			try {			
				ExpressionParser parser = createExpressionParser(eObj);
				Expression exp = parser.parseExpression(initializer);
				EvaluationContext evaluationContext = createEvaluationContext(eObj);
				configureInitializerEvaluationContext(evaluationContext, registry, progressMonitor);
				semanticElement = (S) exp.getValue(evaluationContext, eObj, EObject.class);
			} catch (ParseException e) {
				throw new ConfigurationException("Error parsing semantic selector: '" + initializer, e, asMarked(eObj));
			} catch (EvaluationException e) {
				throw new ConfigurationException("Error evaluating semantic selector: '" + initializer, e, asMarked(eObj));
			}
		}
		
		if (semanticElement == null) {
			String type = getTypeName(eObj);		
		
			if (!Util.isBlank(type)) {
				semanticElement = (S) create(type.trim(), eObj);
			}
		}
		
		if (semanticElement instanceof ModelElement) {
			ModelElement sme = (ModelElement) semanticElement;
			if (Util.isBlank(sme.getUuid())) {
				sme.setUuid(UUID.randomUUID().toString());
			}
		}
		
		semanticElement = executeInitializerScript(eObj, semanticElement, registry, progressMonitor);
		
		if (semanticElement instanceof MinimalEObjectImpl && isRefIdProxyURI()) {
			String refIdPropertyName = getRefIdProperty();
			if (!Util.isBlank(refIdPropertyName)) {
				String refId = getProperty(eObj, refIdPropertyName);
				if (!Util.isBlank(refId)) {
					URI refIdURI = URI.createURI(refId);
					if (refIdURI.isRelative()) {
						URI baseURI = getBaseURI(eObj);
						if (baseURI != null) {
							refIdURI = refIdURI.resolve(baseURI);
						}
					}
					((MinimalEObjectImpl) semanticElement).eSetProxyURI(refIdURI);					
				}
			}
		}
		
		return semanticElement;
	}
	
	/**
	 * @return true if ref-id shall be treated as proxy URI. 
	 */
	protected boolean isRefIdProxyURI() {
		return false;
	}
		
	protected String getInitializerScriptPropertyName() {
		return getPropertyNamespace() + "initializer-script";
	}
		
	protected void configureInitializerScriptEngine(
			ScriptEngine engine,
			EObject diagramElement,
			EObject semanticElement,
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		engine.put("diagramElement", diagramElement);
		engine.put("semanticElement", diagramElement);
		engine.put("registry", registry);
		engine.put("baseURI", getBaseURI(diagramElement));
		engine.put("logicalParent", getLogicalParent(diagramElement));
		for (Entry<String, Object> ve: getVariables(diagramElement)) {
			engine.put(ve.getKey(), ve.getValue());
		}
	}		
	
	/**
	 * Initializer expression
	 * @return
	 */
	protected String getInitializerProperty() {
		return getPropertyNamespace() + "initializer";
	}

	protected void configureInitializerEvaluationContext(
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
	protected abstract S getByRefId(
			EObject eObj,
			String refId, 
			int pass, 
			Map<EObject, EObject> registry);
	
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
		
		S refTarget = getByRefId(eObj, refId, pass, registry);
		if (refTarget != null) {
			registry.put(eObj, refTarget); // Resolved refId triggers a new wave of wiring
		}
		return true;
	}
		
	protected String getSelectorProperty() {
		return getPropertyNamespace() + SELECTOR_KEY;
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
	public final boolean wireModelElementLinkTarget(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		LinkTarget linkTarget = modelElement.getLinkTarget();
		if (linkTarget instanceof org.nasdanika.drawio.model.ModelElement) {
			EObject semanticTarget = registry.get(linkTarget);
			if (semanticTarget == null) {
				return false; // Not there yet
			}
			registry.put(modelElement, semanticTarget); // Тriggers a new wave of wiring
		}
		
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
			ExpressionParser parser = createExpressionParser(eObj);
			Expression exp = parser.parseExpression(selector);
			EvaluationContext evaluationContext = createEvaluationContext(eObj);
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
		evaluationContext.setVariable(PASS_KEY, pass);
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
			ExpressionParser parser = createExpressionParser(eObj);
			Expression exp = parser.parseExpression(prototypeExpr);
			EvaluationContext evaluationContext = createEvaluationContext(eObj);
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
			ExpressionParser parser = createExpressionParser(eObj);
			Expression exp = parser.parseExpression(semanticSelector);
			EvaluationContext evaluationContext = createEvaluationContext(eObj);
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
		evaluationContext.setVariable(PASS_KEY, pass);
		evaluationContext.setVariable("progressMonitor", progressMonitor);
	}
		
	protected EvaluationContext createEvaluationContext(EObject context) {
		StandardEvaluationContext ret = new StandardEvaluationContext();
		ClassLoader classLoader = getClassLoader(context);
		if (classLoader != null) {
			ret.setTypeLocator(new StandardTypeLocator(classLoader));
		}
		for (Entry<String, Object> ve: getVariables(context)) {
			ret.setVariable(ve.getKey(), ve.getValue());
		}
		return ret;
	}
	
	/**
	 * Override to provide variables for expressions and scripts.
	 * 
	 * @return
	 */
	protected Iterable<Map.Entry<String, Object>> getVariables(EObject context) {
		return Collections.emptySet();
	}
	
	protected SpelExpressionParser createExpressionParser(EObject context) {
		SpelParserConfiguration config = new SpelParserConfiguration(null, getClassLoader(context));
		return new SpelExpressionParser(config);
	}	
	
	/**
	 * Returns a {@link ClassLoader} for an object (diagram element). 
	 * This implementation delegates to logical parent and returns this class' classLoader for the root object.
	 * Classloader returned by this method is used to create SpEL parser and to execute scripts.
	 * Override to implement object-specific classloaders. For example, load Maven POM from a property value or resource and build a classloader from dependencies:
	 * 
	 *  https://maven.apache.org/resolver/maven-resolver-api/index.html, 
	 *  https://stackoverflow.com/questions/11799923/programmatically-resolving-maven-dependencies-outside-of-a-plugin-get-reposito,
	 *  https://mvnrepository.com/artifact/org.apache.maven.resolver/maven-resolver-api, 
	 *  https://wiki.eclipse.org/Aether, 
	 *  https://github.com/yahro/maven-classloader/blob/master/src/main/java/com/bigfatgun/MavenClassLoader.java - old code, needs to be refactored.
	 * @param context
	 * @return
	 */
	protected ClassLoader getClassLoader(EObject context) {
		EObject logicalParent = getLogicalParent(context);
		if (logicalParent == null) {
			return Thread.currentThread().getContextClassLoader();
		}
		return getClassLoader(logicalParent);
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
	
	// --- Phase 1: Mapping "reference" elements
	
	protected String getReferenceProperty() {
		return getPropertyNamespace() + "reference";
	}
	
	protected class ReferenceMapper {
		
		private static final String NAME_KEY = "name";
		private static final String CONDITION_KEY = "condition";
		private static final String EXPRESSION_KEY = "expression";
		private static final String ELEMENT_CONDITION_KEY = "element-condition";
		private static final String ELEMENT_EXPRESSION_KEY = "element-expression";
		
		private static final String REGISTRY_VAR = "registry";
		private static final String SOURCE_PATH_VAR = "sourcePath";
		
		private Map<?,?> spec;
		
		private String referenceName;
		private EObject context;
		
		public ReferenceMapper(String specYaml, EObject context) {
			Yaml yaml = new Yaml();
			Object specObj = yaml.load(specYaml);
			if (specObj instanceof Map) {			
				this.spec =  (Map<?,?>) specObj;
			} else if (specObj instanceof String) {
				 this.spec =  Collections.singletonMap(NAME_KEY, specObj);			
			} else {						
				throw new ConfigurationException("Usupported reference configuration type: " + specObj, asMarked(context));
			}
			
			Object rName = this.spec.get(NAME_KEY);
			if (rName instanceof String) {
				this.referenceName = (String) rName;
			} else {
				throw new ConfigurationException("Reference name is not a string: " + specObj, asMarked(context));				
			}
			this.context = context;
			
		}
		
		public Comparator<Object> getComparator(EObject semanticElement, Map<EObject, EObject> registry) {
			return mapper.getComparator(semanticElement, spec, registry, context);
			
		}
		
		public String getReferenceName() {
			return referenceName;
		}

		public boolean matchLogicalAncestorSemanticelement(
				EObject logicalAncestorSemanticElement, 
				List<EObject> logicalAncestorPath,
				Map<EObject, EObject> registry,
				EObject context) {
			
			Object cObj = spec.get(CONDITION_KEY);
			if (cObj == null) {
				return logicalAncestorSemanticElement != null; // Shall always be true?
			}
			
			if (cObj instanceof String) {
				mapper.evaluatePredicate(
						logicalAncestorSemanticElement, 
						(String) cObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, logicalAncestorPath)), 
						context);
			}
			
			throw new ConfigurationException("Usupported reference condition type: " + cObj, asMarked(context));		
		}
		
		public EObject getLogicalAncestorSemanticElementRefObj(
				EObject logicalAncestorSemanticElement, 
				List<EObject> logicalAncestorPath,
				Map<EObject, EObject> registry, 
				EObject context) {
			
			Object eObj = spec.get(EXPRESSION_KEY);
			if (eObj == null) {
				return logicalAncestorSemanticElement;
			}
			
			if (eObj instanceof String) {
				mapper.evaluate(
						logicalAncestorSemanticElement, 
						(String) eObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, logicalAncestorPath)), 
						EObject.class,
						context);
			}
			
			throw new ConfigurationException("Usupported reference expression type: " + eObj, asMarked(context));		
		}
		
		private boolean matchContentsSemanticelement(
				EObject contentsSemanticElement, 
				List<EObject> sourcePath,
				Map<EObject, EObject> registry,
				EObject context) {
			
			Object cObj = spec.get(ELEMENT_CONDITION_KEY);
			if (cObj == null) {
				return sourcePath.size() == 2; // Immediate children
			}
			
			if (cObj instanceof String) {
				mapper.evaluatePredicate(
						contentsSemanticElement, 
						(String) cObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, sourcePath)), 
						context);
			}
			
			throw new ConfigurationException("Usupported reference element condition type: " + cObj, asMarked(context));		
		}
		
		private EObject getContentsSemanticElementRefObj(
				EObject contentsSemanticElement, 
				List<EObject> sourcePath,
				Map<EObject, EObject> registry, 
				EObject context) {
			
			Object eObj = spec.get(ELEMENT_EXPRESSION_KEY);
			if (eObj == null) {
				return contentsSemanticElement;
			}
			
			if (eObj instanceof String) {
				mapper.evaluate(
						contentsSemanticElement, 
						(String) eObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, sourcePath)), 
						EObject.class,
						context);
			}
			
			throw new ConfigurationException("Usupported reference element expression type: " + eObj, asMarked(context));		
		}
				
		public List<EObject> getElements(
				LinkedList<EObject> sourcePath,
				Map<EObject, EObject> registry, 
				Predicate<EObject> tracker,
				EObject context,
				ProgressMonitor progressMonitor) {
			
			List<EObject> ret = new ArrayList<>();			
			for (EObject childSource: mapper.contents(sourcePath.getLast(), tracker)) {
				sourcePath.add(childSource);
				for (EObject childSemanticElement: mapper.select(childSource, registry, null)) {
					if (matchContentsSemanticelement(childSemanticElement, sourcePath, registry, context)) {
						EObject refObj = getContentsSemanticElementRefObj(childSemanticElement, sourcePath, registry, context);
						if (refObj != null) {
							ret.add(refObj);
						}
					}
				}
				ret.addAll(getElements(sourcePath, registry, tracker, context, progressMonitor));
				sourcePath.removeLast();				
			}
			
			return ret;
		}		
		
	}
			
	@SuppressWarnings("unchecked")
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 1)
	public final void wireSemanticReferences(
			org.nasdanika.drawio.model.Layer drawioModelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		String referenceProperty = getReferenceProperty();
		if (!Util.isBlank(referenceProperty)) {		
			String referenceSpec = getProperty(drawioModelElement, referenceProperty);			
			if (!Util.isBlank(referenceSpec)) {
				ReferenceMapper referenceMapper = new ReferenceMapper(referenceSpec, drawioModelElement);
				List<EObject> logicalAncestorsPath = new ArrayList<>();
				Z: for (EObject logicalAncestor = getLogicalParent(drawioModelElement); logicalAncestor != null; logicalAncestor = getLogicalParent(logicalAncestor)) {
					logicalAncestorsPath.add(logicalAncestor);					
					for (EObject logicalAncestorSemanticElement: mapper.select(logicalAncestor, registry, progressMonitor)) {						
						if (referenceMapper.matchLogicalAncestorSemanticelement(logicalAncestorSemanticElement, logicalAncestorsPath, registry, drawioModelElement)) {
							EObject refObj = referenceMapper.getLogicalAncestorSemanticElementRefObj(logicalAncestorSemanticElement, logicalAncestorsPath, registry, drawioModelElement); 
							if (refObj != null) {
								EClass eClass = refObj.eClass();
								String referenceName = referenceMapper.getReferenceName();
								EStructuralFeature feature = eClass.getEStructuralFeature(referenceName);
								if (feature == null) {
									throw new ConfigurationException("Feature " + referenceName + " not found in " + eClass.getName(), drawioModelElement); 
								} else if (feature instanceof EReference) {							
									LinkedList<EObject> sourcePath = new LinkedList<EObject>();
									sourcePath.add(drawioModelElement);
									Comparator<Object> comparator = referenceMapper.getComparator(logicalAncestorSemanticElement, registry);
									for (EObject semanticElement: referenceMapper.getElements(sourcePath, registry, new HashSet<>()::add, drawioModelElement, progressMonitor)) {
										if (semanticElement != null && feature.getEType().isInstance(semanticElement)) {
											if (feature.isMany()) {
												List<EObject> fvl = (List<EObject>) logicalAncestorSemanticElement.eGet(feature);
												if (comparator == null || fvl.isEmpty()) {										
													fvl.add(semanticElement);
												} else {
													// Iterating and comparing
													boolean added = false;
													for (int i = 0; i < fvl.size(); ++i) {
														Object fvle = fvl.get(i);
														if (comparator.compare(semanticElement, fvle) < 0) {
															fvl.add(i, semanticElement);
															added = true;
															break;
														}
													}
													if (!added) {
														fvl.add(semanticElement);
													}
												}
											} else {
												logicalAncestorSemanticElement.eSet(feature, semanticElement);										
											}									
										}
									}
								} else {
									throw new ConfigurationException("Not a reference: " + referenceName + " in " + eClass.getName(), drawioModelElement); 									
								}
								break Z;								
							}
						}
					}
				}
			}
		}
	}

	// --- Phase 2: Mapping features and adding representations ---
	
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
	@org.nasdanika.common.Transformer.Wire(phase = 2)
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
	@org.nasdanika.common.Transformer.Wire(phase = 2)
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
		
	@org.nasdanika.common.Transformer.Wire(phase = 2)
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
			LinkTarget linkTarget = drawioModelElement.getLinkTarget();
			if (linkTarget instanceof Page) {
				addRepresentationPage(
						semanticModelElement, 
						(Page) linkTarget, 
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
	
	// --- Phase 3: mapping features of null semantic elements such as pass-through connections
	
	/**
	 * Feature maps null semantic elements, which is needed for connections as they might be pass-through.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 3)
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
	
	// --- Phase 4: Configuration ---
	
	/**
	 * Wires document configuration
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 4)
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
			ExpressionParser parser = createExpressionParser(eObj);
			Expression exp = parser.parseExpression(configPrototypeExpr);
			EvaluationContext evaluationContext = createEvaluationContext(eObj);
			configurePrototypeEvaluationContext(evaluationContext, progressMonitor);
			return exp.getValue(evaluationContext, eObj, EObject.class);
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing congfig prototype: '" + configPrototypeExpr, e, asMarked(eObj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating config prototype: '" + configPrototypeExpr, e, asMarked(eObj));
		}
	}		
	
	@org.nasdanika.common.Transformer.Wire(phase = 4)
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
	
	@org.nasdanika.common.Transformer.Wire(phase = 4)
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
	
	protected void addDocumentation(S semanticElement, Collection<EObject> documentation) {
		if (semanticElement instanceof org.nasdanika.ncore.Documented) {
			((org.nasdanika.ncore.Documented) semanticElement).getDocumentation().addAll(documentation);
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
	
	private Collection<DocumentationFactory> documentationFactories;
	
	protected Collection<DocumentationFactory> getDocumentationFactories(ProgressMonitor progressMonitor) {
		if (documentationFactories == null) {
			documentationFactories = new ArrayList<>();
			if (capabilityLoader != null) {
				Requirement<Object, DocumentationFactory> requirement = ServiceCapabilityFactory.createRequirement(DocumentationFactory.class);
				Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(requirement, progressMonitor);
				for (CapabilityProvider<Object> cp: cpi) {				
					cp.getPublisher().subscribe(df -> documentationFactories.add((DocumentationFactory) df));
				}
			}
		}
		return documentationFactories;
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
		boolean isLinked = eObj instanceof org.nasdanika.drawio.model.ModelElement
				&& isPageElement(eObj)
				&& !isTopLevelPage(((org.nasdanika.drawio.model.ModelElement) eObj).getPage());
		
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
				String[] docFormatStr = { null };
				String docFormatProperty = getDocFormatProperty();
				if (!Util.isBlank(docFormatProperty)) {
					docFormatStr[0] = getProperty(eObj, docFormatProperty);
				}
				if (Util.isBlank(docFormatStr[0])) {
					docFormatStr[0] = "markdown";
				}
				
				Optional<DocumentationFactory> dfo = getDocumentationFactories(progressMonitor)
					.stream()
					.filter(df -> df.canHandle(docFormatStr[0]))
					.findAny();
				
				if (dfo.isPresent()) {
					addDocumentation(semanticElement, dfo.get().createDocumentation(doc, baseUri, progressMonitor));
				} else {
					throw new ConfigurationException("Unsupported documentation format: " + docFormatStr, eObj instanceof Marked ? (Marked) eObj : null);
				}
			}
		}
		
		String docRefProperty = getDocRefProperty();
		if (!Util.isBlank(docRefProperty)) {
			String docRefStr = getProperty(eObj, docRefProperty);
			if (!Util.isBlank(docRefStr)) {					
				String docFormatProperty = getDocFormatProperty();
				DocumentationFactory docFactory = null;
				if (Util.isBlank(docFormatProperty)) {
					String docFormatStr = getProperty(eObj, docFormatProperty);
					if (!Util.isBlank(docFormatStr)) {
						Optional<DocumentationFactory> dfo = getDocumentationFactories(progressMonitor)
								.stream()
								.filter(df -> df.canHandle(docFormatStr))
								.findAny();
						if (dfo.isPresent()) {
							docFactory = dfo.get();
						} else {
							throw new ConfigurationException("Unsupported documentation format: " + docFormatStr, eObj instanceof Marked ? (Marked) eObj : null);												
						}
					}
				}
				URI[] docRefURI = { URI.createURI(docRefStr) };
				if (baseUri != null && !baseUri.isRelative()) {
					docRefURI[0] = docRefURI[0].resolve(baseUri);
				}
				if (docFactory == null) {
					Optional<DocumentationFactory> dfo = getDocumentationFactories(progressMonitor)
							.stream()
							.filter(df -> df.canHandle(docRefURI[0]))
							.findAny();		
					
					if (dfo.isPresent()) {
						docFactory = dfo.get();
					} else {
						throw new ConfigurationException("Unsupported documentation URI: " + docRefURI, eObj instanceof Marked ? (Marked) eObj : null);												
					}
				}
				
				addDocumentation(semanticElement, docFactory.createDocumentation(docRefURI[0], progressMonitor));				
			}
		}
				
		// Root is logically "merged" with the containing page
		if (eObj instanceof Root) {
			Page page = (Page) eObj.eContainer().eContainer();
			setPageName(semanticElement, page.getName());
		}
		
	}
	
	protected void setPageName(S semanticElement, String pageName) {
		if (semanticElement instanceof org.nasdanika.ncore.NamedElement) {
			org.nasdanika.ncore.NamedElement namedSemanticElement = (org.nasdanika.ncore.NamedElement) semanticElement;
			if (Util.isBlank(namedSemanticElement.getName())) {
				namedSemanticElement.setName(pageName);
			}
		}		
	}
//		
//	protected abstract EObject createHtmlDoc(String doc, URI baseUri, ProgressMonitor progressMonitor);
//	
//	protected abstract EObject createTextDoc(String doc, URI baseUri, ProgressMonitor progressMonitor);
//	
//	protected abstract EObject createMarkdownDoc(String doc, URI baseUri, ProgressMonitor progressMonitor);
//	
//	protected abstract EObject createHtmlDoc(URI docRef, ProgressMonitor progressMonitor);
//	
//	protected abstract EObject createTextDoc(URI docRef, ProgressMonitor progressMonitor);
//	
//	protected abstract EObject createMarkdownDoc(URI docRef, ProgressMonitor progressMonitor);
	
	// --- Phase 5: Operations
		
	protected String getOperationMapPropertyName() {
		return getPropertyNamespace() + "operation-map";
	}
	
	/**
	 * Invokes {@link EOperation}s of the semantic element. 	
	 * @param diagramElement
	 * @param semanticElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 5)
	public final boolean mapOperations(
			EObject diagramElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		boolean result = true;
		SourceRecord operationMap = loadSource(semanticElement, getOperationMapPropertyName());
		if (operationMap != null) {
			try {
				Yaml yaml = new Yaml();
				Object opMapObj = yaml.load(operationMap.source());
				if (opMapObj instanceof Map) {
					Map<?,?> opMap = (Map<?,?>) opMapObj;
					EClass eClass = semanticElement.eClass();
					for (EOperation eOperation: eClass.getEAllOperations()) {
						Object opSpec = opMap.get(eOperation.getName());
						if (opSpec instanceof Map) {
							opSpec = Collections.singleton(opSpec); // Wrapping into a list to simplify further logic
						}
						
						if (opSpec instanceof Iterable) {
							OSE: for (Object opSpecElement: (Iterable<?>) opSpec) {
								if (opSpecElement instanceof Map) {
									Map<?,?> opSpecElementMap = (Map<?,?>) opSpecElement;
									org.nasdanika.persistence.Util.checkUnsupportedKeys(
											opSpecElementMap, 
											SELECTOR_KEY, 
											ITERATOR_KEY, 
											ARGUMENTS_KEY,
											PASS_KEY);
									
									Object passObj = opSpecElementMap.get(PASS_KEY);
									if (passObj instanceof Number) {
										int passNum = ((Number) passObj).intValue();
										if (passNum > pass) {
											result = false; // This op shall be invoked later
										} else if (passNum < pass) {
											continue OSE; // Already invoked if matched
										}
									} else if (passObj != null) {
										throw new ConfigurationException("Usupported operation pass type: " + passObj, asMarked(diagramElement));																																				
									}
									
									Map<String,String> argMap = new HashMap<>(); 
									Object arguments = opSpecElementMap.get(ARGUMENTS_KEY);
									if (arguments instanceof Map) {
										for (Entry<?, ?> ae: ((Map<?,?>) arguments).entrySet()) {
											Object key = ae.getKey();
											if (key instanceof String) {
												boolean hasParameter = false;
												for (EParameter prm: eOperation.getEParameters()) {
													if (prm.getName().equals(key)) {
														hasParameter = true;
														break;
													}
												}
												
												if (!hasParameter) {
													continue OSE;
												}
												
												Object val = ae.getValue();
												if (val instanceof String) {
													argMap.put((String) key, (String) val);
												} else {
													throw new ConfigurationException("Usupported operation argument expression type: " + val, asMarked(diagramElement));																																																													
												}
											} else {
												throw new ConfigurationException("Usupported operation parameter name type: " + key, asMarked(diagramElement));																																																
											}
										}
									} else {
										throw new ConfigurationException("Usupported operation arguments type: " + arguments, asMarked(diagramElement));																																				
									}																		
									
									Object selector = opSpecElementMap.get(SELECTOR_KEY);
									if (selector instanceof String) {
										if (!mapper.evaluatePredicate(eOperation, (String) selector, null, diagramElement)) {
											continue;
										}
									} else if (selector != null) {
										throw new ConfigurationException("Usupported operation selector type: " + selector, asMarked(diagramElement));																										
									}
									
									Iterator<?> it = Collections.singleton(diagramElement).iterator();
									
									Object iterator = opSpecElementMap.get(ITERATOR_KEY);
									if (iterator instanceof String) {										
										Object itVal = mapper.evaluate(
												diagramElement, 
												(String) iterator,												
												Map.of("registry", registry),
												Object.class,
												diagramElement);
										
										if (itVal instanceof Iterator) {
											it = (Iterator<?>) itVal;
										} else if (itVal instanceof Iterable) {
											it = ((Iterable<?>) itVal).iterator();
										} else if (itVal instanceof Stream) {
											it = ((Stream<?>) itVal).iterator();
										} else if (itVal == null) {
											it = Collections.emptyIterator();
										} else if (itVal.getClass().isArray()) {
											Collection<Object> values = new ArrayList<>();
											for (int i = 0; i < Array.getLength(itVal); ++i) {
												values.add(Array.get(itVal, i));
											}
											it = values.iterator();
										} else {
											
											it = Collections.singleton(itVal).iterator();
										} 
									} else if (iterator != null) {
										throw new ConfigurationException("Usupported operation iterator type: " + iterator, asMarked(diagramElement));																										
									}
									
									if (it != null) {
										while (it.hasNext()) {
											Object next = it.next();
											EList<Object> argList = ECollections.newBasicEList();
											for (EParameter prm: eOperation.getEParameters()) {
												String argExpr = argMap.get(prm.getName());
												if (Util.isBlank(argExpr)) {
													argList.add(null);
												} else {
													Object arg = mapper.evaluate(
															next, 
															argExpr,												
															Map.of("registry", registry),
															Object.class,
															diagramElement);
													argList.add(arg);
												}
											}
											try {
												semanticElement.eInvoke(eOperation, argList);
											} catch (InvocationTargetException e) {
												throw new ConfigurationException("Error invoking eOperation " + eOperation, e, asMarked(diagramElement));																
											}
										}
									}
								} else {
									throw new ConfigurationException("Usupported operation spec element type: " + opSpecElement, asMarked(diagramElement));																
								}								
							}
						} else {
							throw new ConfigurationException("Usupported operation spec type: " + opSpec, asMarked(diagramElement));							
						}						
					}
				} else {				
					throw new ConfigurationException("Usupported operation map type: " + opMapObj, asMarked(diagramElement));
				}
			} catch (YAMLException yamlException) {
				throw new ConfigurationException("Error loading operation map: " + yamlException, yamlException, asMarked(diagramElement));
			}
		}	
		return result;
	}
	
	// --- Phase 6: Script
	
	@org.nasdanika.common.Transformer.Wire(phase = 6, targetType = Void.class)
	public final boolean wireScript(
			EObject diagramElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
	
		return executeScript(diagramElement, null, registry, pass, progressMonitor);
	}
	
	
	@org.nasdanika.common.Transformer.Wire(phase = 6)
	public final boolean wireScript(
			EObject diagramElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
	
		return executeScript(diagramElement, semanticElement, registry, pass, progressMonitor);
	}
	
	protected String getScriptPropertyName() {
		return getPropertyNamespace() + "script";
	}
	
	protected void configureScriptEngine(
			ScriptEngine engine,
			EObject diagramElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		engine.put("diagramElement", diagramElement);
		engine.put("semanticElement", semanticElement);
		engine.put("pass", pass);
		engine.put("registry", registry);
		engine.put("baseURI", getBaseURI(diagramElement));
		engine.put("logicalParent", getLogicalParent(diagramElement));
		for (Entry<String, Object> ve: getVariables(diagramElement)) {
			engine.put(ve.getKey(), ve.getValue());
		}
	}	
	
	// --- Phase 7: Processor
	
	/**
	 * Element processor
	 */
	public interface Processor<S extends EObject> {
		
		/**
		 * Processes elements
		 * @param diagramElement
		 * @param semanticElement
		 * @param registry
		 * @param pass
		 * @param progressMonitor
		 * @return false if processing shall be done in subsequent passes, e.g. pending some other processing 
		 */
		boolean process(
				EObject diagramElement,
				S semanticElement,
				Map<EObject, EObject> registry,
				int pass,
				ProgressMonitor progressMonitor);
		
	}
	
	protected String getProcessorPropertyName() {
		return getPropertyNamespace() + "processor";
	}
	
	@org.nasdanika.common.Transformer.Wire(phase = 7, targetType = Void.class)
	public final boolean wireProcessors(
			EObject diagramElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		return invokeProcessors(diagramElement, null, registry, pass, progressMonitor);
	}
	
	@org.nasdanika.common.Transformer.Wire(phase = 7)
	public final boolean wireProcessors(
			EObject diagramElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		return invokeProcessors(diagramElement, semanticElement, registry, pass, progressMonitor);
	}

	/**
	 * Calls processors
	 * @param diagramElement
	 * @param semanticElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean invokeProcessors(
			EObject diagramElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String ppn = getProcessorPropertyName();
		if (Util.isBlank(ppn)) {
			return true;
		}
		
		String processorExpr = getProperty(diagramElement, ppn);
		if (Util.isBlank(processorExpr)) {
			return true;
		}
		
		Map<String, Object> variables = Map.ofEntries(
				Map.entry("semanticElement", semanticElement),
				Map.entry("pass", pass),
				Map.entry("registry", registry));
		
		Object result = mapper.evaluate(diagramElement, processorExpr, variables, Object.class, diagramElement);
		if (result == null) {
			return true;
		}
		if (result instanceof Processor) {
			return ((Processor<S>) result).process(diagramElement, semanticElement, registry, pass, progressMonitor); 
		}
		if (result instanceof Iterator) {
			boolean ret = true;
			Iterator<Processor<S>> pit = (Iterator<Processor<S>>) result;
			while (pit.hasNext()) {
				if (!pit.next().process(diagramElement, semanticElement, registry, pass, progressMonitor)) {
					ret = false;
				}
			}
			
			return ret;
		}
		if (result instanceof Iterable) {
			boolean ret = true;
			for (Processor<S> processor: (Iterable<Processor<S>>) result) {
				if (!processor.process(diagramElement, semanticElement, registry, pass, progressMonitor)) {
					ret = false;
				}
			}
				
			return ret;	
		}
		if (result instanceof Stream) {
			boolean ret = true;
			for (Processor<S> processor: ((Stream<Processor<S>>) result).toList()) {
				if (!processor.process(diagramElement, semanticElement, registry, pass, progressMonitor)) {
					ret = false;
				}
			}
				
			return ret;	
		}
		if (result.getClass().isArray()) {
			boolean ret = true;
			for (int i = 0; i < Array.getLength(result); ++i) {
				Processor<S> processor = (Processor<S>) Array.get(result, i);
				if (!processor.process(diagramElement, semanticElement, registry, pass, progressMonitor)) {
					ret = false;
				}
			}
				
			return ret;	
		}
				
		throw new ConfigurationException("Unsupported processor type: " + result, asMarked(diagramElement));
	}
		
}
