package org.nasdanika.persistence;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

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

	public Collection<? extends Marker> getMarkers() {
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
	
	public void load(
			ObjectLoader loader, 
			Map<?, ?> source, 
			URI base,
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers, 			
			ProgressMonitor progressMonitor) {
		delegate.load(loader, source, base, resolver, markers, progressMonitor);		
	}
	
	public boolean isDefault() {
		return delegate.isDefault();
	}
	
	public boolean isConstructor() {
		return delegate.isConstructor();
	}
	
	public String getDescription() {
		return delegate.getDescription();
	}

}
