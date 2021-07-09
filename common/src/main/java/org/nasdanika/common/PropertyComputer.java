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
	 * @param key Property computer registration key.
	 * @param path Key path after the registration key or null if there is no path.
	 * @param type
	 * @return
	 */
	<T> T compute(Context context, String key, String path, Class<T> type);

}
