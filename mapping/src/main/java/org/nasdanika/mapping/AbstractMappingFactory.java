package org.nasdanika.mapping;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
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
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DocumentationFactory;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NcoreFactory;
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
 * Base class for classes which map/transform Drawio diagrams to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 * @param <S> Semantic element type
 */
public abstract class AbstractMappingFactory<S, T extends EObject> {
	
	/**
	 * Capability/service interface for contributing to the mapping process
	 */
	public interface Contributor<S,T extends EObject> {
		
		boolean canHandle(Object source, EObject target);
		
		/**
		 * Called from createTarget(), allows to customize both the source and target for further mapping
		 * @param obj
		 * @param target
		 * @param elementProvider
		 * @param registry
		 * @param progressMonitor
		 */
		default void initialize(
				S obj,
				T target,
				BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
				Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
				ProgressMonitor progressMonitor) {
			
		}		
		
		/**
		 * Called from configureTarget()
		 * @param obj
		 * @param documentation
		 * @param target
		 * @param contentProvider
		 * @param registry
		 * @param isPrototype
		 * @param progressMonitor
		 */
		default void configure(
				S obj,
				Collection<EObject> documentation,
				T target,
				Map<S, T> registry,
				boolean isPrototype,
				ProgressMonitor progressMonitor) {
			
		}
		
	}
	
	protected static final int PASS_PADDING = 100; // Arbitrary pass padding just in case, not really needed.
	private static final String REFERENCE_PROPERTY = "reference";
	private static final String PASS_VAR = "pass";
	private static final String TARGET_VAR = "target";
	private static final String CONTENT_PROVIDER_VAR = "contentProvider";
	private static final String INVOKE_PROPERTY = "invoke";
	private static final String OPERATIONS_PROPERTY = "operations";
	private static final String CONFIG_PROTOTYPE_PROPERTY = "config-prototype";
	private static final String TARGET_SELECTOR_PROPERTY = "target-selector";
	private static final String PROGRESS_MONITOR_VAR = "progressMonitor";
	private static final String REGISTRY_VAR = "registry";
	private static final String PROTOTYPE_PROPERTY = "prototype";
	private static final String REF_ID_PROPERTY = "ref-id";
	private static final String INITIALIZER_PROPERTY = "initializer";
	private static final String DOC_FORMAT_PROPERTY = "doc-format";
	private static final String DOC_REF_PROPERTY = "doc-ref";
	private static final String DOCUMENTATION_PROPERTY = "documentation";
	private static final String IDENTITY_PROPERTY = "identity";
	private static final String REF_SUFFIX = "-ref";
	private static final String TYPE_PROPERTY = "type";
	private static final String PASS_KEY = PASS_VAR;
	private static final String ARGUMENTS_KEY = "arguments";
	private static final String ITERATOR_KEY = "iterator";
	private static final String SELECTOR_KEY = "selector";
	
	private static final String NAME_KEY = "name";
	private static final String CONDITION_KEY = "condition";
	private static final String EXPRESSION_KEY = "expression";
	private static final String ELEMENT_CONDITION_KEY = "element-condition";
	private static final String ELEMENT_EXPRESSION_KEY = "element-expression";
	private static final String SOURCE_PATH_VAR = "sourcePath";
	
	protected CapabilityLoader capabilityLoader;
	protected ContentProvider<S> contentProvider;
	
