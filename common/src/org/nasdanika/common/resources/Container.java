package org.nasdanika.common.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.nasdanika.common.ProgressMonitor;

/**
 * Container of other resources
 * @author Pavel
 *
 * @param <T> Content type.
 */
public interface Container<T> extends Resource<T> {
	
	/**
	 * Finds an existing resource with a given path.
	 * @param path
	 * @return
	 */
	Resource<T> find(String path);
	
	/**
	 * Returns an entity for a given path. It is a logical operation - the entity state doesn't have to exist in the underlying storage.
	 * @param path
	 * @return Entity instance or null if it would not be possible to create such an entity, e.g. a resource with this path already exists and is a container but not a {@link Composite}, or one of path elements is an entity.
	 */
	Entity<T> getEntity(String path);
	
	/**
	 * Returns a container for a given path. It is a logical operation - the container doesn't have to exist.
	 * @param path
	 * @return Container instance or null if it would not be possible to create such a container, e.g. a resource with this path already exists and is an entity or one of path elements is an entity.
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
	 * @param sizeConverter converts size of an entity. Size is passed as-is if null.
	 * @return
	 */
	@Override
	default <V> Container<V> adapt(BiFunction<Entity<T>, T, V> decoder, BiFunction<Entity<T>, V, T> encoder, BiFunction<Entity<T>, Long, Long> sizeConverter) {
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
			public Entity<V> getEntity(String path) {
				return Container.this.getEntity(path).adapt(decoder, encoder, sizeConverter);
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
	
	/**
	 * Filters this container to return null for getParent() so this container is considered to the a root container.
	 * @return
	 */
	default Container<T> asRoot() {
		return new ContainerFilter<T>(this) {

			@Override
			public Container<T> getParent() {
				return null;
			}
			
		};
	}
	
	/**
	 * Filters this container. 
	 * @param filter Child path filter. 
	 * @return
	 */
	default Container<T> filter(Predicate<String> filter) {
		return new ContainerFilter<T>(this) {
			
			@Override
			public Collection<Resource<T>> getChildren() {
				return super.getChildren()
						.stream()
						.filter(c -> filter.test(c.getPath()))
						.map(c -> c instanceof Container ? ((Container<T>) c).filter(filter) : c)
						.collect(Collectors.toList());
			}
			
			@Override
			public Resource<T> find(String path) {
				return filter.test(path) ? super.find(path) : null;
			}
			
			@Override
			public Container<T> getContainer(String path) {
				return filter.test(path) ? super.getContainer(path).filter(filter) : null;
			}
			
			@Override
			public Entity<T> getEntity(String path) {
				return filter.test(path) ? super.getEntity(path) : null;
			}
			
		};
	}
	
	/**
	 * Loads entries from a {@link ZipInputStream} into this container. This method used getXXX() methods to obtain container children.
	 * If getXXX() method returns null of if {@link Entity} entry is not null and canWrite() returns false then an exception is thrown.
	 * @param zipInputStream ZipInputStream.
	 * @param filter Entry name filter. Can be null.
	 * @param contentLoader Converts input stream to the entity state type. The first argument is the resource (entity) path.
	 * @param progressMonitor Progress monitor.
	 * @throws IOException
	 */
	default void load(
			ZipInputStream zipInputStream, 
			Predicate<String> filter, 
			BiFunction<String,InputStream,T> contentLoader, 
			ProgressMonitor progressMonitor) throws IOException {
		ZipEntry zipEntry;
		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String entryName = zipEntry.getName();
			if (filter == null || filter.test(entryName)) {
				if (entryName.endsWith("/")) {
					if (getContainer(entryName.substring(0, entryName.length() - 1)) == null) {
						throw new IOException("Container with path "+entryName+" cannot be created");
					}
				} else {
					Entity<T> entity = getEntity(entryName);
					if (entity == null || !entity.canWrite()) {
						throw new IOException("Entity with path "+entryName+" cannot be written");						
					}
					entity.setState(contentLoader.apply(entryName, zipInputStream), progressMonitor.split("Loading "+entryName, 1, zipEntry));
				}
			}
			zipInputStream.closeEntry();
		}				
		zipInputStream.close();
	}
	
	/**
	 * Stores readable children of this container to a {@link ZipOutputStream}. 
	 * @param zipOutputStream Zip output stream. This method does not close the stream.
	 * @param prefix Optional path prefix.
	 * @param contentWriter Converts entity state to the input stream.
	 * @param progressMonitor Progress monitor.
	 * @throws IOException
	 */
	default void store(
		ZipOutputStream zipOutputStream, 
		String prefix,
		BiFunction<String,T,InputStream> contentSerializer, 
		ProgressMonitor progressMonitor) throws IOException {
		
		if (prefix == null) {
			prefix = "";
		}
		for (Resource<T> child: getChildren()) {
			if (child instanceof Container) {
				zipOutputStream.putNextEntry(new ZipEntry(prefix+child.getPath()+"/"));
				zipOutputStream.closeEntry();
				((Container<T>) child).store(zipOutputStream, prefix, contentSerializer, progressMonitor);
			} else if (child instanceof Entity && ((Entity<T>) child).canRead()) {
				zipOutputStream.putNextEntry(new ZipEntry(prefix+child.getPath()));
				Entity<T> entity = (Entity<T>) child;
				try (InputStream contents = contentSerializer.apply(child.getPath(), entity.getState(progressMonitor.split("Reading "+entity.getPath(), 1, entity)))) {
					int b;
					while ((b = contents.read()) != -1) {
						zipOutputStream.write(b);
					}
				}
				zipOutputStream.closeEntry();				
			}
		}		
	}
	
}
