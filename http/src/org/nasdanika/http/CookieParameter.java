package org.nasdanika.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.Cookie;

/**
 * Route method parameters with this annotation are provided values from request cookie with a given name. 
 * Parameter type shall be String or String[] (cookie value), or {@link Cookie} or Cookie[] . 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CookieParameter {
	
	/**
	 * Path parameter name.
	 * @return
	 */
	String value();
	
}
