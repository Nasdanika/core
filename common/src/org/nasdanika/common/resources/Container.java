package org.nasdanika.common.resources;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;

/**
 * Container of other resources
 * @author Pavel
 *
 * @param <T> Content type.
 */
public interface Container<T> extends Resource<T> {
	
	/**
	 * Finds an existing resources with a given path.
	 * @param path
	 * @return
	 */
	Resource<T> find(String path);
	
	/**
	 * Returns a file for a given path. It is a logical operation - the file doesn't have to 
	 * exist.
	 * @param path
	 * @return File instance or null if it would not be possible to create such a file, e.g. a resource with this path already exists and is a container or one of path elements is a file.
	 */
	File<T> getFile(String path);
	
	/**
	 * Returns a container for a given path. It is a logical operation - the container doesn't have to exist.
	 * @param path
	 * @return Container instance or null if it would not be possible to create such a container, e.g. a resource with this path already exists and is a file or one of path elements is a file.
	 */
	Container<T> getContainer(String path);
	
	/**
	 * Lists existing children.
	 * @return A list of existing children. May return null for containers where children can't be retrieved, e.g. write-only containers.
	 */
	Collection<Resource<T>> getChildren();
	
	default long size() {
		long ret = 0;
		for (Resource<T> child: getChildren()) {
			if (child.exists()) {
				ret += child.size();
			}
		}
		return ret;
	}
		
	/**
	 * Recursively copies children.
	 */
	@Override
	default void copy(Container<? super T> container, String path, ProgressMonitor monitor) {
		if (exists()) {			
			try (ProgressMonitor subMonitor = monitor.split("Copying "+getName(), size(), this)) {
				Container<? super T> target = container.getContainer(path);
				Collection<Resource<T>> children = getChildren();
				if (children != null) {				
					children.forEach(child -> child.copy(target, child.getName(), subMonitor));
				}
			}
		}
	}
	
	/**
	 * Recursively moves children
	 */
	@Override
	default void move(Container<? super T> container, String path, ProgressMonitor monitor) {
		if (exists()) {			
			try (ProgressMonitor subMonitor = monitor.split("Moving "+getName(), size()*2 + 1, this)) {
				Container<? super T> target = container.getContainer(path);
				Collection<Resource<T>> children = getChildren();
				if (children != null) {
					children.forEach(child -> child.move(target, child.getName(), subMonitor));
				}
				delete(subMonitor);
			}
		}		
	}	
	
	/**
	 * Adapts to a different content type.
	 * @param <V>
	 * @param decoder Decodes T to V.
	 * @param encoder Encodes V to T.
	 * @param sizeConverter converts size of a file. Size is passed as-is if null.
	 * @return
	 */
	@Override
	default <V> Container<V> adapt(BiFunction<File<T>, T, V> decoder, BiFunction<File<T>, V, T> encoder, BiFunction<File<T>, Long, Long> sizeConverter) {
		return new Container<V>() {

			@Override
			public String getName() {
				return Container.this.getName();
			}

			@Override
			public boolean exists() {
				return Container.this.exists();
			}

			@Override
			public Container<V> getParent() {
				Container<T> parent = Container.this.getParent();
				return parent == null ? null : parent.adapt(decoder, encoder, sizeConverter);
			}

			@Override
			public void delete(ProgressMonitor monitor) {
				Container.this.delete(monitor);
			}

			@Override
			public String getPath() {
				return Container.this.getPath();
			}

			@Override
			public Resource<V> find(String path) {
				Resource<T> res = Container.this.find(path);
				if (res == null) {
					return null;
				}
				
				return res.adapt(decoder, encoder, sizeConverter);
			}

			@Override
			public File<V> getFile(String path) {
				return Container.this.getFile(path).adapt(decoder, encoder, sizeConverter);
			}

			@Override
			public Container<V> getContainer(String path) {
				return Container.this.getContainer(path).adapt(decoder, encoder, sizeConverter);
			}

			@Override
			public Collection<Resource<V>> getChildren() {
				Collection<Resource<T>> ch = Container.this.getChildren();
				
				if (ch == null) {
					return null;
				}
				
				return ch.stream().map(r -> r.adapt(decoder, encoder, sizeConverter)).collect(Collectors.toList());
			}
			
		};
	}
	
}
