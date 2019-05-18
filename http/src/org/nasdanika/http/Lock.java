package org.nasdanika.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines locking to apply when executing route method.
 * @author Pavel Vlasov
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Locks.class)
public
@interface Lock {
	
	/**
	 * Lock to apply to the resource specified in resource attribute before executing the route method.
	 * @author Pavel Vlasov
	 *
	 */
	enum Type { 
		/**
		 * No locking.
		 */
		NONE, 
		/**
		 * Read lock.
		 */
		READ, 
		/**
		 * Write lock.
		 */
		WRITE,		
		/**
		 * Lock type is implied from the request method. WRITE for PUT, POST, PATCH, and DELETE, READ otherwise.
		 */
		IMPLY_FROM_HTTP_METHOD
	}
	
	/**
	 * Lock to apply to the context object of the route method before executing the route method.
	 * @return
	 */
	Type value() default Type.IMPLY_FROM_HTTP_METHOD;
	
	/**
	 * Lock resource path relative to the context resource. 
	 */
	String resource() default "";
			
}