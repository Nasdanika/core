package org.nasdanika.common;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A generic source of contextual information keyed by a {@link String} - property, a type - service, or a combination of thereof - a typed property or a named service. 
 * @author Pavel
 *
 */
public interface Context {
	
	/**
	 * @param key Object key (property name).
	 * @return Context object (property) by a key.
	 */
	Object get(String key);
	
	/**
	 * @param key Object key (property name).
	 * @param defaultValue value to return if key is not present.
	 * @return Context object (property) by a key.
	 */
	default Object get(String key, Object defaultValue) {
		Object ret = get(key);
		return ret == null ? defaultValue : ret;
	}
	
	/**
	 * @param key Object key (property name).
	 * @param computer Function to compute value if key is not present.
	 * @return Context object (property) by a key.
	 */
	default Object get(String key, Function<String,Object> computer) {
		Object ret = get(key);
		return ret == null ? computer.apply(key) : ret;		
	}
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @return Context object (service) of a particular type. Multiple service instances maybe obtained by passing an array type.
	 */
	<T> T get(Class<T> type);
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param defaultService Default service implementation to return if no service is found. 
	 * @return Context object (service) of a particular type. Multiple service instances maybe obtained by passing an array type.
	 */
	default <T> T get(Class<T> type, T defaultService) {
		T ret = get(type);
		return ret == null ? defaultService : ret;		
	}
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param computer Function to compute a service instance if none is found in the context.
	 * @return Context object (service) of a particular type. Multiple service instances maybe obtained by passing an array type.
	 */
	default <T> T get(Class<T> type, Function<Class<T>,T> computer) {
		T ret = get(type);
		return ret == null ? computer.apply(type) : ret;		
	}

	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param predicate Service predicate (filter).
	 * @return Context object (service) of a particular type. Multiple service instances maybe obtained by passing an array type. To filter multiple instances use Predicate&lt;Object&gt; for
	 * compatibility with both array and component types. 
	 */
	default <T> T get(Class<T> type, Predicate<? super T> predicate) {
		T ret = get(type);
		return predicate == null || predicate.test(ret) ? ret : null;
	};
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param predicate Service predicate (filter).
	 * @param defaultService Default service implementation to return if no service is found. 
	 * @return Context object (service) of a particular type. Multiple service instances maybe obtained by passing an array type. To filter multiple instances use Predicate&lt;Object&gt; for
	 * compatibility with both array and component types. 
	 */
	default <T> T get(Class<T> type, Predicate<? super T> predicate, T defaultService) {
		T ret = get(type, predicate);
		return ret == null ? defaultService : ret;		
	}
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param predicate Service predicate (filter).
	 * @param computer Function to compute a service instance if none is found in the context.
	 * @return Context object (service) of a particular type. Multiple service instances maybe obtained by passing an array type. To filter multiple instances use Predicate&lt;Object&gt; for
	 * compatibility with both array and component types. 
	 */
	default <T> T get(Class<T> type, Predicate<? super T> predicate, Function<Class<T>,T> computer) {
		T ret = get(type, predicate);
		return ret == null ? computer.apply(type) : ret;		
	}
	
	/**
	 * Returns a property converted to the requested type. E.g. property which is a {@link File} or {@link URL} may be converted to {@link InputStream}, {@link Reader} or {@link String}.
	 * The default implementation looks up {@link Converter} service to convert the value returned by <code>get(String)</code> to requested type and falls-back to {@link DefaultConverter}.INSTANCE
	 * if the converter service is not present in the context.
	 * @param <T> Property type.
	 * @param key Object key (property/service name).
	 * @param type Property type class.
	 * @return Context object by type and key. 
	 */
	@SuppressWarnings("unchecked")
	default <T> T get(String key, Class<T> type) {
		Object prop = get(key);
		if (type == null || type.isInstance(prop)) {
			return (T) prop;
		}
		return get(Converter.class, DefaultConverter.INSTANCE).convert(prop, type);
	}
	
	/**
	 * This method may have different semantics depending on situation. 
	 * One option is property type conversion. E.g. property which is a {@link File} or {@link URL} may be converted to {@link InputStream}, {@link Reader} or {@link String}.
	 * Another option is named services or service selectors when there are multiple services of the same type and only one of them shall be selected (or some of them if the type is array).
	 * @param <T> Property/service type.
	 * @param key Object key (property/service name).
	 * @param type Property/service type class.
	 * @param defaultService Default service implementation to return if no service is found. 
	 * @return Context object by type and key. 
	 */
	default <T> T get(String key, Class<T> type, T defaultService) {
		T ret = get(key, type);
		return ret == null ? defaultService : ret;		
	}
	
