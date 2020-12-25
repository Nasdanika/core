package org.nasdanika.common.persistence;

import org.nasdanika.common.SupplierFactory;

public class DelegatingSupplierFactoryFeature<T> extends AbstractFeatureDelegate<Feature<T>> implements SupplierFactoryFeature<T> {

	public DelegatingSupplierFactoryFeature(Feature<T> delegate) {
		super(delegate);
	}

	@SuppressWarnings("unchecked")
	@Override
	public SupplierFactory<T> getValue() {
		return (SupplierFactory<T>) SupplierFactory.adapt(delegate.getValue(), "Feature " + getKey());
	}

}
