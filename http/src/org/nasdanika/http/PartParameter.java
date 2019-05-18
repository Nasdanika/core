package org.nasdanika.http;

import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.Part;

/**
 * Route method parameters with this annotation are provided values from a request part with a given name. 
 * Parameter type can be {@link Part} or {@link InputStream}. 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PartParameter {
	
	/**
	 * Part name.
	 * @return
	 */
	String value();
		
}
