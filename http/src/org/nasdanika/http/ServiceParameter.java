package org.nasdanika.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates command method parameter as service reference.
 * Reference type is taken from parameter type. If parameter type is array, then its component type is used as service type.
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ServiceParameter {
	
	/**
	 * Reference filter.
	 * @return
	 */
	String value() default "";
	
	/**
	 * With parameter type is used to compute reference cardinality. If parameter type is array then mandatory true 
	 * corresponds to 1..* cardinality, mandatory false to 0..*. For non-array parameter types mandatory true corresponds to
	 * 1..1 and mandatory false to 0..1 cardinality.
	 * @return
	 */
	boolean mandatory() default true;
}
