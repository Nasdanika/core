package org.nasdanika.common.persistence;

import java.util.function.Consumer;

/**
 * Resolves references when loading persistent objects.
 * @author Pavel Vlasov
 * 
 * @param <T> Persistent object type.
 * @param <R> Reference type.
 */
public interface Resolver<T,R> {
	
	/**
	 * Resolves the reference.
	 * @param source Source object.
	 * @param reference Reference.
	 * @param targetConsumer Consumer of resolved target object. This consumer may be invoked as part of resolve() method
	 * or later, once all objects are loaded, in the case of a reference to a not yet loaded object.
	 */
	void resolve(T source, R reference, Consumer<T> targetConsumer);

}
