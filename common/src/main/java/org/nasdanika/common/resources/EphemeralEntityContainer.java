package org.nasdanika.common.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * {@link MapContainer} of {@link EphemeralEntity}ies.
 * @author Pavel
 *
 * @param <T>
 */
public class EphemeralEntityContainer<T> extends MapContainer<TypedEntity<T>> implements TypedEntityContainer<T> {

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
			public TypedEntityContainer<T> getParent() {
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
	
	@Override
	protected MapContainer<TypedEntity<T>> createSubContainer(String path) {
		return new EphemeralEntityContainer<T>() {

			@Override
			public String getName() {
				return path;
			}

			@Override
			public TypedEntityContainer<T> getParent() {
				return EphemeralEntityContainer.this;
			}
			
		};
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
	
	@Override
	public TypedEntityContainer<T> getContainer(String path, ProgressMonitor monitor) {
		return (TypedEntityContainer<T>) super.getContainer(path, monitor);
	}
	
	@Override
	public TypedEntityContainer<T> getParent() {
		return (TypedEntityContainer<T>) super.getParent();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TypedResource<T> find(String path, ProgressMonitor monitor) {
		return (TypedResource<T>) super.find(path, monitor);
	}
	
}
