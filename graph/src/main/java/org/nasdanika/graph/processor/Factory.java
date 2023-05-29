package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.graph.Element;

/**
 * On a type this annotation is used to filter reflective factory targets using this annotation value as a predicate.
 * Annotating a class without providing a value makes no sense.
 * 
 * For methods and fields this annotation indicates that the method return value or field value shall be used as introspection targets to create element processors.
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
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
	
	/**
	 * Matching by object type.
	 * @return
	 */
	Class<? extends Element> type() default Element.class; 

}
