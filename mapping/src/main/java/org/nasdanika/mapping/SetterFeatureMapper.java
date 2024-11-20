package org.nasdanika.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.persistence.ConfigurationException;
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
 * Content mapper which uses feature setters loaded from configuration to wire content 
 * @param <S>
 * @param <T>
 */
public abstract class SetterFeatureMapper<S, T extends EObject> extends FeatureMapper<S, T> {
	
	private static final String PROGRESS_MONITOR_VAR = "progressMonitor";
	private static final String ARGUMENT_VALUE_VAR = "argumentValue";
	private static final String TYPE_VAR = "type";
	private static final String BASE_URI_VAR = "baseURI";
	private static final String SOURCE_PATH_VAR = "sourcePath";
	private static final String CONTEXT_VAR = "context";
	private static final String OTHER_KEY = "other";
	private static final String KEY_KEY = "key";
	private static final String NATURAL_KEY = "natural";
	private static final String REGISTRY_VAR = "registry";
	private static final String PATH_VAR = "path";
	private static final String VALUE_VAR = "value";
	public static final String PATH_KEY = PATH_VAR;
	public static final String CONDITION_KEY = "condition";
	public static final String NOP_KEY = "nop";
	public static final String EXPRESSION_KEY = "expression";
	public static final String GREEDY_KEY = "greedy";
	public static final String GREEDY_NO_CHILDREN_KEY = "no-children";
	public static final String POSITION_KEY = "position";
	public static final String TYPE_KEY = TYPE_VAR;
	public static final String ARGUMENT_TYPE_KEY = "argument-type";
	public static final String COMPARATOR_KEY = "comparator";
	public static final String INVOKE_KEY = "invoke";
	
	private FeatureMapper<S, T> defaultFeatureMapper;
	protected CapabilityLoader capabilityLoader;
	
	protected SetterFeatureMapper(ContentProvider<S> contentProvider, CapabilityLoader capabilityLoader) {
		super(contentProvider);
		this.capabilityLoader = capabilityLoader;
	}
	
	protected SetterFeatureMapper(Mapper<S,T> chain, ContentProvider<S> contentProvider, CapabilityLoader capabilityLoader) {
		super(chain, contentProvider);
		this.capabilityLoader = capabilityLoader;
	}
		
	/**
	 * 
	 * @param chain <code>chain.wire()</code> is called by the <code>wire()</code> method of this class after this class performs its own wiring.
	 * @param defaulFeaturetMapper methods of defaultFeatureMapper are called by respective methods of this class if there is no feature setter.
	 */
	protected SetterFeatureMapper(
			Mapper<S,T> chain, 
			ContentProvider<S> contentProvider, 
			CapabilityLoader capabilityLoader,
			FeatureMapper<S,T> defaulFeaturetMapper) {
		this(chain, contentProvider, capabilityLoader);
		this.defaultFeatureMapper = defaulFeaturetMapper; 
	}	
	
	/**
	 * @param source
	 * @return Feature mapping configuration YAML string.
	 */
	protected abstract Object getFeatureMapConfig(S source);
	
	/**
	 * Resolves type
	 * @param type
	 * @param context
	 * @return
	 */
	protected abstract EClassifier getType(String type, S context);
	
	protected interface Setter<S, T extends EObject> {
		
		/**
		 * Possibly sets a feature. Returns false if some preconditions were not met and the setter shall be invoked at a later phase
		 * @param eObj
		 * @param feature
		 * @param value
		 * @param sourcePath
		 * @param registry
		 * @param defaultFeatureMapper Is called in case of this setter does not match
		 * @param progressMonitor
		 * @return
		 */
		boolean setFeature(
				T eObj, 
				EStructuralFeature feature,
				S argument,
				T argumentValue,
				LinkedList<S> sourcePath, 
				Map<S, T> registry, 
				ProgressMonitor progressMonitor);
		
	}
	
	protected enum ConfigType { 
		self,
		container, 
		contents, 
		/**
		 * Feature map configuration of the connection source, the connection or its target semantic object is passed as an argument
		 */
		source, 
		/**
		 * Feature map configuration of the connection target, the connection or its source semantic object is passed as an argument 
		 */		
		target, 
		/**
		 * Feature map configuration of the connection start, the connection source is passed as an argument
		 */
		start, 
		/**
		 * Feature map configuration of the conection end, the connection target is passed as an argument
		 */
		end }
	
	protected enum ConfigSubType { self, other }
		
