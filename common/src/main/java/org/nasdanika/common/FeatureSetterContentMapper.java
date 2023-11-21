package org.nasdanika.common;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.yaml.snakeyaml.Yaml;

/**
 * Content mapper which uses feature setters loaded from configuration to wire content 
 * @param <S>
 * @param <T>
 */
public abstract class FeatureSetterContentMapper<S extends EObject, T extends EObject> extends ContentMapper<S, T> {
	
	public static final String CONTAINER_KEY = "container";
	public static final String CONTENTS_KEY = "contents";
	public static final String SELF_KEY = "self";
	public static final String ARGUMENT_KEY = "argument";
	public static final String PATH_KEY = "path";
	public static final String CONDITION_KEY = "condition";
	public static final String EXPRESSION_KEY = "expression";
	public static final String GREEDY_KEY = "greedy";
	public static final String GREEDY_NO_CHILDREN_KEY = "no-children";
	
	protected interface FeatureSetter<S extends EObject, T extends EObject> {
		
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
				T value, 
				LinkedList<EObject> sourcePath, 
				Map<S, T> registry, 
				ProgressMonitor progressMonitor);
		
	}
	
	/**
	 * @param source
	 * @return Feature mapping configuration YAML string.
	 */
	protected abstract String getFeatureMapConfigStr(EObject source);
		
	/**
	 * 
	 * @param source
	 * @param isContents
	 * @param isArgument
	 * @return
	 */
	protected Object getFeatureMapConfig(EObject source, boolean isContents, boolean isArgument) {
		String config = getFeatureMapConfigStr(source);
		if (Util.isBlank(config)) {
			return null;
		}
		Yaml yaml = new Yaml();
		Object configObj = yaml.load(config);
		if (configObj instanceof Map) {
			Object subConfigObj = ((Map<?,?>) configObj).get(isContents ? CONTENTS_KEY : CONTAINER_KEY);
			if (subConfigObj == null) {
				return null;
			}
			if (subConfigObj instanceof Map) {
				return ((Map<?,?>) subConfigObj).get(isArgument ? ARGUMENT_KEY : SELF_KEY);
			}
			throw new IllegalArgumentException("Usupported configuration type: " + subConfigObj);
		}
		
		throw new IllegalArgumentException("Usupported configuration type: " + configObj);
	}

	@SuppressWarnings("unchecked")
	protected Map<String, FeatureSetter<S,T>> loadFeatureSetters(Object configs) {
		if (configs == null) {
			return Collections.emptyMap();
		}
		if (configs instanceof String) {
			String strConfig = (String) configs;
			if (Util.isBlank(strConfig)) {
				return Collections.emptyMap();
			}
			return Collections.singletonMap(strConfig.trim(), loadFeatureSetter(true));
		} 
		
		if (configs instanceof Collection) {
			Map<String, FeatureSetter<S,T>> ret = new LinkedHashMap<>();
			for (Object e: (Collection<?>) configs) {
				if (e instanceof String) {
					String se = (String) e; 
					if (!Util.isBlank(se)) {
						ret.put(se.trim(), loadFeatureSetter(true));
					}
				} else {
					throw new IllegalArgumentException("Feature config in a collection shall be a string, got: " + e);
				}
			}
			return ret;
		}
		
		if (configs instanceof Map) {
			Map<String, FeatureSetter<S,T>> ret = new LinkedHashMap<>();
			for (Entry<String, Object> fce: ((Map<String,Object>) configs).entrySet()) {
				ret.put(fce.getKey().trim(), loadFeatureSetter(fce.getValue()));
			}
			return ret;
		}
		
		throw new IllegalArgumentException("Unexpected config type: " + configs);
	}
	
	protected enum Greedy { FALSE, NO_CHILDREN, TRUE }
	
	protected FeatureSetter<S,T> loadFeatureSetter(Object config) {
		return new FeatureSetter<S, T>() {

			@Override
			public boolean setFeature(
					T eObj, 
					EStructuralFeature feature, 
					T value, 
					LinkedList<EObject> sourcePath,
					Map<S, T> registry, 
					ProgressMonitor progressMonitor) {
				
				if (getCondition(config, value, registry).test(eObj) && getPathPredicate(config, registry).test(sourcePath)) {

					Object featureValue = eval(config, value, sourcePath, registry);
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
							Object element = convert(featureValue, feature.getEType());
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
	
	protected Object convert(Object value, EClassifier type) {
		if (value == null || type.isInstance(value)) {
			return value;
		}
		
		Object converted = DefaultConverter.INSTANCE.convert(value, type.getInstanceClass());
		if (converted == null) {
			throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getInstanceClass());
		}
		return converted;
	}
			
	protected Predicate<LinkedList<EObject>> getPathPredicate(Object config, Map<S, T> registry) {
		if (config == Boolean.TRUE) {
			return path -> true;
		}
		// TODO - number && list
		throw new UnsupportedOperationException();
	}
	
	protected Predicate<T> getCondition(Object config, T value, Map<S, T> registry) {
		if (config == Boolean.TRUE) {
			return path -> true;
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
			T value, 
			LinkedList<EObject> sourcePath,
			Map<S, T> registry) {
		if (config == Boolean.TRUE) {
			return value;
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
	
	protected Map<String,FeatureSetter<S,T>> getFeatureSetters(
			EObject source,
			boolean isContents, 
			boolean isArgument) {
		Object config = getFeatureMapConfig(source, isContents, isArgument);
		return loadFeatureSetters(config);		
	}	
	
	protected FeatureSetter<S,T> getFeatureSetter(
			EObject contentsSource, 
			boolean isContents, 
			boolean isArgument, 
			String featureName) {
		return getFeatureSetters(contentsSource, isContents, isArgument).get(featureName);
	}
	
	protected FeatureSetter<S,T> getFeatureSetter(
			EObject source, 
			boolean isContents, 
			boolean isArgument, 
			EStructuralFeature feature) {
		return getFeatureSetter(source, isContents, isArgument, feature.getName());
	}

	@Override
	protected boolean wireContainerFeature(
			S source, 
			T target, 
			EStructuralFeature targetFeature, 
			T childTarget,
			LinkedList<EObject> sourcePath, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {
		
		// Own (self) feature mapping
		FeatureSetter<S,T> featureSetter = getFeatureSetter(source, false, false, targetFeature);
		
		if (featureSetter == null) {
			// Mapping defined at the argument (contents)
			featureSetter = getFeatureSetter(sourcePath.getLast(), false, true, targetFeature);
		}
		
		if (featureSetter != null) {
			return featureSetter.setFeature(target, targetFeature, childTarget, sourcePath, registry, progressMonitor);
		}

		return true;
	}

	@Override
	protected boolean wireContentsFeature(
			S source, 
			T target, 
			T childTarget,
			EStructuralFeature childTargetFeature,
			LinkedList<EObject> sourcePath, 
			Map<S, T> registry, 
			ProgressMonitor progressMonitor) {
		
		@SuppressWarnings("unchecked")
		S childSource = (S) sourcePath.getLast();
		
		// Own (self) feature mapping
		FeatureSetter<S,T> featureSetter = getFeatureSetter(childSource, true, false, childTargetFeature);
		
		if (featureSetter == null) {
			// Mapping defined at the argument (container)
			featureSetter = getFeatureSetter(source, true, true, childTargetFeature);
		}
		
		if (featureSetter != null) {
			return featureSetter.setFeature(childTarget, childTargetFeature, target, sourcePath, registry, progressMonitor);
		}
		return true;
	}

}
