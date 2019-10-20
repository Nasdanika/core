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
	
}
