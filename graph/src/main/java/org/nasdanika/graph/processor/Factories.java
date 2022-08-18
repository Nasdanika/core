package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

/**
 * For methods and fields this annotation indicates that the method return value or field value shall be a {@link Collection} whose elements are used as introspection targets to create element processors.
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
	 * @return {@link IntrospectionLevel}, DECLARED by default.
	 */
	IntrospectionLevel introspect() default IntrospectionLevel.DECLARED;
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;

}