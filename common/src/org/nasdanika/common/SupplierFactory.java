package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface SupplierFactory<T> extends ExecutionParticipantFactory<Supplier<T>>, java.util.function.BiFunction<Context,ProgressMonitor,T> {
		
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
		
	@Override
	default T apply(Context context, ProgressMonitor progressMonitor) {
		try {
			return create(context).apply(progressMonitor);
		} catch (DiagnosticException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
	}
	
	default <V> FunctionFactory<V,BiSupplier<V,T>> asFunctionFactory() { 
		return new FunctionFactory<V, BiSupplier<V,T>>() {

			@Override
			public Function<V, BiSupplier<V, T>> create(Context arg) throws Exception {				
				return SupplierFactory.this.create(arg).asFunction();
			}
		};
	}
	
}
