package org.nasdanika.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to pass the context resource or a resource at the specified relative or absolute path to a method. 
 * If the resource is not an instance of the parameter type it gets converted/adapted to the parameter type. 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ResourceParameter {
	
	/**
	 * Resource path relative to the context resource. The context resource if the path is blank (default).
	 * @return
	 */
	String value() default "";
	
}
