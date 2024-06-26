package org.nasdanika.common;

import java.io.IOException;
import java.io.Reader;
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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
public abstract class SetterFeatureMapper<S extends EObject, T extends EObject> extends FeatureMapper<S, T> {
	
	public static final String PATH_KEY = "path";
	public static final String CONDITION_KEY = "condition";
	public static final String NOP_KEY = "nop";
	public static final String EXPRESSION_KEY = "expression";
	public static final String GREEDY_KEY = "greedy";
	public static final String GREEDY_NO_CHILDREN_KEY = "no-children";
	public static final String POSITION_KEY = "position";
	public static final String TYPE_KEY = "type";
	public static final String ARGUMENT_TYPE_KEY = "argument-type";
	public static final String COMPARATOR_KEY = "comparator";
	public static final String SCRIPT_KEY = "script";
	public static final String SCRIPT_REF_KEY = "script-ref";
	public static final String SCRIPT_ENGINE_KEY = "script-engine";
	
	private FeatureMapper<S, T> defaultFeatureMapper;
	
	protected SetterFeatureMapper() {
		
	}
	
	protected SetterFeatureMapper(Mapper<S,T> chain) {
		super(chain);
	}
		
	/**
	 * 
	 * @param chain <code>chain.wire()</code> is called by the <code>wire()</code> method of this class after this class performs its own wiring.
	 * @param defaulFeaturetMapper methods of defaultFeatureMapper are called by respective methods of this class if there is no feature setter.
	 */
	protected SetterFeatureMapper(Mapper<S,T> chain, FeatureMapper<S,T> defaulFeaturetMapper) {
		this(chain);
		this.defaultFeatureMapper = defaulFeaturetMapper; 
	}	
	
	protected interface Setter<S extends EObject, T extends EObject> {
		
		/**
		 * Possibly sets feature. Returns false if some preconditions were not met and the setter shall be invoked at a later phase
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
				LinkedList<EObject> sourcePath, 
				Map<S, T> registry, 
				ProgressMonitor progressMonitor);
		
	}
	
	/**
	 * @param source
	 * @return Feature mapping configuration YAML string.
	 */
	protected abstract String getFeatureMapConfigStr(EObject source);
	
	protected abstract EClassifier getType(String type, EObject context);
	
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
		
	/**
	 * 
	 * @param source
	 * @param isContents
	 * @param isArgument
	 * @return
	 */
	protected Object getFeatureMapConfig(EObject source, ConfigType configType, ConfigSubType configSubType) {
		String config = getFeatureMapConfigStr(source);
		if (Util.isBlank(config)) {
			return null;
		}
		try {
			Yaml yaml = new Yaml();
			Object configObj = yaml.load(config);
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
					throwConfigurationException("Unsupported config type: " + ck, null, source);
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
							throwConfigurationException("Unsupported config subtype: " + sck, null, source);
						}
						
						return subConfigMap.get(configSubType.name());
					}
				} else {
					return subConfigObj;
				}
				
