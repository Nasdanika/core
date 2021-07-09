package org.nasdanika.common;

import java.util.function.BiFunction;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface SupplierFactory<T> extends ExecutionParticipantFactory<Supplier<T>>, java.util.function.BiFunction<Context,ProgressMonitor,T> {
		
	static <T> SupplierFactory<T> empty() {
		return context -> Supplier.empty();
	}	
	
	/**
	 * Provides {@link SupplierFactory} for a specific type.
	 * @author Pavel
	 *
	 */
	interface Provider {
		<T> SupplierFactory<T> getFactory(Class<T> type);
	}
		
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
			public Function<V, BiSupplier<V, T>> create(Context context) throws Exception {				
				return SupplierFactory.this.create(context).asFunction();
			}
		};
	}
	
	static <T> SupplierFactory<T> from(BiFunction<Context, ProgressMonitor, T> biFunction, String name, double size) {
		return new SupplierFactory<T>() {

			@Override
			public Supplier<T> create(Context context) throws Exception {
				return Supplier.fromFunction(pm -> biFunction.apply(context, pm), name, size);
			}
			
		};
	}
	
	static <T> SupplierFactory<T> from(T value, String name) {
		return from((c,p) -> value, name, 0);		
	}	
	
	/**
	 * If source is adaptable to SupplierFactory then adapter is returned. Otherwise it is wrapped into a supplier factory
	 * @param <T>
	 * @param value
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static <T> SupplierFactory<T> adapt(Object source, String name) {
		SupplierFactory<?> ret = Adaptable.adaptTo(source, SupplierFactory.class);
		if (ret == null) {
			ret = from((c,p) -> source, name, 0);
		}
		return (SupplierFactory<T>) ret;
	}	
	
	/**
	 * @param contextSupplierFactory Factory which creates a context to be passed to this factory to create supplier.
	 * @return Supplier factory which creates a context using the context supplier factory and then uses that context and this factory to create a supplier.
	 */
	default SupplierFactory<T> contextify(SupplierFactory<Context> contextSupplierFactory) {
		return new SupplierFactory<T>() {

			@Override
			public Supplier<T> create(Context context) throws Exception {
				return new ContextifiedExecutionParticipant.ContextifiedSupplier<T>(contextSupplierFactory.create(context), SupplierFactory.this);
			}
			
		};
	}	
	
}