	/**
	 * This method may have different semantics depending on situation. 
	 * One option is property type conversion. E.g. property which is a {@link File} or {@link URL} may be converted to {@link InputStream}, {@link Reader} or {@link String}.
	 * Another option is named services or service selectors when there are multiple services of the same type and only one of them shall be selected (or some of them if the type is array).
	 * @param <T> Property/service type.
	 * @param key Object key (property/service name).
	 * @param type Property/service type class.
	 * @param computer Function to compute a service instance if none is found in the context.
	 * @return Context object by type and key. 
	 */
	default <T> T get(String key, Class<T> type, BiFunction<String,Class<T>,T> computer)  {
		T ret = get(key, type);
		return ret == null ? computer.apply(key, type) : ret;		
	}
	
	/**
	 * Mounts a context under a specified prefix. get(String) and get(String,Class) methods look up property/source in the mounted context first if the key start with the prefix, removing the prefix.
	 * E.g. if the prefix is <code>resources/</code> then <code>get("resources/myResource")</code> will call <code>get("myResource")</code> in the mounted resource first and will return a value if it is not null.
	 * If the value returned by the mounted context is null, then the value from <code>get("resources/myResource")</code> call to this context will be returned.
	 * In other words, the mounted value shadows this context value, but doesn't hide it.
	 * Typed service calls are always made against this context.
	 * @param context Context to mount.
	 * @param prefix Mount prefix.
	 * @return This context if argument context or argument prefix are null null or a context which delegates get(String) and get(String,Class) to the mounted context if the key start with the prefix. 
	 */
	default Context mount(Context context, String prefix) {
		if (context == null || prefix == null) {
			return this;
		}
		
		return new Context() {

			@Override
			public Object get(String key) {
				if (key != null && key.startsWith(prefix)) {
					Object ret = context.get(key.substring(prefix.length()));
					if (ret != null) {
						return ret;
					}
				}
				return Context.this.get(key);
			}

			@Override
			public <T> T get(Class<T> type) {
				return Context.this.get(type);
			}

			@Override
			public <T> T get(String key, Class<T> type) {
				if (key != null && key.startsWith(prefix)) {
					T ret = context.get(key.substring(prefix.length()), type);
					if (ret != null) {
						return ret;
					}
				}
				return Context.this.get(key, type);
			}
			
		};
	}
	
	/**
	 * @param prefix
	 * @return A context which adds prefix before the key in <code>get(String></code> and <code>get(String,Class)</code> methods.
	 */
	default Context subContext(String prefix) {
		if (prefix == null) {
			return this;
		}
		
		return new Context() {

			@Override
			public Object get(String key) {
				return Context.this.get(prefix+key);
			}

			@Override
			public <T> T get(Class<T> type) {
				return Context.this.get(type);
			}

			@Override
			public <T> T get(String key, Class<T> type) {
				return Context.this.get(prefix+key, type);
			}
			
		};
	}
	
	/**
	 * Returns a context which look up values in the chain elements which are not null.
	 * @param chain
	 * @return Chained context, never null.
	 */
	static Context chain(Context... chain) {
		return new Context() {
			
			@Override
			public <T> T get(Class<T> type) {
				for (Context ce: chain) {
					if (ce != null) {
						T ret = ce.get(type);
						if (ret != null) {
							return ret;
						}
					}
				}
				return null;
			}
						
			@Override
			public <T> T get(Class<T> type, Predicate<? super T> predicate) {
				for (Context ce: chain) {
					if (ce != null) {
						T ret = ce.get(type, predicate);
						if (ret != null) {
							return ret;
						}
					}
				}
				return null;
			}			
			
			@Override
			public Object get(String key) {
				for (Context ce: chain) {
					if (ce != null) {
						Object ret = ce.get(key);
						if (ret != null) {
							return ret;
						}
					}
				}
				return null;
			}
			
			@Override
			public <T> T get(String key, Class<T> type) {
				for (Context ce: chain) {
					if (ce != null) {
						T ret = ce.get(key, type);
						if (ret != null) {
							return ret;
						}
					}
				}
				return null;
			}
						
		};
	}

}
