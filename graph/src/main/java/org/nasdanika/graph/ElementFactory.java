package org.nasdanika.graph;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for a method creating a {@link Element} from an object
 * TODO - method parameters
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ElementFactory {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
	 * which is evaluated in the context of an element. 
	 * @return
	 */
	String value() default "";

	/**
	 * Matching by object type.
	 * @return
	 */
	Class<?> type() default Object.class; 
	
	/**
	 * Factory priority
	 * @return
	 */
	int priority() default 0;

}
