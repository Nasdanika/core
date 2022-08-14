package org.nasdanika.persistence;

import java.util.function.Consumer;

/**
 * Creates a reference from source to target.
 * @author Pavel Vlasov
 * 
 * @param <T> Persistent object type.
 * @param <R> Reference type.
 */
public interface Referencer<T,R> {
	
	/**
	 * Creates a reference
	 * @param source Source object.
	 * @param target Target object.
	 * @param referenceConsumer ConsumerFactory of a reference. This consumer may be invoked as part of reference() method
	 * or later, once all objects are stored, in the case of a reference to a not yet stored object if referencing requires an object to be already stored.
	 */
	void reference(T source, T target, Consumer<R> refernceConsumer);

}
