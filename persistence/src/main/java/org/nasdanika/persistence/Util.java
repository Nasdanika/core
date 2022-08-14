package org.nasdanika.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.CollectionCompoundConsumerFactory;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.CompoundCommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.resources.BinaryEntityContainer;

public class Util {
		
	/**
	 * Returns marker if map is {@link MarkedLinkedHashMap} and the key is marked, or null otherwise.
	 * @param map
	 * @param key
	 * @return
	 */
	public static List<? extends Marker> getMarkers(Map<?,?> map, Object key) {
		if (map instanceof MarkedLinkedHashMap) {
			return ((MarkedLinkedHashMap<?,?>) map).getEntryMarkers(key);
		}
		return null;
	}
		
	/**
	 * Returns marker if collection is {@link MarkedArrayList} and the index is present is marked, or null otherwise.
	 * @param map
	 * @param key
	 * @return
	 */
	public static List<? extends Marker> getMarkers(Collection<?> collection, int index) {
		return collection instanceof MarkedArrayList && index > -1 && index < ((MarkedArrayList<?>) collection).getElementMarkers().size() ? ((MarkedArrayList<?>) collection).getElementMarkers().get(index) : null;
	}
		
	/**
	 * Convenience method for adding a marker to exception messages if source is {@link MarkedLinkedHashMap}.
	 * @param candidate
	 * @param prefix
	 * @return
	 */
	public static String mark(String prefix, Object source, String key) {
		if (source instanceof MarkedLinkedHashMap) {
			List<? extends Marker> markers = ((MarkedLinkedHashMap<?,?>) source).getEntryMarkers(key);
			if (markers != null && !markers.isEmpty()) {
				return (prefix == null ? "" : prefix) + markers.stream().map(Object::toString).collect(Collectors.joining(", "));
			}
		}
		
		return "";
	}
	
	// --- Helper methods for loading configuration from maps

