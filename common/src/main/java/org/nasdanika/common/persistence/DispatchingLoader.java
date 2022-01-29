package org.nasdanika.common.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;

/**
 * Registers loaders by prefixes and then delegates to the registered loaders removing prefix first.  
 * @author Pavel
 *
 */
public class DispatchingLoader implements ObjectLoader {
	
	private org.nasdanika.common.persistence.ObjectLoader chain;

	public DispatchingLoader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public DispatchingLoader() {}
	
	protected Map<String,ObjectLoader> registry = new HashMap<>();
	
	/**
	 * Registers a loader with given prefix
	 * @param prefix
	 * @param loader
	 */
	public void register(String prefix, ObjectLoader loader) {
		registry.put(prefix, loader);
	}

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URI base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		for (Entry<String, ObjectLoader> re: registry.entrySet()) {
			if (type.startsWith(re.getKey())) {
				return re.getValue().create(loader, type.substring(re.getKey().length()), config, base, progressMonitor, marker);
			}			
		}
		
		if (chain == null) {
			throw new ConfigurationException("Unsupported type: " + type, marker);
		}
		
		return chain.create(loader, type, config, base, progressMonitor, marker);
	}
		
}
