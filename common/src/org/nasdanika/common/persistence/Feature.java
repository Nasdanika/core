package org.nasdanika.common.persistence;

import java.net.URL;
import java.util.Map;

import org.nasdanika.common.ProgressMonitor;

public interface Feature<T> extends Marked {
	
	Object getKey();
	T getValue();
	boolean isRequired();
	boolean isLoaded();

	void load(ObjectLoader loader, Map<?,?> source, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception;
	
}
