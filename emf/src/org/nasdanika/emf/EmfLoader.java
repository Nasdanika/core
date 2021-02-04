package org.nasdanika.emf;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public class EmfLoader implements ObjectLoader {

	@Override
	public Object create(
			ObjectLoader loader, 
			String type, 
			Object config, 
			URL base, 
			ProgressMonitor progressMonitor,
			Marker marker) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	// TODO - Epackage registration with optional prefixes, package name is the default prefix, throw an exception if already registered.
	// Loading - leverage supplier features and annotations. Something like new EObjectLoader(config).create(...) as in App 
	// Chaining
	// Doc - code snippet.

}
