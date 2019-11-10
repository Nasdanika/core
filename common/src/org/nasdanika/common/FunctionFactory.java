package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface FunctionFactory<T,R> extends ExecutionParticipantFactory<Function<T,R>> {
		
	Function<T,R> create(Context context) throws Exception;
	
	default <V> FunctionFactory<T,V> then(FunctionFactory<R,V> then) throws Exception {
		throw new UnsupportedOperationException();
	}
		
	default ConsumerFactory<T> then(ConsumerFactory<R> then) throws Exception {
		throw new UnsupportedOperationException();
	}		
	
}
