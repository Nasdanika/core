package org.nasdanika.common;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
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
	 * On a type this annotation is used to filter reflective factory targets using this annotation value as a predicate.
	 * Annotating a class without providing a value makes no sense.
	 * 
	 * For methods and fields this annotation indicates that the method return value or field value shall be used as 
	 * introspection/reflection targets.
	 * @author Pavel
	 *
	 */
	@Retention(RUNTIME)
	@Target({ FIELD, METHOD, TYPE })
	@Inherited
	public @interface Factory {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
		 * which is evaluated in the context of an element. 
		 * @return
		 */
		String value() default "";
		
		/**
		 * Matching by object type.
		 * @return
		 */
		Class<?> type() default Object.class; 

	}
	
	/**
	 * For methods and fields this annotation indicates that the method return value or field value shall be a {@link Collection} 
	 * whose elements are used as introspection/reflection targets.
	 * @author Pavel
	 *
	 */
	@Retention(RUNTIME)
	@Target({ FIELD, METHOD })
	public @interface Factories {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
		 * which is evaluated in the context of an element. 
		 * @return
		 */
		String value() default "";
		
		/**
		 * Matching by object type.
		 * @return
		 */
		Class<?> type() default Object.class; 

	}	
	
	/**
	 * A record-like class of an {@link AnnotatedElement} - {@link Method} or {@link Field} - used for reflective operations.
	 * Using class instead of a record to have access to the enclosing Reflector.
	 * @author Pavel
	 *
	 */
	protected class AnnotatedElementRecord implements Predicate<Object> {
		
		private Predicate<Object> predicate;
		private Object target;
		private AnnotatedElement annotatedElement;
		private URI baseURI;

		public AnnotatedElementRecord(Predicate<Object> predicate, Object target, AnnotatedElement annotatedElement) {
			this.predicate = predicate;
			this.target = target;
			this.annotatedElement = annotatedElement;
			if (annotatedElement instanceof Method) {
				this.baseURI = URI.createURI(Util.CLASSPATH_URL_PREFIX + ((Method) annotatedElement).getDeclaringClass().getName().replace('.', '/'));				
			} else if (annotatedElement instanceof Field) {
				this.baseURI = URI.createURI(Util.CLASSPATH_URL_PREFIX + ((Field) annotatedElement).getDeclaringClass().getName().replace('.', '/'));								
			} else if (annotatedElement instanceof Class) {
				this.baseURI = URI.createURI(Util.CLASSPATH_URL_PREFIX + ((Class<?>) annotatedElement).getName().replace('.', '/'));				
			}
		}
		
		public void set(Object value) {
			Reflector.this.set(target, annotatedElement, value);
		}

		@Override
		public boolean test(Object t) {
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
		public AnnotatedElementRecord and(Predicate<Object> other) {
			if (other == null) {
				return this;
			}
						
			return new AnnotatedElementRecord(e -> test(e) && other.test(e), target, annotatedElement);
		}
		
		public AnnotatedElement getAnnotatedElement() {
			return annotatedElement;
		}

		/**
		 * Classpath URI of the declaring class. Use to resolve resource references.
		 * @return
		 */
		public URI getBaseURI() {
			return baseURI;
		}
		
	}	
	
	/**
	 * Recursively collects annotated elements
	 * @param target
	 * @return
	 */
	protected Stream<AnnotatedElementRecord> getAnnotatedElementRecords(Object target) {
		Predicate<Object> targetPredicate = getTargetPredicate(target);
		return Util.getFieldsAndMethods(target.getClass()).flatMap(ae -> getAnnotatedElementRecords(target, ae)).map(aer -> aer.and(targetPredicate));
	}
	
	/**
	 * @return target-specific predicate, e.g. filtering by an annotation on a target.
	 */
	protected Predicate<Object> getTargetPredicate(Object target) {
		return null;
	}
	
	protected Stream<AnnotatedElementRecord> getAnnotatedElementRecords(Object target, AnnotatedElement annotatedElement) {
		Factory factory = annotatedElement.getAnnotation(Factory.class);
		if (factory == null) {			
			Factories factories = annotatedElement.getAnnotation(Factories.class);
			if (factories == null) {
				return Stream.of(new AnnotatedElementRecord(null, target, annotatedElement));
			}
			Predicate<Object> factoriesPredicate = e -> factories.type().isInstance(e) && matchPredicate(e, factories.value());
			@SuppressWarnings("unchecked")
			Collection<Object> factoriesTargets = (Collection<Object>) get(target, annotatedElement);
			return factoriesTargets.stream().flatMap(this::getAnnotatedElementRecords).map(r -> r.and(factoriesPredicate));
		}
		Predicate<Object> factoryPredicate = e -> factory.type().isInstance(e) && matchPredicate(e, factory.value());
		return getAnnotatedElementRecords(get(target, annotatedElement)).map(r -> r.and(factoryPredicate));
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