	/**
	 * Returns a {@link String} value or throws {@link ConfigurationException} if value is not null and not a string.
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getString(Map<?,?> map, Object key, String defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof String) {
			return (String) val;
		}
		throw new ConfigurationException(key + " value must be a string", getMarkers(map, key));		
	}

	/**
	 * Returns a {@link String} value or throws {@link ConfigurationException} if value is not null and not a string.
	 * @param map
	 * @param key
	 * @return
	 */
	public static int getInt(Map<?,?> map, Object key, int defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Number) {
			return ((Number) val).intValue();
		}
		if (val instanceof String) {
			try {
				return Integer.parseInt((String) val);
			} catch (NumberFormatException e) {
				throw new ConfigurationException(e.getMessage(), e, getMarkers(map, key));						
			}
		}
		throw new ConfigurationException(key + " value must be a string", getMarkers(map, key));		
	}

	/**
	 * Returns a {@link String} value or throws {@link ConfigurationException} if value is not null and not a string.
	 * @param map
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Map<?,?> map, Object key, boolean defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Boolean) {
			return (Boolean) val;
		}
		throw new ConfigurationException(key + " value must be a boolean", getMarkers(map, key));		
	}

	/**
	 * Returns a {@link Collection} value or throws {@link ConfigurationException} if value is not null and not a collection.
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> getCollection(Map<String,?> map, Object key, Collection<T> defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Collection) {
			return (Collection<T>) val;
		}
		throw new ConfigurationException(key + " value must be a collection", getMarkers(map, key));		
	}

	/**
	 * Returns a {@link Map} value or throws {@link ConfigurationException} if value is not null and not a map.
	 * @param map
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> getMap(Map<?,?> map, Object key, Map<K,V> defaultValue) {
		Object val = map.get(key);
		if (val == null) {
			return defaultValue;
		}
		if (val instanceof Map) {
			return (Map<K,V>) val;
		}
		throw new ConfigurationException(key + " value must be a map", getMarkers(map, key));		
	}
		
	/**
	 * A convenience method to check for presence of required configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument
	 */
	public static Map<String,Object> checkRequiredKeys(Map<String,Object> config, String... requiredKeys) throws ConfigurationException {
		return checkRequiredKeys(config, Arrays.asList(requiredKeys));
	}
	
	/**
	 * A convenience method to check for presence of required configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument
	 */
	public static Map<String,Object> checkRequiredKeys(Map<String,Object> config, Collection<String> requiredKeys) throws ConfigurationException {
		StringBuilder missingKeyList = new StringBuilder();
		for (String rk: requiredKeys) {
			if (!config.containsKey(rk)) {
				if (missingKeyList.length() > 0) {
					missingKeyList.append(", ");
				}
				missingKeyList.append(rk);
			}
		}
		
		if (missingKeyList.length() == 0) {
			return config;
		}
		
		throw new ConfigurationException("Missing required configuration keys: " + missingKeyList, config instanceof Marked ? ((Marked) config).getMarkers() : null);
	}

	/**
	 * A convenience method to check for presence of unsupported configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument.
	 */
	public static Map<?,?> checkUnsupportedKeys(Map<?,?> config, Collection<?> supportedKeys) throws ConfigurationException {
		if (config == null) {
			return config;
		}
		Collection<?> unsupportedKeys = new ArrayList<Object>(config.keySet());
		unsupportedKeys.removeAll(supportedKeys);
		if (unsupportedKeys.isEmpty()) {
			return config;
		}		
		
		if (unsupportedKeys.size() == 1) {
			Object unsupportedKey = unsupportedKeys.iterator().next();
			throw new ConfigurationException("Unsupported configuration key: " + unsupportedKey, getMarkers(config, unsupportedKey));
		}
		
		StringBuilder keyList = new StringBuilder();
		for (Object uk: unsupportedKeys) {
			if (keyList.length() > 0) {
				keyList.append(", ");
			}
			keyList.append(uk);
		}
		throw new ConfigurationException("Unsupported configuration keys: " + keyList, config instanceof Marked ? ((Marked) config).getMarkers() : null);
	}

	/**
	 * A convenience method to check for presence of unsupported configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument
	 */
	public static Map<?,?> checkUnsupportedKeys(Map<?,?> config, Object... supportedKeys) throws ConfigurationException {
		return checkUnsupportedKeys(config, Arrays.asList(supportedKeys));
	}

	/**
	 * Gets string configuration value.
	 * @param configMap
	 * @param key
	 * @param required
	 * @return
	 */
	public static String getString(Map<?, ?> configMap, Object key, boolean required, List<? extends Marker> markers) {
		if (configMap.containsKey(key)) {
			Object val = configMap.get(key);
			if (val == null && !required) {
				return null;
			}
			if (val instanceof String) {
				return (String) val;
			} 
			
			throw new ConfigurationException(key + " value must be a string", getMarkers(configMap, key));
		}
		
		if (required) {
			throw new ConfigurationException(key + " is missing", markers);			
		}
		
		return null;
	}

	/**
	 * Loads values from a key which can be either a string (single value) or a list of strings (multi-value)
	 * @param configMap
	 * @param key
	 * @param consumer
	 */
	public static void loadMultiString(Map<?,?> configMap, Object key, Consumer<String> consumer) {
		if (configMap.containsKey(key)) {
			Object val = configMap.get(key);
			if (val instanceof String) {
				consumer.accept((String) val);
			} else if (val instanceof Collection) {
				int idx = 0;
				for (Object ve: (Collection<?>) val) {
					if (ve instanceof String) {
						consumer.accept((String) ve);
					} else {
						throw new ConfigurationException(key + " element must be a string", getMarkers((Collection<?>) val, idx));							
					}
					++idx;
				}
			} else {
				throw new ConfigurationException(key + " value must be a string or list", getMarkers(configMap, key));
			}
		}		
	}
	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 */
	public static ConsumerFactory<BinaryEntityContainer> asConsumerFactory(Object obj)  {
		return asConsumerFactory(obj, obj instanceof Marked ? ((Marked) obj).getMarkers() : null);
	}
	
	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ConsumerFactory<T> asConsumerFactory(Object obj, List<? extends Marker> markers) {
		if (obj instanceof Collection) {
			CollectionCompoundConsumerFactory<T> ret = new CollectionCompoundConsumerFactory<>("Consumer collection");
			int idx = 0;
			for (Object e: (Collection<?>) obj) {
				ret.add(asConsumerFactory(e, getMarkers((Collection<?>) obj, idx++)));
			}
			return ret;
		}
		
		if (obj instanceof ConsumerFactory) {		
			return (ConsumerFactory<T>) obj;
		}		
		
		if (obj instanceof Adaptable) {
			ConsumerFactory<T> adapter = ((Adaptable) obj).adaptTo(ConsumerFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
		
		throw new ConfigurationException(obj.getClass() + " cannot be wrapped/adapted to a consumer factory", markers);
	}

	/**
	 * Wraps object into a {@link CommandFactory}. Handles adapters and collections.
	 * @param obj
	 * @return
	 */
	public static CommandFactory asCommandFactory(Object obj) {
		return asCommandFactory(obj, obj instanceof Marked ? ((Marked) obj).getMarkers() : null);
	}

	/**
	 * Wraps object into a {@link CommandFactory}. Handles adapters and collections.
	 * @param obj
	 * @return
	 */
	public static CommandFactory asCommandFactory(Object obj, List<? extends Marker> markers) {
		if (obj instanceof Collection) {
			CompoundCommandFactory ret = new CompoundCommandFactory("Command collection");
			int idx = 0;
			for (Object e: (Collection<?>) obj) {
				ret.add(asCommandFactory(e, getMarkers((Collection<?>) obj, idx++)));
			}
			return ret;
		}
		
		if (obj instanceof CommandFactory) {		
			return (CommandFactory) obj;
		}
		
		if (obj instanceof Adaptable) {
			CommandFactory adapter = ((Adaptable) obj).adaptTo(CommandFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
				
		throw new ConfigurationException(obj.getClass() + " cannot be wrapped/adapted to a command factory", markers);
	}
		
}
