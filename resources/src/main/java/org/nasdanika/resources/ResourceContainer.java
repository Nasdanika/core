package org.nasdanika.resources;

import java.util.Map;

import org.nasdanika.common.ProgressMonitor;

/**
 * Container of resources.
 * @author Pavel
 *
 * @param <T> Content type.
 */
public interface ResourceContainer<E extends Resource<?>> extends Container<E> {
		
	@SuppressWarnings("unchecked")
	@Override
	default void copy(Container<? super E> container, String path, ProgressMonitor monitor) {
		if (exists(monitor.split("Checking existence", 1, this))) {			
			long size = size(monitor.split("Getting size", 1, this));
			try (ProgressMonitor subMonitor = monitor.split("Copying "+getName(), size, this)) {
				Container<? super E> target = container.getContainer(path, monitor.split("Getting target container "+path, 1, container));
				Map<String, Object> children = getChildren(monitor.split("Getting children", 1, this));
				if (children != null) {				
					children.forEach((childName, child) -> ((Resource<E>) child).copy(target, childName, subMonitor.split("Copying child "+path, 1, this)));
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	default void move(Container<? super E> container, String path, ProgressMonitor monitor) {
		if (exists(monitor.split("Checking existence", 1, this))) {			
			long size = size(monitor.split("Getting size", 1, this));
			try (ProgressMonitor subMonitor = monitor.split("Moving "+getName(), size*2 + 1, this)) {
				Container<? super E> target = container.getContainer(path, monitor.split("Getting target container "+path, 1, container));
				Map<String, Object> children = getChildren(monitor.split("Getting children", 1, this));
				if (children != null) {				
					children.forEach((childName, child) -> ((Resource<E>) child).move(target, childName, subMonitor.split("Copying child "+path, 1, this)));
				}
				delete(subMonitor.split("Deleting", 1, this));
			}
		}				
	}
	
}
