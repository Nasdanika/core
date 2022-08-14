package org.nasdanika.persistence;

import java.util.Map;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class InterpolatedMapSupplierFactoryAttribute extends AbstractFeatureDelegate<SupplierFactoryFeature<Map<?,?>>> implements SupplierFactoryFeature<Map<?,?>> {

	public InterpolatedMapSupplierFactoryAttribute(SupplierFactoryFeature<Map<?,?>> delegate) {
		super(delegate);
	}

	@Override
	public SupplierFactory<Map<?,?>> getValue() {
		return delegate.getValue().then(Util.INTERPOLATE_MAP);
	}

}
