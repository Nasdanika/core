package org.nasdanika.common;

/**
 * Implementations of this interface are invoked by the computing context to 
 * obtain property values to return to the client.
 * @author Pavel
 *
 */
public interface PropertyComputer {
	
	/**
	 * Computes property value.
	 * @param <T>
	 * @param context
	 * @param key
	 * @param type
	 * @return
	 */
	<T> T compute(Context context, String key, Class<T> type);

}
