package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

/**
 * Annotation for a method creating an {@link Element} processor.
 * The method shall have 5 parameters compatible with:
 * <pre>
ProcessorConfig&lt;H,E&gt; config, 
boolean parallel, 
BiConsumer&lt;Element,BiConsumer&lt;ProcessorInfo&lt;Object&gt;,ProgressMonitor&gt;&gt; infoProvider,
Function<ProgressMonitor,P> next,
ProgressMonitor progressMonitor
</pre>
 *  The first parameter type shall be assignable from 
 * {@link ProcessorConfig} or its subclasses - {@link NodeProcessorConfig} or {@link ConnectionProcessorConfig}, the second - from {@link ProgressMonitor}.
 * An annotated method may return an object or null. In the first case the returned object may be introspected to wire handlers and endpoints 
 * to methods and fields with annotations.
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Processor {
	
	/**
	 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
	 * which is evaluated in the context of an element. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
	 * @return
	 */
	int priority() default 0;
	
	/**
	 * Matching by object type.
	 * @return
	 */
	Class<? extends Element> type() default Element.class; 

// Too complex with chaining, always wiring and hiding wired	
//	/**
//	 * @return If true, handlers, children, and endpoints wired to annotated methods and fields are removed from wired maps for handlers, children, and endpoints  
//	 */
//	boolean hideWired() default true;
//	
//	/**
//	 * @return If true, reflection is used to wire endpoints, handlers, registry entries and other configuration.  
//	 */
//	boolean wire() default true;

}
