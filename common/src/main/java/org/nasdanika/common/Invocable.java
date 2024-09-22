package org.nasdanika.common;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.lang3.ClassUtils;

/**
 * 
 */
public interface Invocable {
	
	public interface Parameter {
		
		String getName();
		
		Class<?> getType();
		
		static Parameter of(String name, Class<?> type) {
			return new Parameter() {

				@Override
				public String getName() {
					return name;
				}

				@Override
				public Class<?> getType() {
					return type;
				}
				
			};
		}
		
	}
	
	/**
	 * 
	 * @return null for unknown parameters
	 */
	default Parameter[] getParameters() {
		return null;
	}
	
	/**
	 * 
	 * @return null for unknown return type
	 */
	default Class<?> getReturnType() {
		return null;
	}
	
	<T> T invoke(Object... args);
	
	/**
	 * Different name to avoid ambiguity 
	 * @param argumentProvider
	 * @return
	 */
	default <T> T call(java.util.function.BiFunction<Integer,Parameter,Object> argumentProvider) {
		Parameter[] parameters = getParameters();
		if (parameters == null) {
			throw new UnsupportedOperationException("Parameters are null (unknown)");
		}
						
		Object[] args = new Object[parameters.length];
		
		for (int i = 0; i < args.length; ++i) {
			args[i] = argumentProvider.apply(i, parameters[i]);
		}
		
		return invoke(args);
	}
	
	default Invocable bindByName(String name, Object binding) {		
		Parameter[] parameters = getParameters();
		if (parameters == null) {
			throw new UnsupportedOperationException("Cannot bind by name because parameters are unknown");
		}
		
		for (int i = 0; i < parameters.length; ++i) {
			if (name.equals(parameters[i].getName())) {
				return bindWithOffset(i, binding);
			}
		}
		
		throw new IllegalArgumentException("Parameter not found: " + name);
	}
		
	default Invocable bindMap(Map<String,Object> bindings) {
		Invocable ret = this;
		for (Entry<String, Object> bindingEntry: bindings.entrySet()) {
			ret = ret.bindByName(bindingEntry.getKey(), bindingEntry.getValue());
		}		
		return ret;
	}	
	
	default Invocable bind(Object... bindings) {
		return bindWithOffset(0, bindings);
	}
	
	default Invocable bindWithOffset(int offset, Object... bindings) {
		if (bindings.length == 0) {
			return this;
		}		
		
		Parameter[] parameters = getParameters();
		Class<?> returnType = getReturnType();
		
		return new Invocable() {
			
			@Override
			public Parameter[] getParameters() {
				if (parameters == null) {
					return parameters;
				}
				Parameter[] ret = new Parameter[parameters.length - bindings.length];
				for (int i = 0; i < ret.length; ++i) {
					ret[i] = parameters[i < offset ? i : i + bindings.length];
				}
				return ret;
			}
			
			@Override
			public Class<?> getReturnType() {
				return returnType;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {				
				Object[] finalArgs = new Object[bindings.length + args.length];
				System.arraycopy(args, 0, finalArgs, 0, offset);
				System.arraycopy(bindings, 0, finalArgs, offset, bindings.length);
				System.arraycopy(args, offset, finalArgs, offset + bindings.length, args.length - offset);
				return Invocable.this.invoke(finalArgs);
			}
			
		};
	}
	
	static Invocable of(Object target, Method method, String... parameterNames) {
		return new Invocable() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				try {
					Class<?>[] pTypes = method.getParameterTypes();
					for (int i = 0; i < args.length; ++i) {
						if (args[i] == null && pTypes[i].isPrimitive()) {
							if (pTypes[i] == char.class) {
								args[i] = Character.valueOf('0');
							} else {
								args[i] = ClassUtils.primitiveToWrapper(pTypes[i]).getConstructor(String.class).newInstance("0");								
							}							
						}
					}
					return method.invoke(target, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException e) {
					throw new ExecutionException("Exception invoking " + method + " of " + target + ": " + e, e);
				}
			}
			
			@Override
			public Parameter[] getParameters() {
				java.lang.reflect.Parameter[] parameters = method.getParameters();
				Parameter[] ret = new Parameter[parameters.length];
				for (int i = 0; i < parameters.length; ++i) {
					String pName = parameterNames == null || parameterNames.length <= i ? parameters[i].getName() : parameterNames[i];					
					ret[i] = Parameter.of(pName, parameters[i].getType());
				}
				return ret;
			}
			
			@Override
			public Class<?> getReturnType() {
				return method.getReturnType();
			}
			
		};
	}
		
