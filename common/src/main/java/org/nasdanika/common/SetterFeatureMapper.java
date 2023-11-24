package org.nasdanika.common;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
	public static final String EXPRESSION_KEY = "expression";
	public static final String GREEDY_KEY = "greedy";
	public static final String GREEDY_NO_CHILDREN_KEY = "no-children";
	
	protected interface Setter<S extends EObject, T extends EObject> {
		
		/**
		 * Possibly sets feature. Returns false if some preconditions were not met and the setter shall be invoked at a later phase
		 * @param eObj
		 * @param feature
		 * @param value
		 * @param sourcePath
		 * @param registry
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
	
	protected enum ConfigType { 
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
				Object subConfigObj = ((Map<?,?>) configObj).get(configType.name());
				if (subConfigObj == null) {
					return null;
				}
				
				if (configType == ConfigType.container || configType == ConfigType.contents) {				
					if (subConfigObj instanceof Map) {
						return ((Map<?,?>) subConfigObj).get(configSubType.name());
					}
				} else {
					return subConfigObj;
				}
				
				throwConfigurationException(source, "Usupported configuration type: " + subConfigObj, null);
			}
			
			throwConfigurationException(source, "Usupported configuration type: " + configObj, null);
		} catch (YAMLException yamlException) {
			throwConfigurationException(source, null, yamlException);
		}
		return null; // Never gets here
	}
	
	/**
	 * Configuration exception and Marker/Marked is not available here, so throwing {@link IllegalArgumentException}.
	 * Override to throw ConfigurationException in subclasses.
	 * @param context
	 * @param message
	 */
	protected void throwConfigurationException(EObject context, String message, Throwable cause) {
		if (cause == null) {
			throw new IllegalArgumentException(message);
		}
		if (Util.isBlank(message)) {
			throw new IllegalArgumentException(cause);			
		}
		throw new IllegalArgumentException(message, cause);		
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Setter<S,T>> loadFeatureSetters(EObject context, Object configs) {
		if (configs == null) {
			return Collections.emptyMap();
		}
		if (configs instanceof String) {
			String strConfig = (String) configs;
			if (Util.isBlank(strConfig)) {
				return Collections.emptyMap();
			}
			return Collections.singletonMap(strConfig.trim(), loadFeatureSetter(context, true));
		} 
		
		if (configs instanceof Collection) {
			Map<String, Setter<S,T>> ret = new LinkedHashMap<>();
			for (Object e: (Collection<?>) configs) {
				if (e instanceof String) {
					String se = (String) e; 
					if (!Util.isBlank(se)) {
						ret.put(se.trim(), loadFeatureSetter(context, true));
					}
				} else {
					throwConfigurationException(context, "Feature config in a collection shall be a string, got: " + e, null);
					return null; // Never gets here
				}
			}
			return ret;
		}
		
		if (configs instanceof Map) {
			Map<String, Setter<S,T>> ret = new LinkedHashMap<>();
			for (Entry<String, Object> fce: ((Map<String,Object>) configs).entrySet()) {
				ret.put(fce.getKey().trim(), loadFeatureSetter(context, fce.getValue()));
			}
			return ret;
		}
		
		throwConfigurationException(context, "Unexpected config type: " + configs, null);
		return null; // Never gets here
	}
	
	protected enum Greedy { FALSE, NO_CHILDREN, TRUE }
	
	protected Setter<S,T> loadFeatureSetter(EObject context, Object config) {
		return new Setter<S, T>() {

			@Override
			public boolean setFeature(
					T eObj, 
					EStructuralFeature feature, 
					S argument,
					T argumentValue,
					LinkedList<EObject> sourcePath,
					Map<S, T> registry, 
					ProgressMonitor progressMonitor) {
				
				if (matchCondition(config, eObj, argument, argumentValue, sourcePath, registry) && matchPath(config, sourcePath, registry)) {
					Object featureValue = eval(config, argument, argumentValue, sourcePath, registry);
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
									Greedy greedy = getGreedy(config);
									if (greedy != Greedy.FALSE) {
										if (EcoreUtil.isAncestor(eObj, fvc)) {
											shallSet = greedy != Greedy.NO_CHILDREN; // Grab only if truly greedy
										} else {
											shallSet = false; // Not a child, grab
										}
									}
								}
							}
						}
						
						if (shallSet) {
							Object element = convert(context, featureValue, feature.getEType());
							if (feature.isMany()) {
								@SuppressWarnings("unchecked")
								List<Object> fvl = (List<Object>) eObj.eGet(feature);
								int position = getPosition(config);
								if (position == -1) {
									fvl.add(element);
								} else {
									fvl.add(position, element);								
								}
							} else {
								eObj.eSet(feature, element);
							}
						}
					}
				}
				
				return true;
			}
			
		};
	}
	
	protected Object convert(EObject context, Object value, EClassifier type) {
		if (value == null || type.isInstance(value)) {
			return value;
		}
		
		Object converted = DefaultConverter.INSTANCE.convert(value, type.getInstanceClass());
		if (converted == null) {
			throwConfigurationException(context, "Cannot convert " + value + " to " + type.getInstanceClass(), null);
		}
		return converted;
	}
			
	protected boolean matchPath(Object config, LinkedList<EObject> path, Map<S, T> registry) {
		if (config == Boolean.TRUE) {
			return true;
		}
		// TODO - number && list
		throw new UnsupportedOperationException();
	}
	
	protected boolean matchCondition(
			Object config, 
			T eObj,
			S argument,
			T argumentValue,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry) {
		if (config == Boolean.TRUE) {
			return true;
		}
		// TODO
		throw new UnsupportedOperationException();
	}
	
	protected int getPosition(Object config) {
		if (config == Boolean.TRUE) {
			return -1;
		}
		// TODO
		throw new UnsupportedOperationException();
	}
	
	protected Object eval(
			Object config,
			S argument,
			T argumentValue,
			LinkedList<EObject> sourcePath,
			Map<S, T> registry) {
		if (config == Boolean.TRUE) {
			return argumentValue;
		}
		// TODO
		throw new UnsupportedOperationException();		
	}
	
	protected Greedy getGreedy(Object config) {
		if (config == Boolean.TRUE) {
			return Greedy.NO_CHILDREN;
		}
		// TODO
		throw new UnsupportedOperationException();		
	}
	
	protected Map<String,Setter<S,T>> getFeatureSetters(
			EObject source,
			ConfigType configType, 
			ConfigSubType configSubType) {
		Object config = getFeatureMapConfig(source, configType, configSubType);
		return loadFeatureSetters(source, config);		
	}	
	
	protected Setter<S,T> getFeatureSetter(
			EObject source, 
			ConfigType configType, 
			ConfigSubType configSubType, 
			String featureName) {
		return getFeatureSetters(source, configType, configSubType).get(featureName);
	}
	
	protected Setter<S,T> getFeatureSetter(
			EObject source, 
			ConfigType configType, 
			ConfigSubType configSubType, 
			EStructuralFeature feature) {
		return getFeatureSetter(source, configType, configSubType, feature.getName());
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

		// Own (self) feature mapping
		Setter<S,T> featureSetter = getFeatureSetter(container, ConfigType.container, ConfigSubType.self, containerValueFeature);
		
		if (featureSetter == null) {
			// Mapping defined at the argument (contents)
			featureSetter = getFeatureSetter(contents, ConfigType.container, ConfigSubType.other, containerValueFeature);
		}
		
		if (featureSetter != null) {
			return featureSetter.setFeature(containerValue, containerValueFeature, contents, contentsValue, sourcePath, registry, progressMonitor);
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
		
		// Own (self) feature mapping
		Setter<S,T> featureSetter = getFeatureSetter(contents, ConfigType.contents, ConfigSubType.self, contentsValueFeature);
		
		if (featureSetter == null) {
			// Mapping defined at container
			featureSetter = getFeatureSetter(container, ConfigType.contents, ConfigSubType.other, contentsValueFeature);
		}
		
		if (featureSetter != null) {
			return featureSetter.setFeature(contentsValue, contentsValueFeature, container, containerValue, sourcePath, registry, progressMonitor);
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
		
		Setter<S,T> featureSetter = getFeatureSetter(connection, ConfigType.source, null, connectionSourceValueFeature);
		
		if (featureSetter != null) {
			featureSetter.setFeature(connectionSourceValue, connectionSourceValueFeature, argument, argumentValue, null, registry, progressMonitor);
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
		
		Setter<S,T> featureSetter = getFeatureSetter(connection, ConfigType.target, null, connectionTargetValueFeature);
		
		if (featureSetter != null) {
			featureSetter.setFeature(connectionTargetValue, connectionTargetValueFeature, argument, argumentValue, null, registry, progressMonitor);
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
		
		Setter<S,T> featureSetter = getFeatureSetter(connection, ConfigType.start, null, connectionValueFeature);
		
		if (featureSetter != null) {
			featureSetter.setFeature(connectionValue, connectionValueFeature, connectionSource, connectionSourceValue, null, registry, progressMonitor);
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
		
		Setter<S,T> featureSetter = getFeatureSetter(connection, ConfigType.end, null, connectionValueFeature);
		
		if (featureSetter != null) {
			featureSetter.setFeature(connectionValue, connectionValueFeature, connectionTarget, connectionTargetValue, null, registry, progressMonitor);
		}
	}
	
}
