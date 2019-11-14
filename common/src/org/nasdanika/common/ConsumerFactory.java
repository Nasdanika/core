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
	
}
