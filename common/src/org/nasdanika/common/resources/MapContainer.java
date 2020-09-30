package org.nasdanika.common.resources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;

/**
 * Container backed by a map. For this container delete() means removal from the map.
 * @author Pavel
 *
 * @param <T>
 */
public class MapContainer<E> implements Container<E> {
			
	protected Map<String,Object> children = new HashMap<>();

	/**
	 * Create a new root memory container.
	 */
	public MapContainer() {
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object find(String path, ProgressMonitor monitor) {
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return children.get(path); 
		}
		String firstSegment = path.substring(0, sPos);
		Object child = children.get(firstSegment);
		if (child instanceof Container) {
			return ((Container<E>) child).find(path.substring(sPos+1), monitor.split("Finding in "+firstSegment, 1, child));
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(String path, ProgressMonitor monitor) {
		Object existing = find(path, monitor.split("Finding existing child at " + path, 1));
		if (existing instanceof Container) {
			// container - can't have another child with the same name.
			return null;
		}
		if (existing != null) {
			return (E) existing;
		}
		
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			E ret = createElement(path, monitor.split("Creating element on access at "+path, 1, this));
			children.put(path, ret);
			return ret;
		}
		
		String pathHead = path.substring(0, sPos);
		Container<E> container = getContainer(pathHead, monitor.split("Getting sub-container at "+pathHead, 1, this));
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.get(pathTail, monitor.split("Getting element at "+pathTail , 1, container));
	}
	
	/**
	 * Override to create elements on access. This method delegates to the parent if the parent is an instance of MapContainer.
	 * @param path
	 * @param monitor
	 * @return
	 */
	protected E createElement(String path, ProgressMonitor monitor) {
		Container<E> parent = getParent();
		if (parent instanceof MapContainer) {
			return ((MapContainer<E>) parent).createElement(path, monitor);
		}
		return null;
	}

	@Override
	public void put(String path, E element, ProgressMonitor monitor) throws IllegalArgumentException {
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			children.put(path, element);
		} else {		
			String firstSegment = path.substring(0, sPos);
			Container<E> container = getContainer(firstSegment, monitor.split("Getting container at "+firstSegment, 1, this));
			if (container == null) {
				throw new IllegalArgumentException("Unable to put element at "+path+" - no container at firstSe");
			}
			String pathTail = path.substring(sPos + 1);
			container.put(pathTail, element, monitor.split("Putting element at "+pathTail, 1, container));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object delete(String path, ProgressMonitor monitor) {
		Object existing = find(path, monitor.split("Finding existing element at "+path, 1));
		if (existing == null) {
			return null;
		}
		
		int sPos = path.indexOf(SEPARATOR);
		
		if (sPos == -1) {
			return children.remove(path);
		}
		
		if (existing instanceof Container) {
			((Container<E>) existing).delete(monitor.split("Removing container at "+path, 1, existing));
			return existing;
		}
		
		String firstSegment = path.substring(0, sPos);
		Container<E> container = getContainer(firstSegment, monitor.split("Getting container at "+firstSegment, 1, this));
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.delete(pathTail, monitor.split("Getting container for "+pathTail, 1, container));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Container<E> getContainer(String path, ProgressMonitor monitor) {
		Object existing = find(path, monitor.split("Finding existing element at "+path, 1));
		if (existing instanceof Container) {
			return (Container<E>) existing;
		}
		if (existing != null) {
			// non-container - can't have another resource with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			Container<E> ret = createSubContainer(path);
			children.put(path, ret);
			return ret;
		}
		
		String firstSegment = path.substring(0, sPos);
		Container<E> container = getContainer(firstSegment, monitor.split("Getting container at "+firstSegment, 1, this));
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.getContainer(pathTail, monitor.split("Getting container for "+pathTail, 1, container));
	}

	/**
	 * Creates a sub-container, override to specialize returned container.
	 * @param path
	 * @return
	 */
	protected MapContainer<E> createSubContainer(String path) {
		return new MapContainer<E>() {

			@Override
			public String getName() {
				return path;
			}

			@Override
			public Container<E> getParent() {
				return MapContainer.this;
			}
			
		};
	}

	@Override
	public Map<String, Object> getChildren(ProgressMonitor monitor) {
		return Collections.unmodifiableMap(children);
	}

	@Override
	public Container<E> getParent() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String toString() {
		return getClass().getName()+"("+getPath()+")";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void copy(Container<? super E> container, String path, ProgressMonitor monitor) {
		if (exists(monitor.split("Checking existence", 1, this))) {			
			long size = size(monitor.split("Getting size", 1, this));
			try (ProgressMonitor subMonitor = monitor.split("Copying "+getName(), size, this)) {
				Container<? super E> target = container.getContainer(path, monitor.split("Getting target container "+path, 1, container));
				Map<String, Object> children = getChildren(monitor.split("Getting children", 1, this));
				if (children != null) {				
					children.forEach((childName, child) -> {
						if (child instanceof Resource) {
							((Resource<E>) child).copy(target, childName, subMonitor.split("Copying child "+path, 1, this));
						} else {
							put(childName, (E) child, subMonitor.split("Copying child "+path, 1, this));
						}
					});
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void move(Container<? super E> container, String path, ProgressMonitor monitor) {
		if (exists(monitor.split("Checking existence", 1, this))) {			
			long size = size(monitor.split("Getting size", 1, this));
			try (ProgressMonitor subMonitor = monitor.split("Moving "+getName(), size*2 + 1, this)) {
				Container<? super E> target = container.getContainer(path, monitor.split("Getting target container "+path, 1, container));
				Map<String, Object> children = getChildren(monitor.split("Getting children", 1, this));
				if (children != null) {				
					children.forEach((childName, child) -> {
						if (child instanceof Resource) {
							((Resource<E>) child).copy(target, childName, subMonitor.split("Copying child "+path, 1, this));
						} else {
							put(childName, (E) child, subMonitor.split("Copying child "+path, 1, this));
						}
					});
				}
				delete(subMonitor.split("Deleting", 1, this));
			}
		}				
	}

}
