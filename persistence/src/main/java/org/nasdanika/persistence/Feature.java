package org.nasdanika.persistence;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

public interface Feature<T> extends Marked {
	
	Object getKey();
	T getValue();
	boolean isRequired();
	boolean isLoaded();
	
	default Object get(Map<?,?> data) {
		return data.get(getKey());
	}
	
	/**
	 * @return If true, this feature is loaded if {@link FeatureObject} configuration is not a map. E.g. HTTP call can be configured with just a URL string, i.e. URL is the default feature of HTTP Call. 
	 */
	boolean isDefault();

	void load(ObjectLoader loader, Map<?,?> source, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers);
	
	/**
	 * @return Feature description in Markdown.
	 */
	String getDescription();	
	
}
