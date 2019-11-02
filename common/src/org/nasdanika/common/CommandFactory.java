package org.nasdanika.common;

/**
 * Creates command to be executed.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface CommandFactory<T> extends Factory<Context,Command<T>> {
		
	
}
