package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;

public class SupplierFactoryMapAttribute<K,V> implements SupplierFactoryFeature<Map<K,V>> {

	private Feature<Map<K,V>> delegate;

	public SupplierFactoryMapAttribute(Feature<Map<K,V>> delegate) {
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
	public SupplierFactory<Map<K,V>> getValue() {
		MapCompoundSupplierFactory<K,V> ret = new MapCompoundSupplierFactory<>("Feature " + getKey());
		for (Entry<K, V> e: delegate.getValue().entrySet()) {
			ret.put(e.getKey(), SupplierFactory.<V>adapt(e.getValue(), "Feature " + getKey() + "[" + e.getKey() + "]"));
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
