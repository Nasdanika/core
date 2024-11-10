package org.nasdanika.drawio.emf;

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
import org.jsoup.Jsoup;
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
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.Page;
import org.nasdanika.mapping.AbstractMappingFactory;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.mapping.Mapper;
import org.nasdanika.mapping.SetterFeatureMapper;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
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

/**
 * Base class for classes which map/transform Drawio model to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 * @param <S> Semantic element type
 */
public abstract class AbstractDrawioFactory<T extends EObject> extends AbstractMappingFactory<Element, T> {
	
	private static final String PAGE_ELEMENT_PROPERTY = "page-element";
	private static final String TAG_SPEC_PROPERTY = "tag-spec";
	private static final String TOP_LEVEL_PAGE_PROPERTY = "top-level-page";

	protected AbstractDrawioFactory(ContentProvider<Element> contentProvider, CapabilityLoader capabilityLoader) {
		super(contentProvider, capabilityLoader);
	}

	protected AbstractDrawioFactory(ContentProvider<Element> contentProvider) {
		super(contentProvider);
	}
	
	protected String getTopLevelPageProperty() {
		return TOP_LEVEL_PAGE_PROPERTY;
	}	
	
	protected String getTagSpecPropertyName() {
		return TAG_SPEC_PROPERTY;
	}
		
