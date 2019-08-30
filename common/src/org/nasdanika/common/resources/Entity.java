package org.nasdanika.common.resources;

import java.util.function.BiFunction;
import java.util.zip.ZipFile;

import org.nasdanika.common.ProgressMonitor;

/**
 * Entity is something which has state. E.g. file is an entity with binary contents - a sequence of bytes.
 * @author Pavel
 *
 * @param <T> Entity state type
 */
public interface Entity<T> extends Resource<T> {
	
	/**
	 * @param monitor
	 * @return Entity state. 
	 * @throws IllegalStateException if the entity's underlying state storage does not exist or cannot be read.
	 */
	T getState(ProgressMonitor monitor);
		
	/**
	 * 
	 * @param type State type
	 * @param monitor
	 * @return Entity state converted to the the requested content type. E.g. file contents converted to String. This implementation does not perform any conversion and
	 * returns contents AS-IS if it is already of the requested type or throws an {@link UnsupportedOperationException} otherwise.
	 * @throws IllegalStateException if the entity state storage does not exist, cannot be read, or conversion from T to V is not supported.
	 */
	@SuppressWarnings("unchecked")
	default <V> V getState(Class<V> type, ProgressMonitor monitor) {
		T contents = getState(monitor);
		if (type.isInstance(contents)) {
			return (V) contents;
		}
		throw new UnsupportedOperationException("Unsupported content type: "+type);
	}	
	
	/**
	 * Sets entity state, e.g. file contents. If the entity storage does not exist this call creates the storage and intermediary containers as needed.
	 * @param state Entity state. 
	 * @param monitor
	 * @throws IllegalStateException if canWrite() returns false.
	 */
	void setState(T contents, ProgressMonitor monitor);
	
	/**
	 * Appends state if the entity state exists and calls setState if it doesn't.
	 * @param Entity state.
	 * @param monitor
	 * @throws IllegalStateException if canWrite() returns false.
	 */
	void appendState(T contents, ProgressMonitor monitor);

	/**
	 * @return true if entity state can be read. Some containers may be write-only.
	 */
	boolean canRead();
	
	/**
	 * @return true if entity state can be changed. Some containers may be read-only, e.g. a container backed by a {@link ZipFile} would only support reading resources from it.
	 */
	boolean canWrite();
	
	/**
	 * Copies entity. 
	 * @param container target container, may be the same container.
	 * @param path Path in the target container.
	 * @param monitor Progress monitor.
	 */
	@Override
	default void copy(Container<? super T> container, String path, ProgressMonitor monitor) {	
		if (exists()) {
			try (ProgressMonitor readMonitor = monitor.split("Reading "+getPath(), 100, this); ProgressMonitor writeMonitor = monitor.split("Writing "+path, 100, container, path)) {
				container.getEntity(path).setState(getState(readMonitor), monitor);			
			}
		}
	}
	
	/**
	 * Moves entity. This implementation implements move as copy and delete.
	 * @param container target container, may be the same container.
	 * @param path Path in the target container.
	 * @param monitor Progress monitor.
	 */
	@Override
	default void move(Container<? super T> container, String path, ProgressMonitor monitor) {
		if (exists()) {
			try (
					ProgressMonitor readMonitor = monitor.split("Reading "+getPath(), 100, this); 
					ProgressMonitor writeMonitor = monitor.split("Writing "+getPath(), 100, container, path);
					ProgressMonitor deleteMonitor = monitor.split("Delete "+getPath(), 100, this)) {
				container.getEntity(path).setState(getState(readMonitor), monitor);			
				delete(deleteMonitor);
			}
		}
	}
	
	/**
	 * Adapts to a different state type.
	 * @param <V>
	 * @param decoder Decodes T to V.
	 * @param encoder Encodes V to T.
	 * @param sizeConverter converts size of an entity. Size is passed as-is if null.
	 * @return
	 */
	default <V> Entity<V> adapt(BiFunction<Entity<T>, T, V> decoder, BiFunction<Entity<T>, V, T> encoder, BiFunction<Entity<T>, Long, Long> sizeConverter) {
		return new Entity<V>() {

			@Override
			public String getName() {
				return Entity.this.getName();
			}

			@Override
			public boolean exists() {
				return Entity.this.exists();
			}

			@Override
			public Container<V> getParent() {
				Container<T> parent = Entity.this.getParent();
				return parent == null ? null : parent.adapt(decoder, encoder, sizeConverter);
			}

			@Override
			public void delete(ProgressMonitor monitor) {
				Entity.this.delete(monitor);
			}

			@Override
			public String getPath() {
				return Entity.this.getPath();
			}

			@Override
			public V getState(ProgressMonitor monitor) {
				return decoder.apply(Entity.this, Entity.this.getState(monitor));
			}

			@Override
			public void setState(V contents, ProgressMonitor monitor) {
				Entity.this.setState(encoder.apply(Entity.this, contents), monitor);
			}

			@Override
			public void appendState(V contents, ProgressMonitor monitor) {
				Entity.this.appendState(encoder.apply(Entity.this, contents), monitor);
			}

			@Override
			public boolean canRead() {
				return decoder != null && Entity.this.canRead();
			}

			@Override
			public boolean canWrite() {
				return encoder != null && Entity.this.canWrite();
			}

			@Override
			public long size() {
				return sizeConverter == null ? Entity.this.size() : sizeConverter.apply(Entity.this, Entity.this.size());
			}

			@Override
			public String toString() {
				return getClass().getName()+"("+getPath()+")";
			}
			
		};
	}

}
