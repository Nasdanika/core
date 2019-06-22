package org.nasdanika.common.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * File with the contents stored in memory.
 * @author Pavel
 *
 * @param <T>
 */
public abstract class MemoryFile<T> implements File<T> {

	protected T contents;

	@Override
	public T getContents(ProgressMonitor monitor) {
		return contents;
	}

	@Override
	public void setContents(T contents, ProgressMonitor monitor) {
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
		setContents(null, monitor);	
	}	

}
