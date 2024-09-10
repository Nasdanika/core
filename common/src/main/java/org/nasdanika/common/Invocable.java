package org.nasdanika.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public interface Invocable {
	
	/**
	 * 
	 * @return null for unknown parameter names
	 */
	default String[] parameterNames() {
		return null;
	}
	
	/**
	 * 
	 * @return null for unknown parameter types
	 */
	default Class<?>[] parameterTypes() {
		return null; 
	}
	
	Object invoke(Object... args);
	
	default Invocable bind(String name, Object binding) {		
		String[] pNames = parameterNames();
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
		
		String[] pNames = parameterNames();
		Class<?>[] pTypes = parameterTypes();		
		
		return new Invocable() {
			
			@Override
			public String[] parameterNames() {
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
			public Class<?>[] parameterTypes() {
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
			public Class<?>[] parameterTypes() {
				return method.getParameterTypes();
			}
			
			@Override
			public String[] parameterNames() {
				return Stream.of(method.getParameters()).map(Parameter::getName).toArray(size -> new String[size]);
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
			public Class<?>[] parameterTypes() {
				return constructor.getParameterTypes();
			}
			
			@Override
			public String[] parameterNames() {
				return Stream.of(constructor.getParameters()).map(Parameter::getName).toArray(size -> new String[size]);
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
	
}
