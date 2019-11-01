package org.nasdanika.common;

/**
 * Function creates a work factory which in turn creates work. The work may be applied to the argument as well as
 * return result. This interface is similar to {@link java.util.function.Function}, but allows to perform execution in
 * a {@link Context} with progress reporting and ability to cancel via {@link ProgressMonitor}.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Function<T,R> {
	
	WorkFactory<R> createWork(T arg) throws Exception;
	
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
	
}
