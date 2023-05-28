package org.nasdanika.graph.processor.emf;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NodeProcessorConfig;

/**
 * Annotation for a method creating an {@link EObjectNode} processor for {@link EObject}'s with matching URI.
 * The method shall have zero or one parameter. In the second case the parameter type shall be assignable from {@link NodeProcessorConfig}.
 * An annotated method may return an object or null. In the first case the returned object may be introspected to wire handlers and endpoints 
 * to methods and fields with annotations.
 * 
 * When applied to a type (class) identifier is used as a base URI to resolve URI of method annotations, type and value are used for filtering in addition to method annotation and priority is ignored.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({ METHOD, TYPE })
public @interface EObjectNodeProcessor {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
	 * which is evaluated in the context of an element. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * {@link EObject} URI.
	 * @return
	 */
	String identifier() default "";
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
	
	// TODO - EObject type
	
	/**
	 * Node's object type
	 * @return
	 */
	Class<? extends EObject> type() default EObject.class; 

}
