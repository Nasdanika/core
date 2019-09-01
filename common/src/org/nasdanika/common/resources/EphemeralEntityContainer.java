package org.nasdanika.common.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * {@link MapContainer} of {@link EphemeralEntity}ies.
 * @author Pavel
 *
 * @param <T>
 */
public class EphemeralEntityContainer<T> extends MapContainer<EphemeralEntity<T>> implements EntityContainer<T,EphemeralEntity<T>> {

	@Override
	protected EphemeralEntity<T> createElement(String path, ProgressMonitor monitor) {
		return new EphemeralEntity<T>() {

			@Override
			public void appendState(T state, ProgressMonitor monitor) {
				T existingState = getState(monitor.split("Getting existing state", 1, this));
				EphemeralEntityContainer.this.appendState(existingState, state, monitor.split("Appending state", 1, this, existingState, state));				
			}

			@Override
			public String getName() {
				return path;
			}

			@Override
			public Container<EphemeralEntity<T>> getParent() {
				return EphemeralEntityContainer.this;
			}

			@Override
			public long size(ProgressMonitor monitor) {
				return EphemeralEntityContainer.this.stateSize(getState(monitor));
			}
			
		};
	}
	
	protected long stateSize(T state) {
		return 0;
	}

	/**
	 * Override to add support of appending state.
	 * @param existingState
	 * @param newState
	 * @param monitor
	 */
	protected void appendState(T existingState, T newState, ProgressMonitor monitor) {
		throw new UnsupportedOperationException();
	}
	
}
