package org.nasdanika.drawio.processor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;

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
	 * If not blank value is used to match {@link ModelElement} label or {@link Page} name.
	 * If blank and selector is not provided, then model element label is converted to camel case by 
	 * lower casing the first word, upper casing the rest and concatenating them. E.g. "Hello world" would become "helloWorld".
	 * The resulting string is used to match method name. If selector is set and value is blank then value is not used.
	 * @return
	 */
	String value() default "";
	
	/**
	 * Model element selector in the form of <code>property=value pattern</code>.
	 * Elements are concatenated with and. 
	 * @return
	 */
	String[] selector() default {};
	
	/**
	 * @return {@link IntrospectionLevel}, DECLARED by default.
	 */
	IntrospectionLevel introspect() default IntrospectionLevel.DECLARED;
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
	
	/**
	 * @return Element type to match. Defaults to {@link ModelElement}.
	 */
	Class<? extends Element> type() default ModelElement.class;
	
	/**
	 * @return If true, handlers and endpoints wired to annotated methods and fields are removed from {@link ElementProcessorConfig} returned from {@link ElementProcessorInfo}.getConfig().
	 */
	boolean hideWired() default true;

}
