package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface SupplierFactory<T> extends ExecutionParticipantFactory<Supplier<T>> {
		
	Supplier<T> create(Context context) throws Exception;
		
	default <V> SupplierFactory<V> then(FunctionFactory<T,V> then) {
		throw new UnsupportedOperationException();
	}
	
	default CommandFactory then(ConsumerFactory<T> then) {
		throw new UnsupportedOperationException();
	}
	
}
