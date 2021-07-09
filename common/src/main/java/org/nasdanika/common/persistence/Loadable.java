package org.nasdanika.common.persistence;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;

/**
 * Objects which know how to load themselves from configuration may implement this interface.
 * Also this interface may be used as an adapter interface. In this case an object to be loaded is first created by the object loader based on 
 * type passed to {@link ObjectLoader}'s create() method and then is adapted to this interface to be loaded.
 * @author Pavel
 *
 */
public interface Loadable {
	
	void load(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception;	

}
