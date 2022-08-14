package org.nasdanika.persistence;

import java.util.List;
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

	public List<? extends Marker> getMarkers() {
		return delegate.getMarkers();
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

	public void load(ObjectLoader loader, Map<?, ?> source, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		delegate.load(loader, source, base, progressMonitor, markers);		
	}
	
	public boolean isDefault() {
		return delegate.isDefault();
	}
	
	public String getDescription() {
		return delegate.getDescription();
	}

}
