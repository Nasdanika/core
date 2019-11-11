package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface SupplierFactory<T> extends ExecutionParticipantFactory<Supplier<T>> {
		
	Supplier<T> create(Context context) throws Exception;
		
	default <V> SupplierFactory<V> then(FunctionFactory<? super T,V> then) {
		return new SupplierFactory<V>() {
			
			@Override
			public Supplier<V> create(Context context) throws Exception {
				return SupplierFactory.this.create(context).then(then.create(context));
			}
			
		};
	}
	
	default CommandFactory then(ConsumerFactory<? super T> then) {
		return new CommandFactory() {
			
			@Override
			public Command create(Context context) throws Exception {
				return SupplierFactory.this.create(context).then(then.create(context));
			}
			
		};
	}
	
}
