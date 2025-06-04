package org.nasdanika.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Command, option, or parameter description
 * The description is to add to the documentation action after the usage information.
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
	ElementType.TYPE, 
	ElementType.METHOD, 
	ElementType.FIELD, 
	ElementType.PARAMETER,
	ElementType.MODULE})
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
	
	/**
	 * Contributor classes. Use of contributors depends on where the annotation is used.
	 * A typical scenario would be:
	 * <UL>
	 * <LI>Inspect class for implementing a specific interface or extending a specific base class</LI>
	 * <LI>Instantiate contributors of interest with a default constructor or a constructor taking context such as, say, capability loader or progress monitor</LI>
	 * <LI>Invoke interface methods</LI>
	 * @return
	 */
	Class<?>[] contributors() default {};
	
}
