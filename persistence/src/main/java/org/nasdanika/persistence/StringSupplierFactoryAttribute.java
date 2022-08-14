package org.nasdanika.persistence;

import java.io.InputStream;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class StringSupplierFactoryAttribute extends AbstractFeatureDelegate<SupplierFactoryFeature<InputStream>> implements SupplierFactoryFeature<String> {

	private boolean interpolate;

	public StringSupplierFactoryAttribute(SupplierFactoryFeature<InputStream> delegate, boolean interpolate) {
		super(delegate);
		this.interpolate = interpolate;
	}

	public StringSupplierFactoryAttribute(Feature<?> delegate, boolean interpolate) {
		this(new InputStreamSupplierFactoryAttribute(delegate), interpolate);
	}

	@Override
	public SupplierFactory<String> getValue() {
		if (!delegate.isLoaded()) {
			return SupplierFactory.from(null, "Null string from not loaded stream " + getKey()); 
		}
		SupplierFactory<String> ret = delegate.getValue().then(Util.TO_STRING);
		return interpolate ? ret.then(Util.INTERPOLATE_TO_STRING) : ret;
	}

}
