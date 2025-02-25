package org.nasdanika.common;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 
 */
public interface Invocable {
	
	/**
	 * Something that invokes an invocable and returns its result, possibly post-processing.
	 * This interface can be implemented by parent commands to which sub-commands pass their invocable so parent/sub commands can collaborate
	 * - the sub-command creates an invocable and configures it, the parent invokes it with arguments, the parent may bind arguments
	 * or wrap the invocable into another invocable and pass to its parent. 
	 * 
	 */
	interface Invoker {
		
		Object invoke(Invocable invocable);
		
	}
	
	/**
	 * Allows to specify method name and optionally narrow type.
	 */
	@Retention(RUNTIME)
	@Target(ElementType.PARAMETER)	
	@interface Parameter {
		
		String name();
		
		Class<?> type() default Void.class;
		
	}
		
	static Parameter createParameter(String name, Class<?> type) {
		return new Parameter() {

			@Override
			public String name() {
				return name;
			}

			@Override
			public Class<?> type() {
				return type;
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return Parameter.class;
			}
			
		};
	}
	
	/**
	 * If the argument is annotated with Parameter, then the annotation is returned if type() is not void.
	 * If type() is void, the parameter type is used.
	 * Otherwise the parameter name and type is used to create the return value.
	 * @param parameter
	 * @return
	 */
	static Parameter createParameter(java.lang.reflect.Parameter parameter) {
		Parameter pAnnotation = parameter.getAnnotation(Parameter.class);		
		
		return new Parameter() {

			@Override
			public String name() {
				return pAnnotation == null ? parameter.getName() : pAnnotation.name();
			}

			@Override
			public Class<?> type() {
				return pAnnotation == null || pAnnotation.type() == Void.class ? parameter.getType() : pAnnotation.type();
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return Parameter.class;
			}
			
		};
	}
	
	/**
	 * 
	 * @return null for unknown parameters
	 */
	default Parameter[] getParameters() {
		return null;
	}
	
	/**
	 * @return all known parameters for composite invocables, e.g. a group of constructors or methods
	 */
	default Parameter[][] getAllParameters() {
		Parameter[] parameters = getParameters();
		return parameters == null ? new Parameter[][] {} : new Parameter[][] { parameters };
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
	 * Convenience method
	 * @param <T>
	 * @param args
	 * @return
	 */
	default <T> T listInvoke(List<?> args) {		
		return args == null ? invoke() : invoke(args.toArray());
	}
	
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
			if (name.equals(parameters[i].name())) {				
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
	
	private static boolean isInstance(Class<?> type, Object value) {
		if (value == null) {
			return true;
		}
		if (type.isInstance(value)) {
			return true;
		}
		return type.isPrimitive() && ClassUtils.primitiveToWrapper(type).isInstance(value);
	}
	
	default Invocable bindWithOffset(int offset, Object... bindings) {
		if (bindings.length == 0) {
			return this;
		}		
		
		Parameter[] parameters = getParameters();
		Class<?> returnType = getReturnType();
		
		if (parameters != null) {
			for (int i = 0; i < bindings.length; ++i) {
				if (i + offset >= parameters.length) {
					throw new IllegalArgumentException("Bindings exceed parameters length");
				}				
				Class<?> parameterType = parameters[offset + i].type();
				if (!isInstance(parameterType, bindings[i])) {
					throw new IllegalArgumentException(bindings[i].getClass() + " is not an instance of " + parameterType.getName()); 
				}
			}
		}
		
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
				if (args == null) {
					args = new Object[0];
				}
				Object[] finalArgs = new Object[bindings.length + args.length];
				System.arraycopy(args, 0, finalArgs, 0, offset);
				System.arraycopy(bindings, 0, finalArgs, offset, bindings.length);
				System.arraycopy(args, offset, finalArgs, offset + bindings.length, args.length - offset);
				return Invocable.this.invoke(finalArgs);
			}
			
		};
	}
	