				throwConfigurationException("Usupported configuration type: " + subConfigObj, null, source);
			}
			
			throwConfigurationException("Usupported configuration type: " + configObj, null, source);
		} catch (YAMLException yamlException) {
			throwConfigurationException(null, yamlException, source);
		}
		return null; // Never gets here
	}
	
	/**
	 * Configuration exception and Marker/Marked is not available here, so throwing {@link IllegalArgumentException}.
	 * Override to throw ConfigurationException in subclasses.
	 * @param context
	 * @param message
	 */
	protected void throwConfigurationException(String message, Throwable cause, EObject context) {
		if (cause == null) {
			throw new IllegalArgumentException(message);
		}
		if (Util.isBlank(message)) {
			throw new IllegalArgumentException(cause);			
		}
		throw new IllegalArgumentException(message, cause);		
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Setter<S,T>> loadFeatureSetters(
			Object configs, 
			java.util.function.BiConsumer<String, EObject> featureNameValidator, 
			EObject context, 
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
					throwConfigurationException("Feature config in a collection shall be a string, got: " + e, null, context);
					return null; // Never gets here
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
		
		throwConfigurationException("Unexpected config type: " + configs, null, context);
		return null; // Never gets here
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
	
	protected Setter<S,T> loadFeatureSetter(Object config, EObject context, Setter<S, T> chain) {
		return new Setter<S, T>() {

			@SuppressWarnings("unchecked")
			@Override
			public boolean setFeature(
					T eObj, 
					EStructuralFeature feature, 
					S argument,
					T argumentValue,
					LinkedList<EObject> sourcePath,
					Map<S, T> registry, 					
					ProgressMonitor progressMonitor) {

				boolean shallCallChain = true;
				for (Object configElement: (config instanceof Iterable ? (Iterable<?>) config : Collections.singleton(config))) {
					if (matchCondition(configElement, eObj, argument, argumentValue, sourcePath, registry, context)
							&& matchType(eObj, configElement, context)
							&& matchArgumentType(argumentValue, configElement, context)
							&& matchPath(configElement, sourcePath, registry, context)) {
						
						if (isNop(configElement)) {
							return true;
						}
						
						Class<?> featureType = feature.getEType().getInstanceClass();
						Object featureValue = eval(configElement, argument, argumentValue, sourcePath, registry, featureType, context);
						if (configElement instanceof Map) {
							featureValue = evaluateScript(
									(Map<?,?>) configElement, 
									argument,
									argumentValue,
									sourcePath,
									registry,
									featureType,
									context);
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
											for (S eObjSource: getSources(eObj, registry) ) {
												for (S fvcSource: getSources(fvc, registry)) {
													if (EcoreUtil.isAncestor(eObjSource, fvcSource)) {
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
									List<Object> fvl = (List<Object>) eObj.eGet(feature);
									int position = getPosition(configElement, context);
									if (position == -1) {
										Comparator<Object> comparator = getComparator(eObj, configElement, registry, context);
										if (comparator == null || fvl.isEmpty()) {										
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
									} else {
										fvl.add(position, element);								
									}
								} else {
									eObj.eSet(feature, element);
								}
								shallCallChain = false;
							}
						}	
					}
				}
				
				if (shallCallChain && chain != null) {
					return chain.setFeature(
							eObj, 
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
	
	
	protected Object convert(Object value, EClassifier type, EObject context) {
		if (value == null || type.isInstance(value)) {
			return value;
		}
		
		Object converted = DefaultConverter.INSTANCE.convert(value, type.getInstanceClass());
		if (converted == null) {
			throwConfigurationException("Cannot convert " + value + " to " + type.getInstanceClass(), null, context);
		}
		return converted;
	}
			
	protected boolean matchPath(Object config, LinkedList<EObject> path, Map<S, T> registry, EObject context) {
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
				variables.put("registry", registry);
				
				Iterator<EObject> pathIterator = path.iterator();				
				for (Object condition: pathConditions) {
					EObject pathElement = pathIterator.next();
					if (condition instanceof Boolean) {
						if (Boolean.FALSE.equals(condition)) {
							return false;
						}
					} else if (condition instanceof String) {
						if (!evaluatePredicate(pathElement, (String) condition, variables, context));
					} else {
						throwConfigurationException("Unsupported path condition type: " + condition.getClass() + " " + condition, null, context);						
					}
				}
			}
		}
		
		throwConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, context);
		return true;
	}
		
	protected boolean matchType(EObject eObj, Object config, EObject context) {
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
		
		throwConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, context);
		return true;
	}
	
	protected boolean matchArgumentType(Object argument, Object config, EObject context) {
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
						throwConfigurationException("Unsupported argument-type element type: " + ate.getClass() + " " + ate, null, context);						
					}
				}
				return false;
			}
		}
		
		throwConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, context);
		return true;
	}
	
	/**
	 * Matches argument type taking negation into account (!)
	 * @param argument
	 * @param value
	 * @param context
	 */
	protected boolean matchArgumentTypeValue(Object argument, String value, EObject context) {
		boolean isNegation = value.startsWith("!");
		String typeName = isNegation ? value.substring(1) : value;
		EClassifier eClassifier = getType(typeName, context);
		return eClassifier.isInstance(argument) ^ isNegation;
	}
	
	protected boolean matchCondition(
			Object config, 
			T eObj,
			S argument,
			T argumentValue,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			EObject context) {
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
				variables.put("value", argumentValue);
				variables.put("path", sourcePath);
				variables.put("registry", registry);
				return evaluatePredicate(argument, (String) condition, variables, context);
			}
			throwConfigurationException("Unsupported condition type: " + condition.getClass() + " " + condition, null, context);
		}
		
		throwConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, context);
		return true;
	}	

	public boolean evaluatePredicate(
			Object obj, 
			String expr, 
			Map<String,Object> variables, 
			EObject context) {
		
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
			EObject context) {
		
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
			throwConfigurationException("Error parsing expression: '" + expr, e, context);
			return null;
		} catch (EvaluationException e) {
			throwConfigurationException("Error evaluating expression: '" + expr + "' in the context of " + obj + " with variables " + variables, e, context);
			return null;
		}
	}
	
	protected EvaluationContext createEvaluationContext(EObject context) {
		return new StandardEvaluationContext();
	}

	protected SpelExpressionParser createExpressionParser(EObject context) {
		return new SpelExpressionParser();
	}
	
	/**
	 * A comparator of elements for many features.
	 * @param semanticElement Semantic element receiving objects being compared. For some types of comparators the semantic element may be used to compute the base coordinates/geometry for comparison.
	 * @param feature Semantic element's many feature into which object being compared are injected (set)     
	 * @param config
	 * @param registry
	 * @return
	 */
	public Comparator<Object> getComparator(
			EObject semanticElement,
			Object config, 
			Map<S, T> registry, 
			EObject context) {
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
					semanticElement,
					comparatorConfig, 
					registry, 
					context);
		}
		throwConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, context);
		return null; 
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
			EObject semanticElement,
			Object comparatorConfig, 
			Map<S, T> registry, 
			EObject context) {
		if ("natural".equals(comparatorConfig)) {
			return NATURAL_COMPARATOR;
		} 
		
		if (comparatorConfig instanceof Map) {
			Map<?, ?> comparatorConfigMap = (Map<?,?>) comparatorConfig;
			if (comparatorConfigMap.size() == 1) {
				for (Entry<?, ?> cce: comparatorConfigMap.entrySet()) {
					if ("key".equals(cce.getKey()) && cce.getValue() instanceof String) {
						return new Comparator<Object>() {
							
							@Override
							public int compare(Object o1, Object o2) {
								Object key1 = evaluate(
										o1, 
										(String) cce.getValue(), 
										Map.of("registry", registry), 
										Object.class, 
										context);
								
								Object key2 = evaluate(
										o2, 
										(String) cce.getValue(), 
										Map.of("registry", registry), 
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
												"other", o2,
												"registry", registry), 
										Integer.class, 
										context);
							}							
						};
					}
				}
			}
		}
		
		throwConfigurationException("Unsupported comparator config: " + comparatorConfig, null, context);
		return null;
	}
	
	protected int getPosition(Object config, EObject context) {
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
			throwConfigurationException("Unsupported position type: " + position.getClass() + " " + position, null, context);
		}
		throwConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, context);
		return -1;
	}
	
	protected Object eval(
			Object config,
			S argument,
			T argumentValue,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			Class<?> type,
			EObject context) {
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
					ret.add(eval(ee, argument, argumentValue, sourcePath, registry, type, context));
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
		variables.put("value", argumentValue);
		variables.put("path", sourcePath);
		variables.put("registry", registry);
		Object result = evaluate(argument, (String) expression, variables, type, context);
		return result;
	}
	
	protected SourceRecord getScript(Map<?,?> configMap, EObject context) {
		URI baseURI = getBaseURI(context);
		Object script = configMap.get(SCRIPT_KEY);
		if (script instanceof String) {
			String ss = (String) script;
			if (!Util.isBlank(ss)) {		
				return new SourceRecord(baseURI, ss);
			}
		} else if (script != null) {
			throwConfigurationException("Script is not a string: " + script, null, context);			
		} else {
			return null;
		}
					
		Object ref = configMap.get(SCRIPT_REF_KEY);
		if (ref instanceof String) {
			String sRef = (String) ref;
			if (Util.isBlank(sRef)) {
				return null;
			}

			URI refURI = URI.createURI(sRef);
			if (baseURI != null && !baseURI.isRelative()) {
				refURI = refURI.resolve(baseURI);
			}
			try {
				DefaultConverter converter = DefaultConverter.INSTANCE;
				Reader reader = converter.toReader(refURI);
				return new SourceRecord(refURI, converter.toString(reader));
			} catch (IOException e) {
				throwConfigurationException("Error loading script from " + refURI, e, context);
			}
		}
		throwConfigurationException("Script ref is not a string: " + ref, null, context);	
		return null;
	}
	
	protected URI getBaseURI(EObject eObj) {
		return null;
	}
	
	protected ClassLoader getClassLoader(EObject obj) {
		return Thread.currentThread().getContextClassLoader();
	}
		
	/**
	 * Override to provide variables for expressions and scripts.
	 * 
	 * @return
	 */
	protected Iterable<Map.Entry<String, Object>> getVariables(EObject context) {
		return Collections.emptySet();
	}	
	
	/**
	 * Executes script
	 * @param diagramElement
	 * @param semanticElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	protected Object evaluateScript(
			Map<?,?> configMap, 
			Object argument,
			Object argumentValue,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry,
			Class<?> type,
			EObject context) {		
		SourceRecord script = getScript(configMap, context);
		
		if (script == null || Util.isBlank(script.source())) {
			return argumentValue;
		}
		
		Object enginePredicateExpr = configMap.get(SCRIPT_ENGINE_KEY);
		if (enginePredicateExpr != null && !(enginePredicateExpr instanceof String)) {
			throwConfigurationException("Script engine expression is not a string: " + enginePredicateExpr, null, context);				
		}
		
		ScriptEngineManager scriptEngineManger = new ScriptEngineManager(getClassLoader(context));
		for (ScriptEngineFactory scriptEngineFactory: scriptEngineManger.getEngineFactories()) {
			if (!Util.isBlank((String) enginePredicateExpr)) {
				if (!evaluatePredicate(scriptEngineFactory, (String) enginePredicateExpr, null, context)) {
					continue;
				}
			} else if (script.uri() != null) {
				String extension = script.uri().fileExtension();
				if (!Util.isBlank(extension) && !scriptEngineFactory.getExtensions().contains(extension)) {
					continue;
				}
			}
			
			ScriptEngine engine = scriptEngineFactory.getScriptEngine();
			engine.put("context", context);
			engine.put("argument", argument);
			engine.put("argumentValue", argumentValue);
			engine.put("registry", registry);
			engine.put("sourcePath", sourcePath);
			engine.put("type", type);
			engine.put("baseURI", getBaseURI(context));
			for (Entry<String, Object> ve: getVariables(context)) {
				engine.put(ve.getKey(), ve.getValue());
			}
			
			try {
				return engine.eval(script.source());
			} catch (ScriptException e) {
				throwConfigurationException("Error evaluating script: " + e, e, context);
			}
		}
		throwConfigurationException("No matching script engine", null, context);
		return argumentValue;
	}
	
	protected Greedy getGreedy(Object config, EObject context) {
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
			throwConfigurationException("Unsupported greedy type: " + greedy.getClass() + " " + greedy, null, context);
		}
		throwConfigurationException("Unsupported config type: " + config.getClass() + " " + config, null, context);
		return null;
	}
	
	protected Map<String,Setter<S,T>> getFeatureSetters(
			EObject source,
			ConfigType configType, 
			ConfigSubType configSubType,
			java.util.function.BiConsumer<String, EObject> featureNameValidator,
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
			EObject source, 
			ConfigType configType, 
			ConfigSubType configSubType, 
			String featureName,
			java.util.function.BiConsumer<String, EObject> featureNameValidator, 
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
			EObject source, 
			ConfigType configType, 
			ConfigSubType configSubType, 
			EStructuralFeature feature,
			java.util.function.BiConsumer<String, EObject> featureNameValidator,
			Setter<S, T> chain) {
		return getFeatureSetter(
				source, 
				configType, 
				configSubType, 
				feature.getName(), 
				featureNameValidator, 
				chain);
	}
	
	protected java.util.function.BiConsumer<String, EObject> getFeatureNameValidator(EObject value) { 
		return (featureName, context) -> {
			EClass valueEClass = value.eClass();
			if (valueEClass.getEStructuralFeature(featureName) == null) {
				throwConfigurationException("Feature " + featureName + " not found in " + valueEClass.getName(), null, context);
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
			LinkedList<EObject> sourcePath, 
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
			LinkedList<EObject> sourcePath, 
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
