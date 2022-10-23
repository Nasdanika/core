package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Field or method to inject a matching outgoing endpoint. A method shall have one or two parameters. 
 * If a single parameter than the endpoint is passed to the method. If two parameters then the connection and endpoint are passed - it allows to use one method for more than one connection/endpoint.   
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface OutgoingEndpoint {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
	 * which is evaluated in the context of the outgoing connection. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
		
}
