package org.nasdanika.persistence;

import java.util.Collection;

public interface Marked {
	
	Collection<? extends Marker> getMarkers();
	
}
