package org.nasdanika.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Route method parameters with this annotation are provided values from request path element. Path elements 
 * to be mapped to parameters shall be provided in the path attribute in the form <code>{parameter name}</code>, e.g. <code>{page-number}</code>.
 * Parameter type shall be String. 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PathParameter {
	
	/**
	 * Path parameter name.
	 * @return
	 */
	String value();
	
}
