package org.nasdanika.graph;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for methods to which reflective visitor calls are dispatched. 
 * Methods may have one or two parameters taking the element and a map of children.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface Handler {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
	 * which is evaluated in the context of the element being visited. 
	 * Methods with longer conditions are matched before methods with shorter (including empty) conditions.
	 * @return
	 */
	String value() default "";
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
		
}
