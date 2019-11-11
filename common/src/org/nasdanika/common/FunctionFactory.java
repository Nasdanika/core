package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface FunctionFactory<T,R> extends ExecutionParticipantFactory<Function<T,R>> {
			
	Function<T,R> create(Context context) throws Exception;
	
	default <V> FunctionFactory<T,V> then(FunctionFactory<? super R,V> then) {
		return new FunctionFactory<T, V>() {
			
			@Override
			public Function<T, V> create(Context context) throws Exception {
				return FunctionFactory.this.create(context).then(then.create(context));
			}
			
		};
	}
		
	default ConsumerFactory<T> then(ConsumerFactory<? super R> then) {
		return new ConsumerFactory<T>() {
			
			@Override
			public Consumer<T> create(Context context) throws Exception {
				return FunctionFactory.this.create(context).then(then.create(context));
			}
			
		};
	}		
	
}
