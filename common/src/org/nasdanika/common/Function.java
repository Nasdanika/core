package org.nasdanika.common;

/**
 * Function creates a work factory which in turn creates work. The work may be applied to the work result of the argument {@link WorkFactory} as well as
 * return result. This interface is similar to {@link java.util.function.Function}, but allows to perform execution in
 * a {@link Context} with progress reporting and ability to cancel via {@link ProgressMonitor}.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Function<T,R> extends Factory<WorkFactory<T>,WorkFactory<R>> {
	
	/**
     * Returns a composed function that first executes this function's work with its input, 
     * and then executes the {@code then} function work with the result.
     *
     * @param <V> the type of work result of the {@code then} function, and of the composed function's work
	 * @param then
	 * @return
	 */
	default <V> Function<T,V> then(Function<R,V> then) {
		throw new UnsupportedOperationException();
	}
	
	default Consumer<T> then(Consumer<R> then) {
		throw new UnsupportedOperationException();		
	}
	
	static <T,R> Function<T,R> from(java.util.function.Function<T, R> function) {
		return new Function<T, R>() {

			@Override
			public WorkFactory<R> create(WorkFactory<T> arg) throws Exception {				
				return new WorkFactory<R>() {

					@Override
					public Work<R> create(Context context) throws Exception {
						return arg.create(context).adapt(function);
					}
				};
			}
		};
	}
	
	
}
