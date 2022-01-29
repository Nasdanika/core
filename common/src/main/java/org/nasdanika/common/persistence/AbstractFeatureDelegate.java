package org.nasdanika.common.persistence;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Base for classes delegating to a feature.
 * @author Pavel
 *
 * @param <T>
 */
public class AbstractFeatureDelegate<F extends Feature<?>> {

	protected F delegate;

	protected AbstractFeatureDelegate(F delegate) {
		this.delegate = delegate;
	}

	public Marker getMarker() {
		return delegate.getMarker();
	}

	public Object getKey() {
		return delegate.getKey();
	}

	public boolean isRequired() {
		return delegate.isRequired();
	}

	public boolean isLoaded() {
		return delegate.isLoaded();
	}

	public void load(ObjectLoader loader, Map<?, ?> source, URI base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		delegate.load(loader, source, base, progressMonitor, marker);		
	}
	
	public boolean isDefault() {
		return delegate.isDefault();
	}
	
	public String getDescription() {
		return delegate.getDescription();
	}

}
