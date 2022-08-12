package org.nasdanika.common;

/**
 * Creates context from input.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface ContextFactory<T> {
		
	/**
	 * Creates context from a given input.
	 * @param input
	 * @return
	 */
	Context createContext(T input);
	
}