	/**
	 * Indicates a top level page which shall be a child of document element
	 * @param page
	 * @return
	 */
	public boolean isTopLevelPage(Page page) {
		String topLevelPageProperty = getTopLevelPageProperty();
		if (!Util.isBlank(topLevelPageProperty)) {
			Object topLevelPage = getContentProvider().getProperty(page, topLevelPageProperty);
			if (Boolean.TRUE.equals(topLevelPage)) {
				return true;
			}
			if (topLevelPage instanceof String && !Util.isBlank((String) topLevelPage)) {
				return "true".equals(((String) topLevelPage).trim());
			}
		}

		return getContentProvider().getParent(page) instanceof Document; // No links
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
	public final Element createDocumentElement(
			Document document,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		Element documentElement = createDocumentElement(document, elementProvider, registry, progressMonitor);

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
	protected Element createDocumentElement(
			Document document,
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
	public final T createModelElementTarget(
			org.nasdanika.drawio.ModelElement modelElement,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {

		return createTarget(
				modelElement,
				elementProvider,
				registry,
				progressMonitor);
	}		

//	@org.nasdanika.common.Transformer.Factory
//	public final T createTagSemanticElement(
//			Tag tag,
//			boolean parallel,
//			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
//			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
//			ProgressMonitor progressMonitor) {
//	
//		return createSemanticElement(
//				tag,
//				elementProvider,
//				registry,
//				progressMonitor);
//	
//	}
	
	// --- Wiring ---
	
	// --- Phase 0 - semantic elements using ref-id's, page element, selector, prototype, semantic-selector ---	
	
	/**
	 * Wires elements with ref-id property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireModelElementRefIds(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wireRefIds(modelElement, registry, pass, progressMonitor);
	}
		
//	/**
//	 * Wires elements with ref-id property. Remaps which triggers wireContainment.
//	 * @param modelElement
//	 * @param registry
//	 * @param pass
//	 * @param progressMonitor
//	 */
//	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
//	public final boolean wireTagRefIds(
//			org.nasdanika.drawio.Tag tag,
//			Map<EObject, EObject> registry,
//			int pass,
//			ProgressMonitor progressMonitor) {
//		
//		return wireRefIds(tag, registry, pass, progressMonitor);
//	}
	
	protected String getPageElementProperty() {
		return PAGE_ELEMENT_PROPERTY;
	}	
	
	public boolean isPageElement(Element obj) {	
		String pageElementProperty = getPageElementProperty();
		if (Util.isBlank(pageElementProperty)) {
			return false;
		}
		
		Object pev = getContentProvider().getProperty(obj, pageElementProperty);
		if (Boolean.TRUE.equals(obj)) {
			return true;
		}
		return pev instanceof String && !Util.isBlank((String) pev) && "true".equals(((String) pev).trim());
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
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		if (!isPageElement(modelElement)) {
			return true;
		}
		Page page = modelElement.getModel().getPage();
		if (isTopLevelPage(page)) {
			return true;
		}
		
		Element pageParent = getContentProvider().getParent(page);
		T pageParentTarget = registry.get(pageParent);		
		if (pageParentTarget == null) {
			return false; // Not there yet
		}
		registry.put(modelElement, pageParentTarget);
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
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		LinkTarget linkTarget = modelElement.getLinkTarget();
		if (linkTarget instanceof org.nasdanika.drawio.ModelElement) {
			T target = registry.get(linkTarget);
			if (target == null) {
				return false; // Not there yet
			}
			registry.put(modelElement, target); // Тriggers a new wave of wiring
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
			org.nasdanika.drawio.ModelElement modelElement,
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
			org.nasdanika.drawio.Tag tag,
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
			org.nasdanika.drawio.ModelElement modelElement,
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
			org.nasdanika.drawio.Tag tag,
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
			org.nasdanika.drawio.ModelElement modelElement,
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
			org.nasdanika.drawio.Tag tag,
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
			org.nasdanika.drawio.Document document,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		for (Page page: document.getPages()) {
			if (isTopLevelPage(page)) {
				TreeIterator<EObject> cit = page.eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof org.nasdanika.drawio.ModelElement) {
						org.nasdanika.drawio.ModelElement modelElement = (org.nasdanika.drawio.ModelElement) next;
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
			org.nasdanika.drawio.Layer drawioModelElement,
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
			org.nasdanika.drawio.Document document,
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
			org.nasdanika.drawio.Document document,
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
			org.nasdanika.drawio.ModelElement drawioModelElement,
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
		if (semanticElement instanceof org.nasdanika.drawio.SemanticElement) {
			SemanticMapping semanticMapping = createSemanticMapping();
			for (EObject eContainer = drawioModelElement.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
				if (eContainer instanceof Page) {
					semanticMapping.setPageID(((Page) eContainer).getId());						
				} else if (eContainer instanceof org.nasdanika.drawio.Document) {
					semanticMapping.setDocumentURI(((org.nasdanika.drawio.Document) eContainer).getUri());
				}
			}
			semanticMapping.setModelElementID(drawioModelElement.getId());
			semanticMapping.setPageElement(isPageElement);
			((org.nasdanika.drawio.SemanticElement) semanticElement).getSemanticMappings().add(semanticMapping);
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
			org.nasdanika.drawio.ModelElement modelElement,
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
			org.nasdanika.drawio.Document document,
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
			org.nasdanika.drawio.Document document,
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
			org.nasdanika.drawio.ModelElement drawioModelElement,
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
			org.nasdanika.drawio.Tag tag,
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
		if (configPrototype instanceof org.nasdanika.drawio.ModelElement) {
			configureSemanticElement(
					(org.nasdanika.drawio.ModelElement) configPrototype, 
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
		boolean isLinked = eObj instanceof org.nasdanika.drawio.ModelElement
				&& isPageElement(eObj)
				&& !isTopLevelPage(((org.nasdanika.drawio.ModelElement) eObj).getPage());
		
		if (!isPrototype && !isLinked) {
			String semanticIdProperty = getSemanticIdProperty();
			if (!Util.isBlank(semanticIdProperty)) {
				String semanticId = getProperty(eObj, semanticIdProperty);
				String elementId = null;
				if (eObj instanceof org.nasdanika.drawio.ModelElement) {
					elementId = ((org.nasdanika.drawio.ModelElement) eObj).getId();
				}
				setSemanticId(semanticElement, semanticId, elementId);
			}
		}
		
		if (eObj instanceof org.nasdanika.drawio.ModelElement) {
			String label = ((org.nasdanika.drawio.ModelElement) eObj).getLabel();
			if (!Util.isBlank(label)) {
				String labelText = Jsoup.parse(label).text();
				setLabelText(semanticElement, labelText);				
			}
			String tooltip = ((org.nasdanika.drawio.ModelElement) eObj).getTooltip();
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
	
	/**
	 * Executes script
	 * @param diagramElement
	 * @param semanticElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	protected boolean executeScript(
			EObject diagramElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		SourceRecord script = loadSource(diagramElement, getScriptPropertyName());
		
		if (script == null || Util.isBlank(script.source())) {
			return true;
		}
		
		String sepn = getScriptPropertyName() + ENGINE_PROPERTY_SUFFIX;
		String enginePredicateExpr = Util.isBlank(sepn) ? null : getProperty(diagramElement, sepn);

		Map<String, Object> variables = Map.ofEntries(
				Map.entry("diagramElement", diagramElement),
				Map.entry("semanticElement", semanticElement),
				Map.entry("pass", pass),
				Map.entry("registry", registry));		
		
		ScriptEngineManager scriptEngineManger = new ScriptEngineManager(getClassLoader(diagramElement));
		for (ScriptEngineFactory scriptEngineFactory: scriptEngineManger.getEngineFactories()) {
			if (!Util.isBlank(enginePredicateExpr)) {
				if (!mapper.evaluatePredicate(scriptEngineFactory, enginePredicateExpr, variables, diagramElement)) {
					continue;
				}
			} else if (script.uri() != null) {
				String extension = script.uri().fileExtension();
				if (!Util.isBlank(extension) && !scriptEngineFactory.getExtensions().contains(extension)) {
					continue;
				}
			}
			
			ScriptEngine engine = scriptEngineFactory.getScriptEngine();
			configureScriptEngine(
					engine, 
					diagramElement, 
					semanticElement, 
					registry, 
					pass, 
					progressMonitor);
			
			try {
				Object result = engine.eval(script.source());
				return !Boolean.FALSE.equals(result);
			} catch (ScriptException e) {
				throw new ConfigurationException("Error evaluating script: " + e, e, asMarked(diagramElement));
			}
		}
		throw new ConfigurationException("No matching script engine", asMarked(diagramElement));
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
