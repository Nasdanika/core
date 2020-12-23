package org.nasdanika.common;

/**
 * Binds {@link Factory} first type parameter to {@link Context}
 * @author Pavel
 *
 * @param <T>
 */
public interface ContextualFactory<T> extends Factory<Context,T> {

}
