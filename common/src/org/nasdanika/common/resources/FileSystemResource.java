package org.nasdanika.common.resources;

import java.io.InputStream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

/**
 * Wrapper for {@link java.io.File}.
 * @author Pavel
 *
 */
public abstract class FileSystemResource implements Resource<InputStream> {

	protected java.io.File file;

	public FileSystemResource(java.io.File file) {
		this.file = file;
	}

	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public boolean exists() {
		return file.exists();
	}

	@Override
	public Container<InputStream> getParent() {
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

}
