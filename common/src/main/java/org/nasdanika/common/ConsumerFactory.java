package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface ConsumerFactory<T> extends ExecutionParticipantFactory<Consumer<T>> {
	
	/**
	 * Provides {@link ConsumerFactory} for a specific type.
	 * @author Pavel
	 *
	 */
	interface Provider {
		<T> ConsumerFactory<T> getFactory(Class<T> type);
	}
	
	default FunctionFactory<T,T> asFunctionFactory() {
		return new FunctionFactory<T, T>() {

			@Override
			public Function<T, T> create(Context context) {
				return ConsumerFactory.this.create(context).asFunction();
			}
			
		};
	}
	
	default <R> FunctionFactory<Supplier.FunctionResult<T,R>,R> asResultFunctionFactory() {
		return new FunctionFactory<Supplier.FunctionResult<T,R>, R>() {

			@Override
			public Function<Supplier.FunctionResult<T,R>, R> create(Context context) {
				return ConsumerFactory.this.create(context).asResultFunction();
			}
			
		};
	}
	
	/**
	 * @param contextSupplierFactory Factory which creates a context to be passed to this factory to create supplier.
	 * @return Consumer factory which creates a context using the context supplier factory and then uses that context and this factory to create a function.
	 */
	default ConsumerFactory<T> contextify(SupplierFactory<Context> contextSupplierFactory) {
		return new ConsumerFactory<T>() {

			@Override
			public Consumer<T> create(Context context) {
				return new ContextifiedExecutionParticipant.ContextifiedConsumer<T>(contextSupplierFactory.create(context), ConsumerFactory.this);
			}
			
		};
	}
	
}
