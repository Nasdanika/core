package org.nasdanika.common;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A generic source of contextual information keyed by a {@link String} - property, or a {@link Class} (type) - service. 
 * @author Pavel
 *
 */
public interface Context extends Composeable<Context> {
	
	/**
	 * Empty context which can be used as a starting point
	 * for creating of composed/mounted contexts. 
	 */
	static final Context EMPTY_CONTEXT = new Context() {
		
		@Override
		public <T> T get(Class<T> type) {
			return null;
		}
		
		@Override
		public Object get(String key) {
			return null;
		}
		
		/**
		 * Returns this context if other is null or other context otherwise because there is no
		 * reason to compose the empty context. A convenient method to get a non-null context.
		 */
		@Override
		public Context compose(Context other) {
			return other == null ? this : other;
		}
		
	};
	
	/**
	 * @param key Object key (property name).
	 * @return Property by a key.
	 */
	Object get(String key);
	
	/**
	 * Convenience method for retrieving string properties.
	 * @param key
	 * @return
	 */
	default String getString(String key) {
		return get(key, String.class);
	}
	
	/**
	 * Convenience method for retrieving string properties.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	default String getString(String key, String defaultValue) {
		return get(key, String.class, defaultValue);
	}
	
	/**
	 * @param key Object key (property name).
	 * @param defaultValue value to return if key is not present.
	 * @return Property by key or default value if property is not present.
	 */
	default Object get(String key, Object defaultValue) {
		Object ret = get(key);
		return ret == null ? defaultValue : ret;
	}
	
	/**
	 * @param key Object key (property name).
	 * @param computer Function to compute value if key is not present.
	 * @return Property by key of a value returned by the computer if property is not present.
	 */
	default Object get(String key, Function<String,Object> computer) {
		Object ret = get(key);
		return ret == null ? computer.apply(key) : ret;		
	}
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @return Service of a particular type. Multiple service instances maybe obtained by passing an array type.
	 */
	<T> T get(Class<T> type);
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param defaultService Default service implementation to return if no service is found. 
	 * @return Service of a particular type or default service if no service of requested type is found in the context. Multiple service instances maybe obtained by passing an array type.
	 */
	default <T> T get(Class<T> type, T defaultService) {
		T ret = get(type);
		return ret == null ? defaultService : ret;		
	}
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param computer Function to compute a service instance if none is found in the context.
	 * @return Service of a particular type or a service returned by the computer if not service of requested type is found in the context. Multiple service instances maybe obtained by passing an array type.
	 */
	default <T> T get(Class<T> type, Function<Class<T>,T> computer) {
		T ret = get(type);
		return ret == null ? computer.apply(type) : ret;		
	}
	
	/**
	 * @param <T> Service type.
	 * @param type Service type class.
	 * @param predicate Service predicate (filter).
	 * @return Service of a particular type. Multiple service instances maybe obtained by passing an array type. To filter multiple instances use Predicate&lt;Object&gt; for
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
	 * @return Service of a particular type. Multiple service instances maybe obtained by passing an array type. To filter multiple instances use Predicate&lt;Object&gt; for
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
	 * @return Service of a particular type. Multiple service instances maybe obtained by passing an array type. To filter multiple instances use Predicate&lt;Object&gt; for
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
	 * @return Property by key converted to requested type. 
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
	 * Returns a property converted to the requested type. E.g. property which is a {@link File} or {@link URL} may be converted to {@link InputStream}, {@link Reader} or {@link String}.
	 * The default implementation looks up {@link Converter} service to convert the value returned by <code>get(String)</code> to requested type and falls-back to {@link DefaultConverter}.INSTANCE
	 * if the converter service is not present in the context.
	 * @param <T> Property/service type.
	 * @param key Object key (property/service name).
	 * @param type Property/service type class.
	 * @param defaultValue Default property value. 
	 * @return Property by key converted to requested type or default value if property is not found. 
	 */
	default <T> T get(String key, Class<T> type, T defaultValue) {
		T ret = get(key, type);
		return ret == null ? defaultValue : ret;		
	}
	
	/**
	 * Returns a property converted to the requested type. E.g. property which is a {@link File} or {@link URL} may be converted to {@link InputStream}, {@link Reader} or {@link String}.
	 * The default implementation looks up {@link Converter} service to convert the value returned by <code>get(String)</code> to requested type and falls-back to {@link DefaultConverter}.INSTANCE
	 * if the converter service is not present in the context.
	 * @param <T> Property/service type.
	 * @param key Object key (property/service name).
	 * @param type Property/service type class.
	 * @param computer Function to compute property value if none is found in the context.
	 * @return Property by key converted to requested type or computed value if property is not present. 
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
	 * @param mapper Key mapping function. 
	 * @return A context which maps the key using the mapper function in <code>get(String></code> and <code>get(String,Class)</code> methods. Returns this context if mapper is null.
	 */
	default Context map(Function<String,String> mapper) {
		if (mapper == null) {
			return this;
		}
		
		return new Context() {

			@Override
			public Object get(String key) {
				return Context.this.get(mapper.apply(key));
			}

			@Override
			public <T> T get(Class<T> type) {
				return Context.this.get(type);
			}

			@Override
			public <T> T get(String key, Class<T> type) {
				return Context.this.get(mapper.apply(key), type);
			}
			
		};
	}
	
