package org.nasdanika.common;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public interface Invocable {
	
	/**
	 * 
	 * @return null for unknown parameter names
	 */
	default String[] getParameterNames() {
		return null;
	}
	
	/**
	 * 
	 * @return null for unknown parameter types
	 */
	default Class<?>[] getParameterTypes() {
		return null; 
	}
	
	/**
	 * 
	 * @return null for unknown return type
	 */
	default Class<?> getReturnType() {
		return null;
	}
	
	Object invoke(Object... args);
	
	/**
	 * Different name to avoid ambiguity 
	 * @param argumentProvider
	 * @return
	 */
	default Object call(java.util.function.BiFunction<String,Class<?>,Object> argumentProvider) {
		String[] parameterNames = getParameterNames();
		if (parameterNames == null) {
			throw new UnsupportedOperationException("Parameter names are null");
		}
		Class<?>[] parameterTypes = getParameterTypes();
		if (parameterTypes == null) {
			throw new UnsupportedOperationException("Parameter types are null");
		}
		if (parameterTypes.length != parameterNames.length) {
			throw new IllegalStateException("Parameter names and types lengths are different");
		}
		
		Object[] args = new Object[parameterNames.length];
		
		for (int i = 0; i < args.length; ++i) {
			args[i] = argumentProvider.apply(parameterNames[i], parameterTypes[i]);
		}
		
		return invoke(args);
	}
	
	default Invocable bind(String name, Object binding) {		
		String[] pNames = getParameterNames();
		if (pNames == null) {
			throw new UnsupportedOperationException("Cannot bind by name because parameter names are not available");
		}
		
		for (int i = 0; i < pNames.length; ++i) {
			if (name.equals(pNames[i])) {
				return bindWithOffset(i, binding);
			}
		}
		
		throw new IllegalArgumentException("Parameter not found: " + name);
	}
		
	default Invocable bind(Map<String,Object> bindings) {
		Invocable ret = this;
		for (Entry<String, Object> bindingEntry: bindings.entrySet()) {
			ret = ret.bind(bindingEntry.getKey(), bindingEntry.getValue());
		}		
		return ret;
	}	
	
	default Invocable bind(Object... bindings) {
		return bind(0, bindings);
	}
	
	default Invocable bindWithOffset(int offset, Object... bindings) {
		if (bindings.length == 0) {
			return this;
		}		
		
		String[] pNames = getParameterNames();
		Class<?>[] pTypes = getParameterTypes();	
		Class<?> returnType = getReturnType();
		
		return new Invocable() {
			
			@Override
			public String[] getParameterNames() {
				if (pNames == null) {
					return pNames;
				}
				String[] ret = new String[pNames.length - bindings.length];
				for (int i = 0; i < ret.length; ++i) {
					ret[i] = pNames[i < offset ? i : i + bindings.length];
				}
				return ret;
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				if (pTypes == null) {
					return pTypes;
				}
				Class<?>[] ret = new Class<?>[pTypes.length - bindings.length];
				for (int i = 0; i < ret.length; ++i) {
					ret[i] = pTypes[i < offset ? i : i + bindings.length];
				}
				return ret;
			}
			
			@Override
			public Class<?> getReturnType() {
				return returnType;
			}

			@Override
			public Object invoke(Object... args) {
				if (args.length == 0) {
					return Invocable.this.invoke();
				}
				
				Object[] finalArgs = new Object[bindings.length + args.length];
				System.arraycopy(args, 0, finalArgs, 0, offset);
				System.arraycopy(bindings, 0, finalArgs, offset, bindings.length);
				System.arraycopy(args, offset, finalArgs, offset + bindings.length, args.length - offset);
				return Invocable.this.invoke(finalArgs);
			}
			
		};
	}
	
	static Invocable of(Object target, Method method) {
		return new Invocable() {
			
			@Override
			public Object invoke(Object... args) {
				try {
					return method.invoke(target, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new ExecutionException("Exception invoking " + method + " of " + target + ": " + e, e);
				}
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				return method.getParameterTypes();
			}
			
			@Override
			public String[] getParameterNames() {
				return Stream.of(method.getParameters()).map(Parameter::getName).toArray(size -> new String[size]);
			}
			
			@Override
			public Class<?> getReturnType() {
				return method.getReturnType();
			}
			
		};
	}
		
	static Invocable of(Constructor<?> constructor) {
		return new Invocable() {
			
			@Override
			public Object invoke(Object... args) {
				try {
					return constructor.newInstance(args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
					throw new ExecutionException("Exception invoking " + constructor + ": " + e, e);
				}
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				return constructor.getParameterTypes();
			}
			
			@Override
			public String[] getParameterNames() {
				return Stream.of(constructor.getParameters()).map(Parameter::getName).toArray(size -> new String[size]);
			}
			
			@Override
			public Class<?> getReturnType() {
				return constructor.getDeclaringClass();
			}
			
		};
	}	
		
	/**
	 * Creates a dynamic proxy dispatching to invocables.
	 * @param classLoader
	 * @param resolver
	 * @param interfaces
	 * @return
	 */
	static Object createProxy(
			ClassLoader classLoader, 
			BiFunction<Method, Object[], Invocable> resolver, Class<?>... interfaces) {
		
		InvocationHandler invocationHandler = new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Invocable target = resolver.apply(method, args);
				if (target == null) {
					throw new UnsupportedOperationException("No invocable for " + method); 
				}
				return target.invoke(args);
			}
		};
		
		return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
	}
	
	// of
	
	static Invocable ofValue(Object value) {
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return value == null ? null : value.getClass();
			}
			
			@Override
			public String[] getParameterNames() {
				return new String[0];
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				return new Class<?>[0];
			}
			
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
			public String[] getParameterNames() {
				return new String[0];
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				return new Class<?>[0];
			}
			
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
			public String[] getParameterNames() {
				return new String[] { parameterName };
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				return new Class<?>[] { parameterType };
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
			public String[] getParameterNames() {
				return new String[] { firstParameterName, secondParameterName };
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				return new Class<?>[] { firstParameterType, secondParameterType };
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
			java.util.function.BiFunction<Integer,String,String> parameterNameMapper,
			java.util.function.BiFunction<Integer,Class<?>,Class<?>> parameterTypeMapper,
			java.util.function.Function<Class<?>, Class<?>> returnTypeMapper,
			java.util.function.BiFunction<Integer,Object,Object> argumentMapper) {
		
		String[] pNames = getParameterNames();
		if (pNames != null && parameterNameMapper != null) {
			for (int i = 0; i < pNames.length; ++i) {
				pNames[i] = parameterNameMapper.apply(i, pNames[i]);
			}
		}
		
		Class<?>[] pTypes = getParameterTypes();	
		if (pTypes != null && parameterTypeMapper != null) {
			for (int i = 0; i < pNames.length; ++i) {
				pTypes[i] = parameterTypeMapper.apply(i, pTypes[i]);
			}
		}
		
		Class<?> returnType = returnTypeMapper == null ?  getReturnType() : returnTypeMapper.apply(getReturnType());
		
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return returnType;
			}
			
			@Override
			public String[] getParameterNames() {
				return pNames;
			}
			
			@Override
			public Class<?>[] getParameterTypes() {
				return pTypes;
			}
			
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
	 * 
	 * @param config
	 * @param converter
	 * @return
	 */
	default Object call(Map<?,?> config, BiFunction<Object,Class<?>, Object> converter) {
		return call((name, type) -> converter.apply(config.get(name), type));				
	}
	
	/**
	 * Uses converter which leverages factory provider. 
	 * @param config
	 * @param converter
	 * @return
	 */
	default Object call(Map<?,?> config, java.util.function.Function<Class<?>, Invocable> factoryProvider) {
		BiFunction<Object,Class<?>, Object> converter = new BiFunction<Object, Class<?>, Object>() {
			
			@Override
			public Object apply(Object source, Class<?> type) {
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
					throw new IllegalArgumentException("Not a collection: " + source);
				}
				
				// Not an array, not of expected type - use factory
				if (source instanceof Map) {				
					Invocable factory = factoryProvider.apply(type);
					return factory.call((Map<?,?>) source, factoryProvider);
				}
				
				throw new IllegalArgumentException("Not a map: " + source);				
			}			
			
		};
		
		return call(config, converter);				
	}
	
	/**
	 * Factory provider which uses canonical or longest constructor
	 * @param config
	 * @return
	 */
	default Object call(Map<?,?> config) {
		java.util.function.Function<Class<?>, Invocable> factoryProvider = type -> {
	        Constructor<?>[] constructors = type.getDeclaredConstructors();
			if (type.isRecord()) {
		        for (Constructor<?> constructor : constructors) {
		            if (constructor.getParameterCount() == type.getRecordComponents().length) {
		            	return Invocable.of(constructor);
		            }
		        }				
			}
			
			Optional<Constructor<?>> longestConstructorOpt = Stream.of(constructors).reduce((a, b) -> a.getParameterCount() > b.getParameterCount() ? a : b);
			if (longestConstructorOpt.isPresent()) {
				return Invocable.of(longestConstructorOpt.get());
			}
			
			throw new IllegalArgumentException("No constructors in " + type);
		};
		return call(config, factoryProvider);
	}
	
}
