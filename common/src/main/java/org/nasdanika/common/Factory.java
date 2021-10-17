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
	 * @throws Exception
	 */
	R create(T arg) throws Exception;
	
}
