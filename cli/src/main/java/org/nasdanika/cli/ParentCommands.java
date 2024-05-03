package org.nasdanika.cli;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Types of parent command to add a sub-command or mix-in annotated with this annotation to.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ParentCommands {

	Class<?>[] value();
	
}


