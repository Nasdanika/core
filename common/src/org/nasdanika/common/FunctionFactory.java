package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface FunctionFactory<T,R> extends ExecutionParticipantFactory<Function<T,R>> {
			
	Function<T,R> create(Context context) throws Exception;
	
	default <V> FunctionFactory<T,V> then(FunctionFactory<R,V> then) throws Exception {
		return new FunctionFactory<T, V>() {
			
			@Override
			public Function<T, V> create(Context context) throws Exception {
				return FunctionFactory.this.create(context).then(then.create(context));
			}
			
		};
	}
		
	default ConsumerFactory<T> then(ConsumerFactory<R> then) throws Exception {
		return new ConsumerFactory<T>() {
			
			@Override
			public Consumer<T> create(Context context) throws Exception {
				return FunctionFactory.this.create(context).then(then.create(context));
			}
			
		};
	}		
	
}
