package org.nasdanika.common;

/**
 * A context which support setting properties and registering services.
 * @author Pavel
 *
 */
public interface MutableContext extends Context {
		
	/**
	 * Sets property value.
	 * @param key
	 * @param value
	 */
	void put(String key, Object value);
	
	/**
	 * Registers a service. Multiple registrations are allowed.
	 * @param <T>
	 * @param type
	 * @param service
	 */
	<T> void register(Class<T> type, T service);
	
	/**
	 * Registers a service computer.
	 * @param <T>
	 * @param type
	 * @param serviceComputer
	 */
	<T> void register(Class<T> type, ServiceComputer<? super T> serviceComputer);
		
	/**
	 * Unregisters a service.
	 * @param <T>
	 * @param type
	 * @param service
	 */
	<T> void unregister(Class<T> type, T service);
		
	/**
	 * Registers a service computer.
	 * @param <T>
	 * @param type
	 * @param serviceComputer
	 */
	<T> void unregister(Class<T> type, ServiceComputer<? super T> serviceComputer);	
	
}
