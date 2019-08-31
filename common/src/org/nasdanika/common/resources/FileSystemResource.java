package org.nasdanika.common.resources;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

/**
 * Wrapper for {@link java.io.File}.
 * @author Pavel
 *
 */
public abstract class FileSystemResource implements Resource<FileSystemEntity> {

	protected java.io.File file;

	public FileSystemResource(java.io.File file) {
		this.file = file;
	}

	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public boolean exists(ProgressMonitor monitor) {
		return file.exists();
	}

	@Override
	public FileSystemContainer getParent() {
		java.io.File parent = file.getParentFile();
		return parent == null ? null : new FileSystemContainer(parent);
	}

	@Override
	public void delete(ProgressMonitor monitor) {
		try (ProgressMonitor deleteMonitor = monitor.split("Deleting "+getName(), 1, file)) {
			if (!file.delete()) {
				throw new NasdanikaException("Cannot delete "+file.getAbsolutePath());
			}
		}
	}
		
	@Override
	public long getTimestamp() {
		return file.lastModified();
	}	

	@Override
	public String toString() {
		return getClass().getName()+"("+getPath()+")";
	}
	
}
