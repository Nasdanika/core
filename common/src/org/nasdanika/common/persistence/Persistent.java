package org.nasdanika.common.persistence;

/**
 * Something which knows how to store state into storage and load it from it.
 * @author Pavel Vlasov
 * 
 * @param <T> Referenced object type.
 * @param <R> Reference type.
 * @param <S> Storage type.
 */
public interface Persistent<T, R, S> {
	
	/**
	 * Loads state from the storage.
	 * @param storage Storage.
	 * @param resolver Reference resolver. Can be ``null`` if the object itself "knows" how to resolve references,
	 * e.g. it utilizes object proxies resolved on access.
	 */
	void load(S storage, Resolver<T, R> resolver);
	
	/**
	 * Same as load(storage, null).
	 * @param storage
	 */
	default void load(S storage) {
		load(storage, null);
	}
	
	/**
	 * Stores state into the storage.
	 * @param storage
	 */
	void store(S storage, Referencer<T, R> referencer);
	
	/**
	 * Same as store(storage, null).
	 * @param storage
	 */
	default void store(S storage) {
		store(storage, null);
	}

}
