package org.nasdanika.capability;

import java.util.Collection;
import java.util.Collections;

import org.nasdanika.common.Component;
import org.nasdanika.common.ProgressMonitor;

/**
 * Capability provider with a lifecycle - start, publish, stop, close.
 * Chains the lifecycle with dependencies.
 * @param <T>
 */
public abstract class CapabilityProviderComponent<T> implements CapabilityProvider<T>, Component {
	
	protected Collection<Component> getDepedencies() {
		return Collections.emptySet();
	}

	@Override
	public void start(ProgressMonitor progressMonitor) {
		getDepedencies().forEach(d -> d.start(progressMonitor));
	}

	@Override
	public void stop(ProgressMonitor progressMonitor) {
		getDepedencies().forEach(d -> d.stop(progressMonitor));
	}

	@Override
	public void close(ProgressMonitor progressMonitor) {
		getDepedencies().forEach(d -> d.close(progressMonitor));
	}

}
