package org.nasdanika.common.resources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;

/**
 * Wrapper for a file system directory.
 * @author Pavel
 *
 */
public class FileSystemContainer extends FileSystemResource implements BinaryEntityContainer<FileSystemEntity> {

	public FileSystemContainer(java.io.File file) {
		super(file);
	}

	protected FileSystemResource getChild(String name, ProgressMonitor monitor) {
		return (FileSystemResource) getChildren(monitor).get(name);
	}

	@Override
	public FileSystemResource find(String path, ProgressMonitor monitor) {
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return getChild(path, monitor); 
		}
		String firstSegment = path.substring(0, sPos);
		FileSystemResource child = getChild(firstSegment, monitor.split("Getting child at "+firstSegment, 1, this));
		if (child instanceof FileSystemContainer) {
			String pathTail = path.substring(sPos+1);
			return ((FileSystemContainer) child).find(pathTail, monitor.split("Finding "+pathTail, 1, child));
		}
		return null;
	}

	@Override
	public FileSystemEntity get(String path, ProgressMonitor monitor) {
		FileSystemResource existing = find(path, monitor.split("Finding "+path, 1, this));
		if (existing instanceof FileSystemEntity) {
			return (FileSystemEntity) existing;
		}
		if (existing instanceof FileSystemContainer) {
			// container - can't have an entity with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return new FileSystemEntity(new java.io.File(file, path));
		}
		
		String firstSegment = path.substring(0, sPos);
		FileSystemContainer container = getContainer(firstSegment, monitor.split("Getting container "+firstSegment, 1, this));
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.get(pathTail, monitor.split("Getting entity at "+pathTail, 1, container));
	}

	@Override
	public FileSystemContainer getContainer(String path, ProgressMonitor monitor) {
		FileSystemResource existing = find(path, monitor.split("Finding existing container", 1, this));
		if (existing instanceof FileSystemContainer) {
			return (FileSystemContainer) existing;
		}
		if (existing instanceof FileSystemEntity) {
			// entity - can't have a container with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return new FileSystemContainer(new java.io.File(file, path));
		}
		
		String pathHead = path.substring(0, sPos);
		FileSystemContainer container = getContainer(pathHead, monitor.split("Getting child container "+pathHead, 1, this));
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.getContainer(pathTail, monitor.split("Getting child container "+pathHead, 1, container));
	}

	/**
	 * Returned values are instances of {@link FileSystemResource}, so the return value can be cast to Map&lt;String, FileSystemResource&gt;
	 */
	@Override
	public Map<String, Object> getChildren(ProgressMonitor monitor) {
		Map<String, FileSystemResource> ret = new HashMap<>();		
		java.io.File[] children = file.listFiles();
		if (children != null) {
			for (java.io.File child: children) {
				if (child.isFile()) {
					ret.put(child.getName(), new FileSystemEntity(child));
				} else if (child.isDirectory()) {
					ret.put(child.getName(), new FileSystemContainer(child));
				}
			}
		}
		return Collections.unmodifiableMap(ret);
	}

	@Override
	public void copy(Container<? super FileSystemEntity> container, String path, ProgressMonitor monitor) {
		throw new UnsupportedOperationException("Implement when needed");		
	}

	@Override
	public void move(Container<? super FileSystemEntity> container, String path, ProgressMonitor monitor) {
		throw new UnsupportedOperationException("Implement when needed");		
	}

	@Override
	public void put(String path, FileSystemEntity element, ProgressMonitor monitor) throws IllegalArgumentException {
		throw new UnsupportedOperationException("Use get() and getContainer() to operate with file system resources");		
	}

	@Override
	public void delete(ProgressMonitor monitor) {
		for (Object child: getChildren(monitor.split("Getting children", 1, this)).values()) {
			((FileSystemResource) child).delete(monitor.split("Deleting child", 1, child));
		}
		super.delete(monitor);
	}

	@Override
	public Object delete(String path, ProgressMonitor monitor) {
		FileSystemResource toDelete = get(path, monitor.split("Getting resource to delete: "+path, 1, this));
		if (toDelete != null) {
			toDelete.delete(monitor.split("Deleting "+path, 1, toDelete));
		}
		return toDelete;
	}

}