	protected boolean matchCondition(
			Object config, 
			T eObj,
			S argument,
			T argumentValue,
			LinkedList<S> sourcePath,
			Map<S, T> registry,
			S context,
			ProgressMonitor progressMonitor) {
		if (config == Boolean.TRUE) {
			return true;
		}
	
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Object condition = configMap.get(CONDITION_KEY);
			if (condition == null) {
				return true;
			}
			if (condition instanceof String) {
				Map<String,Object> variables = new LinkedHashMap<>();
				variables.put(VALUE_VAR, argumentValue);
				variables.put(PATH_VAR, sourcePath);
				variables.put(REGISTRY_VAR, registry);
				variables.put(PROGRESS_MONITOR_VAR, progressMonitor);
				return evaluatePredicate(argument, (String) condition, variables, context);
			}
			throw new ConfigurationException("Unsupported condition type: " + condition.getClass() + " " + condition, null, getContentProvider().asMarked(context));
		}
		
		throw new ConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, getContentProvider().asMarked(context));
	}	

	public boolean evaluatePredicate(
			Object obj, 
			String expr, 
			Map<String,Object> variables, 
			S context) {
		
		if (Util.isBlank(expr)) {
			return true;
		}
		return evaluate(obj, expr, variables, Boolean.class, context);
	}

	public <V> V evaluate(
			Object obj, 
			String expr, 
			Map<String,Object> variables, 
			Class<V> resultType, 
			S context) {
		
		if (Util.isBlank(expr)) {
			return null;
		}
		
		try {			
			ExpressionParser parser = createExpressionParser(context);
			Expression exp = parser.parseExpression(expr);
			EvaluationContext evaluationContext = createEvaluationContext(context);
			if (variables != null) {
				variables.entrySet().forEach(ve -> evaluationContext.setVariable(ve.getKey(), ve.getValue()));
			}
			return evaluationContext == null ? exp.getValue(obj, resultType) : exp.getValue(evaluationContext, obj, resultType);
		} catch (ParseException e) {
			throw new ConfigurationException("Error parsing expression: '" + expr, e, getContentProvider().asMarked(context));
		} catch (EvaluationException e) {
			throw new ConfigurationException("Error evaluating expression: '" + expr + "' in the context of " + obj + " with variables " + variables, e, getContentProvider().asMarked(context));
		}
	}
	
	protected EvaluationContext createEvaluationContext(S context) {
		return new StandardEvaluationContext();
	}

	protected SpelExpressionParser createExpressionParser(S context) {
		return new SpelExpressionParser();
	}	
		
	/**
	 * 
	 * @param source
	 * @param isContents
	 * @param isArgument
	 * @return
	 */
	protected Object getFeatureMapConfig(S source, ConfigType configType, ConfigSubType configSubType) {
		Object config = getFeatureMapConfig(source);
		if (config == null || (config instanceof String && Util.isBlank((String) config))) {
			return null;
		}
		try {
			Yaml yaml = new Yaml();
			Object configObj = config instanceof String ? yaml.load((String) config) : config;
			if (configObj instanceof Map) {
				Map<?,?> configMap = (Map<?,?>) configObj;
				// Validating keys
				Z: for (Object ck: configMap.keySet()) {
					if (ck instanceof String) {
						for (ConfigType ct: ConfigType.values()) {
							if (ct.name().equals(ck)) {
								continue Z;
							}
						}
					}
					throw new ConfigurationException("Unsupported config type: " + ck, null, getContentProvider().asMarked(source));
				}
				Object subConfigObj = configMap.get(configType.name());
				if (subConfigObj == null) {
					return null;
				}
				
				if (configType == ConfigType.container || configType == ConfigType.contents) {				
					if (subConfigObj instanceof Map) {
						// validate sub conf type names
						Map<?,?> subConfigMap = (Map<?,?>) subConfigObj;
						Y: for (Object sck: subConfigMap.keySet()) {
							if (sck instanceof String) {
								for (ConfigSubType cst: ConfigSubType.values()) {
									if (cst.name().equals(sck)) {
										continue Y;
									}
								}
							}
							throw new ConfigurationException("Unsupported config subtype: " + sck, null, getContentProvider().asMarked(source));
						}
						
						return subConfigMap.get(configSubType.name());
					}
				} else {
					return subConfigObj;
				}
				
				throw new ConfigurationException("Usupported configuration type: " + subConfigObj, null, getContentProvider().asMarked(source));
			}
			
			throw new ConfigurationException("Usupported configuration type: " + configObj, null, getContentProvider().asMarked(source));
		} catch (YAMLException yamlException) {
			throw new ConfigurationException(null, yamlException, getContentProvider().asMarked(source));
		}
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Setter<S,T>> loadFeatureSetters(
			Object configs, 
			java.util.function.BiConsumer<String, S> featureNameValidator, 
			S context, 
			Setter<S, T> chain) {
		
		if (configs == null) {
			return Collections.emptyMap();
		}
		if (configs instanceof String) {
			String strConfig = (String) configs;
			if (Util.isBlank(strConfig)) {
				return Collections.emptyMap();
			}
			String trimmedKey = strConfig.trim();
			featureNameValidator.accept(trimmedKey, context);
			return Collections.singletonMap(trimmedKey, loadFeatureSetter(true, context, chain));
		} 
		
		if (configs instanceof Collection) {
			Map<String, Setter<S,T>> ret = new LinkedHashMap<>();
			for (Object e: (Collection<?>) configs) {
				if (e instanceof String) {
					String se = (String) e; 
					if (!Util.isBlank(se)) {
						String trimmedKey = se.trim();
						featureNameValidator.accept(trimmedKey, context);
						ret.put(trimmedKey, loadFeatureSetter(true, context, chain));
					}
				} else {
					throw new ConfigurationException("Feature config in a collection shall be a string, got: " + e, null, getContentProvider().asMarked(context));
				}
			}
			return ret;
		}
		
		if (configs instanceof Map) {
			Map<String, Setter<S,T>> ret = new LinkedHashMap<>();
			for (Entry<String, Object> fce: ((Map<String,Object>) configs).entrySet()) {
				String trimmedKey = fce.getKey().trim();
				featureNameValidator.accept(trimmedKey, context);
				ret.put(trimmedKey, loadFeatureSetter(fce.getValue(), context, chain));
			}
			return ret;
		}
		
		throw new ConfigurationException("Unexpected config type: " + configs, null, getContentProvider().asMarked(context));
	}
			
	protected Collection<S> getSources(Object target, Map<S, T> registry, @SuppressWarnings("unchecked") Predicate<S>... predicates) {
		Collection<S> result = new ArrayList<>();
		if (target != null) {
			Z: for (Entry<S, T> re: registry.entrySet()) {
				if (Objects.equals(target, re.getValue())) {
					S source = re.getKey();
					for (Predicate<S> predicate: predicates) {
						if (!predicate.test(source)) {
							continue Z;
						}
					}
					result.add(source);
				}
			}
		}
		return result;
	}	
	
	protected enum Greedy { FALSE, NO_CHILDREN, TRUE }
	
	protected boolean isAncestor(S ancestor, S obj) {
		if (ancestor == null || obj == null) {
			return false;
		}
		S parent = getContentProvider().getParent(obj);
		return parent != null && (parent.equals(ancestor) || isAncestor(ancestor, parent)); 
	};
	
	protected Setter<S,T> loadFeatureSetter(Object config, S context, Setter<S, T> chain) {
		return new Setter<S, T>() {

			@SuppressWarnings("unchecked")
			@Override
			public boolean setFeature(
					T target, 
					EStructuralFeature feature, 
					S argument,
					T argumentValue,
					LinkedList<S> sourcePath,
					Map<S, T> registry, 					
					ProgressMonitor progressMonitor) {

				boolean shallCallChain = true;
				for (Object configElement: (config instanceof Iterable ? (Iterable<?>) config : Collections.singleton(config))) {
					if (matchCondition(configElement, target, argument, argumentValue, sourcePath, registry, context, progressMonitor)
							&& matchType(target, configElement, context)
							&& matchArgumentType(argumentValue, configElement, context)
							&& matchPath(configElement, sourcePath, registry, context)) {
						
						if (isNop(configElement)) {
							return true;
						}
						
						Class<?> featureType = feature.getEType().getInstanceClass();
						Object featureValue = eval(configElement, argument, argumentValue, sourcePath, registry, featureType, context, progressMonitor);
						if (configElement instanceof Map) {
							featureValue = invoke(
									(Map<?,?>) configElement, 
									argument,
									argumentValue,
									sourcePath,
									registry,
									featureType,
									context,
									progressMonitor);
						}

						if (featureValue != null) {
							boolean shallSet = true;
							
							if (feature instanceof EReference) {
								shallSet = feature.getEType().isInstance(featureValue);
								
								if (shallSet 
										&& ((EReference) feature).isContainment()
										&& featureValue instanceof EObject) {
									
									EObject featureValueEObject = (EObject) featureValue;
									EObject fvc = featureValueEObject.eContainer();
									if (fvc != null) {
										Greedy greedy = getGreedy(configElement, context);
										if (greedy == Greedy.FALSE) {
											shallSet = false;
										} else if (greedy == Greedy.NO_CHILDREN) {
											for (S eObjSource: getSources(target, registry) ) {
												for (S fvcSource: getSources(fvc, registry)) {
													if (isAncestor(eObjSource, fvcSource)) {
														shallSet = false; // No taking from children
													}													
												}												
											}
										}
									}
								}
							}
							
							if (shallSet) {
								Object element = convert(featureValue, feature.getEType(), context);								
								if (feature.isMany()) {
									List<Object> fvl = (List<Object>) target.eGet(feature);
									int position = getPosition(configElement, context);
									if (position == -1) {
										if (fvl.isEmpty()) {										
											fvl.add(element);
										} else {
											Comparator<Object> comparator = getComparator(
													target,
													feature,
													configElement, 
													registry, 
													context,
													progressMonitor);
											if (comparator == null) {
												fvl.add(element);												
											} else {											
												// Iterating and comparing
												boolean added = false;
												for (int i = 0; i < fvl.size(); ++i) {
													Object fvle = fvl.get(i);
													if (comparator.compare(element, fvle) < 0) {
														fvl.add(i, element);
														added = true;
														break;
													}
												}
												if (!added) {
													fvl.add(element);
												}
											}
										}
									} else {
										fvl.add(position, element);								
									}
								} else {
									target.eSet(feature, element);
								}
								shallCallChain = false;
							}
						}	
					}
				}
				
				if (shallCallChain && chain != null) {
					return chain.setFeature(
							target, 
							feature, 
							argument, 
							argumentValue, 
							sourcePath, 
							registry, 
							progressMonitor);
				}
				
				return true;
			}
			
		};
	}
	
	/**
	 * NOP means "If matches - do nothing and don't call the chain" 
	 * Use nop to suppress chained feature mapping. E.g. by default all element children are added to XYZ reference, but a given child shall not be added to any reference. 
	 * @param config
	 * @return
	 */
	protected boolean isNop(Object config) {
		return config instanceof Map &&  Boolean.TRUE.equals(((Map<?,?>) config).get(NOP_KEY));
	}	
	
	
	protected Object convert(Object value, EClassifier type, S context) {
		if (value == null || type.isInstance(value)) {
			return value;
		}
		
		Object converted = DefaultConverter.INSTANCE.convert(value, type.getInstanceClass());
		if (converted == null) {
			throw new ConfigurationException("Cannot convert " + value + " to " + type.getInstanceClass(), null, getContentProvider().asMarked(context));
		}
		return converted;
	}
			
	protected boolean matchPath(Object config, LinkedList<S> path, Map<S, T> registry, S context) {
		if (config == Boolean.TRUE) {
			return true;
		}
		
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Object pathVal = configMap.get(PATH_KEY);
			if (pathVal == null) {
				return true;
			}
			if (pathVal instanceof Number) {
				return path.size() == ((Number) pathVal).intValue();
			}
			if (pathVal instanceof Collection) {
				Collection<?> pathConditions = (Collection<?>) pathVal;
				if (pathConditions.size() != path.size()) {
					return false;
				}
				Map<String,Object> variables = new LinkedHashMap<>();
				variables.put(REGISTRY_VAR, registry);
				
				Iterator<S> pathIterator = path.iterator();				
				for (Object condition: pathConditions) {
					S pathElement = pathIterator.next();
					if (condition instanceof Boolean) {
						if (Boolean.FALSE.equals(condition)) {
							return false;
						}
					} else if (condition instanceof String) {
						if (!evaluatePredicate(pathElement, (String) condition, variables, context));
					} else {
						throw new ConfigurationException("Unsupported path condition type: " + condition.getClass() + " " + condition, null, getContentProvider().asMarked(context));						
					}
				}
			}
		}
		
		throw new ConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, getContentProvider().asMarked(context));
	}
		
	protected boolean matchType(T eObj, Object config, S context) {
		if (config == Boolean.TRUE) {
			return true;
		}
		
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Object type = configMap.get(TYPE_KEY);
			if (type == null) {
				return true;
			}
			if (type instanceof String) {
				EClassifier eClassifier = getType((String) type, context);
				return eClassifier.isInstance(eObj);
			}
		}
		
		throw new ConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, getContentProvider().asMarked(context));
	}
	
	protected boolean matchArgumentType(Object argument, Object config, S context) {
		if (argument == null) {
			return false;
		}
		
		if (config == Boolean.TRUE) {
			return true;
		}
		
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Object argumentType = configMap.get(ARGUMENT_TYPE_KEY);
			if (argumentType == null) {
				return true;
			}
			if (argumentType instanceof String) {
				return matchArgumentTypeValue(argument, (String) argumentType, context);
			}
			if (argumentType instanceof Iterable) {
				for (Object ate: ((Iterable<?>) argumentType)) {
					if (ate instanceof String) {
						if (matchArgumentTypeValue(argument, (String) ate, context)) {
							return true;
						}
					} else if (ate != null) {
						throw new ConfigurationException("Unsupported argument-type element type: " + ate.getClass() + " " + ate, null, getContentProvider().asMarked(context));						
					}
				}
				return false;
			}
		}
		
		throw new ConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, getContentProvider().asMarked(context));
	}
	
	/**
	 * Matches argument type taking negation into account (!)
	 * @param argument
	 * @param value
	 * @param context
	 */
	protected boolean matchArgumentTypeValue(Object argument, String value, S context) {
		boolean isNegation = value.startsWith("!");
		String typeName = isNegation ? value.substring(1) : value;
		EClassifier eClassifier = getType(typeName, context);
		return eClassifier.isInstance(argument) ^ isNegation;
	}
	
	
	/**
	 * A comparator of elements for many features.
	 * @param target Semantic element receiving objects being compared. For some types of comparators the semantic element may be used to compute the base coordinates/geometry for comparison.
	 * @param feature Semantic element's many feature into which object being compared are injected (set)     
	 * @param config
	 * @param registry
	 * @param context
	 * @return
	 */
	public Comparator<Object> getComparator(
			T target,
			EStructuralFeature feature,
			Object config, 
			Map<S, T> registry, 
			S context,
			ProgressMonitor progressMonitor) {
		if (config == Boolean.TRUE) {
			return null;
		}
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Object comparatorConfig = configMap.get(COMPARATOR_KEY);
			if (comparatorConfig == null) {
				return null;
			}
			return createComparator(
					target,
					comparatorConfig, 
					registry, 
					context,
					progressMonitor);
		}
		throw new ConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, getContentProvider().asMarked(context));
	}
	
	public static final Comparator<Object> NATURAL_COMPARATOR = new Comparator<Object>() {
		
		@SuppressWarnings("unchecked")
		@Override
		public int compare(Object o1, Object o2) {
			if (Objects.equals(o1,  o2)) {
				return 0;
			}
			if (o1 instanceof Comparable) {
				return ((Comparable<Object>) o1).compareTo(o2);
			}
			if (o2 instanceof Comparable) {
				return - ((Comparable<Object>) o2).compareTo(o1);
			}
			
			if (o1 == null) {
				return 1;
			}
			
			if (o2 == null) {
				return -1;
			}
			
			return o1.hashCode() - o2.hashCode();
		}
	};	
	
	/**
	 * Creates a comparator from config. This implementation supports the following comparators:
	 * 
	 * <UL>
	 * <LI>natural (String) - objects being compared shall implement Comparable. if not, they are compared by hashCode value. Nulls are considered larger than non-nulls</LI>
	 * <LI>key - maps to a SpEL expression which evaluates a comparison key. The keys are then compared using natural comparison</LI>
	 * <LI>expression - maps to a SpEL expression evaluated in the context of the first element with the second element in the <code>other</code> variable. The expression shall return an integer</LI>
	 * </UL>
	 * SpEL expressions have access to a <code>registry</code> variable
	 * Override to add support of more comparators
	 * @param comparatorConfig
	 * @param registry
	 * @param context
	 * @return
	 */
	public Comparator<Object> createComparator(
			T target,
			Object comparatorConfig, 
			Map<S, T> registry, 
			S context,
			ProgressMonitor progressMonitor) {
		if (NATURAL_KEY.equals(comparatorConfig)) {
			return NATURAL_COMPARATOR;
		} 
		
		if (comparatorConfig instanceof Map) {
			Map<?, ?> comparatorConfigMap = (Map<?,?>) comparatorConfig;
			if (comparatorConfigMap.size() == 1) {
				for (Entry<?, ?> cce: comparatorConfigMap.entrySet()) {
					if (KEY_KEY.equals(cce.getKey()) && cce.getValue() instanceof String) {
						return new Comparator<Object>() {
							
							@Override
							public int compare(Object o1, Object o2) {
								Object key1 = evaluate(
										o1, 
										(String) cce.getValue(), 
										Map.of(REGISTRY_VAR, registry), 
										Object.class, 
										context);
								
								Object key2 = evaluate(
										o2, 
										(String) cce.getValue(), 
										Map.of(REGISTRY_VAR, registry), 
										Object.class, 
										context);
								
								return NATURAL_COMPARATOR.compare(key1, key2);
							}
						};
					} 
					
					if (EXPRESSION_KEY.equals(cce.getKey()) && cce.getValue() instanceof String) {
						return new Comparator<Object>() {

							@Override
							public int compare(Object o1, Object o2) {
								return evaluate(
										o1, 
										(String) cce.getValue(), 
										Map.of(
												OTHER_KEY, o2,
												REGISTRY_VAR, registry,
												PROGRESS_MONITOR_VAR, progressMonitor), 
										Integer.class, 
										context);
							}							
						};
					}
				}
			}
		}
		
		throw new ConfigurationException("Unsupported comparator config: " + comparatorConfig, null, getContentProvider().asMarked(context));
	}
	
	protected int getPosition(Object config, S context) {
		if (config == Boolean.TRUE) {
			return -1;
		}
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Object position = configMap.get(POSITION_KEY);
			if (position == null) {
				return -1;
			}
			if (position instanceof Number) {
				return ((Number) position).intValue();
			}
			throw new ConfigurationException("Unsupported position type: " + position.getClass() + " " + position, null, getContentProvider().asMarked(context));
		}
		throw new ConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, getContentProvider().asMarked(context));
	}
	
	protected Object eval(
			Object config,
			S argument,
			T argumentValue,
			LinkedList<S> sourcePath,
			Map<S, T> registry,
			Class<?> type,
			S context,
			ProgressMonitor progressMonitor) {
		if (config == Boolean.TRUE) {
			return argumentValue;
		}
		Object expression;
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			expression = configMap.get(EXPRESSION_KEY);
			if (expression == null) {
				return argumentValue;
			}
			if (expression instanceof Collection) {
				Collection<Object> ret = new ArrayList<>();
				for (Object ee: (Collection<?>) expression) {
					ret.add(eval(ee, argument, argumentValue, sourcePath, registry, type, context, progressMonitor));
				}
				return ret;
			}
			if (!(expression instanceof String)) {
				return expression;
			}			
		} else if (config instanceof String) {
			expression = config;
		} else {
			return config; // Number, date, ...
		}
		
		Map<String,Object> variables = new LinkedHashMap<>();
		variables.put(VALUE_VAR, argumentValue);
		variables.put(PATH_VAR, sourcePath);
		variables.put(REGISTRY_VAR, registry);
		variables.put(PROGRESS_MONITOR_VAR, progressMonitor);
		Object result = evaluate(argument, (String) expression, variables, type, context);
		return result;
	}
	
	protected URI getInvokeURI(Map<?,?> configMap, S context) {
		Object invokeVal = configMap.get(INVOKE_KEY);
		if (invokeVal == null) {
			return null;
		}
		if (invokeVal instanceof String) {
			String invokeStr = (String) invokeVal;
			if (Util.isBlank(invokeStr)) {
				return null;
			}

			URI invokeURI = URI.createURI(invokeStr);
			if (invokeURI.isRelative()) {
				URI baseURI = getContentProvider().getBaseURI(context);
				if (baseURI != null && !baseURI.isRelative()) {
					invokeURI = invokeURI.resolve(baseURI);
				}
			}
			return invokeURI;
		}
		throw new ConfigurationException("Invoke value is not a string: " + invokeVal, null, getContentProvider().asMarked(context));	
	}
	
	protected ClassLoader getClassLoader(S obj) {
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * Override to provide variables for expressions and invocable.
	 * 
	 * @return
	 */
	protected Map<String, Object> getVariables(S context) {
		return Collections.emptyMap();
	}	
	
	protected Object invoke(
			Map<?,?> configMap, 
			Object argument,
			Object argumentValue,
			LinkedList<S> sourcePath,
			Map<S, T> registry,
			Class<?> type,
			S context,
			ProgressMonitor progressMonitor) {		
		URI invokeURI = getInvokeURI(configMap, context);
		
		if (invokeURI == null) {
			return argumentValue;
		}
					
		Invocable invocable = capabilityLoader.loadOne(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, invokeURI),
				progressMonitor);

		invocable.bindByName(ARGUMENT_VALUE_VAR, argumentValue);
		invocable.bindByName(BASE_URI_VAR, getContentProvider().getBaseURI(context));
		invocable.bindByName(CONTEXT_VAR, context);
		invocable.bindByName(PROGRESS_MONITOR_VAR, progressMonitor);
		invocable.bindByName(REGISTRY_VAR, registry);
		invocable.bindByName(SOURCE_PATH_VAR, sourcePath);
		invocable.bindByName(TYPE_VAR, type);
		// registry
		for (Entry<String, Object> ve: getVariables(context).entrySet()) {
			invocable.bindByName(ve.getKey(), ve.getValue());
		}
		
		return invocable.invoke(argument);
	}
	
	protected Greedy getGreedy(Object config, S context) {
		if (config == Boolean.TRUE) {
			return Greedy.NO_CHILDREN;
		}
		
		if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			Object greedy = configMap.get(GREEDY_KEY);
			if (greedy == null) {
				return Greedy.NO_CHILDREN;
			}
			if (greedy instanceof Boolean) {
				return (Boolean) greedy ? Greedy.TRUE : Greedy.FALSE;
			}
			throw new ConfigurationException("Unsupported greedy type: " + greedy.getClass() + " " + greedy, null, getContentProvider().asMarked(context));
		}
		throw new ConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, getContentProvider().asMarked(context));
	}
	
	protected Map<String,Setter<S,T>> getFeatureSetters(
			S source,
			ConfigType configType, 
			ConfigSubType configSubType,
			java.util.function.BiConsumer<String, S> featureNameValidator,
			Setter<S, T> chain) {
		Object config = getFeatureMapConfig(source, configType, configSubType);
		if (configType == ConfigType.self && config instanceof Map) {
			// Converting to maps with expression key
			config = ((Map<?,?>) config)
				.entrySet()
				.stream()
				.map(e -> Map.entry(e.getKey(), Collections.singletonMap(EXPRESSION_KEY, e.getValue())))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		}
		return loadFeatureSetters(config, featureNameValidator, source, chain);		
	}	
	
	protected Setter<S,T> getFeatureSetter(
			S source, 
			ConfigType configType, 
			ConfigSubType configSubType, 
			String featureName,
			java.util.function.BiConsumer<String, S> featureNameValidator, 
			Setter<S, T> chain) {
		return getFeatureSetters(
				source, 
				configType, 
				configSubType, 
				featureNameValidator,
				chain)
			.get(featureName);
	}
	
	protected Setter<S,T> getFeatureSetter(
			S source, 
			ConfigType configType, 
			ConfigSubType configSubType, 
			EStructuralFeature feature,
			java.util.function.BiConsumer<String, S> featureNameValidator,
			Setter<S, T> chain) {
		return getFeatureSetter(
				source, 
				configType, 
				configSubType, 
				feature.getName(), 
				featureNameValidator, 
				chain);
	}
	
	protected java.util.function.BiConsumer<String, S> getFeatureNameValidator(EObject value) { 
		return (featureName, context) -> {
			EClass valueEClass = value.eClass();
			if (valueEClass.getEStructuralFeature(featureName) == null) {
				throw new ConfigurationException("Feature " + featureName + " not found in " + valueEClass.getName(), null, getContentProvider().asMarked(context));
			}
		};
	}
	
	
	@Override
	protected void wireFeature(
			S source, 
			T value, 
			EStructuralFeature valueFeature, 
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		
		Setter<S,T> chain = defaultFeatureMapper == null ? null : (eObj, feature, argument, argumentValue, sPath, reg, pm) -> {
			defaultFeatureMapper.wireFeature(
					source, 
					value, 
					valueFeature, 
					registry, 
					progressMonitor);
			return true;
		};							
		
		// Own (self) feature mapping
		Setter<S,T> featureSetter = getFeatureSetter(
				source, 
				ConfigType.self, 
				null, 
				valueFeature, 
				getFeatureNameValidator(value), 
				chain);
		
		if (featureSetter == null) {
			featureSetter = chain;
		}
		
		if (featureSetter != null) {
			featureSetter.setFeature(
					value, 
					valueFeature, 
					source, 
					null, 
					null, 
					registry,
					progressMonitor);
		}
	}
	@Override
	protected boolean wireContainerFeature(
			S container, 
			T containerValue, 
			EStructuralFeature containerValueFeature,
			S contents, 
			T contentsValue, 
			LinkedList<S> sourcePath, 
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		
		Setter<S,T> chain = defaultFeatureMapper == null ? null : (eObj, feature, argument, argumentValue, sPath, reg, pm) -> {
			return defaultFeatureMapper.wireContainerFeature(
					container, 
					containerValue, 
					containerValueFeature, 
					contents, 
					contentsValue, 
					sourcePath, 
					registry, 
					progressMonitor);
		};							
				
		// Own (self) feature mapping
		Setter<S,T> featureSetter = getFeatureSetter(
				container, 
				ConfigType.container, 
				ConfigSubType.self, 
				containerValueFeature, 
				getFeatureNameValidator(containerValue),
				chain);
		
		if (featureSetter == null) {
			// Mapping defined at the argument (contents)
			featureSetter = getFeatureSetter(
					contents, 
					ConfigType.container, 
					ConfigSubType.other, 
					containerValueFeature, 
					getFeatureNameValidator(containerValue),
					chain);
		}
		
		if (featureSetter == null) {
			featureSetter = chain;
		}
		
		if (featureSetter != null) {
			return featureSetter.setFeature(
					containerValue, 
					containerValueFeature, 
					contents, 
					contentsValue, 
					sourcePath, 
					registry,
					progressMonitor);
		}

		return true;
	}
	
	@Override
	protected boolean wireContentsFeature(
			S contents, 
			T contentsValue, 
			EStructuralFeature contentsValueFeature,
			S container, 
			T containerValue, 
			LinkedList<S> sourcePath, 
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		
		Setter<S,T> chain = defaultFeatureMapper == null ? null : (eObj, feature, argument, argumentValue, sPath, reg, pm) -> {
			return defaultFeatureMapper.wireContentsFeature(
					contents, 
					contentsValue, 
					contentsValueFeature, 
					container, 
					containerValue, 
					sourcePath, 
					registry, 
					progressMonitor);
		};							
		
		// Own (self) feature mapping
		Setter<S,T> featureSetter = getFeatureSetter(
				contents, 
				ConfigType.contents, 
				ConfigSubType.self, 
				contentsValueFeature, 
				getFeatureNameValidator(contentsValue),
				chain);
		
		if (featureSetter == null) {
			// Mapping defined at container
			featureSetter = getFeatureSetter(
					container, 
					ConfigType.contents, 
					ConfigSubType.other, 
					contentsValueFeature, 
					getFeatureNameValidator(contentsValue),
					chain);
		}
		
		if (featureSetter == null) {
			featureSetter = chain;
		}
		
		if (featureSetter != null) {
			return featureSetter.setFeature(
					contentsValue, 
					contentsValueFeature, 
					container, 
					containerValue, 
					sourcePath, 
					registry,
					progressMonitor);
		}
				
		return true;
	}
	
	@Override
	protected void wireConnectionSourceFeature(
			S connection, 
			S connectionSource, 
			T connectionSourceValue,
			EStructuralFeature connectionSourceValueFeature, 
			S argument,
			T argumentValue,
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		
		Setter<S,T> chain = defaultFeatureMapper == null ? null : (eObj, feature, arg, argValue, sPath, reg, pm) -> {
			defaultFeatureMapper.wireConnectionSourceFeature(
					connection, 
					connectionSource, 
					connectionSourceValue, 
					connectionSourceValueFeature, 
					argument, 
					argumentValue, 
					registry, 
					progressMonitor);
			return true;
		};							
		
		Setter<S,T> featureSetter = getFeatureSetter(
				connection, 
				ConfigType.source, 
				null, 
				connectionSourceValueFeature, 
				getFeatureNameValidator(connectionSourceValue),
				chain);
		
		if (featureSetter == null) {
			featureSetter = chain;
		}
		
		if (featureSetter != null) {
			featureSetter.setFeature(
					connectionSourceValue, 
					connectionSourceValueFeature, 
					argument, 
					argumentValue, 
					null, 
					registry, 
					progressMonitor);
		}
	}
	
	@Override
	protected void wireConnectionTargetFeature(
			S connection, 
			S connectionTarget, 
			T connectionTargetValue,
			EStructuralFeature connectionTargetValueFeature, 
			S argument,
			T argumentValue,
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		
		Setter<S,T> chain = defaultFeatureMapper == null ? null : (eObj, feature, arg, argValue, sPath, reg, pm) -> {
			defaultFeatureMapper.wireConnectionTargetFeature(
					connection, 
					connectionTarget, 
					connectionTargetValue, 
					connectionTargetValueFeature, 
					argument, 
					argumentValue, 
					registry, 
					progressMonitor);
			return true;
		};							
		
		Setter<S,T> featureSetter = getFeatureSetter(
				connection, 
				ConfigType.target, 
				null, 
				connectionTargetValueFeature, 
				getFeatureNameValidator(connectionTargetValue),
				chain);
		
		if (featureSetter == null) {
			featureSetter = chain;
		}
		
		if (featureSetter != null) {
			featureSetter.setFeature(
					connectionTargetValue, 
					connectionTargetValueFeature, 
					argument, 
					argumentValue, 
					null, 
					registry, 
					progressMonitor);
		}
	}
	
	@Override
	protected void wireConnectionStartFeature(
			S connection, 
			T connectionValue,
			EStructuralFeature connectionValueFeature, 
			S connectionSource, 
			T connectionSourceValue, 
			Map<S, T> registry,
			ProgressMonitor progressMonitor) {
		
		Setter<S,T> chain = defaultFeatureMapper == null ? null : (eObj, feature, arg, argValue, sPath, reg, pm) -> {
			defaultFeatureMapper.wireConnectionStartFeature(
					connection, 
					connectionValue, 
					connectionValueFeature, 
					connectionSource, 
					connectionSourceValue, 
					registry, 
					progressMonitor);
			return true;
		};							
		
		Setter<S,T> featureSetter = getFeatureSetter(
				connection, 
				ConfigType.start, 
				null, 
				connectionValueFeature, 
				getFeatureNameValidator(connectionValue),
				chain);
		
		if (featureSetter == null) {
			featureSetter = chain;
		}
		
		if (featureSetter != null) {
			featureSetter.setFeature(
					connectionValue, 
					connectionValueFeature, 
					connectionSource, 
					connectionSourceValue, 
					null, 
					registry, 
					progressMonitor);
		}
	}
	
	@Override
	protected void wireConnectionEndFeature(
			S connection, 
			T connectionValue, 
			EStructuralFeature connectionValueFeature,
			S connectionTarget, 
			T connectionTargetValue, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {
		
		Setter<S,T> chain = defaultFeatureMapper == null ? null : (eObj, feature, arg, argValue, sPath, reg, pm) -> {
			defaultFeatureMapper.wireConnectionEndFeature(
					connection, 
					connectionValue, 
					connectionValueFeature, 
					connectionTarget, 
					connectionTargetValue, 
					registry, 
					progressMonitor);
			return true;
		};							
		
		Setter<S,T> featureSetter = getFeatureSetter(
				connection, 
				ConfigType.end, 
				null, 
				connectionValueFeature, 
				getFeatureNameValidator(connectionValue), 
				chain);
		
		if (featureSetter == null) {
			featureSetter = chain;
		}
		
		if (featureSetter != null) {
			featureSetter.setFeature(
					connectionValue, 
					connectionValueFeature, 
					connectionTarget, 
					connectionTargetValue, 
					null, 
					registry, 
					progressMonitor);
		}
	}
	
}
