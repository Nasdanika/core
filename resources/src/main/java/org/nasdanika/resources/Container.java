package org.nasdanika.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.ProgressMonitor;

/**
 * Container of entries and other containers provides an abstraction for dealing with hierarchies/trees.
 * While a container does not necessarily have an out-of-JVM representation it extends {@link Resource} for convenience because these two 
 * interfaces share common characteristics.
 * @author Pavel
 *
 * @param <E> Element type.
 */
public interface Container<E> extends Resource<E> {
	
	/**
	 * Finds an existing element or container with a given path.
	 * @param path Child resource path. 
	 * @return Element if it exists or null.
	 */
	Object find(String path, ProgressMonitor monitor);
	
	/**
	 * @param path
	 * @return Element at the path or null if the element does not exist, is not created on access, or it would not be possible to create such an entity, e.g. a container with this path already exists, or one of path elements is not a container.
	 */
	E get(String path, ProgressMonitor monitor);
	
	/**
	 * 
	 * @param path Element path
	 * @param element Element
	 * @throws IllegalArgumentException if it is not possible to put an element at this path. E.g. getContainer(parent path) returns null.
	 */
	void put(String path, E element, ProgressMonitor monitor) throws IllegalArgumentException;
	
	/**
	 * Deletes an element or container at a given path.
	 * @param path
	 * @return Removed element/container or null if it did not exist or the container removed it without retrieving. 
	 */
	Object delete(String path, ProgressMonitor monitor);
	
	/**
	 * Gets an element from the container creating it using a factory if it doesn't already exist.
	 * @param path Path.
	 * @param factory Factory
	 * @throws IllegalArgumentException If it is not possible to put element at the given path.
	 * @return
	 */
	default E get(String path, BiFunction<String,ProgressMonitor,E> factory, ProgressMonitor monitor) throws IllegalArgumentException {
		try {
			E ret = get(path, monitor.split("Getting existing element", 1));
			if (ret == null && factory != null) {			
				ret = factory.apply(path, monitor.split("Creating a new element", 1, path));
				if (ret != null) {
					put(path, ret, monitor.split("Putting a new element", 1, path, ret));
				}
			}
			return ret;
		} finally {
			monitor.close();
		}
	}
	
	/**
	 * Returns a container for a given path. It is a logical operation - the container doesn't have to exist.
	 * @param path
	 * @return Container instance or null if it would not be possible to create such a container, e.g. an element with this path already exists or one of path elements is not a container.
	 */
	Container<E> getContainer(String path, ProgressMonitor monitor);
	
	/**
	 * Lists existing children.
	 * @return A list of existing children. May return null for containers where children can't be retrieved, e.g. write-only containers.
	 */
	Map<String, Object> getChildren(ProgressMonitor monitor);
	
	interface Copier<T,R> {
		
		/**
		 * Makes a copy of an element, merges with the existing value.
		 * @param element
		 * @param existingChild Existing child in the target container - element or sub-container.
		 * @param monitor
		 * @return
		 */
		R copy(String path, T source, Object existingElement, ProgressMonitor monitor);	
		
	}
		
	/**
	 * Recursively copies children to another container.
	 * @param <F> Element type of the other container.
	 * @param container Target container.
	 * @param copier Copier of elements, converting type, and merging with the existing element if it is present. The first argument is 
	 * element being copied, the second is an existing element or null if there is no such element.
	 * @param monitor Progress monitor
	 */
	@SuppressWarnings("unchecked")
	default <F> void copy(Container<F> target, Copier<E,F> copier, ProgressMonitor monitor) {
		try {
			Map<String, Object> children = getChildren(monitor.split("Getting children", 1, this));		
			try (ProgressMonitor subMonitor = monitor.split("Copying "+getPath(), children.size(), this)) {
				if (children != null) {				
					children.forEach((path, child) -> {
						if (child instanceof Container) {
							Container<F> targetChild = target.getContainer(path, monitor.split("Getting container "+path, 1));
							if (targetChild == null) {
								throw new IllegalArgumentException("Cannot obtain target container for path "+path);
							}
							((Container<E>) child).copy(targetChild, copier, subMonitor);
						} else {
							F copy = copier.copy(path, (E) child, target.find(path, subMonitor.split("Finiding", 1, path)), subMonitor.split("Copying "+path, 1));
							target.put(path, copy, subMonitor.split("Putting copied element", 1, path, copy)); 
						}
					});
				}
			}
		} finally {
			monitor.close();
		}
	}
	
