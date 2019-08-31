package org.nasdanika.common.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * {@link MapContainer} of {@link EphemeralEntity}ies.
 * @author Pavel
 *
 * @param <T>
 */
public class EphemeralContainer<T, E extends EphemeralEntity<T,E>> extends MapContainer<E> {

//	@Override
//	protected E createElement(String path, ProgressMonitor monitor) {
//		return new EphemeralEntity<T,E>() {
//
//			@Override
//			public void appendState(T state, ProgressMonitor monitor) {
//				T existingState = getState(monitor.split("Getting existing state", 1, this));
//				EphemeralContainer.this.appendState(existingState, state, monitor.split("Appending state", 1, this, existingState, state));				
//			}
//
//			@Override
//			public String getName() {
//				return path;
//			}
//
//			@Override
//			public Container<EphemeralEntity<T>> getParent() {
//				return EphemeralContainer.this;
//			}
//
//			@Override
//			public long size(ProgressMonitor monitor) {
//				return EphemeralContainer.this.stateSize(getState(monitor));
//			}
//			
//		};
//	}
	
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
