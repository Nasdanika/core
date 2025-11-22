package org.nasdanika.graph.processor;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * A field or method to inject processor of config of matching registry entry 
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface RegistryEntry {
	
	/**
	 * A <a href="https://docs.spring.io/spring-framework/reference/core/expressions.html">Spring boolean expression</a>
	 * which is evaluated in the context of an element with <code>target</code> variable for registry targets and <code>element</code>,<code>config</code>, and <code>processor</code> for element processors. 
	 * @return
	 */
	String value();
	
	/**
	 * If <code>true</code> {@link ProcessorInfo} is injected, processor otherwise.
	 * If the target (field or parameter) type is ProcessorInfo then info is injected regardless of this setting.
	 * If processor is null and the target type is compatible with ProcessorInfo then info is injected even if this attribute is false. 
	 * @return   
	 */
	boolean info() default false;
			
}
