package org.nasdanika.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a classloader markdown resource with a detailed description of a command.
 * The description is output to HTML documentation below the usage information.
 * By default, if value attribute is not set, a resource with the same name as the annotated type and <code>.md</code> extension is used for description if it exists. 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Description {

	/**
	 * Description resource. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Documentation icon. Treated as URL if there is a slash and as a CSS class otherwise.
	 * @return
	 */
	String icon() default "";
	
	/**
	 * Documentation tooltip.
	 * @return
	 */
	String tooltip() default "";
	
}
