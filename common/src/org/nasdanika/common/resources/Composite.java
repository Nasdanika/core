package org.nasdanika.common.resources;

import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;

/**
 * An entity which can contain other entities.
 * @author Pavel
 *
 * @param <T>
 */
public interface Composite<T> extends Entity<T>, Container<T> {
	
	default void move(Container<? super T> container, String path, ProgressMonitor monitor) {
		throw new UnsupportedOperationException("TODO - implement");
	}

	default <V> Composite<V> adapt(BiFunction<Entity<T>, T, V> decoder, BiFunction<Entity<T>, V, T> encoder, BiFunction<Entity<T>, Long, Long> sizeConverter) {
		throw new UnsupportedOperationException("TODO - implement");		
	}

	default void copy(Container<? super T> container, String path, ProgressMonitor monitor) {
		throw new UnsupportedOperationException("TODO - implement");				
	}	
	
}
