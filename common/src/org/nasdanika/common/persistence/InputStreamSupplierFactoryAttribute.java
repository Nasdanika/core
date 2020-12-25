package org.nasdanika.common.persistence;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class InputStreamSupplierFactoryAttribute implements SupplierFactoryFeature<InputStream> {

	private Feature<?> delegate;

	public InputStreamSupplierFactoryAttribute(Feature<?> delegate) {
		this.delegate = delegate;
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
	public SupplierFactory<InputStream> getValue() {
		return Util.asSupplierFactory(delegate.getValue());
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
