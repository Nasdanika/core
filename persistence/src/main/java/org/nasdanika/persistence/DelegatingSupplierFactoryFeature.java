package org.nasdanika.persistence;

import org.nasdanika.common.SupplierFactory;

public class DelegatingSupplierFactoryFeature<T> extends AbstractFeatureDelegate<Feature<?>> implements SupplierFactoryFeature<T> {

	public DelegatingSupplierFactoryFeature(Feature<?> delegate) {
		super(delegate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SupplierFactory<T> getValue() {
		return (SupplierFactory<T>) SupplierFactory.adapt(delegate.getValue(), "Feature " + getKey());
	}

}