	static Invocable of(Constructor<?> constructor, String... parameterNames) {
		return new Invocable() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				try {
					Class<?>[] pTypes = constructor.getParameterTypes();
					for (int i = 0; i < args.length; ++i) {
						if (args[i] == null && pTypes[i].isPrimitive()) {
							if (pTypes[i] == char.class) {
								args[i] = Character.valueOf('0');
							} else {
								args[i] = ClassUtils.primitiveToWrapper(pTypes[i]).getConstructor(String.class).newInstance("0");								
							}							
						}
					}
					
					return constructor.newInstance(args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException e) {
					throw new ExecutionException("Exception invoking " + constructor + ": " + e, e);
				}
			}
			
			@Override
			public Parameter[] getParameters() {				
				java.lang.reflect.Parameter[] parameters = constructor.getParameters();
				Parameter[] ret = new Parameter[parameters.length];
				for (int i = 0; i < parameters.length; ++i) {
					String pName = parameterNames == null || parameterNames.length <= i ? parameters[i].getName() : parameterNames[i];					
					ret[i] = Parameter.of(pName, parameters[i].getType());
				}
				return ret;
			}
			
			@Override
			public Class<?> getReturnType() {
				return constructor.getDeclaringClass();
			}
			
		};
	}	
	
	interface ProxyTargetResolver {
		
		Invocable resolve(Object proxy, Method method, Object[] args);
		
	}
			
	/**
	 * Creates a dynamic proxy dispatching to invocables.
	 * @param classLoader
	 * @param resolver
	 * @param interfaces
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static <T> T createProxy(
			ClassLoader classLoader, 
			ProxyTargetResolver resolver, 
			Class<?>... interfaces) {
		
		InvocationHandler invocationHandler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Invocable target = resolver.resolve(proxy, method, args);
				if (target == null) {
					if (method.getDeclaringClass().isInstance(resolver)) {
						return method.invoke(resolver, args);
					}
					throw new UnsupportedOperationException("No invocable for " + method); 
				}
				return target.invoke(args);
			}
		};
		
		return (T) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
	}
	
	/**
	 * Creates a dynamic proxy dispatching to invocables.
	 * @param classLoader
	 * @param resolver
	 * @param interfaces
	 * @return
	 */
	static <T> T createProxy(ProxyTargetResolver resolver, Class<?>... interfaces) {
		return createProxy(
				Thread.currentThread().getContextClassLoader(),
				resolver, 
				interfaces);
	}
	
	/**
	 * Resolves by signature or method name.
	 * @param <T>
	 * @param classLoader
	 * @param resolver Takes proxy as its first argument and name/signature as second
	 * @param interfaces
	 * @return
	 */
	static <T> T createProxy(
			ClassLoader classLoader, 
			BiFunction<Object, String, Invocable> resolver, 
			Class<?>... interfaces) {
		
		ProxyTargetResolver r = (proxy, method, args) -> {
			StringBuilder signatureBuilder = new StringBuilder(method.getName()).append("(");		
			Class<?>[] pt = method.getParameterTypes();
			for (int i = 0; i < pt.length; ++i) {
				if (i > 0) {
					signatureBuilder.append(",");
				}
				signatureBuilder.append(pt[i]);
			}
			Invocable ret = resolver.apply(proxy, signatureBuilder.append(")").toString());
			if (ret != null) {
				return ret;
			}
			return resolver.apply(proxy, method.getName());
		};
		
		return createProxy(classLoader, r, interfaces);
	}
	
	/**
	 * Creates a dynamic proxy dispatching to invocables.
	 * @param classLoader
	 * @param resolver Takes proxy as its first argument and signature as second
	 * @param interfaces
	 * @return
	 */
	static <T> T createProxy(BiFunction<Object, String, Invocable> resolver, Class<?>... interfaces) {
		return createProxy(
				Thread.currentThread().getContextClassLoader(),
				resolver, 
				interfaces);
	}
	
	// of
	
	static Invocable ofValue(Object value) {
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return value == null ? null : value.getClass();
			}
			
			@Override
			public Parameter[] getParameters() {
				return new Parameter[0];
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				return value;
			}
		};
	}
	
	static <T> Invocable of(java.util.function.Supplier<T> supplier, Class<T> type) {
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return type;
			}
			
			@Override
			public Parameter[] getParameters() {
				return new Parameter[0];
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				return supplier.get();
			}
		};
		
	}
		
	static <T,R> Invocable of(
			java.util.function.Function<T,R> function, 
			Class<T> parameterType,
			String parameterName,
			Class<R> returnType) {
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return returnType;
			}
			
			@Override
			public Parameter[] getParameters() {
				if (parameterType == null && parameterName == null) {
					return null;
				}
				return new Parameter[] { Parameter.of(parameterName, parameterType) };
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				return function.apply((T) args[0]);
			}
			
		};
		
	}
	
	static <T,U,R> Invocable of(
			java.util.function.BiFunction<T,U,R> biFunction, 
			Class<T> firstParameterType,
			String firstParameterName,
			Class<U> secondParameterType,
			String secondParameterName,
			Class<R> returnType) {
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return returnType;
			}
			
			@Override
			public Parameter[] getParameters() {
				return new Parameter[] { 
						Parameter.of(firstParameterName, firstParameterType), 
						Parameter.of(secondParameterName, secondParameterType) };
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				return biFunction.apply((T) args[0], (U) args[1]);
			}
		};
		
	}
	
	// as
	
	default java.util.function.Supplier<?> asSupplier() {
		return () -> invoke();
	}
	
	default java.util.function.Function<?,?> asFunction() {
		return arg -> invoke(arg);
	}
	
	default java.util.function.BiFunction<?,?,?> asBiFunction() {
		return (a,b) -> invoke(a,b);
	}
	
	// map - by index and by name
	
	default Invocable map(
			java.util.function.BiFunction<Integer,Parameter,Parameter> parameterMapper,
			java.util.function.Function<Class<?>, Class<?>> returnTypeMapper,
			java.util.function.BiFunction<Integer,Object,Object> argumentMapper) {
		
		Parameter[] parameters = getParameters();
		if (parameters != null && parameterMapper != null) {
			for (int i = 0; i < parameters.length; ++i) {
				parameters[i] = parameterMapper.apply(i, parameters[i]);
			}
		}
		
		Class<?> returnType = returnTypeMapper == null ?  getReturnType() : returnTypeMapper.apply(getReturnType());
		
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return returnType;
			}
			
			@Override
			public Parameter[] getParameters() {
				return parameters;
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				if (argumentMapper == null) {
					Invocable.this.invoke(args);
				}
				Object[] mappedArgs = new Object[args.length];
				for (int i = 0; i < mappedArgs.length; ++i) {
					mappedArgs[i] = argumentMapper.apply(i, args[i]);
				}
				return Invocable.this.invoke(mappedArgs);
			}
			
		};
		
	}
		
	/**
	 * Calls with conversion.
	 * @param config
	 * @param converter
	 * @return
	 */
	default <T> T convert(
			Object config, 
			BiFunction<Object,Class<?>, Object> converter) {
		
		if (config instanceof Map) {		
			return call((index, parameter) -> converter.apply(((Map<?,?>) config).get(parameter.getName()), parameter.getType()));
		}
		if (config instanceof List) {		
			return call((index, parameter) -> converter.apply(((List<?>) config).get(index), parameter.getType()));
		}
		throw new IllegalArgumentException("Config is neither a Map nor a List: " + config);
	}
	
	interface FactoryProvider {
		
		Invocable getFactory(
				Class<?> type, 
				Collection<?> keys,
				Predicate<Parameter[]> parameterPredicate);
		
	}

	/**
	 * 
	 * @param <T>
	 * @param config - Map for construction by name and Iterable for positional construction
	 * @param factoryProvider
	 * @param keyValidator
	 * @return
	 */
	default <T> T call(
			Object config, 
			BiFunction<Object,Class<?>, Optional<Object>> converter,			
			FactoryProvider factoryProvider) {
		BiFunction<Object,Class<?>, Object> theConverter = new BiFunction<Object, Class<?>, Object>() {
			
			@Override
			public Object apply(Object source, Class<?> type) {
				if (converter != null) {
					Optional<Object> result = converter.apply(source, type);
					if (result != null) {
						return result.orElse(null);
					}
				}
				
				if (source == null || type.isInstance(source)) {
					return source;
				}
				
				if (type.isArray()) {
					Class<?> componentType = type.getComponentType();
					if (source instanceof Collection) {
						Collection<?> sourceCollection = (Collection<?>) source;
						Object ret = Array.newInstance(componentType, sourceCollection.size());
						int idx = 0;
						for (Object element: sourceCollection) {
							if (element == null || componentType.isInstance(element)) {
								Array.set(ret, idx++, element);
							} else {
								Object convertedElement = this.apply(element, componentType);
								Array.set(ret, idx++, convertedElement);								
							}
						}
						return ret;
					}
					
					// Treating as single element 
					Object ret = Array.newInstance(componentType, 1);
					if (componentType.isInstance(source)) {
						Array.set(ret, 0, source);
					} else {
						Object convertedElement = this.apply(source, componentType);
						Array.set(ret, 0, convertedElement);								
					}
					return ret;
				}
								
				// Not an array, not of expected type - use factory
				if (source instanceof Map) {				
					Map<?,?> sourceMap = (Map<?,?>) source;
					Invocable factory = factoryProvider.getFactory(type, sourceMap.keySet(), parameters -> sourceMap.size() <= parameters.length); // TODO Additional matching by type if possible 
					return factory.call(source, converter, factoryProvider);
				}
				if (source instanceof List) {				
					Invocable factory = factoryProvider.getFactory(type, null, parameters -> ((List<?>) source).size() == parameters.length); // TODO Additional matching by type if possible 
					return factory.call(source, converter, factoryProvider);
				}
				
				throw new IllegalArgumentException("Cannot create " + type + " from " + source);				
			}			
			
		};
		
		return convert(config, theConverter);				
	}
	
	/**
	 * A convenience method to check for presence of unsupported configuration keys
	 * @param config Configuration map.
	 * @param supportedKeys Supported keys.
	 * @param marker Map location.
	 * @return config argument.
	 */
	private void checkUnsupportedKeys(Collection<?> keys, Collection<String> supportedKeys, Object target) {
		if (keys == null) {
			return;
		}
		
		Collection<?> unsupportedKeys = new ArrayList<Object>(keys);
		unsupportedKeys.removeAll(supportedKeys);
		if (unsupportedKeys.isEmpty()) {
			return;
		}		
		
		if (unsupportedKeys.size() == 1) {
			Object unsupportedKey = unsupportedKeys.iterator().next();
			throw new IllegalArgumentException("Unsupported configuration key for " + target + ": " + unsupportedKey + ", supported keys: " + supportedKeys);
		}
		
		StringBuilder keyList = new StringBuilder();
		for (Object uk: unsupportedKeys) {
			if (keyList.length() > 0) {
				keyList.append(", ");
			}
			keyList.append(uk);
		}
		throw new IllegalArgumentException("Unsupported configuration keys for " + target + ": " + keyList + ", supported keys: " + supportedKeys);
	}
	/**
	 * Factory provider which uses canonical or longest constructor
	 * @param config
	 * @return
	 */
	default <T> T call(List<?> config) {
		return call(config, null);
	}
	
	/**
	 * Factory provider which uses canonical or longest constructor
	 * @param config
	 * @return
	 */
	default <T> T call(Map<?,?> config) {
		return call(config, null);
	}
	
	private static Parameter[] adaptParameters(Constructor<?> constructor) {	
		return Stream.of(constructor.getParameters()).map(p -> Parameter.of(p.getName(), p.getType())).toArray(size -> new Parameter[size]);
	}
	
	/**
	 * Factory provider which uses canonical or longest constructor
	 * @param config
	 * @return
	 */
	default <T> T call(
			Object config,
			BiFunction<Object,Class<?>, Optional<Object>> converter) {
		FactoryProvider factoryProvider = (type, keys, parametersPredicate) -> {
	        Constructor<?>[] constructors = type.getDeclaredConstructors();
			if (type.isRecord() && parametersPredicate == null) {
		        for (Constructor<?> constructor : constructors) {
		            if (constructor.getParameterCount() == type.getRecordComponents().length) {
		            	if (keys != null) {
			               	checkUnsupportedKeys(
			               			keys,
			               			Stream.of(constructor.getParameters()).map(java.lang.reflect.Parameter::getName).toList(),
			               			constructor);
		            	}
		            	return Invocable.of(constructor);
		            }
		        }				
			}
			
			Optional<Constructor<?>> longestMatchingConstructorOpt = Stream.of(constructors)
					.filter(c -> parametersPredicate == null || parametersPredicate.test(adaptParameters(c)))
					.reduce((a, b) -> a.getParameterCount() > b.getParameterCount() ? a : b);
			if (longestMatchingConstructorOpt.isPresent()) {
				Constructor<?> constructor = longestMatchingConstructorOpt.get();
				if (keys != null) {
	            	checkUnsupportedKeys(
	            			keys, 
	               			Stream.of(constructor.getParameters()).map(java.lang.reflect.Parameter::getName).toList(),
	            			constructor);
				}
				return Invocable.of(constructor);
			}
			
			throw new IllegalArgumentException("No matching constructor in " + type);
		};
		
		if (config instanceof Map) {
			Parameter[] parameters = getParameters();
			if (parameters == null) {
				throw new IllegalArgumentException("Parameters are unknown (null)");
			}
			checkUnsupportedKeys(
					((Map<?,?>) config).keySet(), 
	       			Stream.of(parameters).map(Parameter::getName).toList(), 
					this);
		}
		
		return call(config, converter, factoryProvider);		
	}
	
	/**
	 * 
	 * @param invocable
	 * @return Invocable which invokes the most specific of the argument invocables based on argument types and parameter types.. 
	 */
	static Invocable of(Invocable... invocables) {
		if (invocables.length == 1) {
			return invocables[0];
		}		
		
		return new Invocable() {
			
			private boolean matchArgs(Object[] args, Parameter[] parameters) {
				if (parameters == null) {
					return true;
				}
				if (parameters.length != args.length) {
					return false;
				}
				for (int i = 0; i < args.length; ++i) {
					Class<?> pType = parameters[i].getType();
					if (pType.isPrimitive()) {
						pType = ClassUtils.primitiveToWrapper(pType);								
					}							
					
					if (args[i] != null && !pType.isInstance(args[i])) {
						return false;
					}
				}
				return true;
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				Optional<Invocable> mostSpecificOpt = Stream.of(invocables)
					.filter(i -> matchArgs(args, i.getParameters()))
					.reduce((a,b) -> a.isMoreSpecific(b) ? a : b);
				
				if (mostSpecificOpt.isPresent()) {
					return mostSpecificOpt.get().invoke(args);
				}
				throw new UnsupportedOperationException("No matching invocable");
			}
			
		};
	}
	
	/**
	 * Invocable which calls the best matching (most specific) constructor
	 * @param type
	 * @return
	 */
	static Invocable of(Class<?> type) {
		Constructor<?>[] constructors = type.getConstructors();
		if (constructors.length == 1) {
			return of(constructors[0]);
		}
		return of(Stream.of(constructors).map(Invocable::of).toArray(size -> new Invocable[size]));
	}
	
	private boolean isMoreSpecific(Invocable o) {
		// Looking for more specific parameters
		int counter = 0;
		Parameter[] parameters = getParameters();
		Parameter[] otherParameters = o.getParameters();
		for (int i = 0; i < parameters.length; ++i) {
			if (parameters[i].getType().equals(otherParameters[i].getType())) {
				continue;
			}
			if (parameters[i].getType().isAssignableFrom(otherParameters[i].getType())) {
				--counter;
			} else if (otherParameters[i].getType().isAssignableFrom(parameters[i].getType())) {
				++counter;
			} 
		}
		
		return counter >= 0;
	}
	
}
