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
import org.nasdanika.common.Invocable;

/**
 * Base class for classes which map/transform Drawio diagrams to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 * @param <S> Semantic element type
 */
public abstract class AbstractMappingFactory<S, T extends EObject> {
	
	private static final String TARGET_SELECTOR_PROPERTY = "target-selector";
	private static final String PROGRESS_MONITOR_VAR = "progressMonitor";
	private static final String REGISTRY_VAR = "registry";
	private static final String PROTOTYPE_PROPERTY = "prototype";
	private static final String REF_ID_PROPERTY = "ref-id";
	private static final String INITIALIZER_PROPERTY = "initializer";
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
	
	private static final String NAME_KEY = "name";
	private static final String CONDITION_KEY = "condition";
	private static final String EXPRESSION_KEY = "expression";
	private static final String ELEMENT_CONDITION_KEY = "element-condition";
	private static final String ELEMENT_EXPRESSION_KEY = "element-expression";
	private static final String SOURCE_PATH_VAR = "sourcePath";
	
	protected CapabilityLoader capabilityLoader;
	protected ContentProvider<S> contentProvider;
	
	public AbstractMappingFactory(ContentProvider<S> contentProvider) {
		this(contentProvider, new CapabilityLoader());
	}
	
	public AbstractMappingFactory(ContentProvider<S> contentProvider, CapabilityLoader capabilityLoader) {
		this.contentProvider = contentProvider;
		this.capabilityLoader = capabilityLoader;
		
		mapper = new PropertySetterFeatureMapper<S, T>(contentProvider) {
				
				@Override
				protected EClassifier getType(String type, S context) {
					return AbstractMappingFactory.this.getType(type, context);
				}
				
				@Override
				protected String getPropertyNamespace() {
					return AbstractMappingFactory.this.getPropertyNamespace();
				}
				
				@Override
				protected EvaluationContext createEvaluationContext(S context) {
					return AbstractMappingFactory.this.createEvaluationContext(context);
				}
				
				@Override
				protected SpelExpressionParser createExpressionParser(S context) {
					return AbstractMappingFactory.this.createExpressionParser(context);
				}
				
				@Override
				protected ClassLoader getClassLoader(S source) {
					return AbstractMappingFactory.this.getClassLoader(source);
				}
				
				@Override
				protected Map<String, Object> getVariables(S context) {
					return AbstractMappingFactory.this.getVariables(context);
				}
				
				@SuppressWarnings("unchecked")
				@Override
				public Iterable<T> select(S source, Map<S, T> registry, ProgressMonitor progressMonitor) {
					String referenceProperty = getReferenceProperty();
					if (!Util.isBlank(referenceProperty)) {		
						Object referenceSpec = getContentProvider().getProperty(source, referenceProperty);			
						if (referenceSpec instanceof String && !Util.isBlank((String) referenceSpec)) {
							List<T> ret = new ArrayList<>();
							ReferenceMapper referenceMapper = new ReferenceMapper((String) referenceSpec, source);
							List<S> logicalAncestorsPath = new ArrayList<>();
							for (S logicalAncestor = getContentProvider().getParent(source); logicalAncestor != null; logicalAncestor = getContentProvider().getParent(logicalAncestor)) {
								logicalAncestorsPath.add(logicalAncestor);					
								for (T ancestorTarget: mapper.select(logicalAncestor, registry, progressMonitor)) {						
									if (referenceMapper.matchAncestorTarget(ancestorTarget, logicalAncestorsPath, registry, source)) {
										T refObj = referenceMapper.getAncestorTargetRefObj(ancestorTarget, logicalAncestorsPath, registry, source); 
										if (refObj != null) {
											EClass eClass = refObj.eClass();
											String referenceName = referenceMapper.getReferenceName();
											EStructuralFeature feature = eClass.getEStructuralFeature(referenceName);
											if (feature == null) {
												throw new ConfigurationException("Feature " + referenceName + " not found in " + eClass.getName(), getContentProvider().asMarked(source)); 
											} else if (feature instanceof EReference) {
												Object featureValue = refObj.eGet(feature);
												if (feature.isMany()) {
													ret.addAll((Collection<T>) featureValue);
												} else if (featureValue instanceof EObject) {
													ret.add((T) featureValue);
												}
											} else {
												throw new ConfigurationException("Not a reference: " + referenceName + " in " + eClass.getName(), null, getContentProvider().asMarked(source)); 									
											}
										}
									}
									Comparator<Object> comparator = referenceMapper.getComparator(ancestorTarget, registry);
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
	}
	
	public ContentProvider<S> getContentProvider() {
		return contentProvider;
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
	
	/**
	 * Property containing domain class name.
	 * The name can be simple <code>class  name</code>, qualified <code>package name.class name</code>, or be a URI <code>package namespace uri#//class name</code>  
	 * @return
	 */
	protected String getTypeProperty() {
		return getPropertyNamespace() + TYPE_PROPERTY;
	}	
		
	/**
	 * Loads source from a property or from a URL specified by refProperty
	 * @param source
	 * @param property
	 * @param refProperty
	 * @return
	 */
	protected SourceRecord loadSource(S obj, String property, String refProperty) {
		URI baseURI = getContentProvider().getBaseURI(obj);
		if (!Util.isBlank(property)) {
			Object source = getContentProvider().getProperty(obj, property);
			if (source instanceof String && !Util.isBlank((String) source)) {
				return new SourceRecord(baseURI, (String) source);
			}
		}
		
		if (!Util.isBlank(refProperty)) {
			Object ref = getContentProvider().getProperty(obj, refProperty);
			URI refURI = null;
			if (ref instanceof URI) {
				refURI = (URI) ref;
			} else if (ref instanceof String && !Util.isBlank((String) ref)) {
				refURI = URI.createURI((String) ref); 
			}
			
			if (refURI != null) {
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}
				try {
					DefaultConverter converter = DefaultConverter.INSTANCE;
					Reader reader = converter.toReader(refURI);
					return new SourceRecord(refURI, converter.toString(reader));
				} catch (IOException e) {
					throw new ConfigurationException("Error loading source from " + refURI, e, getContentProvider().asMarked(obj));
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
		return NcoreUtil.getType(type, getEPackages(), msg -> new ConfigurationException(msg, getContentProvider().asMarked(source)));
	}
	
	/**
	 * Creates an instance of specified type.
	 * This implementation uses flow model EClass names as types to instantiate respective {@link EClass}es using factory
	 * @param kind
	 * @return
	 */
	protected EObject create(String type, S source) {
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
	
	protected SetterFeatureMapper<S, T> mapper;
	
	/**
	 * Override to return content mappers for different passes. 
	 * This implementation returns {@link PropertySetterFeatureMapper}.
	 * @param pass
	 * @return
	 */
	protected Mapper<S, T> getMapper(int pass) {
		return mapper;
	}
		
	/**
	 * Creates target mapping from the source object
	 * @param obj
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T createTarget(
			S obj,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {	
	
		T target = null;		
		String initializerPropertyName = getInitializerProperty();
		Object initializer = Util.isBlank(initializerPropertyName) ? null : getContentProvider().getProperty(obj, initializerPropertyName);
		
		Invocable initializerInvocable = null;
		if (initializer instanceof Invocable) {
			initializerInvocable = (Invocable) initializer;
		} else {			
			URI requirement = null;
			if (initializer instanceof URI) {
				requirement = (URI) initializer;
			} else if (initializer instanceof String && !Util.isBlank((String) initializer)) {
				requirement = URI.createURI((String) initializer);
			}
			if (requirement != null) {
				initializerInvocable = capabilityLoader.loadOne(
						ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
						progressMonitor);
			}
		}
		if (initializerInvocable != null) {
			target = configureInitializer(obj, initializerInvocable)
				.bindByName(REGISTRY_VAR, registry)	
				.bindByName("contentProvider", getContentProvider())	
				.bindByName(PROGRESS_MONITOR_VAR, progressMonitor)	
				.invoke(obj);
		}
		
		if (target == null) {
			String type = getTypeName(obj);		
		
			if (!Util.isBlank(type)) {
				target = (T) create(type.trim(), obj);
			}
		}
		
		if (target instanceof MinimalEObjectImpl && isRefIdProxyURI()) {
			String refIdPropertyName = getRefIdProperty();
			if (!Util.isBlank(refIdPropertyName)) {
				Object refId = getContentProvider().getProperty(obj, refIdPropertyName);
				URI refIdURI = null;
				if (refId instanceof URI) {
					refIdURI = (URI) refId;
				} else if (refId instanceof String && !Util.isBlank((String) refId)) {
					refIdURI = URI.createURI((String) refId);
				}
				if (refIdURI != null) {
					if (refIdURI.isRelative()) {
						URI baseURI = getContentProvider().getBaseURI(obj);
						if (baseURI != null) {
							refIdURI = refIdURI.resolve(baseURI);
						}
					}
					((MinimalEObjectImpl) target).eSetProxyURI(refIdURI);					
					
				}
			}
		}
		
		return target;
	}
	
	/**
	 * @return true if ref-id shall be treated as proxy URI. 
	 */
	protected boolean isRefIdProxyURI() {
		return false;
	}
	
	/**
	 * Initializer expression
	 * @return
	 */
	protected String getInitializerProperty() {
		return getPropertyNamespace() + INITIALIZER_PROPERTY;
	}
		
	/**
	 * Override to provide variables for expressions and scripts.
	 * 
	 * @return
	 */
	protected Map<String, Object> getVariables(S context) {
		return Collections.emptyMap();
	}	

	/**
	 * Override to configure the initializer invocable. E.g. bind additional arguments.
	 * This method binds variables returned from <code>getVariables()</code>
	 * @param initializer
	 * @return
	 */
	protected Invocable configureInitializer(S source, Invocable initializer) {
		Map<String, Object> variables = getVariables(source);
		if (variables == null || variables.isEmpty()) {
			return initializer;
		}
		return initializer.bindMap(variables);
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
	protected String getTypeName(S obj) {
		String typeProperty = getTypeProperty();
		if (Util.isBlank(typeProperty)) {
			return null;
		}
		
		Object typeName = getContentProvider().getProperty(obj, typeProperty);
		return typeName instanceof String ? (String) typeName : null;
	}
	
	// === Wiring ===
	
	// --- Phase 0 - semantic elements using ref-id's, page element, selector, prototype, semantic-selector ---	
	
	protected String getRefIdProperty() {
		return getPropertyNamespace() + REF_ID_PROPERTY;
	}
	
	/**
	 * Resolves semantic element by reference id. 
	 * @param refId
	 * @param wiring pass
	 * @return Null if element cannot be resolved yet
	 * @throws IllegalArgumentException If refId does not resolve to a semantic element
	 */
	protected abstract T getByRefId(
			S obj,
			String refId, 
			int pass, 
			Map<S, T> registry);
	
	protected boolean wireRefIds(
			S obj,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String refIdPropertyName = getRefIdProperty();
		if (Util.isBlank(refIdPropertyName)) {
			return true;
		}
		Object refId = getContentProvider().getProperty(obj, refIdPropertyName);
		if (refId instanceof String) {
			String refIdStr = (String) refId;
			if (!Util.isBlank(refIdStr)) {
				T refTarget = getByRefId(obj, refIdStr, pass, registry);
				if (refTarget != null) {
					registry.put(obj, refTarget); // Resolved refId triggers a new wave of wiring
				}
			}
		}		
		
		return true;
	}
		
	protected String getSelectorProperty() {
		return getPropertyNamespace() + SELECTOR_KEY;
	}
	
	protected boolean wireSelector(
			S obj,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String selectorPropertyName = getSelectorProperty();
		if (Util.isBlank(selectorPropertyName)) {
			return true;
		}
		Object selector = getContentProvider().getProperty(obj, selectorPropertyName);		
		if (selector == null) {
			return true;
		}
		
		if (selector instanceof String) {
			String selectorStr = (String) selector;
			if (Util.isBlank(selectorStr)) {
				return true;
			}
			try {			
				ExpressionParser parser = createExpressionParser(obj);
				Expression exp = parser.parseExpression(selectorStr);
				EvaluationContext evaluationContext = createEvaluationContext(obj);
				configureSelectorEvaluationContext(evaluationContext, registry, pass, progressMonitor);
				Object result = exp.getValue(evaluationContext, obj, Object.class);
				if (result instanceof EObject) {
					T selectedTarget = registry.get(result);
					if (selectedTarget == null) {
						return false; // Not there yet
					}
					registry.put(obj, selectedTarget); // Тriggers a new wave of wiring
					return true;				
				}
				if (result instanceof Boolean) {
					return (Boolean) result;
				} 
				if (result == null) {
					return true;
				}
				throw new ConfigurationException("Unexpected result type of selector: '" + selector + "': " + result, getContentProvider().asMarked(obj));			
			} catch (ParseException e) {
				throw new ConfigurationException("Error parsing selector: '" + selector, e, getContentProvider().asMarked(obj));
			} catch (EvaluationException e) {
				throw new ConfigurationException("Error evaluating selector: '" + selector, e, getContentProvider().asMarked(obj));
			}
		}		
		throw new ConfigurationException("Unsupported selector type: '" + selector, getContentProvider().asMarked(obj));			
	}

	protected void configureSelectorEvaluationContext(
			EvaluationContext evaluationContext,
			Map<S, T> registry, 
			int pass,
			ProgressMonitor progressMonitor) {
		
		evaluationContext.setVariable(REGISTRY_VAR, registry);
		evaluationContext.setVariable(PASS_KEY, pass);
		evaluationContext.setVariable(PROGRESS_MONITOR_VAR, progressMonitor);
	}
	
	protected String getPrototypeProperty() {
		return getPropertyNamespace() + PROTOTYPE_PROPERTY;
	}
	
	protected S getPrototype(
			S obj,
			ProgressMonitor progressMonitor) {
	
		String prototypePropertyName = getPrototypeProperty();
		if (Util.isBlank(prototypePropertyName)) {
			return null;
		}
		Object prototypeExpr = getContentProvider().getProperty(obj, prototypePropertyName);
		if (prototypeExpr == null) {
			return null;
		}
		
		if (prototypeExpr instanceof String) {
			String prototypeExprStr = (String) prototypeExpr;
			if (Util.isBlank(prototypeExprStr)) {
				return null;
			}
			
			try {			
				ExpressionParser parser = createExpressionParser(obj);
				Expression exp = parser.parseExpression(prototypeExprStr);
				EvaluationContext evaluationContext = createEvaluationContext(obj);
				configurePrototypeEvaluationContext(evaluationContext, progressMonitor);
				return (S) exp.getValue(evaluationContext, obj);
			} catch (ParseException e) {
				throw new ConfigurationException("Error parsing prototype: '" + prototypeExpr, e, getContentProvider().asMarked(obj));
			} catch (EvaluationException e) {
				throw new ConfigurationException("Error evaluating prototype: '" + prototypeExpr, e, getContentProvider().asMarked(obj));
			}
		}
		throw new ConfigurationException("Unsupported prototype type: '" + prototypeExpr, getContentProvider().asMarked(obj));			
	}	

	protected boolean wirePrototype(
			S obj,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
	
		String prototypePropertyName = getPrototypeProperty();
		if (Util.isBlank(prototypePropertyName)) {
			return true;
		}
		Object prototypeExpr = getContentProvider().getProperty(obj, prototypePropertyName);
		if (prototypeExpr == null || (prototypeExpr instanceof String && Util.isBlank((String) prototypeExpr))) {
			return true;
		}
		
		try {			
			S result = getPrototype(obj, progressMonitor);
			T prototype = registry.get(result);
			if (prototype == null) {
				return false; // Not there yet
			}
			T copy = EcoreUtil.copy(prototype);
			if (copy instanceof ModelElement) {
				((ModelElement) copy).setUuid(UUID.randomUUID().toString());
			}
			registry.put(obj, copy); // Тriggers a new wave of wiring
			return true;				
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing selector: '" + prototypeExpr, e, getContentProvider().asMarked(obj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating selector: '" + prototypeExpr, e, getContentProvider().asMarked(obj));
		}
	}

	protected void configurePrototypeEvaluationContext(
			EvaluationContext evaluationContext,
			ProgressMonitor progressMonitor) {
		
		evaluationContext.setVariable(PROGRESS_MONITOR_VAR, progressMonitor);
	}
	
	protected String getTargetSelectorProperty() {
		return getPropertyNamespace() + TARGET_SELECTOR_PROPERTY;
	}

	protected boolean wireTargetSelector(
			S obj,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String targetSelectorPropertyName = getTargetSelectorProperty();
		if (Util.isBlank(targetSelectorPropertyName)) {
			return true;
		}
		Object targetSelector = getContentProvider().getProperty(obj, targetSelectorPropertyName);
		if (targetSelector == null) {
			return true;
		}
		
		if (targetSelector instanceof String) {
			String targetSelectorStr = (String) targetSelector;
			if ( Util.isBlank(targetSelectorStr)) {
				return true;
			}
			
			try {			
				ExpressionParser parser = createExpressionParser(obj);
				Expression exp = parser.parseExpression(targetSelectorStr);
				EvaluationContext evaluationContext = createEvaluationContext(obj);
				configureTargetSelectorEvaluationContext(evaluationContext, registry, pass, progressMonitor);
				Object result = exp.getValue(evaluationContext, obj);
				if (result instanceof EObject) {
					registry.put(obj, (T) result); 
					return true;				
				}
				if (result == null) {
					return true;
				}
				throw new ConfigurationException("Unexpected result type of target selector: '" + targetSelectorStr + "': " + result, getContentProvider().asMarked(obj));			
			} catch (ParseException e) {
				throw new ConfigurationException("Error parsing target selector: '" + targetSelectorStr, e, getContentProvider().asMarked(obj));
			} catch (EvaluationException e) {
				throw new ConfigurationException("Error evaluating semantic selector: '" + targetSelectorStr, e, getContentProvider().asMarked(obj));
			}
		}
		throw new ConfigurationException("Unsupported target selector type: '" + targetSelector, getContentProvider().asMarked(obj));					
		
	}

	protected void configureTargetSelectorEvaluationContext(
			EvaluationContext evaluationContext,
			Map<S,T> registry, 
			int pass,
			ProgressMonitor progressMonitor) {
		
		evaluationContext.setVariable(REGISTRY_VAR, registry);
		evaluationContext.setVariable(PASS_KEY, pass);
		evaluationContext.setVariable(PROGRESS_MONITOR_VAR, progressMonitor);
	}
		
	protected EvaluationContext createEvaluationContext(S context) {
		StandardEvaluationContext ret = new StandardEvaluationContext();
		ClassLoader classLoader = getClassLoader(context);
		if (classLoader != null) {
			ret.setTypeLocator(new StandardTypeLocator(classLoader));
		}
		for (Entry<String, Object> ve: getVariables(context).entrySet()) {
			ret.setVariable(ve.getKey(), ve.getValue());
		}
		return ret;
	}
	
	protected SpelExpressionParser createExpressionParser(S context) {
		SpelParserConfiguration config = new SpelParserConfiguration(null, getClassLoader(context));
		return new SpelExpressionParser(config);
	}	
	
	/**
	 * Returns a {@link ClassLoader} for an object (diagram element). 
	 * This implementation delegates to the parent and returns this class' classLoader for the root object.
	 * Classloader returned by this method is used to create SpEL parser.
	 * @param context
	 * @return
	 */
	protected ClassLoader getClassLoader(S context) {
		// TODO - class loader property, use ClassLoaderRequirement, no need to cache - capability loader will do it				
		S parent = getContentProvider().getParent(context);
		if (parent == null) {
			return Thread.currentThread().getContextClassLoader();
		}
		return getClassLoader(parent);
	}
		
	// --- Phase 1: Mapping "reference" elements
	
	protected String getReferenceProperty() {
		return getPropertyNamespace() + "reference";
	}
	
	protected class ReferenceMapper {
		
		private Map<?,?> spec;
		
		private String referenceName;
		private S context;
		
		public ReferenceMapper(String specYaml, S context) {
			Yaml yaml = new Yaml();
			Object specObj = yaml.load(specYaml);
			if (specObj instanceof Map) {			
				this.spec =  (Map<?,?>) specObj;
			} else if (specObj instanceof String) {
				 this.spec =  Collections.singletonMap(NAME_KEY, specObj);			
			} else {						
				throw new ConfigurationException("Usupported reference configuration type: " + specObj, getContentProvider().asMarked(context));
			}
			
			Object rName = this.spec.get(NAME_KEY);
			if (rName instanceof String) {
				this.referenceName = (String) rName;
			} else {
				throw new ConfigurationException("Reference name is not a string: " + specObj, getContentProvider().asMarked(context));				
			}
			this.context = context;
			
		}
		
		public Comparator<Object> getComparator(T targetElement, Map<S, T> registry) {
			return mapper.getComparator(targetElement, spec, registry, context);
			
		}
		
		public String getReferenceName() {
			return referenceName;
		}

		public boolean matchAncestorTarget(
				T ancestorTargetElement, 
				List<S> logicalAncestorPath,
				Map<S, T> registry,
				S context) {
			
			Object cObj = spec.get(CONDITION_KEY);
			if (cObj == null) {
				return ancestorTargetElement != null; // Shall always be true?
			}
			
			if (cObj instanceof String) {
				mapper.evaluatePredicate(
						ancestorTargetElement, 
						(String) cObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, logicalAncestorPath)), 
						context);
			}
			
			throw new ConfigurationException("Usupported reference condition type: " + cObj, getContentProvider().asMarked(context));		
		}
		
		public T getAncestorTargetRefObj(
				T ancestorTarget, 
				List<S> logicalAncestorPath,
				Map<S, T> registry, 
				S context) {
			
			Object eObj = spec.get(EXPRESSION_KEY);
			if (eObj == null) {
				return ancestorTarget;
			}
			
			if (eObj instanceof String) {
				mapper.evaluate(
						ancestorTarget, 
						(String) eObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, logicalAncestorPath)), 
						EObject.class,
						context);
			}
			
			throw new ConfigurationException("Usupported reference expression type: " + eObj, getContentProvider().asMarked(context));		
		}
		
		private boolean matchContentsTarget(
				T contentsTarget, 
				List<S> sourcePath,
				Map<S, T> registry,
				S context) {
			
			Object cObj = spec.get(ELEMENT_CONDITION_KEY);
			if (cObj == null) {
				return sourcePath.size() == 2; // Immediate children
			}
			
			if (cObj instanceof String) {
				mapper.evaluatePredicate(
						contentsTarget, 
						(String) cObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, sourcePath)), 
						context);
			}
			
			throw new ConfigurationException("Usupported reference element condition type: " + cObj, getContentProvider().asMarked(context));		
		}
		
		private EObject getContentsTargetRefObj(
				T contentsTarget, 
				List<S> sourcePath,
				Map<S, T> registry, 
				S context) {
			
			Object eObj = spec.get(ELEMENT_EXPRESSION_KEY);
			if (eObj == null) {
				return contentsTarget;
			}
			
			if (eObj instanceof String) {
				mapper.evaluate(
						contentsTarget, 
						(String) eObj, 
						Map.ofEntries(
								Map.entry(REGISTRY_VAR, registry),
								Map.entry(SOURCE_PATH_VAR, sourcePath)), 
						EObject.class,
						context);
			}
			
			throw new ConfigurationException("Usupported reference element expression type: " + eObj, getContentProvider().asMarked(context));		
		}
				
		public List<EObject> getElements(
				LinkedList<S> sourcePath,
				Map<S, EObject> registry, 
				Predicate<S> tracker,
				S context,
				ProgressMonitor progressMonitor) {
			
			List<EObject> ret = new ArrayList<>();			
			for (S childSource: getContentProvider().getContents(sourcePath.getLast(), tracker)) {
				sourcePath.add(childSource);
				for (EObject childSemanticElement: mapper.select(childSource, registry, null)) {
					if (matchContentsTarget(childSemanticElement, sourcePath, registry, context)) {
						EObject refObj = getContentsTargetRefObj(childSemanticElement, sourcePath, registry, context);
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

	// --- Phase 2: Mapping features and adding representations ---
	
	// --- Phase 3: mapping features of null semantic elements such as pass-through connections
	
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 3)
	public final boolean featurMapNulls(
			S obj,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<S,T> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(obj, registry, progressMonitor);
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
			throw new ConfigurationException("Error parsing congfig prototype: '" + configPrototypeExpr, e, getContentProvider().asMarked(eObj));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating config prototype: '" + configPrototypeExpr, e, getContentProvider().asMarked(eObj));
		}
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
	
	protected void configureTargetElement(
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
			S diagramElement,
			T semanticElement,
			Map<S, T> registry,
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
										throw new ConfigurationException("Usupported operation pass type: " + passObj, getContentProvider().asMarked(diagramElement));																																				
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
													throw new ConfigurationException("Usupported operation argument expression type: " + val, getContentProvider().asMarked(diagramElement));																																																													
												}
											} else {
												throw new ConfigurationException("Usupported operation parameter name type: " + key, getContentProvider().asMarked(diagramElement));																																																
											}
										}
									} else {
										throw new ConfigurationException("Usupported operation arguments type: " + arguments, getContentProvider().asMarked(diagramElement));																																				
									}																		
									
									Object selector = opSpecElementMap.get(SELECTOR_KEY);
									if (selector instanceof String) {
										if (!mapper.evaluatePredicate(eOperation, (String) selector, null, diagramElement)) {
											continue;
										}
									} else if (selector != null) {
										throw new ConfigurationException("Usupported operation selector type: " + selector, getContentProvider().asMarked(diagramElement));																										
									}
									
									Iterator<?> it = Collections.singleton(diagramElement).iterator();
									
									Object iterator = opSpecElementMap.get(ITERATOR_KEY);
									if (iterator instanceof String) {										
										Object itVal = mapper.evaluate(
												diagramElement, 
												(String) iterator,												
												Map.of(REGISTRY_VAR, registry),
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
										throw new ConfigurationException("Usupported operation iterator type: " + iterator, getContentProvider().asMarked(diagramElement));																										
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
															Map.of(REGISTRY_VAR, registry),
															Object.class,
															diagramElement);
													argList.add(arg);
												}
											}
											try {
												semanticElement.eInvoke(eOperation, argList);
											} catch (InvocationTargetException e) {
												throw new ConfigurationException("Error invoking eOperation " + eOperation, e, getContentProvider().asMarked(diagramElement));																
											}
										}
									}
								} else {
									throw new ConfigurationException("Usupported operation spec element type: " + opSpecElement, getContentProvider().asMarked(diagramElement));																
								}								
							}
						} else {
							throw new ConfigurationException("Usupported operation spec type: " + opSpec, getContentProvider().asMarked(diagramElement));							
						}						
					}
				} else {				
					throw new ConfigurationException("Usupported operation map type: " + opMapObj, getContentProvider().asMarked(diagramElement));
				}
			} catch (YAMLException yamlException) {
				throw new ConfigurationException("Error loading operation map: " + yamlException, yamlException, getContentProvider().asMarked(diagramElement));
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
		engine.put(REGISTRY_VAR, registry);
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
				Map.entry(REGISTRY_VAR, registry));
		
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
				
		throw new ConfigurationException("Unsupported processor type: " + result, getContentProvider().asMarked(diagramElement));
	}
		
}
