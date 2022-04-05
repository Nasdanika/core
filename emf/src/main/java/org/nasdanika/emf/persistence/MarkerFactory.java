package org.nasdanika.emf.persistence;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcoreFactory;

public interface MarkerFactory {
	
	MarkerFactory INSTANCE = (location, progressMonitor) -> {
		org.nasdanika.ncore.Marker marker = NcoreFactory.eINSTANCE.createMarker();
		marker.setLocation(location);
		return marker;
	};
	
	Marker createMarker(String location, ProgressMonitor progressMonitor);

}
