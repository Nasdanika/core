package org.nasdanika.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A simple context context implementation.
 * This implementation composes {@link Composeable} services into a single service.
 * 
 * Also it implements sub-context lookup if property key has one or more slashes (/).
 * For example, for a property key ``my/important/property`` it will work like explained below:
 * 
 * * ``my/important`` property will be looked up. If it is found and is of type {@link Context} then ``property`` will be looked up in the property value and returned if it is not null.
 * * Otherwise ``my`` property will be looked up. If it is found and is of type Context then ``important/property`` will be looked up in the property value and returned if it is not null.
 * * And finally ``my/important/property`` key will be looked up in this context.
 * @author Pavel
 *
 */
public class SimpleMutableContext implements MutableContext {
	
	private Map<String, Object> properties = new HashMap<>();
	
	private Map<Class<Object>, List<Object>> services = new HashMap<>();

	private Context chain;
	
	public SimpleMutableContext() {
		this(Context.EMPTY_CONTEXT);
	}
	
	/**
	 * Constructs a new mutable context which falls-back to the chain {@link Context}'s.
	 * @param chain
	 */
	public SimpleMutableContext(Context chain) {
		this.chain = chain == null ? Context.EMPTY_CONTEXT : chain;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getServices(Class<T> type) {
		return (List<T>) services.computeIfAbsent((Class<Object>) type, (serviceType) -> new ArrayList<Object>());
	}

	/**
	 * Returns property by key. If a property at a given key is not found, the property key is split by the last slash (``/``)
	 * and the tail is looked up in the property at the head if the property is {@link Context}, {@link Map}, or {@link List} and the tail can be parsed to an integer.
	 * For example, if ``my/property`` is not present in properties then ``my`` property will be retrieved and if it is of type Context or Map its ``property`` key will be retrieved.
	 * Similarly, ``my/property/list/0`` will retrieve ``my/property/list`` and if it is a list then it will return its first element. 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object get(String key) {		
		return properties.computeIfAbsent(key, k -> {
			Object ret = chain.get(k);
			if (ret != null) {
				return ret;
			}
			
			int lastSlash = k.lastIndexOf('/');
			if (lastSlash == -1) {
				return null;
			}
			
			Object parentProperty = get(k.substring(0, lastSlash));
			String subKey = k.substring(lastSlash + 1);
			if (parentProperty instanceof Context) {
				return ((Context) parentProperty).get(subKey);	
			}
			if (parentProperty instanceof Map) {
				return ((Map<?,?>) parentProperty).get(subKey);
			}
			if (parentProperty instanceof List) {
				try {
					int idx = Integer.parseInt(subKey);
					if (idx >= 0 && idx < ((List<?>) parentProperty).size()) {
						return ((List<?>) parentProperty).get(idx);
					}
				} catch (NumberFormatException e) {
					// NOP
				}
			}
			if (parentProperty instanceof Function) {
				return ((Function<String, Object>) parentProperty).apply(subKey);
			}
			
			return null;
		});		
	}

	/**
	 * Returns registered services of the type's component type if the type is array, or the first registered service.
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> T get(Class<T> type) {
		if (type.isArray()) {
			List<Object> svcs = new ArrayList<>((List<Object>) getServices(type.getComponentType()));
			Object chained = chain.get(type); 
			if (chained!=null) {
				for (int i = 0; i < Array.getLength(chained); ++i) {
					svcs.add(Array.get(chained, i));
				}
			}
			return (T) svcs.toArray((T[]) Array.newInstance(type.getComponentType(), svcs.size()));
		}
		
		if (Composeable.class.isAssignableFrom(type)) {
			List<T> svcs = new ArrayList<>(getServices(type));
			T chained = chain.get(type); 
			if (chained!=null) {
				svcs.add(chained);
			}
			return svcs.stream().reduce(null, Composeable.<T>composer());
		}
		
		List<T> svcs = getServices(type);
		return svcs.isEmpty() ? chain.get(type) : svcs.get(0);
	}
	
	/**
	 * Returns registered services of the type's component type matching the predicate if the type is array, or the first registered service matching the predicate otherwise.
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> T get(Class<T> type, Predicate<? super T> predicate) {
		if (type.isArray()) {
			List<Object> svcs = new ArrayList<Object>((List<Object>) getServices(type.getComponentType()).stream().filter((Predicate<Object>) predicate).collect(Collectors.toList()));
			Object chained = chain.get(type, predicate); 
			if (chained!=null) {
				for (int i = 0; i < Array.getLength(chained); ++i) {
					svcs.add(Array.get(chained, i));
				}
			}
			return (T) svcs.toArray((T[]) Array.newInstance(type.getComponentType(), svcs.size()));
		}
				
		if (Composeable.class.isAssignableFrom(type)) {
			List<T> svcs = new ArrayList<>(getServices(type).stream().filter(predicate).collect(Collectors.toList()));
			T chained = chain.get(type, predicate); 
			if (chained!=null) {
				svcs.add(chained);
			}
			return svcs.stream().reduce(null, Composeable.<T>composer());
		}
		
		return getServices(type).stream().filter(predicate).findFirst().orElse(chain.get(type, predicate));
	}
	
	/**
	 * Sets property value. If the value is an instance of {@link Context} then it will be used in hierarchical lookups, i.e. it
	 * has the semantics of mounting the context at a particular path. 
	 * It is different from ``mount()`` because mount returns a new context and the original context does not change.
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * Registers a service. Multiple registrations are allowed.
	 * @param <T>
	 * @param type
	 * @param service
	 */
	public <T> void register(Class<T> type, T service) {
		getServices(type).add(service);
	}
	
	
	/**
	 * Unregisters a service.
	 * @param <T>
	 * @param type
	 * @param service
	 */
	public <T> void unregister(Class<T> type, T service) {
		getServices(type).remove(service);
	}
	
}
