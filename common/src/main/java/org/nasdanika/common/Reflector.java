package org.nasdanika.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Base class for classes doing reflective introspection and wiring/injection
 * @author Pavel
 *
 */
public class Reflector {
	
	/**
	 * A record-like class of an {@link AnnotatedElement} - {@link Method} or {@link Field} - used for reflective operations.
	 * Using class instead of a record to have access to the enclosing Reflector.
	 * @author Pavel
	 *
	 */
	protected class AnnotatedElementRecord<T> implements Predicate<T> {
		
		private Predicate<T> predicate;
		private Object target;
		private AnnotatedElement annotatedElement;

		public AnnotatedElementRecord(Predicate<T> predicate, Object target, AnnotatedElement annotatedElement) {
			this.predicate = predicate;
			this.target = target;
			this.annotatedElement = annotatedElement;
		}
		
		public void set(Object value) {
			Reflector.this.set(target, annotatedElement, value);
		}

		@Override
		public boolean test(T t) {
			return predicate == null ? true : predicate.test(t);
		}
		
		public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
			return annotatedElement.getAnnotation(annotationClass);
		}
		
		public Object get() {
			return Reflector.this.get(target, annotatedElement);
		}

		public Object invoke(Object... args) {
			return Reflector.this.invokeMethod(target, (Method) annotatedElement, args);
		}
		
		public boolean canSet(Class<?> type) {
			return Reflector.this.canSet(annotatedElement, type);
		}
		
		public boolean mustSet(Class<?> type, String message) {
			return Reflector.this.mustSet(annotatedElement, type, message);
		}
		
		public boolean canGet(Class<?> type) {
			return Reflector.this.canGet(annotatedElement, type);
		}
		
		public boolean mustGet(Class<?> type, String message) {
			return Reflector.this.mustGet(annotatedElement, type, message);
		}

		public boolean isValueSupplier() {
			return Reflector.this.isValueSupplier(annotatedElement);
		}
		
		@Override
		public AnnotatedElementRecord<T> and(Predicate<? super T> other) {
			if (other == null) {
				return this;
			}
						
			return new AnnotatedElementRecord<T>(e -> test(e) && other.test(e), target, annotatedElement);
		}
		
		public AnnotatedElement getAnnotatedElement() {
			return annotatedElement;
		}
		
	}	

	/**
	 * Parses and evaluates expression using <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions">Spring Expression Language</a> 
	 * @param obj
	 * @param expr 
	 * @return true if expression is blank or evaluates to true, false if the expression evaluates to false or throws EvaluationException.
	 */
	protected boolean matchPredicate(Object obj, String expr) {
		if (Util.isBlank(expr)) {
			return true;
		}
		
		ExpressionParser parser = getExpressionParser();
		Expression exp = parser.parseExpression(expr);
		EvaluationContext evaluationContext = getEvaluationContext();
		try {			
			return evaluationContext == null ? exp.getValue(obj, Boolean.class) : exp.getValue(evaluationContext, obj, Boolean.class);
		} catch (EvaluationException e) {
			onEvaluationException(obj, expr, evaluationContext, e);
			return false;
		}
	}
	
	protected EvaluationContext getEvaluationContext() {
		return null;
	}
	
	protected ThreadLocal<SpelExpressionParser> expressionParserThreadLocal = new ThreadLocal<>() {
		
		@Override
		protected SpelExpressionParser initialValue() {
			return new SpelExpressionParser();			
		}
		
	};

	protected SpelExpressionParser getExpressionParser() {
		return expressionParserThreadLocal.get();
	}
	
	/**
	 * Override to troubleshoot SPEL predicates.
	 * @param obj
	 * @param expr
	 * @param evaluationContext
	 */
	protected void onEvaluationException(Object obj, String expr, EvaluationContext evaluationContext, EvaluationException exception) {
		
	}
	
	/**
	 * Retrieves field value. May need to be overridden if the target class is not exported by its module.
	 * @param target
	 * @param field
	 * @return
	 */
	protected Object getFieldValue(Object target, Field field) {
		try {
			return  field.get(target);
		} catch (IllegalAccessException e) {
			throw new NasdanikaException("Cannot access field " + field + " of " + target + ": " + e, e);
		}	
	}

	/**
	 * Sets field value. May need to be overridden if the target class is not exported by its module.
	 * @param target
	 * @param field
	 * @param value
	 * @return
	 */
	protected void setFieldValue(Object target, Field field, Object value) {
		try {
			field.set(target, value);
		} catch (IllegalAccessException e) {
			throw new NasdanikaException("Cannot access field " + field + " of " + target + ": " + e, e);
		}	
	}
	
	/**
	 * Invokes method. May need to be overridden if the target class is not exported by its module.
	 * @param target
	 * @param method
	 * @param args
	 * @return
	 */
	protected Object invokeMethod(Object target, Method method, Object... args) {
		try {
			return method.invoke(target, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new NasdanikaException("Error invoking " + method + " of " + target + ": " + e, e);
		}		
	}
	
	protected boolean canSet(AnnotatedElement element, Class<?> type) {
		if (element instanceof Field) {
			return type == null || ((Field) element).getType().isAssignableFrom(type);
		}
		if (element instanceof Method) {
			Method method = (Method) element;
			return !Modifier.isAbstract(((Member) method).getModifiers()) && method.getParameterCount() == 1 && (type == null || method.getParameterTypes()[0].isAssignableFrom(type));
		}
		return false;
	}
	
	protected boolean mustSet(AnnotatedElement element, Class<?> type, String message) {
		if (canSet(element, type)) {
			return true;
		}
		throw new NasdanikaException(message);
	}
	
	protected void set(Object target, AnnotatedElement element, Object value) {
		if (element instanceof Field) {
			setFieldValue(target, (Field) element, value);
		} else if (element instanceof Method) {
			invokeMethod(target, (Method) element, value);
		} else {
			throw new IllegalArgumentException("Cannot set value into " + element);
		}
	}
	
	protected Object get(Object target, AnnotatedElement element) {
		if (element instanceof Field) {
			return getFieldValue(target, (Field) element);
		} 
		if (element instanceof Method) {
			return invokeMethod(target, (Method) element);
		}
		
		throw new IllegalArgumentException("Cannot get value from " + element);
	}
	
	protected boolean canGet(AnnotatedElement element, Class<?> type) {
		if (element instanceof Field) {
			return type == null || type.isAssignableFrom(((Field) element).getType());
		}
		if (element instanceof Method) {
			Method method = (Method) element;					
			return !Modifier.isAbstract(((Member) method).getModifiers()) && method.getParameterCount() == 0 && (type == null || type.isAssignableFrom(method.getReturnType()));
		}
		return false;
	}
	
	protected boolean mustGet(AnnotatedElement element, Class<?> type, String message) {
		if (canGet(element, type)) {
			return true;
		}
		throw new NasdanikaException(message);
	}

	protected boolean isValueSupplier(AnnotatedElement element) {
		return element instanceof Field || (element instanceof Method && ((Method) element).getParameterCount() == 0);
	}

}
