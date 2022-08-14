package org.nasdanika.persistence;

import java.util.Collection;
import java.util.List;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class InterpolatedCollectionSupplierFactoryAttribute extends AbstractFeatureDelegate<SupplierFactoryFeature<Collection<?>>> implements SupplierFactoryFeature<List<?>> {

	public InterpolatedCollectionSupplierFactoryAttribute(SupplierFactoryFeature<Collection<?>> delegate) {
		super(delegate);
	}

	@Override
	public SupplierFactory<List<?>> getValue() {
		return delegate.getValue().then(Util.INTERPOLATE_COLLECTION);
	}

}
