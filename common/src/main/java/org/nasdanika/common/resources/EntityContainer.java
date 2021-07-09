package org.nasdanika.common.resources;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.nasdanika.common.ProgressMonitor;

/**
 * Container of {@link Entity}ies.
 * @author Pavel
 *
 */
public interface EntityContainer<C,E extends Entity<C,E>> extends Container<E> {
	
	
	/**
	 * Loads entities from {@link ZipInputStream}
	 * @param zipInputStream
	 * @param filter
	 * @param decoder Decodes input stream to entity state.
	 * @param progressMonitor
	 * @throws IOException
	 */
	default void load(
			ZipInputStream zipInputStream, 
			Predicate<String> filter,
			BiFunction<String,InputStream,C> decoder,
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
							E entity = get(entryName, entryMonitor.split("Getting entity "+entryName, 1, this));
							if (entity == null) {
								throw new IllegalArgumentException("Cannot get entity "+entryName+" ("+this+")");
							}
							// Overriding close because set state closes the source stream when it is drained.
							InputStream entryStream = new FilterInputStream(zipInputStream) { 

								public void close() {
									
								}
								
							};
							
							C state = decoder.apply(entryName, entryStream);
							entity.setState(state, entryMonitor.split("Setting state for "+entryName, 1, entity, state));
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
	 * Stores entities to {@link ZipOutputStream}
	 * @param zipOutputStream
	 * @param prefix
	 * @param encoder Encodes entity state to {@link InputStream}
	 * @param progressMonitor
	 * @throws IOException
	 */
	default void store(
			ZipOutputStream zipOutputStream, 
			String prefix, 
			BiFunction<String,C,InputStream> encoder,
			ProgressMonitor progressMonitor) throws IOException {
		
		Copier<E, InputStream> contentSerializer = (path, source, existingElement, monitor) -> encoder.apply(path, source.getState(monitor));
		store(zipOutputStream, prefix, contentSerializer, progressMonitor);
	}
	
	/**
	 * Adapts to container of entity state.
	 * @return
	 */
	default Container<C> stateAdapter() {
		return new Container<C>() {

			@SuppressWarnings("unchecked")
			@Override
			public Object find(String path, ProgressMonitor monitor) {
				Object ret = EntityContainer.this.find(path, monitor.split("Finiding", 1, this));
				if (ret instanceof EntityContainer) {
					return ((EntityContainer<C,E>) ret).stateAdapter();
				}
				return ret == null ? null : ((E) ret).getState(monitor.split("Getting state", 1, this));
			}

			@Override
			public C get(String path, ProgressMonitor monitor) {
				E entity = EntityContainer.this.get(path, monitor.split("Getting enitity "+path, 1, this));
				return entity == null ? null : entity.getState(monitor.split("Getting state of "+path, 1, this));
			}

			@Override
			public void put(String path, C element, ProgressMonitor monitor) throws IllegalArgumentException {
				E entity = EntityContainer.this.get(path, monitor.split("Getting enitity "+path, 1, this));
				entity.setState(element, monitor.split("Setting state of "+path, 1, this));
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object delete(String path, ProgressMonitor monitor) {
				Object deleted = EntityContainer.this.delete(path, monitor.split("Deleting "+path, 1, this));
				if (deleted instanceof Container) {
					return ((EntityContainer<C,E>) deleted).stateAdapter();
				}
				return deleted == null ? null : ((E) deleted).getState(monitor.split("Getting state of "+path, 1, this));
			}

			@SuppressWarnings("unchecked")
			@Override
			public Container<C> getContainer(String path, ProgressMonitor monitor) {
				Container<E> ret = EntityContainer.this.getContainer(path, monitor);
				return ret instanceof EntityContainer ? ((EntityContainer<C,E>) ret).stateAdapter() : null;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Map<String, Object> getChildren(ProgressMonitor monitor) {
				Map<String, Object> ret = new HashMap<>();
				EntityContainer.this.getChildren(monitor.split("Getting children", 1, this)).forEach((path, child) -> {
					if (child instanceof EntityContainer) {
						ret.put(path, ((EntityContainer<C,E>) ret).stateAdapter());
					} else {
						ret.put(path, ((E) child).getState(monitor.split("Getting state", 1, child)));
					}					
				}); 
				return Collections.unmodifiableMap(ret);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Container<C> getParent() {
				Container<E> parent = EntityContainer.this.getParent();
				return parent instanceof EntityContainer ? ((EntityContainer<C,E>) parent).stateAdapter() : null;
			}

			@Override
			public String getName() {
				return EntityContainer.this.getName();
			}

			@Override
			public void copy(Container<? super C> container, String path, ProgressMonitor monitor) {
				throw new UnsupportedOperationException("Implement when this one is thrown");
			}

			@Override
			public void move(Container<? super C> container, String path, ProgressMonitor monitor) {
				throw new UnsupportedOperationException("Implement when this one is thrown");
			}
			
			@Override
			public String toString() {
				return getClass().getName()+"("+getPath()+")";
			}
			
		};
	}	

}
