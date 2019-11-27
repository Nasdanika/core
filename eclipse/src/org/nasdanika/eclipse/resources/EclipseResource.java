package org.nasdanika.eclipse.resources;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.resources.BinaryResource;
import org.nasdanika.eclipse.ProgressMonitorWrapper;

/**
 * Wrapper for {@link java.io.File}.
 * @author Pavel
 *
 */
public abstract class EclipseResource<T extends IResource> implements BinaryResource {

	protected T resource;

	public EclipseResource(T resource) {
		this.resource = resource;
	}

	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public boolean exists(ProgressMonitor monitor) {
		return resource.exists();
	}

	@Override
	public EclipseContainer getParent() {
		IContainer parent = resource.getParent();
		return parent == null ? null : new EclipseContainer(parent);
	}

	@Override
	public void delete(ProgressMonitor monitor) {
		try (ProgressMonitor deleteMonitor = monitor.split("Deleting "+getName(), 1, resource)) {
			resource.delete(false, ProgressMonitorWrapper.wrap(deleteMonitor));
		} catch (CoreException e) {
			throw new NasdanikaException("Cannot delete "+resource.getFullPath()+": "+e, e);
		}
	}
		
	@Override
	public long getTimestamp() {
		return resource.getModificationStamp();
	}	

	@Override
	public String toString() {
		return getClass().getName()+"("+getPath()+")";
	}
	
}
