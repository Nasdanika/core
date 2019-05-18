package org.nasdanika.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Route method parameters with this annotation are provided values from request parameter with a given name. 
 * Parameter type can be String or String[]. 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface QueryParameter {
	
	/**
	 * Query parameter name.
	 * @return
	 */
	String value();
	
	/**
	 * @return Default value if query parameter is not present in the request.
	 */
	String[] defaultValue() default {};
	
}
