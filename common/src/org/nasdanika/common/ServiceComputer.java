package org.nasdanika.common;

/**
 * Implementations of this interface are invoked by the computing context to 
 * obtain service instances to return to the client.
 * @author Pavel
 *
 */
public interface ServiceComputer<T> {
	
	/**
	 * Computes property value.
	 * @param <T>
	 * @param context
	 * @param key
	 * @return
	 */
	T compute(Context context, Class<T> type);

}
