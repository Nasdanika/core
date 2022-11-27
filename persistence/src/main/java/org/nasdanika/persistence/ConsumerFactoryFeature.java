package org.nasdanika.persistence;

import org.nasdanika.common.ConsumerFactory;

/**
 * Binds {@link Feature} to {@link ConsumerFactory} to indicate that the value consumer factory must be executed
 * in order to obtain a consumer (builder) to which the object being configured should be passed.
 * @author Pavel
 *
 * @param <T>
 */
public interface ConsumerFactoryFeature<T> extends Feature<ConsumerFactory<T>> {

}
