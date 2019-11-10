package org.nasdanika.common._legacy;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Factory;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

/**
 * FunctionFactory creates a work factory which in turn creates work. The work may be applied to the work result of the argument {@link SupplierFactory} as well as
 * return result. This interface is similar to {@link java.util.function.Function}, but allows to perform execution in
 * a {@link Context} with progress reporting and ability to cancel via {@link ProgressMonitor}.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface _LegacyFunctionFactory<T,R> extends Factory<SupplierFactory<T>,SupplierFactory<R>> {
	
	/**
     * Returns a composed function that first executes this function's work with its input, 
     * and then executes the {@code then} function work with the result.
     *
     * @param <V> the type of work result of the {@code then} function, and of the composed function's work
	 * @param then
	 * @return
	 */
	default <V> FunctionFactory<T,V> then(FunctionFactory<R,V> then) {
		throw new UnsupportedOperationException();
	}
	
	default ConsumerFactory<T> then(ConsumerFactory<R> then) {
		throw new UnsupportedOperationException();		
	}
	
	static <T,R> FunctionFactory<T,R> from(java.util.function.Function<T, R> function) {
		return new FunctionFactory<T, R>() {

			@Override
			public SupplierFactory<R> create(SupplierFactory<T> arg) throws Exception {				
				return new SupplierFactory<R>() {

					@Override
					public Supplier<R> create(Context context) throws Exception {
						return arg.create(context).adapt(function);
					}
				};
			}
		};
	}
	
	static <V> FunctionFactory<V,V> nop() {
		return new FunctionFactory<V, V>() {

			@Override
			public SupplierFactory<V> create(SupplierFactory<V> arg) throws Exception {
				return arg;
			}
			
		};
	};
	
	
}
