package org.nasdanika.common;

import java.util.function.BiConsumer;

/**
 * Supplier of two values of different type.
 * @author Pavel
 * @deprecated Use {@link Record}s.
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
	
	static <T,U> BiSupplier<T, U> of(T first, U second) {
		return new BiSupplier<T, U>() {

			@Override
			public T getFirst() {
				return first;
			}

			@Override
			public U getSecond() {
				return second;
			}
			
			@Override
			public String toString() {
				return BiSupplier.class.getName() + " { first=" + first +", second=" + second + "}";
			}
		};
	}

}
