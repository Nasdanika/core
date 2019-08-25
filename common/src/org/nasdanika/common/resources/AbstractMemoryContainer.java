package org.nasdanika.common.resources;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

import org.nasdanika.common.ProgressMonitor;

/**
 * Base class for containers which keep contents in memory.
 * @author Pavel
 *
 * @param <T>
 */
public abstract class AbstractMemoryContainer<T> implements Container<T> {
	
	protected Map<String, Resource<T>> children = new HashMap<>();

	/**
	 * Create a new root memory container.
	 */
	public AbstractMemoryContainer() {
		
	}

	/**
	 * Exists only if contains any children which exist (contain contents).
	 */
	@Override
	public boolean exists() {
		for (Resource<T> child: children.values()) {
			if (child.exists()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Resource<T> find(String path) {
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return children.get(path); 
		}
		Resource<T> child = children.get(path.substring(0, sPos));
		if (child instanceof Container) {
			return ((Container<T>) child).find(path.substring(sPos+1));
		}
		return null;
	}

	@Override
	public Entity<T> getEntity(String path) {
		Resource<T> existing = find(path);
		if (existing instanceof Entity) {
			return (Entity<T>) existing;
		}
		if (existing instanceof Container) {
			// container but not a composite - can't have another resource with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			Entity<T> ret = new MemoryEntity<T>() {

				@Override
				public void appendState(T contents, ProgressMonitor monitor) {
					setState(getContentAppender().apply(getState(monitor), contents), monitor);					
				}

				@Override
				public String getName() {
					return path;
				}

				@Override
				public Container<T> getParent() {
					return AbstractMemoryContainer.this;
				}
				
				@Override
				public void delete(ProgressMonitor monitor) {
					super.delete(monitor);
					children.remove(getName());					
				}

				@Override
				public long size() {
					return AbstractMemoryContainer.this.size(contents);
				}
				
			};
			children.put(path, ret);
			return ret;
		}
		
		Container<T> container = getContainer(path.substring(0, sPos));
		return container == null ? null : container.getEntity(path.substring(sPos + 1));
	}

	/**
	 * @return A binary operator appending new content to the existing. This implementation throws {@link UnsupportedOperationException}.
	 */
	protected BinaryOperator<T> getContentAppender() {
		throw new UnsupportedOperationException("Content appending is not supported");
	};

	@Override
	public Container<T> getContainer(String path) {
		Resource<T> existing = find(path);
		if (existing instanceof Container) {
			return (Container<T>) existing;
		}
		if (existing instanceof Entity) {
			// entity but not a composite - can't have another resource with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			Container<T> ret = new AbstractMemoryContainer<T>() {

				@Override
				public String getName() {
					return path;
				}

				@Override
				public Container<T> getParent() {
					return AbstractMemoryContainer.this;
				}
				
				@Override
				public void delete(ProgressMonitor monitor) {
					super.delete(monitor);
					children.remove(getName());					
				}

				@Override
				protected long size(T contents) {
					return AbstractMemoryContainer.this.size(contents);
				}
				
			};
			children.put(path, ret);
			return ret;
		}
		
		Container<T> container = getContainer(path.substring(0, sPos));
		return container == null ? null : container.getContainer(path.substring(sPos + 1));
	}

	@Override
	public Collection<Resource<T>> getChildren() {
		return Collections.unmodifiableCollection(children.values());
	}

	@Override
	public void delete(ProgressMonitor monitor) {
		getChildren().forEach(child -> child.delete(monitor));
	}

	/**
	 * Computes content size.
	 * @param contents
	 * @return
	 */
	protected abstract long size(T contents);
	

}