	/**
	 * Returns this context if other is null, or a new context which delegates to this context and falls back to the other if property/service is not found in this context.
	 * @param chain
	 */
	default Context compose(Context other) {
		if (other == null) {
			return this;
		}
		
		Context[] chain = {this, other};
		
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
	
	/**
	 * Creates a new mutable context backed by this context.
	 * This call is equivalent to ``fork(null,null)``.
	 * @return
	 */
	default MutableContext fork() {
		return fork(null,null);
	}
	
	/**
	 * Creates a new mutable context with a given prefix and service predicate. 
	 * @param mapper Mapper functions, the same as in map();
	 * @param servicePredicate Predicate to use during look up of parent services. One possible scenario is to filter-out {@link Composeable} services from the parent.
	 * @return
	 */
	default MutableContext fork(Function<String,String> mapper, Predicate<Object> servicePredicate) {
		Context mapped = map(mapper);
		if (servicePredicate == null) {
			return new SimpleMutableContext(mapped);
		}
		Context predicated = new Context() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T get(Class<T> type) {
				T ret = mapped.get(type);
				if (ret == null) {
					return null;
				}
				if (ret.getClass().isArray()) {
					List<Object> filtered = new ArrayList<Object>(); 
					for (int i = 0; i < Array.getLength(ret); ++i) {
						Object service = Array.get(ret, i);
						if (servicePredicate.test(service)) {
							filtered.add(service);
						}
					}
					ret = (T) filtered.toArray((Object[]) Array.newInstance(ret.getClass().getComponentType(), filtered.size()));
				}
				return servicePredicate.test(ret) ? null : ret;
			}
			
			@Override
			public Object get(String key) {
				return mapped.get(key);
			}
		};
		return new SimpleMutableContext(predicated);
	}
		
	static Pattern EXPANDER_PATTERN = Pattern.compile("\\$\\{(.+?)\\}");	
	
	/**
	 * Expands tokens in the form of ``${token name|default value}`` to their values.
	 * If a token is not found the default value after the pipe is used for expansion.
	 * If there is no default value then the expansion is not processed. Token names shall not contain pipes.
	 * If replacement is not null and there is a {@link Converter} service then the converter is used 
	 * to convert the replacement to {@link String}. If no such conversion can be done then toString() is used 
	 * as default conversion.
	 * @param input
	 * @return
	 */
	default String interpolate(String input) {
		if (input == null) {
			return input;
		}
		Matcher matcher = EXPANDER_PATTERN.matcher(input);
		StringBuilder output = new StringBuilder();
		int i = 0;
		while (matcher.find()) {
		    String token = matcher.group();
		    String peeledToken = token.substring(2, token.length()-1); // Peeling of ${ and } 
		    int pipeIdx = peeledToken.indexOf('|');
		    String defaultValue = pipeIdx == -1 ? null : peeledToken.substring(pipeIdx + 1);
		    if (pipeIdx != -1) {
		    	peeledToken = peeledToken.substring(0, pipeIdx);
		    }
		    
			Object replacement = get(peeledToken, defaultValue);
		    if (replacement != null) {
		    	Converter converter = get(Converter.class);
		    	if (converter != null) {
		    		String str = converter.convert(replacement, String.class);
		    		if (str != null) {
		    			replacement = str;
		    		}
		    	}
			    output.append(input.substring(i, matcher.start())).append(replacement);			    
			    i = matcher.end();
		    }
		}
		output.append(input.substring(i, input.length()));
		return output.toString();
	}
	
	/**
	 * @return A context backed by this context which caches values of propeties by key and of services by type.
	 */	
	default Context cachingContext() {
		
		return new Context() {
			
			private Map<String, Optional<Object>> properties = new HashMap<>();
			private Map<Class<?>, Optional<Object>> services = new HashMap<>();
			
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T get(Class<T> type) {
				Optional<Object> sopt = services.computeIfAbsent(type, (theType) -> Optional.ofNullable(Context.this.get(theType)));
				return sopt.isPresent() ? (T) sopt.get() : null;
			}
			
			@Override
			public Object get(String key) {
				Optional<Object> popt = properties.computeIfAbsent(key, (theKey) -> Optional.ofNullable(Context.this.get(theKey)));
				return popt.isPresent() ? popt.get() : null;
			}
			
		};
				
	}
	
	/**
	 * @return Context backed by this context which computes final property values by invoking {@link PropertyComputer}.compute() for properties of that type.
	 */
	default Context computingContext() {
		
		return new Context() {

			@Override
			public Object get(String key) {
				Object ret = Context.this.get(key);
				return ret instanceof PropertyComputer ? ((PropertyComputer) ret).compute(this, key, Object.class) : ret;
			}

			@Override
			public <T> T get(String key, Class<T> type) {
				T ret = Context.this.get(key, type);
				return ret instanceof PropertyComputer ? ((PropertyComputer) ret).compute(this, key, type) : ret;
			}

			@Override
			public <T> T get(Class<T> type) {
				return Context.this.get(type);
			}
			
		};
	}
	
	/**
	 * Creates a context with a single property entry.
	 * @param key
	 * @param value
	 * @return
	 */
	static Context singleton(String key, Object value) {
		return wrap(k -> key.equals(k) ? value : null);
	}

	/**
	 * Wraps a source function, e.g. Map<String,Object>::get into a context. Performs resolution of hierarchical properties.
	 * @param source Source function.
	 * @return
	 */
	static Context wrap(Function<String,Object> source) {
		return new Context() {

			@Override
			public Object get(String key) {
				Object ret = source.apply(key);
				if (ret != null) {
					return ret;
				}
				
				int lastSlash = key.lastIndexOf('/');
				if (lastSlash == -1) {
					return null;
				}
				
				Object parentProperty = get(key.substring(0, lastSlash));
				String subKey = key.substring(lastSlash + 1);
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

				return null;
			}

			@Override
			public <T> T get(Class<T> type) {
				return null;
			}
			
		};
	}
	
}
