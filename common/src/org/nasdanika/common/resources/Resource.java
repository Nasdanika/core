package org.nasdanika.common.resources;

import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;

/**
 * Resource is something uniquily identified by its name within its container and its path within the root container.
 * The resource path separator is ``/``. 
 * @author Pavel
 *
 * @param <T> Content type.
 */
public interface Resource<T> {
	
	public static final String SEPARATOR = "/";

	/**
	 * @return Resource name - the last segment of the path.
	 */
	String getName();
	
	/**
	 * @return true if resource exists.
	 */
	boolean exists();
	
	/**
	 * @return Parent container.
	 */
	Container<T> getParent();
	
	/**
	 * Resource size. -1 for unknown.
	 * @return
	 */
	long size();
	
	/**
	 * @return The root container.
	 */
	default Container<T> getRoot() {
		Container<T> parent = getParent();
		if (parent != null) {
			return parent.getRoot();
		}
		
		return this instanceof Container ? (Container<T>) this : null;
	}
	
	/**
	 * Deletes the resource if it exists.
	 * @param monitor Parent monitor, the resource splits the monitor and takes 1 tick.
	 */
	void delete(ProgressMonitor monitor);
	
	/**
	 * Resource path from the root container.
	 * @return
	 */
	default String getPath() {
		Container<T> parent = getParent();
		if (parent == null) {
			return getName();
		}
		String parentPath = parent.getPath();
		return parentPath == null || parentPath.isEmpty() ? getName() : parentPath + SEPARATOR + getName();
	};
	
	/**
	 * Copies the resource to the target container under a given path.
	 * @param container Target container, may be the same container.
	 * @param path Path to copy the resource to.
	 * @param monitor Parent progress monitor, the resource splits the monitor and takes size() ticks.
	 */
	void copy(Container<? super T> container, String path, ProgressMonitor monitor);
	
	/**
	 * Moves the resource to the target container under a given path. Equivalent to copying and then deleting.
	 * @param container Target container.
	 * @param path Path to move the resource to.
	 * @param monitor Parent progress monitor, the resource splits the monitor and takes 2*size() ticks - one size for reading and one for writing.
	 */
	void move(Container<? super T> container, String path, ProgressMonitor monitor);
	
	/**
	 * Adapts to a different state type through encoding/decoding. 
	 * @param <V> New state type. Can be the same as T in case of filtering - token substitution, encryption/decryption, compression/decompression.
	 * Either encoder or decoder can be null, it will make the adapted entities read-only and write-only respectively. Decoder and encoder take the entity as 
	 * the first argument to allow implement entity-specific operations, e.g. extension-based encoding/decoding such as parsing JSON for entities with ``.json`` extension.
	 * @param decoder Decodes T to V.
	 * @param encoder Encodes V to T.
	 * @param sizeConverter converts size of an entity. Size is passed as-is if null.
	 * @return
	 */
	<V> Resource<V> adapt(BiFunction<Entity<T>, T, V> decoder, BiFunction<Entity<T>, V, T> encoder, BiFunction<Entity<T>, Long, Long> sizeConverter);
	
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
