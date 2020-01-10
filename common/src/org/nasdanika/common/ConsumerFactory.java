package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface ConsumerFactory<T> extends ExecutionParticipantFactory<Consumer<T>> {
	
	default FunctionFactory<T,T> asFunctionFactory() {
		return new FunctionFactory<T, T>() {

			@Override
			public Function<T, T> create(Context context) throws Exception {
				return ConsumerFactory.this.create(context).asFunction();
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
			public Consumer<T> create(Context context) throws Exception {
				return new ContextifiedExecutionParticipant.ContextifiedConsumer<T>(contextSupplierFactory.create(context), ConsumerFactory.this);
			}
			
		};
	}
	
}
