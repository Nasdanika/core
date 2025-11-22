package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.common.ProgressMonitor;

/**
 * Field or method to inject a matching child endpoint. 
 * A method shall have one, two or three parameters. 
 * If a single parameter then the endpoint is passed to the method. 
 * If two parameters then the child and endpoint are passed - it allows to use one method for more than one child/endpoint.   
 * In case of 3 parameters a {@link ProgressMonitor} is passed as the third argument   
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface ChildEndpoint {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
	 * which is evaluated in the context of the child. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
	
	/**
	 * If true, child endpoint is injected once to the first matching field or method.
	 * If false, default, it is injected into all matching fields and methods.
	 * @return
	 */
	boolean consume() default false;
		
}
