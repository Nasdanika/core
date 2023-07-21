package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Field or method to inject parent processor or parent info.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface ParentProcessor {
	
	/**
	 * @return If true then {@link ProcessorInfo} is wired to the annotated field/method.
	 */
	boolean value() default false;
		
}
