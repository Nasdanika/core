package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.graph.Element;

/**
 * Annotation for method creating an {@link Element} processor.
 * The method shall have zero or one parameter. In the second case the parameter type shall be assignable from 
 * {@link ElementProcessorConfig} or its subclasses - {@link NodeProcessorConfig} or {@link ConnectionProcessorConfig}.
 * An annotated method may return an object or null. In the first case the returned object may be introspected to wire handlers and endpoints 
 * to methods and fields with {@link Handler} and {@link Endpoint}
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ElementProcessor {
	
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
	
	Class<Element> type() default Element.class; 
	
	/**
	 * @return If true, handlers and endpoints wired to annotated methods and fields are removed from {@link ElementProcessorConfig} returned from {@link ElementProcessorInfo}.getConfig().
	 */
	boolean hideWired() default true;

}
