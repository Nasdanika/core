package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface FunctionFactory<T,R> extends ExecutionParticipantFactory<Function<T,R>> {
	
	/**
	 * Provides {@link FunctionFactory} for a specific argument and return type.
	 * @author Pavel
	 *
	 */
	interface Provider {
		<T,R> FunctionFactory<T,R> getFactory(Class<T> parameterType, Class<R> resultType);
	}	
	
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
		
	/**
	 * @param contextSupplierFactory Factory which creates a context to be passed to this factory to create supplier.
	 * @return Function factory which creates a context using the context supplier factory and then uses that context and this factory to create a function.
	 */
	default FunctionFactory<T,R> contextify(SupplierFactory<Context> contextSupplierFactory) {
		return new FunctionFactory<T,R>() {

			@Override
			public Function<T,R> create(Context context) throws Exception {
				return new ContextifiedExecutionParticipant.ContextifiedFunction<T,R>(contextSupplierFactory.create(context), FunctionFactory.this);
			}
			
		};
	}	
	
}
