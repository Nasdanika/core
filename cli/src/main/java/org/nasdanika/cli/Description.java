package org.nasdanika.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.nasdanika.common.DocumentationFactory;

/**
 * Command, option, or parameter description
 * The description is add to the documentation action after the usage information.
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Description {

	public static final String MARKDOWN_FORMAT = "markdown";

	/**
	 * Description text. 
	 * @return
	 */
	String value() default "";
	
	/**
	 * Description format - one of available {@link DocumentationFactory} formats.
	 * @return
	 */
	String format() default "";
	
	/**
	 * Description resource used if the value is an empty string
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
