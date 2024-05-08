package org.nasdanika.cli;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Command and mix-in classes can be annotated with this annotation
 * listing types of commands/mix-ins which this command/mix-in overrides.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Overrides {

	Class<?>[] value();
	
}


