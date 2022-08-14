package org.nasdanika.persistence;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class InterpolatedObjectSupplierFactoryAttribute extends AbstractFeatureDelegate<SupplierFactoryFeature<Object>> implements SupplierFactoryFeature<Object> {

	public InterpolatedObjectSupplierFactoryAttribute(SupplierFactoryFeature<Object> delegate) {
		super(delegate);
	}

	@Override
	public SupplierFactory<Object> getValue() {
		return delegate.getValue().then(Util.INTERPOLATE);
	}

}
