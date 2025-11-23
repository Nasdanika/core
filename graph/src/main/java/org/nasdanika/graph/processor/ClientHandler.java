package org.nasdanika.graph.processor;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for fields and methods returning a client handler.
 * Methods may be with zero parameters or with one parameter taking client key. 
 * 
 * Method with wrap attribute set to INVOCABLE or ASYNC_INVOCABLE can have any number of parameters.
 * 
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ClientHandler {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
	 * which is evaluated in the context of the client key. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
	
	/**
	 * Handler wrapper
	 * @return
	 */
	HandlerWrapper wrap() default HandlerWrapper.NONE;
	
	/**
	 * Optional parameter names for method handler wrappers INVOCABLE and ASYNC_INVOCABLE 
	 * @return
	 */
	String[] parameterNames() default {};
	
	/**
	 * Applicable to annotated methods. If this array is not empty then a dynamic proxy is created with 
	 * calls of interface method(s) invoking this method and all other method calls (e.g. equals()) routed to the invocation handler instance.
	 * @return
	 */
	Class<?>[] proxy() default {};	
	
}
