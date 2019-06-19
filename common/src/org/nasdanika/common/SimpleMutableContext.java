package org.nasdanika.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	 * Returns property by key.
	 */
	@Override
	public Object get(String key) {
		int lastSlash;
		String prefix = key;
		while ((lastSlash = prefix.lastIndexOf('/')) != -1) {
			String suffix = key.substring(lastSlash +1);
			prefix = prefix.substring(0, lastSlash);
			Object subCtx = get(prefix);
			if (subCtx instanceof Context) {
				Object ret = ((Context) subCtx).get(suffix);
				if (ret != null) {
					return ret;
				}
			}
		}
		
		return properties.computeIfAbsent(key, chain::get);
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
