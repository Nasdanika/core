package org.nasdanika.common.persistence;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class StringSupplierFactoryAttribute implements SupplierFactoryFeature<String> {

	private SupplierFactoryFeature<InputStream> delegate;
	private boolean interpolate;

	public StringSupplierFactoryAttribute(SupplierFactoryFeature<InputStream> delegate, boolean interpolate) {
		this.delegate = delegate;
		this.interpolate = interpolate;
	}

	public StringSupplierFactoryAttribute(Feature<?> delegate, boolean interpolate) {
		this(new InputStreamSupplierFactoryAttribute(delegate), interpolate);
	}

	@Override
	public Marker getMarker() {
		return delegate.getMarker();
	}

	@Override
	public Object getKey() {
		return delegate.getKey();
	}

	@Override
	public SupplierFactory<String> getValue() {
		SupplierFactory<String> ret = delegate.getValue().then(Util.TO_STRING);
		return interpolate ? ret.then(Util.INTERPOLATE_TO_STRING) : ret;
	}

	@Override
	public boolean isRequired() {
		return delegate.isRequired();
	}

	@Override
	public boolean isLoaded() {
		return delegate.isLoaded();
	}

	@Override
	public void load(ObjectLoader loader, Map<?, ?> source, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		delegate.load(loader, source, base, progressMonitor, marker);		
	}

}
