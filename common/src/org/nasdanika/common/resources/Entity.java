package org.nasdanika.common.resources;

import java.util.function.BiFunction;
import java.util.zip.ZipFile;

import org.nasdanika.common.ProgressMonitor;

/**
 * Entity is something which has externalizable state. E.g. file is an entity with binary contents - a sequence of bytes.
 * @author Pavel
 *
 * @param <T> Entity state type
 * @param <E> Container element type.
 */
public interface Entity<T,E> extends Resource<E> {
	
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
	void setState(T state, ProgressMonitor monitor);
	
	/**
	 * Appends state if the entity state exists and calls setState if it doesn't.
	 * @param Entity state.
	 * @param monitor
	 * @throws IllegalStateException if canWrite() returns false.
	 */
	void appendState(T state, ProgressMonitor monitor);

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
	@SuppressWarnings("unchecked")
	@Override
	default void copy(Container<? super E> container, String path, ProgressMonitor monitor) {	
		if (exists(monitor.split("Checking existence", 1, this))) {
			try (
					ProgressMonitor getCopyTargetMonitor = monitor.split("Getting copy target"+getPath(), 1, this); 
					ProgressMonitor readMonitor = monitor.split("Copying "+getPath(), 1, this); 
					ProgressMonitor writeMonitor = monitor.split("Writing "+path, 1, container, path)) {
				Object copyTarget = container.get(path, getCopyTargetMonitor);
				if (copyTarget instanceof Entity) {
					((Entity<T,E>) copyTarget).setState(getState(readMonitor), writeMonitor);
				} else {
					throw new IllegalArgumentException("Copy target is not an entity: "+copyTarget);
				}
			}
		}
	}
	
	/**
	 * Moves entity. This implementation implements move as copy and delete.
	 * @param container target container, may be the same container.
	 * @param path Path in the target container.
	 * @param monitor Progress monitor.
	 */
	@SuppressWarnings("unchecked")
	@Override
	default void move(Container<? super E> container, String path, ProgressMonitor monitor) {
		if (exists(monitor.split("Checking existence", 1, this))) {
			try (
					ProgressMonitor getCopyTargetMonitor = monitor.split("Getting copy target"+getPath(), 1, this); 
					ProgressMonitor readMonitor = monitor.split("Copying "+getPath(), 1, this); 
					ProgressMonitor writeMonitor = monitor.split("Writing "+path, 1, container, path);
					ProgressMonitor deleteMonitor = monitor.split("Deleting"+path, 1, this, path)) {
				Object moveTarget = container.get(path, getCopyTargetMonitor);
				if (moveTarget instanceof Entity) {
					((Entity<T,E>) moveTarget).setState(getState(readMonitor), writeMonitor);
					delete(deleteMonitor);
				} else {
					throw new IllegalArgumentException("Copy target is not an entity: "+moveTarget);
				}
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
	default <V> Entity<V,E> adapt(BiFunction<Entity<T,E>, T, V> decoder, BiFunction<Entity<T,E>, V, T> encoder, BiFunction<Entity<T,E>, Long, Long> sizeConverter) {
		return new Entity<V,E>() {

			@Override
			public String getName() {
				return Entity.this.getName();
			}

			@Override
			public boolean exists(ProgressMonitor monitor) {
				return Entity.this.exists(monitor);
			}

			@Override
			public Container<E> getParent() {
				Container<E> parent = Entity.this.getParent();
				@SuppressWarnings("unchecked")
				BiFunction<String, E, E> adapter = (path, source) -> {
					return (E) ((Entity<T,E>) source).adapt(decoder, encoder, sizeConverter);
				};
				
				BiFunction<String, E, E> converter = (path, adapted) -> {
					throw new UnsupportedOperationException("Entity put is not supported");
				};
				return parent == null ? null : parent.adapt(adapter, converter);
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
			public long size(ProgressMonitor monitor) {
				return sizeConverter == null ? Entity.this.size(monitor) : sizeConverter.apply(Entity.this, Entity.this.size(monitor));
			}

			@Override
			public String toString() {
				return getClass().getName()+"("+getPath()+")";
			}
			
		};
	}

}
