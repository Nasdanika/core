package org.nasdanika.resources;

import java.io.InputStream;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;

/**
 * Delegates all calls to the target. Wraps return types of getContainer() and getParent() into {@link BinaryContainerImpl}.
 * @author Pavel
 *
 */
public class BinaryContainerImpl implements BinaryContainer {

	private Container<InputStream> target;

	public BinaryContainerImpl(Container<InputStream> target) {
		this.target = target;
	}

	public Object find(String path, ProgressMonitor monitor) {
		return target.find(path, monitor);
	}

	public InputStream get(String path, ProgressMonitor monitor) {
		return target.get(path, monitor);
	}

	public void put(String path, InputStream element, ProgressMonitor monitor) throws IllegalArgumentException {
		target.put(path, element, monitor);
	}

	public void copy(Container<? super InputStream> container, String path, ProgressMonitor monitor) {
		target.copy(container, path, monitor);
	}

	public Object delete(String path, ProgressMonitor monitor) {
		return target.delete(path, monitor);
	}

	public void move(Container<? super InputStream> container, String path, ProgressMonitor monitor) {
		target.move(container, path, monitor);
	}

	public BinaryContainer getContainer(String path, ProgressMonitor monitor) {
		Container<InputStream> c = target.getContainer(path, monitor);
		return c == null ? null : new BinaryContainerImpl(c);
	}

	public Map<String, Object> getChildren(ProgressMonitor monitor) {
		return target.getChildren(monitor);
	}

	public BinaryContainer getParent() {
		Container<InputStream> parent = target.getParent();
		return parent == null ? null : new BinaryContainerImpl(parent);
	}

	public String getName() {
		return target.getName();
	}

}
