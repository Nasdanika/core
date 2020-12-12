package org.nasdanika.common;

import java.util.function.Function;

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
	
	/**
	 * @param mapper Key mapping function. 
	 * @return A context which maps the key using the mapper function in <code>get(String></code> and <code>get(String,Class)</code> methods. Returns this context if mapper is null.
	 * Service registration and unregistration methods are passed to this context without mapping.
	 */
	@Override
	default MutableContext map(Function<String,String> mapper) {
		if (mapper == null) {
			return this;
		}
		
		return new MutableContext() {

			@Override
			public Object get(String key) {
				return MutableContext.this.get(mapper.apply(key));
			}

			@Override
			public <T> T get(Class<T> type) {
				return MutableContext.this.get(type);
			}

			@Override
			public <T> T get(String key, Class<T> type) {
				return MutableContext.this.get(mapper.apply(key), type);
			}

			@Override
			public void put(String key, Object value) {
				MutableContext.this.put(mapper.apply(key), value);				
			}

			@Override
			public <T> void register(Class<T> type, T service) {
				MutableContext.this.register(type, service);								
			}

			@Override
			public <T> void register(Class<T> type, ServiceComputer<? super T> serviceComputer) {
				MutableContext.this.register(type, serviceComputer);								
			}

			@Override
			public <T> void unregister(Class<T> type, T service) {
				MutableContext.this.unregister(type, service);								
			}

			@Override
			public <T> void unregister(Class<T> type, ServiceComputer<? super T> serviceComputer) {
				MutableContext.this.unregister(type, serviceComputer);								
			}
			
		};
	}
	
	
}
