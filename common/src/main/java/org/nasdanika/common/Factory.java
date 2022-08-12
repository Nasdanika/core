package org.nasdanika.common;

/**
 * Creates result from argument.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Factory<T,R> {
		
	/**
	 * @param context
	 * @return
	 */
	R create(T arg);
	
}
