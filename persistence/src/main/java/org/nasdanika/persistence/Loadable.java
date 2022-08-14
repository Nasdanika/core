package org.nasdanika.persistence;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Objects which know how to load themselves from configuration may implement this interface.
 * Also this interface may be used as an adapter interface. In this case an object to be loaded is first created by the object loader based on 
 * type passed to {@link ObjectLoader}'s create() method and then is adapted to this interface to be loaded.
 * @author Pavel
 *
 */
public interface Loadable {
	
	void load(ObjectLoader loader, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers);	

}
