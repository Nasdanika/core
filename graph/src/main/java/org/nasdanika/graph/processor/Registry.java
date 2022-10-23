package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * A field or method inject registry - a map of graph elements to their {@link ProcessorInfo}.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD })
public @interface Registry {
		
}
