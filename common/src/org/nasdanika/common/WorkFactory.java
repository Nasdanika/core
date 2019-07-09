package org.nasdanika.common;

/**
 * Creates work to be executed.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface WorkFactory<T> {
		
	/**
	 * Creates work for a given context.
	 * @param context
	 * @return
	 * @throws Exception
	 */
	Work<T> createWork(Context context) throws Exception;
	
}