	/**
	 * Recursively moves children - copies and then removes.
	 */
	@SuppressWarnings("unchecked")
	default <F> void move(Container<F> target, Copier<E,F> copier, ProgressMonitor monitor) {
		try {
			Map<String, Object> children = getChildren(monitor.split("Getting children", 1, this));		
			try (ProgressMonitor subMonitor = monitor.split("Copying "+getPath(), children.size(), this)) {
				if (children != null) {	
					Collection<String> paths = new ArrayList<>();
					children.forEach((path, child) -> {
						if (child instanceof Container) {
							Container<F> targetChild = target.getContainer(path, monitor.split("Getting container "+path, 1));
							if (targetChild == null) {
								throw new IllegalArgumentException("Cannot obtain target container for path "+path);
							}
							((Container<E>) child).move(targetChild, copier, subMonitor);
						} else {
							F copy = copier.copy(path, (E) child, target.find(path, subMonitor.split("Finiding", 1, path)), subMonitor.split("Copying "+path, 1));
							target.put(path, copy, subMonitor.split("Putting copied element", 1, path, copy)); 
						}
						paths.add(path);
					});
					paths.forEach(path -> delete(path, monitor.split("Removing", 1, path)));
				}
			}
		} finally {
			monitor.close();
		}
	}	
	
