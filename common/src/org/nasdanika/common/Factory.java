package org.nasdanika.common;

/**
 * Creates instance for a given context.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Factory<T> {
		
	/**
	 * @param context
	 * @return
	 * @throws Exception
	 */
	T create(Context context) throws Exception;
	
}
