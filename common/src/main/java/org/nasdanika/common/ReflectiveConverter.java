package org.nasdanika.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReflectiveConverter implements Converter {

	@ConverterMethod
	public String toString(Object value) {
		return String.valueOf(value);
	}
	
	public static Class<?> box(Class<?> primitive) {
		if (primitive == boolean.class) {
			return Boolean.class;
		}
		if (primitive == byte.class) {
			return Byte.class;
		}
		if (primitive == char.class) {
			return Character.class;
		}
		if (primitive == double.class) {
			return Double.class;
		}
		if (primitive == float.class) {
			return Float.class;
		}
		if (primitive == int.class) {
			return Integer.class;
		}
		if (primitive == long.class) {
			return Long.class;
		}
		if (primitive == short.class) {
			return Short.class;
		}
		if (primitive == void.class) {
			return Void.class;
		}
		return primitive;
	}

	/**
	 * Performs conversion using methods annotated with {@link ConverterMethod} first. A method with a more specific compatible parameter type take precedence over a method with more general type.
	 * If conversion cannot be done with converter methods then constructor conversion is attempted in a similar way - a constructor with a more specific compatible parameter types takes precedence over a less specific.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Object source, Class<T> type) {
		// Adaptable conversion
		if (source instanceof Adaptable) {
			T ret = ((Adaptable) source).adaptTo(type);
			if (ret != null) {
				return ret;
			}
		}
		
		// Converter methods conversion
		Optional<Method> cm = Arrays.stream(getClass().getMethods())
				.filter(m -> m.getAnnotation(ConverterMethod.class) != null 
					&& m.getParameterCount() == 1 
					&& m.getParameterTypes()[0].isInstance(source)
					&& type.isAssignableFrom(m.getReturnType()))
				.sorted((m1, m2) -> compare(m1.getParameterTypes()[0], m2.getParameterTypes()[0]))
				.findFirst();
		
		if (cm.isPresent()) {
			try {
				return (T) cm.get().invoke(this,source);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new NasdanikaException("Error converting "+source+" to "+type+" using converter method "+cm.get()+": "+e, e);
			}
		}			
		
		if (acceptConstructorConversionTargetType(type)) {
			// Constructor conversion
			Optional<Constructor<?>> co = Arrays.stream(box(type).getConstructors())
				.filter(c -> c.getParameterCount() == 1 && c.getParameterTypes()[0].isInstance(source))
				.sorted((c1, c2) -> compare(c1.getParameterTypes()[0], c2.getParameterTypes()[0]))
				.findFirst();
			
			if (co.isPresent()) {
				try {
					return (T) co.get().newInstance(source);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
					throw new NasdanikaException("Error converting "+source+" to "+type+" using constructor "+co.get()+": "+e, e);
				}
			}
		}						
		return null;
	}	
	
	/**
	 * Allows to filter out constructor conversions. This implementation filters out {@link JSONObject} and {@link JSONArray}.
	 * @param type
	 * @return
	 */
	protected boolean acceptConstructorConversionTargetType(Class<?> type) {
		if (JSONObject.class.equals(type) || JSONArray.class.equals(type)) {
			return false;
		}
		return true;
	}
	
	// Compares two classes for specificity, more specific (subclass) is "less", e.g. if t1 is a superclass of t2 this method returns 1.
	protected int compare(Class<?> t1, Class<?> t2) {
		if (t1 == t2) {
			return 0;
		}
		if (t1.isAssignableFrom(t2)) {
			return 1;
		}
		if (t2.isAssignableFrom(t1)) {
			return -1;
		}
		return 0; // Not comparable
	}

}
