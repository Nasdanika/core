package org.nasdanika.eclipse.resources;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.eclipse.ProgressMonitorWrapper;

/**
 * Wrapper for a file system file.
 * @author Pavel
 *
 */
public class EclipseEntity extends EclipseResource<IFile> implements BinaryEntity {

	public EclipseEntity(IFile file) {
		super(file);
	}

	@Override
	public InputStream getState(ProgressMonitor monitor) {
		if (!canRead()) {
			throw new UnsupportedOperationException("Cannot read: "+resource.getFullPath());			
		}
		try {
			return resource.getContents();
		} catch (CoreException e) {
			throw new NasdanikaException(e);
		}
	}

	@Override
	public void setState(InputStream contents, ProgressMonitor monitor) {
		try {
			if (resource.exists()) {
				resource.setContents(contents, false, true, ProgressMonitorWrapper.wrap(monitor));
			} else {
				monitor.setWorkRemaining(2);
				EclipseContainer parent = (EclipseContainer) getParent();
				if (parent != null) {
					parent.create(monitor.split("Creating parent container "+parent.getName(), 1));
				}
				resource.create(contents, false, ProgressMonitorWrapper.wrap(monitor.split("Creating file "+resource.getName(), 1)));
			}
		} catch (CoreException e) {
			throw new NasdanikaException(e);
		}
	}

	@Override
	public void appendState(InputStream contents, ProgressMonitor monitor) {
		try {
			resource.appendContents(contents, false, true, ProgressMonitorWrapper.wrap(monitor));
		} catch (CoreException e) {
			throw new NasdanikaException(e);
		}
	}

	@Override
	public boolean canRead() {
		return resource.isAccessible();
	}

	@Override
	public boolean canWrite() {
		return !resource.isReadOnly();
	}

	@Override
	public long size(ProgressMonitor monitor) {
		return 1; // Unknown
	}
	
}
