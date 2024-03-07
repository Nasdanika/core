package org.nasdanika.common;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation for generated code to allow generators to decide whether to overwrite generated code or preserve
 * because it was manually modified.
 */
@Retention(SOURCE)
@Target({PACKAGE, TYPE, ANNOTATION_TYPE, METHOD, CONSTRUCTOR, FIELD,  LOCAL_VARIABLE, PARAMETER})
public @interface Generated {
	
	/**
	 * True (default) if the annotated element was generated, false otherwise.
	 * Developer may modify true to false to indicate that the annotated element was manually modified.
	 * @return
	 */
	boolean value() default true;

   /**
    * Date when the annotated element was generated.
    */
   String date() default "";

   /**
    * Human-readable comments.
    */
   String comments() default "";

   /**
    * Generator-specific digest to detect manual changes in case the developer forgets to set value to false or remove the annotation. 
    */
   String digest() default "";

   /**
    * Generator ID, e.g. a fully qualified name of the generator class.
    */
   String generator() default "";

   /**
    * Generator-specific configuration.
    * Configuration may be used for tracking purposes. 
    * It may also be read by the generator before generation.  
    */
   String generatorConfiguration() default "";
   
}