	/**
	 * Adapts to a different element type.
	 * @param <F> Element type to adapt to
	 * @param adapter Adapts element E to target type F. Takes element path and the element itself.
	 * @param converter Converts element F to E on put and get with a factory. Takes element path and the element itself. 
	 * @return
	 */
	default <F> Container<F> adapt(BiFunction<String,? super E,F> adapter, BiFunction<String,? super F,E> converter) {
		return new Container<F>() {

			@SuppressWarnings("unchecked")
			@Override
			public Object find(String path, ProgressMonitor monitor) {
				Object ret = Container.this.find(path, monitor);
				if (ret instanceof Container) {
					return ((Container<E>) ret).adapt(adapter, converter);
				}
				return adapter.apply(path, (E) ret);
			}

			@Override
			public F get(String path, ProgressMonitor monitor) {
				return adapter.apply(path, Container.this.get(path, monitor));
			}

			@Override
			public void put(String path, F element, ProgressMonitor monitor) throws IllegalArgumentException {
				Container.this.put(path, converter.apply(path, element), monitor);				
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object delete(String path, ProgressMonitor monitor) {
				Object deleted = Container.this.delete(path, monitor);
				if (deleted instanceof Container) {
					return ((Container<E>) deleted).adapt(adapter, converter);
				}
				return adapter.apply(path, (E) deleted);
			}

			@Override
			public Container<F> getContainer(String path, ProgressMonitor monitor) {
				Container<E> ret = Container.this.getContainer(path, monitor);
				return ret.adapt(adapter, converter);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Map<String, Object> getChildren(ProgressMonitor monitor) {
				Map<String, Object> ret = new HashMap<>();
				Container.this.getChildren(monitor).forEach((path, child) -> {
					if (child instanceof Container) {
						ret.put(path, ((Container<E>) ret).adapt(adapter, converter));
					} else {
						ret.put(path, adapter.apply(path, (E) child));
					}					
				}); 
				return Collections.unmodifiableMap(ret);
			}

			@Override
			public Container<F> getParent() {
				return Container.this.getParent().adapt(adapter, converter);
			}

			@Override
			public String getName() {
				return Container.this.getName();
			}

			@Override
			public void copy(Container<? super F> container, String path, ProgressMonitor monitor) {
				throw new UnsupportedOperationException("Implement when this one is thrown");
			}

			@Override
			public void move(Container<? super F> container, String path, ProgressMonitor monitor) {
				throw new UnsupportedOperationException("Implement when this one is thrown");
			}
			
			@Override
			public String toString() {
				return getClass().getName()+"("+getPath()+")";
			}
			
		};
	}
	
	/**
	 * Filters this container to return null for getParent() so this container is considered to be a root container.
	 * @return
	 */
	default Container<E> asRoot() {
		return new ContainerFilter<E>(this) {

			@Override
			public Container<E> getParent() {
				return null;
			}
			
		};
	}
	
	/**
	 * TODO - filter -> read predicate, write predicate
	 * Filters this container. 
	 * @param filter Child path filter. 
	 * @return
	 */
	default Container<E> filter(Predicate<String> filter) {
		return new ContainerFilter<E>(this) {
			
			@SuppressWarnings("unchecked")
			@Override
			public Map<String, Object> getChildren(ProgressMonitor monitor) {
				Map<String, Object> ret = new HashMap<>();
				Container.this.getChildren(monitor).forEach((path, child) -> {
					if (filter.test(path)) {
						if (child instanceof Container) {
							ret.put(path, ((Container<E>) ret).filter(filter));
						} else {
							ret.put(path, child);
						}
					}
				}); 
				return Collections.unmodifiableMap(ret);
			}
			
			
			@Override
			public Object find(String path, ProgressMonitor monitor) {
				return filter.test(path) ? super.find(path, monitor) : null;
			}
			
			@Override
			public Container<E> getContainer(String path, ProgressMonitor monitor) {
				return filter.test(path) ? super.getContainer(path, monitor).filter(filter) : null;
			}
			
			@Override
			public E get(String path, ProgressMonitor monitor) {
				return filter.test(path) ? super.get(path, monitor) : null;
			}
			
			@Override
			public void put(String path, E element, ProgressMonitor monitor) throws IllegalArgumentException {
				if (!filter.test(path)) {
					throw new IllegalArgumentException("Path doesn't match the filter: "+path);
				}
				super.put(path, element, monitor);
			}
			
		};
	}
	
	/**
	 * Loads entries from a {@link ZipInputStream} into this container. This method used getXXX() methods to obtain container children.
	 * If getXXX() method returns null of if {@link Entity} entry is not null and canWrite() returns false then an exception is thrown.
	 * @param zipInputStream ZipInputStream.
	 * @param filter Entry name filter. Can be null.
	 * @param contentLoader Converts input stream to the element type. The first argument is the path.
	 * @param progressMonitor Progress monitor.
	 * @throws IOException
	 */
	default void load(
			ZipInputStream zipInputStream, 
			Predicate<String> filter, 
			Copier<InputStream,E> contentLoader, 
			ProgressMonitor progressMonitor) throws IOException {
		
		try {
			ZipEntry zipEntry;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				try (ProgressMonitor entryMonitor = progressMonitor.split("Entry "+zipEntry.getName(), 1, zipEntry)) {
					String entryName = zipEntry.getName();
					if (filter == null || filter.test(entryName)) {
						if (entryName.endsWith("/")) {
							if (getContainer(entryName.substring(0, entryName.length() - 1), entryMonitor.split("Getting container "+entryName, 1)) == null) {
								throw new IOException("Container with path "+entryName+" cannot be created");
							}
						} else {
							E element = contentLoader.copy(entryName, zipInputStream, get(entryName, entryMonitor.split("Getting existing", 1, entryName)), entryMonitor.split("Loading "+entryName, 1));
							if (element != null) {
								put(entryName, element, entryMonitor.split("Putting copied element", 1, entryName, element));
							}
						}
					}
					zipInputStream.closeEntry();
				}
			}
		} finally {
			progressMonitor.close();
			zipInputStream.close();
		}
	}
	
	/**
	 * Stores children of this container to a {@link ZipOutputStream}. 
	 * @param zipOutputStream Zip output stream. This method does not close the stream.
	 * @param prefix Optional path prefix.
	 * @param contentSerializer Converts entity state to the input stream. If null then {@link DefaultConverter}.INSTANCE is used to convert content to {@link InputStream}
	 * @param progressMonitor Progress monitor.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	default void store(
		ZipOutputStream zipOutputStream, 
		String prefix,
		Copier<E,InputStream> contentSerializer, 
		ProgressMonitor progressMonitor) throws IOException {
		
		try {
			if (contentSerializer == null) {
				contentSerializer = (path, source, existingElement, monitor) -> DefaultConverter.INSTANCE.convert(source, InputStream.class);
			}
			
			if (prefix == null) {
				prefix = "";
			}
			
			for (Entry<String, Object> childEntry: getChildren(progressMonitor.split("Getting children", 1, this)).entrySet()) {
				try (ProgressMonitor childMonitor = progressMonitor.split("Child "+childEntry.getKey(), 1, childEntry.getValue())) {
					if (childEntry.getValue() instanceof Container) {
						zipOutputStream.putNextEntry(new ZipEntry(prefix+"/"+childEntry.getKey()+"/"));
						zipOutputStream.closeEntry();
						((Container<E>) childEntry.getValue()).store(zipOutputStream, prefix+childEntry.getKey()+"/", contentSerializer, childMonitor);
					} else {
						zipOutputStream.putNextEntry(new ZipEntry(prefix+childEntry.getKey()));
						try (InputStream contents = contentSerializer.copy(childEntry.getKey(), (E) childEntry.getValue(), null, childMonitor)) {
							int b;
							while ((b = contents.read()) != -1) {
								zipOutputStream.write(b);
							}
						}
						zipOutputStream.closeEntry();				
					}
				}
			}
		} finally {
			progressMonitor.close();
		}
	}
	
	/**
	 * Deletes all children and then deletes this container from its parent.
	 * @param monitor
	 */
	default void delete(ProgressMonitor monitor) {
		new HashMap<>(getChildren(monitor.split("Getting children", 1))).forEach((path, child) -> delete(path, monitor.split("Removing child "+path, 1, path, child)));		
		Container<E> parent = getParent();
		if (parent != null) {
			parent.delete(getName(), monitor.split("Removing self from the parent", 1, this));
		}		
	}
	
	default long size(ProgressMonitor monitor) {
		long[] ret = {0};
		getChildren(monitor.split("Getting children", 1, this)).forEach((path, child) -> {;
			if (child instanceof Resource && ((Resource<?>) child).exists(monitor.split("Checking child existence", 1, child))) {
				ret[0] += ((Resource<?>) child).size(monitor.split("Getting child size "+path, 1, child));
			}
		});
		return ret[0];
	}
		
	/**
	 * Returns true if contains any existing resources.
	 */
	default boolean exists(ProgressMonitor monitor) {
		Map<String, Object> children = getChildren(monitor.split("Getting children", 1, this));
		for (Object child: children.values()) {
			if (child instanceof Resource && ((Resource<?>) child).exists(monitor.split("Checking child existence", 1, child))) {
				return true;
			}
		};
		return false;		
	}
}
