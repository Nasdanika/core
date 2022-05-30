package org.nasdanika.common.persistence;

import java.util.List;

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
	 * @throws Exception
	 */
	T create(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) throws Exception;	

}
