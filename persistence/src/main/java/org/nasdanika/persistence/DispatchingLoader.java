package org.nasdanika.persistence;

import java.util.HashMap;
import java.util.List;
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
	
	private org.nasdanika.persistence.ObjectLoader chain;

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
	public Object create(ObjectLoader loader, String type, Object config, URI base, ProgressMonitor progressMonitor, List<? extends Marker> markers) {
		for (Entry<String, ObjectLoader> re: registry.entrySet()) {
			if (type.startsWith(re.getKey())) {
				return re.getValue().create(loader, type.substring(re.getKey().length()), config, base, progressMonitor, markers);
			}			
		}
		
		if (chain == null) {
			throw new ConfigurationException("Unsupported type: " + type, markers);
		}
		
		return chain.create(loader, type, config, base, progressMonitor, markers);
	}
		
}