	static Invocable of(Object target, Field field) {
		return new Invocable() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				try {
					field.set(target, args[0]);
					return null;
				} catch (IllegalAccessException e) {
					throw new NasdanikaException("Cannot access field " + field + " of " + target + ": " + e, e);
				}					
			}
			
			@Override
			public Parameter[] getParameters() {
				return new Parameter[] { createParameter(field.getName(), field.getType()) };
			}
			
			@Override
			public Class<?> getReturnType() {
				return Void.class;
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
					Parameter pAnnotation = parameters[i].getAnnotation(Parameter.class);
					String pName = pAnnotation == null ? parameters[i].getName() : pAnnotation.name(); 
					if (parameterNames != null & parameterNames.length > i) {
						pName = parameterNames[i];					
					}
					ret[i] = createParameter(
							pName, 
							pAnnotation == null || pAnnotation.type() == Void.class ? parameters[i].getType() : pAnnotation.type());
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
					Parameter pAnnotation = parameters[i].getAnnotation(Parameter.class);
					String pName = pAnnotation == null ? parameters[i].getName() : pAnnotation.name(); 
					if (parameterNames != null & parameterNames.length > i) {
						pName = parameterNames[i];					
					}
					ret[i] = createParameter(
							pName, 
							pAnnotation == null || pAnnotation.type() == Void.class ? parameters[i].getType() : pAnnotation.type());
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
	 * Handles invocation of default methods not resolved to invocables.
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
					if (method.isDefault()) {						
						return MethodHandles.lookup()
								.findSpecial(
										method.getDeclaringClass(), 
										method.getName(), 
										MethodType.methodType(method.getReturnType(), method.getParameterTypes()), 
										method.getDeclaringClass())
	                            .bindTo(proxy)
	                            .invokeWithArguments(args);					
						
					}					
					
					// A hack for object methods like toString(), hashCode(), equals()
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
	 * Creates a proxy for a functional interface dispatching the functional interface method to this invocable.
	 * @param <T>
	 * @param type
	 * @param classLoader
	 * @return
	 */
	default <T> T createProxy(Class<T> type, ClassLoader classLoader) {
		Method target = Arrays.stream(type.getMethods())
	            .filter(method -> !(method.isDefault() || Modifier.isStatic(method.getModifiers())))
	            .findFirst()
	            .orElseThrow(() -> new IllegalArgumentException("No single abstract method found in " + type.getName()));
		
		return createProxy(
				classLoader,
				(proxy, method, args) -> Objects.equals(method, target) ? this : null,
				type);
	}
	
	/**
	 * Creates a functional interface proxy with the context class loader
	 * @param <T>
	 * @param type
	 * @return
	 */
	default <T> T createProxy(Class<T> type) {
		return createProxy(type, Thread.currentThread().getContextClassLoader());
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
	
	static <T> Invocable of(Runnable task) {
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {				
				return Void.class;
			}
			
			@Override
			public Parameter[] getParameters() {
				return new Parameter[0];
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				task.run();
				return null;
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
				return new Parameter[] { createParameter(parameterName, parameterType) };
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
						createParameter(firstParameterName, firstParameterType), 
						createParameter(secondParameterName, secondParameterType) };
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
			return call((index, parameter) -> converter.apply(((Map<?,?>) config).get(parameter.name()), parameter.type()));
		}
		if (config instanceof List) {		
			return call((index, parameter) -> converter.apply(((List<?>) config).get(index), parameter.type()));
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
		return Stream.of(constructor.getParameters()).map(p -> createParameter(p)).toArray(size -> new Parameter[size]);
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
	       			Stream.of(parameters).map(Parameter::name).toList(), 
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
			
			@Override
			public Invocable bind(Object... bindings) {
				// TODO - retain only matching, return single matching if one, throw an exception if none
				
				// TODO Auto-generated method stub
				return Invocable.super.bind(bindings);
			}
			
			@Override
			public Invocable bindByName(String name, Object binding) {
				// Iterate, return single if one, throw an exception if none
				// TODO Auto-generated method stub
				return Invocable.super.bindByName(name, binding);
			}
			
			@Override
			public Parameter[][] getAllParameters() {
				// Collect from all
				// TODO Auto-generated method stub
				return Invocable.super.getAllParameters();
			}
			
			private boolean matchArgs(Object[] args, Parameter[] parameters) {
				if (parameters == null) {
					return true;
				}
				if (parameters.length != args.length) {
					return false;
				}
				for (int i = 0; i < args.length; ++i) {
					Class<?> pType = parameters[i].type();
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
			if (parameters[i].type().equals(otherParameters[i].type())) {
				continue;
			}
			if (parameters[i].type().isAssignableFrom(otherParameters[i].type())) {
				--counter;
			} else if (otherParameters[i].type().isAssignableFrom(parameters[i].type())) {
				++counter;
			} 
		}
		
		return counter >= 0;
	}
	
	static Invocable of(ScriptEngine scriptEngine, String script) {
		
		return new Invocable() {
						
			private Map<Integer,Optional<Object>> positionalBindings = new TreeMap<>();			
			
			private void bindOneWithOffset(int offset, Object binding) {
				while (positionalBindings.containsKey(offset)) {
					++offset;
				}
				positionalBindings.put(offset, Optional.ofNullable(binding));
			}
			
			@Override
			public Invocable bindWithOffset(int offset, Object... bindings) {
				for (int i = 0; i < bindings.length; ++i) {
					bindOneWithOffset(offset, bindings[i]);
				}
				return this;
			}

			@Override
			public Invocable bindByName(String name, Object binding) {
				scriptEngine.put(name, binding);
				return this;
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				bind(args);
				OptionalInt maxPositionOpt = positionalBindings.keySet().stream().mapToInt(Integer::intValue).max();
				if (maxPositionOpt.isEmpty()) {
					scriptEngine.put("args", new Object[0]);
				} else {
					Object[] argsVar = new Object[maxPositionOpt.getAsInt() + 1];
					for (int i = 0; i < argsVar.length; ++i) {
						Optional<Object> argOpt = positionalBindings.get(i);
						if (argOpt == null) {
							throw new IllegalStateException("Unbound positional argument at index " + i);
						}
						if (argOpt.isPresent()) {
							argsVar[i] = argOpt.get();
						}
					}
					scriptEngine.put("args", argsVar);
				}
				try {
					return scriptEngine.eval(script);
				} catch (ScriptException e) {
					throw new NasdanikaException(e);
				}
			}
			
		};
				
	}
		
	default Invocable async(Executor executor) {
		return new Invocable() {
			
			@Override
			public Class<?> getReturnType() {
				return CompletableFuture.class;
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T invoke(Object... args) {				
				if (executor == null) {
					// Invoking in the caller thread
					return (T) CompletableFuture.supplyAsync(() -> Invocable.this.invoke(args));
				}
				return (T) CompletableFuture.supplyAsync(() -> Invocable.this.invoke(args), executor);
			}
			
		};
	}
	
	default AsyncInvocable asAsync() {
		if (this instanceof AsyncInvocable) {
			return (AsyncInvocable) this;
		}
		return new AsyncInvocable() {
			
			@Override
			public <T> T invoke(Object... args) {
				return Invocable.this.invoke(args);
			}
			
		};
	}
		
	/**
	 * Creates an invocable wrapping <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions">Spring Expression Language</a> expression.
	 * bindByName() sets variables. invoke() passes args as the root object, if args length is 1 then the first element is passed as the root object.
	 * 
	 * @param expression SpEL expression
	 * @return
	 */
	static Invocable ofExpression(String expression) {
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(expression);
		EvaluationContext evaluationContext = new StandardEvaluationContext();
		
		return new Invocable() {
			
			@Override
			public Invocable bindByName(String name, Object binding) {
				evaluationContext.setVariable(name, binding);
				return this;
			}

			@SuppressWarnings("unchecked")
			@Override
			public <T> T invoke(Object... args) {
				return (T) exp.getValue(evaluationContext, args.length == 1 ? args[0] : args);
			}
			
		};
				
	}
	
}
