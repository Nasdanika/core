package org.nasdanika.common.resources;

import java.util.Collection;

import org.nasdanika.common.ProgressMonitor;

public class ContainerFilter<T> implements Container<T> {

	private Container<T> target;

	public ContainerFilter(Container<T> target) {
		this.target = target;
	}

	public Resource<T> find(String path) {
		return target.find(path);
	}

	public String getName() {
		return target.getName();
	}

	public Entity<T> getFile(String path) {
		return target.getFile(path);
	}

	public boolean exists() {
		return target.exists();
	}

	public Container<T> getParent() {
		return target.getParent();
	}

	public Container<T> getContainer(String path) {
		return target.getContainer(path);
	}

	public void delete(ProgressMonitor monitor) {
		target.delete(monitor);
	}

	public Collection<Resource<T>> getChildren() {
		return target.getChildren();
	}
	
	

}
