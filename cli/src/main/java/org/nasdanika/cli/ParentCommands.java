package org.nasdanika.cli;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.common.Adaptable;

/**
 * Types of parent command to add a sub-command or mix-in annotated with this annotation to.
 * Parent commands must be adaptable to the value types - either be instances of these types or implement {@link Adaptable} and return non-null from the <code>adaptTo()</code> method. 
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ParentCommands {

	Class<?>[] value();
	
}


