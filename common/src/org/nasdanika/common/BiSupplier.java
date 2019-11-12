package org.nasdanika.common;

/**
 * Supplier of two values of different type.
 * @author Pavel
 *
 * @param <T>
 * @param <U>
 */
public interface BiSupplier<T,U> {
	
	T getFirst();
	
	U getSecond();

}
