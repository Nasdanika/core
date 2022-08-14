package org.nasdanika.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * Entity with the state stored in memory.
 * @author Pavel
 *
 * @param <T>
 */
public abstract class EphemeralEntity<T> implements TypedEntity<T> {

	protected T state;

	@Override
	public T getState(ProgressMonitor monitor) {
		return state;
	}

	@Override
	public void setState(T contents, ProgressMonitor monitor) {
		this.state = contents;
	}

	@Override
	public boolean canRead() {
		return true;
	}

	@Override
	public boolean canWrite() {
		return true;
	}
	
	@Override
	public boolean exists(ProgressMonitor monitor) {
		return state != null;
	}
	
	@Override
	public void delete(ProgressMonitor monitor) {
		setState(null, monitor);	
	}	

	@Override
	public String toString() {
		return getClass().getName()+"("+getPath()+")";
	}

}
