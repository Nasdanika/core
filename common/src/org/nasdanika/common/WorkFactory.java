package org.nasdanika.common;

/**
 * Creates work to be executed.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface WorkFactory<T> extends CommandFactory<T> {
		
	/**
	 * Creates work for a given context. Narrows CommandFactory return type.
	 * @param context
	 * @return
	 * @throws Exception
	 */
	Work<T> create(Context context) throws Exception;
		
	/**
     * Returns a composed work factory that first executes this work factor work, 
     * and then executes the {@code then} function work with the result.
     *
     * @param <V> the type of work result of the {@code then} function, and of the composed function's work
	 * @param then
	 * @return
	 */
	default <V> WorkFactory<V> then(Function<T,V> after) {
		throw new UnsupportedOperationException();
	}
	
}
