package org.nasdanika.common.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * Entity with the state stored in memory.
 * @author Pavel
 *
 * @param <T>
 */
public abstract class MemoryEntity<T> implements Entity<T> {

	protected T contents;

	@Override
	public T getState(ProgressMonitor monitor) {
		return contents;
	}

	@Override
	public void setState(T contents, ProgressMonitor monitor) {
		this.contents = contents;
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
	public boolean exists() {
		return contents != null;
	}
	
	@Override
	public void delete(ProgressMonitor monitor) {
		setState(null, monitor);	
	}	

}
