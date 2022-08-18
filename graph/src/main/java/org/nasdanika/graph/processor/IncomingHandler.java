package org.nasdanika.graph.processor;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;

/**
 * Annotation for methods returning an incoming handler for {@link Node} processors or incoming handler fields.
 * Methods may be with zero parameters or with one parameter compatible with {@link Connection}. 
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IncomingHandler {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
	 * which is evaluated in the context of the incoming connection. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
}
