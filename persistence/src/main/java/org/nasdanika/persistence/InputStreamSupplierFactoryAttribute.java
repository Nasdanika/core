package org.nasdanika.persistence;

import java.io.InputStream;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class InputStreamSupplierFactoryAttribute extends AbstractFeatureDelegate<Feature<?>> implements SupplierFactoryFeature<InputStream> {

	public InputStreamSupplierFactoryAttribute(Feature<?> delegate) {
		super(delegate);
	}

	@Override
	public SupplierFactory<InputStream> getValue() {
		return Util.asInputStreamSupplierFactory(delegate.getValue());
	}

}
