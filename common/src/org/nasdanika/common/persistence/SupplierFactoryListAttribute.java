package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;

public class SupplierFactoryListAttribute<T> implements SupplierFactoryFeature<List<T>> {

	private Feature<List<T>> delegate;

	public SupplierFactoryListAttribute(Feature<List<T>> delegate) {
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
	public SupplierFactory<List<T>> getValue() {
		ListCompoundSupplierFactory<T> ret = new ListCompoundSupplierFactory<>("Feature " + getKey());
		int idx = 0;
		for (T e: delegate.getValue()) {
			ret.add(SupplierFactory.<T>adapt(e, "Feature " + getKey() + "[" + (idx++) + "]"));
		}
		
		return ret;
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
