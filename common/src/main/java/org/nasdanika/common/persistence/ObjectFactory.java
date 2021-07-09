package org.nasdanika.common.persistence;

import java.net.URL;

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
	T create(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception;	

}
