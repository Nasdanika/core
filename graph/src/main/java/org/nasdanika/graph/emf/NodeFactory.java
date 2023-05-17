package org.nasdanika.graph.emf;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectNode.ResultRecord;

/**
 * Annotation for a method creating a {@link Node} for {@link EObject}
 * The method shall have three parameters compatible with {@link EObject},  {@link BiFunction}&lt; {@link EObject}, {@link ProgressMonitor},  {@link ResultRecord}&gt;, and  {@link ProgressMonitor}.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface NodeFactory {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
	 * which is evaluated in the context of an element. 
	 * @return
	 */
	String value() default "";

	/**
	 * Matching by object type.
	 * @return
	 */
	Class<? extends EObject> type() default EObject.class; 

}