	public AbstractMappingFactory(ContentProvider<S> contentProvider, ProgressMonitor progressMonitor) {
		this(contentProvider, new CapabilityLoader(), progressMonitor);
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Requirement<? extends AbstractMappingFactory<S,T>, Contributor<S,T>> createContributorRequirement() {
		return (Requirement) ServiceCapabilityFactory.createRequirement(Contributor.class, null, this);
	}	
	
	protected Collection<Contributor<S,T>> contributors = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public AbstractMappingFactory(ContentProvider<S> contentProvider, CapabilityLoader capabilityLoader, ProgressMonitor progressMonitor) {
		this.contentProvider = contentProvider;
		this.capabilityLoader = capabilityLoader;
		
		// Capabilities
		if (capabilityLoader != null) {
			Requirement<? extends AbstractMappingFactory<S,T>, Contributor<S,T>> requirement = createContributorRequirement();
			Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(requirement, progressMonitor);
			for (CapabilityProvider<Object> cp: cpi) {
				cp.getPublisher().subscribe(obj -> contributors.add((Contributor<S,T>) obj));
			}
		}				
		
		mapper = new PropertySetterFeatureMapper<S, T>(contentProvider, capabilityLoader) {
				
				@Override
				protected EClassifier getType(String type, S context) {
					return AbstractMappingFactory.this.getType(type, context);
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
				
				@Override
				public Comparator<Object> createComparator(
						T target, 
						Object comparatorConfig, 
						Map<S, T> registry,
						S context, 
						ProgressMonitor progressMonitor) {
															
					Comparator<Object> comparator = AbstractMappingFactory.this.createMapperComparator(target, comparatorConfig, registry, context, progressMonitor);
					return comparator == null ? super.createComparator(target, comparatorConfig, registry, context, progressMonitor) : comparator;
				}
				
				@Override
				public Collection<T> select(S source, Map<S, T> registry, ProgressMonitor progressMonitor) {
					String referenceProperty = getReferenceProperty();
					if (!Util.isBlank(referenceProperty)) {		
						Object referenceSpec = getContentProvider().getProperty(source, referenceProperty);			
						if (referenceSpec != null && !(referenceSpec instanceof String && Util.isBlank((String) referenceSpec))) {
							List<T> ret = new ArrayList<>();
							ReferenceMapper referenceMapper = new ReferenceMapper(referenceSpec, source);
							List<S> ancestorsPath = new ArrayList<>();
							for (S ancestor = getContentProvider().getParent(source); ancestor != null; ancestor = getContentProvider().getParent(ancestor)) {
								ancestorsPath.add(ancestor);					
								for (T ancestorTarget: mapper.select(ancestor, registry, progressMonitor)) {	
									if (referenceMapper.matchAncestorTarget(ancestorTarget, ancestorsPath, registry, source)) {
										T refObj = referenceMapper.getAncestorTargetRefObj(ancestorTarget, ancestorsPath, registry, source); 
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
									Comparator<Object> comparator = referenceMapper.getComparator(ancestorTarget, registry, progressMonitor);
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

	protected String getRefSuffix() {
		return REF_SUFFIX;
	}
	
	/**
	 * Semantic ID is used instead of the Drawio element ID if provided. E.g. "my-process" instead of "HP_1aCX3hRniy5izKEYK"
	 * While it is possible to edit Drawio element ID's, it shall be done with care to avoid introduction of duplicate ID's. 
	 * Semantic ID's shall be unique within their containment collection.   
	 * @return
	 */
	protected String getIdentityProperty() {
		return IDENTITY_PROPERTY;
	}	
	
	protected String getDocumentationProperty() {
		return DOCUMENTATION_PROPERTY;
	}	
		
	protected String getDocRefProperty() {
		return DOC_REF_PROPERTY;
	}	
	
	protected String getDocFormatProperty() {
		return DOC_FORMAT_PROPERTY; 
	}	
	
	/**
	 * Property containing domain class name.
	 * The name can be simple <code>class  name</code>, qualified <code>package name.class name</code>, or be a URI <code>package namespace uri#//class name</code>  
	 * @return
	 */
	protected String getTypeProperty() {
		return TYPE_PROPERTY;
	}	
	
	protected static record YamlSourceRecord(URI uri, Object data) {};	
		
	/**
	 * Loads source from a property or from a URL specified by refProperty
	 * @param source
	 * @param property
	 * @param refProperty
	 * @return
	 */
	protected YamlSourceRecord loadYamlSource(S obj, String property, String refProperty) {
		URI baseURI = getContentProvider().getBaseURI(obj);
		if (!Util.isBlank(property)) {
			Object source = getContentProvider().getProperty(obj, property);
			if (source instanceof String && !Util.isBlank((String) source)) {
				return new YamlSourceRecord(baseURI, new Yaml().load((String) source));
			}
			if (source != null) {
				return new YamlSourceRecord(baseURI, source);				
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
					return new YamlSourceRecord(refURI, new Yaml().load(converter.toString(reader)));
				} catch (IOException e) {
					throw new ConfigurationException("Error loading source from " + refURI, e, getContentProvider().asMarked(obj));
				}
			}
		}
		return null;
	}
	
	protected YamlSourceRecord loadYamlSource(S obj, String property) {
		if (Util.isBlank(property)) {
			return null;
		}
		return loadYamlSource(obj, property, property + getRefSuffix());
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
				if (requirement.isRelative()) {
					URI baseURI = getContentProvider().getBaseURI(obj);
					if (baseURI != null && !baseURI.isRelative()) {
						requirement = requirement.resolve(baseURI);
					}
				}
				initializerInvocable = capabilityLoader.loadOne(
						ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
						progressMonitor);
			}
		}
		if (initializerInvocable != null) {
			target = configureInitializer(obj, initializerInvocable)
				.bindByName(REGISTRY_VAR, registry)	
				.bindByName(CONTENT_PROVIDER_VAR, getContentProvider())	
				.bindByName(PROGRESS_MONITOR_VAR, progressMonitor)	
				.invoke(obj);
		}
		
		if (target == null) {
			String type = getTypeName(obj);		
		
			if (!Util.isBlank(type)) {
				target = (T) create(type.trim(), obj);
				if (target instanceof ModelElement) {
					ModelElement met = (ModelElement) target;
					if (Util.isBlank(met.getUuid())) {
						met.setUuid(UUID.randomUUID().toString());
					}
				}
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
		
		for (Contributor<S,T> contributor: contributors) {
			if (contributor.canHandle(obj, target)) {				
				contributor.initialize(
						obj, 
						target,
						elementProvider,
						registry, 
						progressMonitor);						
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
		return INITIALIZER_PROPERTY;
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
		return configureInvocable(source, initializer);
	}
	
	/**
	 * Property for setting base {@link URI} for an element relative to its containers for resolving resource URI's 
	 * such as doc-ref, feature-map-ref, spec-ref.
	 * @return
	 */
	protected String getBaseURIProperty() {
		return Context.BASE_URI_PROPERTY;
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
		return REF_ID_PROPERTY;
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
		return SELECTOR_KEY;
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
				}
				if (result instanceof Boolean) {
					return (Boolean) result;
				} 
				if (result == null) {
					return true;
				}
				T selectedTarget = registry.get(result);
				if (selectedTarget == null) {
					return pass > registry.size() + PASS_PADDING; // Not there yet if less, never will be if more. 100 is an arbitrary "padding"
				}
				registry.put(obj, selectedTarget); // Тriggers a new wave of wiring
				return true;				
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
		return PROTOTYPE_PROPERTY;
	}
	
	@SuppressWarnings({ "unchecked" })
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
				return pass > registry.size() + PASS_PADDING; // Not there yet or never will be
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
		return TARGET_SELECTOR_PROPERTY;
	}

	@SuppressWarnings("unchecked")
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
		return REFERENCE_PROPERTY;
	}
	
	protected class ReferenceMapper {
		
		private Map<?,?> spec;
		
		private String referenceName;
		private S context;
		
		public ReferenceMapper(Object spec, S context) {
			Yaml yaml = new Yaml();
			Object specObj = spec instanceof String ? yaml.load((String) spec) : spec;
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
		
		public Comparator<Object> getComparator(T targetElement, Map<S, T> registry, ProgressMonitor progressMonitor) {
			return mapper.getComparator(
					targetElement,
					targetElement.eClass().getEStructuralFeature(referenceName),
					spec, 
					registry, 
					context,
					progressMonitor);
			
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
		
		@SuppressWarnings("unchecked")
		private T getContentsTargetRefObj(
				T contentsTarget, 
				List<S> sourcePath,
				Map<S, T> registry, 
				S context) {
			
			Object eObj = spec.get(ELEMENT_EXPRESSION_KEY);
			if (eObj == null) {
				return contentsTarget;
			}
			
			if (eObj instanceof String) {
				return (T) mapper.evaluate(
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
				
		public List<T> getElements(
				LinkedList<S> sourcePath,
				Map<S, T> registry, 
				Predicate<S> tracker,
				S context,
				ProgressMonitor progressMonitor) {
			
			List<T> ret = new ArrayList<>();			
			for (S childSource: getContentProvider().getChildren(sourcePath.getLast())) {
				if (tracker.test(childSource)) {
					sourcePath.add(childSource);
					for (T childTargetElement: mapper.select(childSource, registry, null)) {
						if (matchContentsTarget(childTargetElement, sourcePath, registry, context)) {
							T refObj = getContentsTargetRefObj(childTargetElement, sourcePath, registry, context);
							if (refObj != null) {
								ret.add(refObj);
							}
						}
					}
					ret.addAll(getElements(sourcePath, registry, tracker, context, progressMonitor));
					sourcePath.removeLast();
				}
			}
			
			return ret;
		}		
		
	}

	// --- Phase 2: Mapping features and adding representations ---
	
	// Implement in sub-classes
	
	// --- Phase 3: mapping features of null semantic elements such as pass-through connections
	
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 3)
	public final void featureMapNoTarget(
			S obj,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<S,T> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(obj, registry, progressMonitor);
		}		
	}
	
	// --- Phase 4: Configuration ---
	
	protected String getConfigPrototypeProperty() {
		return CONFIG_PROTOTYPE_PROPERTY;
	}
	
	@SuppressWarnings("unchecked")
	protected S getConfigPrototype(
			S obj,
			ProgressMonitor progressMonitor) {
	
		String configPrototypePropertyName = getConfigPrototypeProperty();
		if (Util.isBlank(configPrototypePropertyName)) {
			return getPrototype(obj, progressMonitor);
		}
		Object configPrototypeExpr = getContentProvider().getProperty(obj, configPrototypePropertyName);
		if (configPrototypeExpr == null) {
			return getPrototype(obj, progressMonitor);			
		}
		
		if (configPrototypeExpr instanceof String) {
			String expr = (String) configPrototypeExpr;
			if (Util.isBlank(expr)) {
				return getPrototype(obj, progressMonitor);
			}
			
			try {			
				ExpressionParser parser = createExpressionParser(obj);
				Expression exp = parser.parseExpression(expr);
				EvaluationContext evaluationContext = createEvaluationContext(obj);
				configurePrototypeEvaluationContext(evaluationContext, progressMonitor);
				return (S) exp.getValue(evaluationContext, obj);
			} catch (ParseException e) {
				throw new ConfigurationException("Error parsing congfig prototype: '" + configPrototypeExpr, e, getContentProvider().asMarked(obj));
			} catch (EvaluationException e) {
				throw new ConfigurationException("Error evaluating config prototype: '" + configPrototypeExpr, e, getContentProvider().asMarked(obj));
			}
		}
		throw new ConfigurationException("Unsupported config prototype type: '" + configPrototypeExpr, getContentProvider().asMarked(obj));
		
	}		
	
	/**
	 * Sets identity if the target element is instance of org.nasdanika.ncore.StringIdentity
	 * @param target
	 * @param identity
	 */
	protected void setIdentity(T target, Object identity, boolean force) {
		if (identity instanceof String && target instanceof org.nasdanika.ncore.StringIdentity) {
			org.nasdanika.ncore.StringIdentity targetStringIdentity = (org.nasdanika.ncore.StringIdentity) target;
			if (force || Util.isBlank(targetStringIdentity.getId())) {
				targetStringIdentity.setId((String) identity);
			}
		}
	}
	
	protected void addDocumentation(T target, Collection<EObject> documentation) {
		if (target instanceof org.nasdanika.ncore.Documented) {
			((org.nasdanika.ncore.Documented) target).getDocumentation().addAll(documentation);
		}	
	}
	
	/**
	 * Sets label text extracted from the label HTML.
	 * @param target
	 * @param name
	 */
	protected void setName(T target, String name) {
		if (target instanceof org.nasdanika.ncore.NamedElement) {
			((org.nasdanika.ncore.NamedElement) target).setName(name);
		}
	}

	protected void setDescription(T target, String tooltip) {
		if (target instanceof org.nasdanika.ncore.ModelElement) {
			((org.nasdanika.ncore.ModelElement) target).setDescription(tooltip);
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
	
	protected void mark(
			Object source, 
			org.nasdanika.ncore.Marked target, 
			ProgressMonitor progressMonitor) {
		if (source instanceof org.nasdanika.persistence.Marked) {
			mark(((org.nasdanika.persistence.Marked) source).getMarkers(), target, progressMonitor);
		} else if (source instanceof org.nasdanika.persistence.Marker) {
			mark(Collections.singleton((org.nasdanika.persistence.Marker) source), target, progressMonitor);
		}
	}
	
	protected void mark(
			Iterable<? extends org.nasdanika.persistence.Marker> markers,
			org.nasdanika.ncore.Marked target, 
			ProgressMonitor progressMonitor) {
		if (markers != null) {
			EList<Marker> targetMarkers = target.getMarkers();
			for (org.nasdanika.persistence.Marker marker: markers) {
				targetMarkers.add(createTargetMarker(marker, progressMonitor));
			}
		}
	}
			
	protected org.nasdanika.ncore.Marker createTargetMarker(
			org.nasdanika.persistence.Marker sourceMarker, 
			ProgressMonitor progressMonitor) {
		
		if (sourceMarker == null) {
			return null;
		}
		Marker ret = NcoreFactory.eINSTANCE.createMarker();
		ret.setDate(new Date());
		ret.setLocation(sourceMarker.getLocation());
		ret.setPosition(sourceMarker.getPosition());		
		return ret;
	}
	
	protected void configureTarget(
			S obj,
			T target,
			Map<S, T> registry,
			boolean isPrototype,
			ProgressMonitor progressMonitor) {
		
		S configPrototype = getConfigPrototype(obj, progressMonitor);
		if (configPrototype != null) {
			configureTarget(
					configPrototype, 
					target, 
					registry, 
					isPrototype, 
					progressMonitor);
		}

		if (target instanceof org.nasdanika.ncore.Marked && obj instanceof org.nasdanika.persistence.Marked) {			
			mark((org.nasdanika.persistence.Marked) obj, (org.nasdanika.ncore.Marked) target, progressMonitor);
		}
		
		if (!isPrototype) {			
			String identityProperty = getIdentityProperty();
			if (!Util.isBlank(identityProperty)) {
				Object identity = getContentProvider().getProperty(obj, identityProperty);
				if (identity != null) {
					setIdentity(target, identity, true);
				}
			}
			setIdentity(target, getContentProvider().getIdentity(obj), false);
		}
		
		String name = getContentProvider().getName(obj);
		if (!Util.isBlank(name)) {
			setName(target, name);				
		}
		String description = getContentProvider().getDescription(obj);
		if (!Util.isBlank(description)) {
			setDescription(target, description);
		}
				
		URI baseUri = getContentProvider().getBaseURI(obj);
		Collection<EObject> documentation = null;
		String docProperty = getDocumentationProperty();
		if (!Util.isBlank(docProperty)) {
			Object doc = getContentProvider().getProperty(obj, docProperty);			
			if (doc instanceof String && !Util.isBlank((String) doc)) {
				String[] docFormatStr = { null };
				String docFormatProperty = getDocFormatProperty();
				if (!Util.isBlank(docFormatProperty)) {
					docFormatStr[0] = (String) getContentProvider().getProperty(obj, docFormatProperty);
				}
				if (Util.isBlank(docFormatStr[0])) {
					docFormatStr[0] = "markdown";
				}
				
				Optional<DocumentationFactory> dfo = getDocumentationFactories(progressMonitor)
					.stream()
					.filter(df -> df.canHandle(docFormatStr[0]))
					.findAny();
				
				if (dfo.isPresent()) {
					documentation = dfo.get().createDocumentation((String) doc, baseUri, progressMonitor);
					addDocumentation(target, documentation);
				} else {
					throw new ConfigurationException("Unsupported documentation format: " + docFormatStr, obj instanceof Marked ? (Marked) obj : null);
				}
			}
		}
		
		String docRefProperty = getDocRefProperty();
		if (!Util.isBlank(docRefProperty)) {
			Object docRefVal = getContentProvider().getProperty(obj, docRefProperty);
			if (docRefVal instanceof String &&  !Util.isBlank((String) docRefVal)) {					
				String docFormatProperty = getDocFormatProperty();
				DocumentationFactory docFactory = null;
				if (Util.isBlank(docFormatProperty)) {
					String docFormatStr = (String) getContentProvider().getProperty(obj, docFormatProperty);
					if (!Util.isBlank(docFormatStr)) {
						Optional<DocumentationFactory> dfo = getDocumentationFactories(progressMonitor)
								.stream()
								.filter(df -> df.canHandle(docFormatStr))
								.findAny();
						if (dfo.isPresent()) {
							docFactory = dfo.get();
						} else {
							throw new ConfigurationException("Unsupported documentation format: " + docFormatStr, obj instanceof Marked ? (Marked) obj : null);												
						}
					}
				}
				URI[] docRefURI = { null };
				if (docRefVal instanceof URI) {
					docRefURI[0] = (URI) docRefVal;
				} else if (docRefVal instanceof String) {
					docRefURI[0] = URI.createURI((String) docRefVal);
				} else {
					throw new ConfigurationException("Unsupported documentation type: " + docRefVal, obj instanceof Marked ? (Marked) obj : null);																	
				}				
				
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
						throw new ConfigurationException("Unsupported documentation URI: " + docRefURI[0], obj instanceof Marked ? (Marked) obj : null);												
					}
				}
				
				documentation =  docFactory.createDocumentation(obj, docRefURI[0], progressMonitor);
				addDocumentation(target, documentation);				
			}
		}
		
		for (Contributor<S,T> contributor: contributors) {
			if (contributor.canHandle(obj, target)) {
				contributor.configure(
						obj, 
						documentation,
						target,
						registry, 
						isPrototype, 
						progressMonitor);						
			}
		}
	}
	
	// --- Phase 5: Operations
		
	protected String getOperationsPropertyName() {
		return OPERATIONS_PROPERTY;
	}
	
	private enum OperationTarget { self, source, target }
	
	protected boolean mapOperations(
			S obj,
			T target,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		boolean result = true;
		YamlSourceRecord operationMap = loadYamlSource(obj, getOperationsPropertyName());
		if (operationMap != null) {
			try {
				Object opTargetObj = operationMap.data();
				if (opTargetObj instanceof Map) {
					Map<?,?> opTargetMap = (Map<?,?>) opTargetObj;
					for (Entry<?, ?> targetEntry: opTargetMap.entrySet()) {
						if (OperationTarget.source.name().equals(targetEntry.getKey())) {
							S connectionSource = getContentProvider().getConnectionSource(obj);
							if (connectionSource == null) {
								throw new ConfigurationException("No connection source: " + obj, getContentProvider().asMarked(obj));																																																			
							}
							target = registry.get(connectionSource);
							if (target == null) {
								throw new ConfigurationException("Connection source is not mapped: " + connectionSource, getContentProvider().asMarked(obj));																																																											
							}
						} else if (OperationTarget.target.name().equals(targetEntry.getKey())) {
							S connectionTarget = getContentProvider().getConnectionTarget(obj);
							if (connectionTarget == null) {
								throw new ConfigurationException("No connection target: " + obj, getContentProvider().asMarked(obj));																																																			
							}
							target = registry.get(connectionTarget);
							if (target == null) {
								throw new ConfigurationException("Connection target is not mapped: " + connectionTarget, getContentProvider().asMarked(obj));																																																											
							}							
						} else if (OperationTarget.self.name().equals(targetEntry.getKey())) {
							if (target == null) {
								throw new ConfigurationException("The element is not mapped (target is null)", getContentProvider().asMarked(obj));																																																											
							}							
						} else {
							throw new ConfigurationException("Usupported operation target: " + targetEntry.getKey(), getContentProvider().asMarked(obj));																																																		
						}

						Object opMapObj = targetEntry.getValue();
						if (opMapObj instanceof Map) {
							Map<?,?> opMap = (Map<?,?>) opMapObj;
							EClass eClass = target.eClass();
							for (EOperation eOperation: eClass.getEAllOperations()) {
								Object opSpec = opMap.get(eOperation.getName());
								if (opSpec != null) {
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
													throw new ConfigurationException("Usupported operation pass type: " + passObj, getContentProvider().asMarked(obj));																																				
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
																throw new ConfigurationException("Usupported operation argument expression type: " + val, getContentProvider().asMarked(obj));																																																													
															}
														} else {
															throw new ConfigurationException("Usupported operation parameter name type: " + key, getContentProvider().asMarked(obj));																																																
														}
													}
												} else {
													throw new ConfigurationException("Usupported operation arguments type: " + arguments, getContentProvider().asMarked(obj));																																				
												}																		
												
												Object selector = opSpecElementMap.get(SELECTOR_KEY);
												if (selector instanceof String) {
													if (!mapper.evaluatePredicate(eOperation, (String) selector, null, obj)) {
														continue;
													}
												} else if (selector != null) {
													throw new ConfigurationException("Usupported operation selector type: " + selector, getContentProvider().asMarked(obj));																										
												}
												
												Iterator<?> it = Collections.singleton(obj).iterator();
												
												Object iterator = opSpecElementMap.get(ITERATOR_KEY);
												if (iterator instanceof String) {										
													Object itVal = mapper.evaluate(
															obj, 
															(String) iterator,												
															Map.of(REGISTRY_VAR, registry),
															Object.class,
															obj);
													
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
													throw new ConfigurationException("Usupported operation iterator type: " + iterator, getContentProvider().asMarked(obj));																										
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
																		obj);
																argList.add(arg);
															}
														}
														try {
															target.eInvoke(eOperation, argList);
														} catch (InvocationTargetException e) {
															throw new ConfigurationException("Error invoking eOperation " + eOperation, e, getContentProvider().asMarked(obj));																
														}
													}
												}
											} else {
												throw new ConfigurationException("Usupported operation spec element type: " + opSpecElement, getContentProvider().asMarked(obj));																
											}								
										}
									} else {
										throw new ConfigurationException("Usupported operation spec type: " + opSpec, getContentProvider().asMarked(obj));							
									}	
								}
							}
						} else {				
							throw new ConfigurationException("Usupported operation map type: " + opMapObj, getContentProvider().asMarked(obj));
						}
					}
				} else {
					throw new ConfigurationException("Usupported operation target type: " + opTargetObj, getContentProvider().asMarked(obj));					
				}
			} catch (YAMLException yamlException) {
				throw new ConfigurationException("Error loading operation map: " + yamlException, yamlException, getContentProvider().asMarked(obj));
			}
		}	
		return result;
	}
	
