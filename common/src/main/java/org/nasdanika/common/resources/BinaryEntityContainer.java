package org.nasdanika.common.resources;

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
 */
public interface BinaryEntityContainer extends EntityContainer<InputStream,BinaryEntity>, BinaryResource {
	
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
		load(zipInputStream, filter, (path, state) -> state, progressMonitor);
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
		store(zipOutputStream, prefix, (path, state) -> state, progressMonitor);
	}
	
	/**
	 * Narrowing down return type for convenience.
	 */
	@Override
	default BinaryContainer stateAdapter() {
		return new BinaryContainerImpl(EntityContainer.super.stateAdapter());
	}
	
	/**
	 * Narrows return type.
	 */
	@Override
	BinaryEntityContainer getContainer(String path, ProgressMonitor monitor);
	
	/**
	 * Narrows return type.
	 */
	@Override
	BinaryEntityContainer getParent();
	
	/**
	 * Narrows return type.
	 */
	@Override
	BinaryResource find(String path, ProgressMonitor monitor);

}
