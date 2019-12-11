package org.nasdanika.common;

import java.util.function.BiConsumer;

/**
 * Supplier of two values of different type.
 * @author Pavel
 *
 * @param <T>
 * @param <U>
 */
public interface BiSupplier<T,U> extends java.util.function.Consumer<BiConsumer<T, U>> {
	
	T getFirst();
	
	U getSecond();
	
	default void accept(BiConsumer<T, U> consumer) {
		consumer.accept(getFirst(), getSecond());
	}

}
