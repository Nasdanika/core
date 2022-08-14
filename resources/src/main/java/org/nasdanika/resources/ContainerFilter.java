package org.nasdanika.resources;

import java.util.Map;

import org.nasdanika.common.ProgressMonitor;

public class ContainerFilter<E> implements Container<E> {

	protected Container<E> target;
	
	public ContainerFilter(Container<E> target) {
		this.target = target;
	}

	public Object find(String path, ProgressMonitor monitor) {
		return target.find(path, monitor);
	}

	public E get(String path, ProgressMonitor monitor) {
		return target.get(path, monitor);
	}

	public void put(String path, E element, ProgressMonitor monitor) throws IllegalArgumentException {
		target.put(path, element, monitor);
	}

	public Object delete(String path, ProgressMonitor monitor) {
		return target.delete(path, monitor);
	}

	public Container<E> getContainer(String path, ProgressMonitor monitor) {
		return target.getContainer(path, monitor);
	}

	public Map<String, Object> getChildren(ProgressMonitor monitor) {
		return target.getChildren(monitor);
	}

	public Container<E> getParent() {
		return target.getParent();
	}

	public String getName() {
		return target.getName();
	}

	@Override
	public void copy(Container<? super E> container, String path, ProgressMonitor monitor) {
		target.copy(container, path, monitor);		
	}

	@Override
	public void move(Container<? super E> container, String path, ProgressMonitor monitor) {
		target.move(container, path, monitor);		
	}
	
}
