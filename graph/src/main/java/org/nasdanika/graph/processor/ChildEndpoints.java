package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.CompletionStage;

/**
 * Field or method to inject a map of children to {@link CompletionStage}s of their endpoints.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface ChildEndpoints {
		
}
