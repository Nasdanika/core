package org.nasdanika.eclipse.resources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemResource;
import org.nasdanika.eclipse.ProgressMonitorWrapper;

/**
 * Wrapper for a file system directory.
 * @author Pavel
 *
 */
public class EclipseContainer extends EclipseResource<IContainer> implements BinaryEntityContainer {

	public EclipseContainer(IContainer container) {
		super(container);
	}

	protected EclipseResource<?> getChild(String name, ProgressMonitor monitor) {
		return (EclipseResource<?>) getChildren(monitor).get(name);
	}

	@Override
	public EclipseResource<?> find(String path, ProgressMonitor monitor) {
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return getChild(path, monitor); 
		}
		String firstSegment = path.substring(0, sPos);
		EclipseResource<?> child = getChild(firstSegment, monitor.split("Getting child at "+firstSegment, 1, this));
		if (child instanceof EclipseContainer) {
			String pathTail = path.substring(sPos+1);
			return ((EclipseContainer) child).find(pathTail, monitor.split("Finding "+pathTail, 1, child));
		}
		return null;
	}

	@Override
	public EclipseEntity get(String path, ProgressMonitor monitor) {
		EclipseResource<?> existing = find(path, monitor.split("Finding "+path, 1, this));
		if (existing instanceof EclipseEntity) {
			return (EclipseEntity) existing;
		}
		if (existing instanceof EclipseContainer) {
			// container - can't have an entity with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return new EclipseEntity(resource.getFile(new Path(path)));
		}
		
		String firstSegment = path.substring(0, sPos);
		EclipseContainer container = getContainer(firstSegment, monitor.split("Getting container "+firstSegment, 1, this));
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.get(pathTail, monitor.split("Getting entity at "+pathTail, 1, container));
	}

	@Override
	public EclipseContainer getContainer(String path, ProgressMonitor monitor) {
		EclipseResource<?> existing = find(path, monitor.split("Finding existing container", 1, this));
		if (existing instanceof EclipseContainer) {
			return (EclipseContainer) existing;
		}
		if (existing instanceof EclipseEntity) {
			// entity - can't have a container with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return new EclipseContainer(resource.getFolder(new Path(path)));
		}
		
		String pathHead = path.substring(0, sPos);
		EclipseContainer container = getContainer(pathHead, monitor.split("Getting child container "+pathHead, 1, this));
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.getContainer(pathTail, monitor.split("Getting child container "+pathHead, 1, container));
	}

	/**
	 * Returned values are instances of {@link FileSystemResource}, so the return value can be cast to Map&lt;String, FileSystemResource&gt;
	 */
	@Override
	public Map<String, Object> getChildren(ProgressMonitor monitor) {
		try {
			Map<String, EclipseResource<?>> ret = new HashMap<>();
			if (resource.exists()) {
				IResource[] children = resource.members();
				if (children != null) {
					for (IResource child: children) {
						if (child instanceof IFile) {
							ret.put(child.getName(), new EclipseEntity((IFile) child));
						} else if (child instanceof IContainer) {
							ret.put(child.getName(), new EclipseContainer((IContainer) child));
						}
					}
				}
			}
			return Collections.unmodifiableMap(ret);
		} catch (CoreException e) {
			throw new NasdanikaException(e);
		}
	}

	@Override
	public void copy(Container<? super BinaryEntity> container, String path, ProgressMonitor monitor) {
		throw new UnsupportedOperationException("Implement when needed");		
	}

	@Override
	public void move(Container<? super BinaryEntity> container, String path, ProgressMonitor monitor) {
		throw new UnsupportedOperationException("Implement when needed");		
	}

	@Override
	public void put(String path, BinaryEntity element, ProgressMonitor monitor) throws IllegalArgumentException {
		throw new UnsupportedOperationException("Use get() and getContainer() to operate with file system resources");		
	}

	@Override
	public void delete(ProgressMonitor monitor) {
		monitor.setWorkRemaining(4);
		for (Object child: getChildren(monitor.split("Getting children", 1, this)).values()) {
			((EclipseResource<?>) child).delete(monitor.split("Deleting child", 1, child));
			monitor.setWorkRemaining(2);
		}
		super.delete(monitor.split("Deleting container", 2));
	}

	@Override
	public Object delete(String path, ProgressMonitor monitor) {
		monitor.setWorkRemaining(2);
		EclipseResource<?> toDelete = get(path, monitor.split("Getting resource to delete: "+path, 1, this));
		if (toDelete != null) {
			toDelete.delete(monitor.split("Deleting "+path, 1, toDelete));
		}
		return toDelete;
	}

	/**
	 * Creates container if it does not exist.
	 */
	void create(ProgressMonitor monitor) {
		if (!resource.exists()) {
			if (resource instanceof IFolder) {
				monitor.setWorkRemaining(2);
				EclipseContainer parent = (EclipseContainer) getParent();
				if (parent != null) {
					parent.create(monitor.split("Creating parent "+parent.getName(), 1));
				}
				try {
					((IFolder) resource).create(false, true, ProgressMonitorWrapper.wrap(monitor.split("Creating container "+resource.getName(), 1)));
				} catch (CoreException e) {
					throw new NasdanikaException(e);
				}
			} else {
				throw new NasdanikaException("Cannot create container for the underlying IContainer "+resource);
			}
		}		
	}

}
