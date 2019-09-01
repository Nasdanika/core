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
		return stateAdapter((path,state) -> state, (path,state) -> state);
	}
	
	/**
	 * Adapts to a container with a different element type by adapting/converting entity state to the adapter element type.
	 * @param <F> Element type to adapt to
	 * @param adapter Adapts entity state C to target type F. Takes entity path and state.
	 * @param converter Converts element F to state on put and get with a factory. Takes element path and the element itself. Put is performed as get().setState()
	 * because entity containers may not support put(). 
	 * @return
	 */
	default <F> Container<F> stateAdapter(BiFunction<String,C,F> stateAdapter, BiFunction<String,F,C> stateConverter) {
		return new Container<F>() {

			@SuppressWarnings("unchecked")
			@Override
			public Object find(String path, ProgressMonitor monitor) {
				Object ret = EntityContainer.this.find(path, monitor.split("Finiding", 1, this));
				if (ret instanceof EntityContainer) {
					return ((EntityContainer<C,E>) ret).stateAdapter(stateAdapter, stateConverter);
				}
				return stateAdapter.apply(path, ((E) ret).getState(monitor.split("Getting state", 1, this)));
			}

			@Override
			public F get(String path, ProgressMonitor monitor) {
				E entity = EntityContainer.this.get(path, monitor.split("Getting enitity "+path, 1, this));
				C state = entity.getState(monitor.split("Getting state of "+path, 1, this));
				return stateAdapter.apply(path, state);
			}

			@Override
			public void put(String path, F element, ProgressMonitor monitor) throws IllegalArgumentException {
				E entity = EntityContainer.this.get(path, monitor.split("Getting enitity "+path, 1, this));
				C state = stateConverter.apply(path, element);
				entity.setState(state, monitor.split("Setting state of "+path, 1, this));
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object delete(String path, ProgressMonitor monitor) {
				Object deleted = EntityContainer.this.delete(path, monitor.split("Deleting "+path, 1, this));
				if (deleted instanceof Container) {
					return ((EntityContainer<C,E>) deleted).stateAdapter(stateAdapter, stateConverter);
				}
				return stateAdapter.apply(path, deleted == null ? null : ((E) deleted).getState(monitor.split("Getting state of "+path, 1, this)));
			}

			@SuppressWarnings("unchecked")
			@Override
			public Container<F> getContainer(String path, ProgressMonitor monitor) {
				Container<E> ret = EntityContainer.this.getContainer(path, monitor);
				return ret instanceof EntityContainer ? ((EntityContainer<C,E>) ret).stateAdapter(stateAdapter, stateConverter) : null;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Map<String, Object> getChildren(ProgressMonitor monitor) {
				Map<String, Object> ret = new HashMap<>();
				EntityContainer.this.getChildren(monitor.split("Getting children", 1, this)).forEach((path, child) -> {
					if (child instanceof EntityContainer) {
						ret.put(path, ((EntityContainer<C,E>) ret).stateAdapter(stateAdapter, stateConverter));
					} else {
						ret.put(path, stateAdapter.apply(path, ((E) child).getState(monitor.split("Getting state", 1, child))));
					}					
				}); 
				return Collections.unmodifiableMap(ret);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Container<F> getParent() {
				Container<E> parent = EntityContainer.this.getParent();
				return parent instanceof EntityContainer ? ((EntityContainer<C,E>) parent).stateAdapter(stateAdapter, stateConverter) : null;
			}

			@Override
			public String getName() {
				return EntityContainer.this.getName();
			}

			@Override
			public void copy(Container<? super F> container, String path, ProgressMonitor monitor) {
				throw new UnsupportedOperationException("Implement when this one is thrown");
			}

			@Override
			public void move(Container<? super F> container, String path, ProgressMonitor monitor) {
				throw new UnsupportedOperationException("Implement when this one is thrown");
			}
			
		};
	}
	

}
