package org.nasdanika.cli;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Types of mix-ins to add to the annotated type.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface MixIns {

	Class<?>[] value();
	
}


