package org.nasdanika.persistence;

import java.util.Collection;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Functional interface for object creation. 
 * @author Pavel
 *
 * @param <T>
 */
public interface ObjectFactory<T> {
	
	/**
	 * Creates an object of requested type with a given config.
	 * @param ObjectLoader for creating object parts. 
	 * @param config
	 * @param base Base URL for resolving references.
	 * @param progressMonitor 
	 * @param marker Optional source marker for troubleshooting.  
	 * @return Created object
	 */
	T create(
			ObjectLoader loader, 
			Object config, 
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,			
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor);	

}
