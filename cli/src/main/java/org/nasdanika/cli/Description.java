package org.nasdanika.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command description
 * The description is add to the documentation action after the usage information.
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Description {

	/**
	 * Command description. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Description format - markdown, text, or html
	 * @return
	 */
	String format() default "markdown";
	
	/**
	 * Description resource used if the description is an empty string
	 * @return
	 */
	String resource() default "";
	
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
