package org.nasdanika.common.resources;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.nasdanika.common.ProgressMonitor;

/**
 * Binds EntityContainer to {@link InputStream} and {@link BinaryEntity}.
 * @author Pavel
 *
 * @param <E>
 */
public interface BinaryEntityContainer<E extends BinaryEntity<E>> extends EntityContainer<InputStream,E> {
	
	/**
	 * Loads entities from {@link ZipInputStream}
	 * @param zipInputStream
	 * @param filter
	 * @param progressMonitor
	 * @throws IOException
	 */
	default void load(
			ZipInputStream zipInputStream, 
			Predicate<String> filter,
			ProgressMonitor progressMonitor) throws IOException {
		Copier<InputStream, E> contentLoader = (path, source, existingElement, monitor) -> {
			
			// Overriding close because set state closes the source stream when it is drained.
			InputStream value = new FilterInputStream(source) { 

				public void close() {
					
				}
				
			};
			
			// Must be created by the container on access.
			((BinaryEntity<?>) existingElement).setState(value, monitor);
			
			// Returning null because entity containers do not support put().
			return null;
			
		};
				
		load(zipInputStream, filter, contentLoader, progressMonitor);
	}
	
	/**
	 * Stores entities to {@link ZipOutputStream}
	 * @param zipOutputStream
	 * @param prefix
	 * @param progressMonitor
	 * @throws IOException
	 */
	default void store(
			ZipOutputStream zipOutputStream, 
			String prefix, 
			ProgressMonitor progressMonitor) throws IOException {
		
		Copier<E, InputStream> contentSerializer = (path, source, existingElement, monitor) -> source.getState(monitor);
		store(zipOutputStream, prefix, contentSerializer, progressMonitor);
	}

}
