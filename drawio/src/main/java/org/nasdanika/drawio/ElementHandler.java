package org.nasdanika.drawio;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for target visitor methods to which invocations are dispatched by the reflective visitor created by Util.reflectiveVisitor(target).
 * Methods shall have one or two parameters compatible with parameters of Element.accept() visitor BiFunction. 
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ElementHandler{
	
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
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;

}
