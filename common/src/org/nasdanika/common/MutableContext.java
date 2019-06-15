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
 * @author Pavel
 *
 */
public class MutableContext implements Context {
	
	private Map<String, Object> properties = new HashMap<>();
	
	private Map<Class<Object>, List<Object>> services = new HashMap<>();

	private Context chain;
	
	/**
	 * Constructs a new mutable context which falls-back to the chain {@link Context}'s.
	 * @param chain
	 */
	public MutableContext(Context... chain) {
		this.chain = Context.chain(chain);
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
		return properties.computeIfAbsent(key, chain::get);
	}

	/**
	 * Returns registered services of the type's component type if the type is array, or the first registered service.
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> T get(Class<T> type) {
		if (type.isArray()) {
			List<Object> svcs = (List<Object>) getServices(type.getComponentType());
			// TODO - chained svcs.
			return (T) svcs.toArray((T[]) Array.newInstance(type.getComponentType(), svcs.size()));
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
			List<Object> svcs = (List<Object>) getServices(type.getComponentType()).stream().filter((Predicate<Object>) predicate).collect(Collectors.toList());
			// TODO - chained svcs.
			return (T) svcs.toArray((T[]) Array.newInstance(type.getComponentType(), svcs.size()));
		}
		
		return getServices(type).stream().filter(predicate).findFirst().orElse(chain.get(type, predicate));
	}
	
	/**
	 * Sets property value.
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
