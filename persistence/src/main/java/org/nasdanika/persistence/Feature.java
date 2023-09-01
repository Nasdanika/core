package org.nasdanika.persistence;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;

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
	
	/**
	 * @return If true, this feature is set to the entire configuration object. If there is a constructor feature all other features are ignored during loading. 
	 */
	boolean isConstructor();

	void load(
			ObjectLoader loader, 
			Map<?,?> source, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor);
	
	/**
	 * @return Feature description in Markdown.
	 */
	String getDescription();	
	
}
