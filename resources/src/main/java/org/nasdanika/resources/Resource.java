package org.nasdanika.resources;

import org.nasdanika.common.ProgressMonitor;

/**
 * Resource typically represents something with a lifespan longer than the JVM. It is  uniquely identified by its name within its container and its path within the root container.
 * The resource path separator is ``/``. 
 * @author Pavel
 *
 * @param <E> Element type for the parent container.
 */
public interface Resource<E> {
	
	public static final String SEPARATOR = "/";

	/**
	 * @return Resource name - the last segment of the path.
	 */
	String getName();
	
	/**
	 * @return true if resource exists.
	 */
	boolean exists(ProgressMonitor monitor);
	
	/**
	 * @return Parent container.
	 */
	Container<E> getParent();
	
	/**
	 * Resource size. -1 for unknown.
	 * @return
	 */
	long size(ProgressMonitor monitor);
	
	/**
	 * @return The root container.
	 */
	default Container<E> getRoot() {
		Container<E> parent = getParent();
		if (parent != null) {
			return parent.getRoot();
		}
		
		return this instanceof Container ? (Container<E>) this : null;
	}
	
	/**
	 * Deletes the resource if it exists.
	 * @param monitor Progress monitor.
	 */
	void delete(ProgressMonitor monitor);
	
	/**
	 * Resource path from the root container.
	 * @return
	 */
	default String getPath() {
		Container<E> parent = getParent();
		if (parent == null) {
			return getName();
		}
		String parentPath = parent.getPath();
		return parentPath == null || parentPath.isEmpty() ? getName() : parentPath + Container.SEPARATOR + getName();
	};
	
	/**
	 * Copies the resource to the target container under a given path.
	 * @param container Target container, may be the same container.
	 * @param path Path to copy the resource to.
	 * @param monitor Parent progress monitor, the resource splits the monitor and takes size() ticks.
	 */
	void copy(Container<? super E> container, String path, ProgressMonitor monitor);
	
	/**
	 * Moves the resource to the target container under a given path. Equivalent to copying and then deleting.
	 * @param container Target container.
	 * @param path Path to move the resource to.
	 * @param monitor Parent progress monitor, the resource splits the monitor and takes 2*size() ticks - one size for reading and one for writing.
	 */
	void move(Container<? super E> container, String path, ProgressMonitor monitor);
	
//	/**
//	 * Adapts to a different resource type. 
//	 * @param <V> New resource type. 
//	 * Either encoder or decoder can be null, it will make the adapted entities read-only and write-only respectively. Decoder and encoder take the entity as 
//	 * the first argument to allow implement entity-specific operations, e.g. extension-based encoding/decoding such as parsing JSON for entities with ``.json`` extension.
//	 * @param decoder Decodes T to V.
//	 * @param encoder Encodes V to T.
//	 * @param sizeConverter converts size of an entity. Size is passed as-is if null.
//	 * @return
//	 */
//	<V extends Resource<?>> Resource<V> adapt(FunctionFactory<T,V> adapter, BiFunction<Entity<T>, V, T> encoder, BiFunction<Entity<T>, Long, Long> sizeConverter);
	
	/**
	 * @return Resource digest. Entities compute state digest, e.g. SHA256 digest of file contents. Containers derive digest value from their children digests. 
	 */
	default String getDigest() {
		return null;
	}
	
	/**
	 * @return Time when this resource was last modified, -1 if unknown. For containers modification means a change in the list of children, not necessarily change in the
	 * children's state.
	 */
	default long getTimestamp() {
		return -1;
	}
	
}