	// --- Phase 6: Invocable
	
	protected String getInvokePropertyName() {
		return INVOKE_PROPERTY;
	}
	
	/**
	 * Calls invocable
	 * @param diagramElement
	 * @param semanticElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	protected boolean invoke(
			S obj,
			T target,
			Map<S, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String ipn = getInvokePropertyName();
		if (Util.isBlank(ipn)) {
			return true;
		}
		
		Object invokeVal = getContentProvider().getProperty(obj, ipn);
		if (invokeVal == null) {
			return true;
		}
		URI invokeURI;
		if (invokeVal instanceof URI) {
			invokeURI = (URI) invokeVal;
		} else if (invokeVal instanceof String) {
			String invokeStr = (String) invokeVal;
			if (Util.isBlank(invokeStr)) {
				return true;
			}
			invokeURI = URI.createURI(invokeStr);
			URI baseURI = getContentProvider().getBaseURI(obj);
			if (baseURI != null && !baseURI.isRelative()) {
				invokeURI = invokeURI.resolve(baseURI);
			}
		} else {
			throw new ConfigurationException("Unsupported invoke type: " + invokeVal, getContentProvider().asMarked(obj));			
		}
		
		Invocable invocable = capabilityLoader.loadOne(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, invokeURI),
				progressMonitor);
		
		Object result = configureInitializer(obj, invocable)
				.bindByName(TARGET_VAR, target)
				.bindByName(PASS_VAR, pass)
				.bindByName(REGISTRY_VAR, registry)	
				.bindByName(CONTENT_PROVIDER_VAR, getContentProvider())	
				.bindByName(PROGRESS_MONITOR_VAR, progressMonitor)	
				.invoke(obj);
		
		// returns false if the invocable explicity returned false
		return Boolean.FALSE.equals(result) ? false : true;
	}
	
	/**
	 * Override to configure the invocable. E.g. bind additional arguments.
	 * This method binds variables returned from <code>getVariables()</code>
	 * @param invocable
	 * @return
	 */
	protected Invocable configureInvocable(S source, Invocable invocable) {
		Map<String, Object> variables = getVariables(source);
		if (variables == null || variables.isEmpty()) {
			return invocable;
		}
		return invocable.bindMap(variables);
	}
	
	/**
	 * Creates a comparator for the mapper.
	 * @param target
	 * @param comparatorConfig
	 * @param registry
	 * @param context
	 * @return
	 */
	protected Comparator<Object> createMapperComparator(
			T target, 
			Object comparatorConfig, 
			Map<S, T> registry,
			S context, 
			ProgressMonitor progressMonitor) {
	
		return null;
	}	
	
}
