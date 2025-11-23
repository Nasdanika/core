package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Field or method from which the connection source handler is obtained.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface SourceHandler {
		
	/**
	 * Applicable to annotated methods. If this array is not empty then a dynamic proxy is created with 
	 * calls of interface method(s) invoking this method and all other method calls (e.g. equals()) routed to the invocation handler instance.
	 * @return
	 */
	Class<?>[] proxy() default {};		
		
}
