package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Field or method to inject a child processor
 * Must have (parameter) type assignable from the processor type or ProcessorConfig if info is set to true 
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface ChildProcessor {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
	 * which is evaluated in the context of an element. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * If <code>true</code>, {@link ProcessorInfo} for the matching child will be injected. The child processor will be injected otherwise.
	 * @return   
	 */
	boolean info() default false;

}
