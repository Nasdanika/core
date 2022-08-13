package org.nasdanika.drawio.processor;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;

/**
 * Annotation for methods returning an inbound handler for {@link Node} processors or inbound handler fields.
 * Methods may be with zero parameters or with one parameter compatible with {@link Connection}. 
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface InboundHandler {
	
	/**
	 * If not blank value is used to match the inbound {@link Connection} label.
	 * If blank and selector is not provided, then connection label is converted to camel case by 
	 * lower casing the first word, upper casing the rest and concatenating them. E.g. "Hello world" would become "helloWorld".
	 * The resulting string is used to match the annotated field. 
	 * For methods all words are upper cased, concatenated and prefixed with get. E.g. "Hello world" would become "getHelloWorld".
	 * If selector is set and value is blank then value is not used for matching.
	 * @return
	 */
	String value() default "";
		
	/**
	 * Inbound {@link Connection} selector in the form of <code>property=value pattern</code>.
	 * Elements are concatenated with and. 
	 * @return
	 */
	String[] selector() default {};
	
	/**
	 * If not blank value is used to match the inbound {@link Connection}'s source {@link Node} label.
	 * @return
	 */
	String source() default "";
		
	/**
	 * Inbound {@link Connection}'s source {@link Node} selector in the form of <code>property=value pattern</code>.
	 * Elements are concatenated with and. 
	 * @return
	 */
	String[] sourceSelector() default {};
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
}
