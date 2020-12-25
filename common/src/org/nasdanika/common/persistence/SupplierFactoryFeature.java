package org.nasdanika.common.persistence;

import org.nasdanika.common.SupplierFactory;

/**
 * Binds {@link Feature} to {@link SupplierFactory} to indicate that the value supplier factory must be executed
 * in order to obtain the final feature value.
 * @author Pavel
 *
 * @param <T>
 */
public interface SupplierFactoryFeature<T> extends Feature<SupplierFactory<T>> {

}
