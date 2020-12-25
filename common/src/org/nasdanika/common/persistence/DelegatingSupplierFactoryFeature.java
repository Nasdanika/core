package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;

public class DelegatingSupplierFactoryFeature<T> implements SupplierFactoryFeature<T> {

	private Feature<T> delegate;

	public DelegatingSupplierFactoryFeature(Feature<T> delegate) {
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

	@SuppressWarnings("unchecked")
	@Override
	public SupplierFactory<T> getValue() {
		return (SupplierFactory<T>) SupplierFactory.adapt(delegate.getValue(), "Feature " + getKey());
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
