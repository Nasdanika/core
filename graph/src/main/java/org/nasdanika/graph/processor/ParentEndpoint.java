package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Field or method into which a parent endpoint is injected. 
 * Parent endpoint allows the processor to interact with the parent handler for this child.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface ParentEndpoint {
		
}
