package org.nasdanika.http;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class ReflectiveConverter implements Converter {
	
	static Converter INSTANCE = new ReflectiveConverter();
	
//	@ConverterMethod
//	public String convert(URL value) {
//		return HTMLElementImpl.stringify(value);
//	}	
//
//	@ConverterMethod
//	public String convert(InputStream value) {
//		return HTMLElementImpl.stringify(value);
//	}	
//
//	@ConverterMethod
//	public String convert(Reader value) {
//		return HTMLElementImpl.stringify(value);
//	}	

	@ConverterMethod
	public String toString(Object value) {
		return String.valueOf(value);
	}

	/**
	 * Performs conversion using methods annotated with {@link ConverterMethod} first. A method with a more specific compatible parameter type take precedence over a method with more general type.
	 * If conversion cannot be done with converter methods then constructor conversion is attempted in a similar way - a constructor with a more specific compatible parameter types takes precedence over a less specific.
	 * If not conversion can be performed using the aforementioned approached and the source object is an instance of {@link EObject} then conversion from adaptation is attempted using {@link EObjectAdaptable}.adaptTo() method.   
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Object source, Class<T> type) throws Exception {
		// Converter methods conversion
		Optional<Method> cm = Arrays.stream(getClass().getMethods())
				.filter(m -> m.getAnnotation(ConverterMethod.class) != null 
					&& m.getParameterCount() == 1 
					&& m.getParameterTypes()[0].isInstance(source)
					&& type.isAssignableFrom(m.getReturnType()))
				.sorted((m1, m2) -> compare(m1.getParameterTypes()[0], m2.getParameterTypes()[0]))
				.findFirst();
		
		if (cm.isPresent()) {
			return (T) cm.get().invoke(this,source);
		}			
		
		// Constructor conversion
		Optional<Constructor<?>> co = Arrays.stream(type.getConstructors())
			.filter(c -> c.getParameterCount() == 1 && c.getParameterTypes()[0].isInstance(source))
			.sorted((c1, c2) -> compare(c1.getParameterTypes()[0], c2.getParameterTypes()[0]))
			.findFirst();
		
		if (co.isPresent()) {
			return (T) co.get().newInstance(source);
		}
		
//		// Adapter conversion		
//		if (source instanceof EObject) {
//			return EObjectAdaptable.adaptTo((EObject) source, type);
//		}
		
		return null;
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
