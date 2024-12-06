package org.nasdanika.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

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
	public <T> T create(
			ObjectLoader loader, 
			String type, 
			Object config, 
			URI base, 
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver,
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		for (Entry<String, ObjectLoader> re: registry.entrySet()) {
			if (type.startsWith(re.getKey())) {
				return re.getValue().create(
						loader, 
						type.substring(re.getKey().length()), 
						config, 
						base,
						resolver,
						markers,
						progressMonitor);
			}			
		}
		
		if (chain == null) {
			throw new ConfigurationException("Unsupported type: " + type, markers);
		}
		
		return chain.create(
				loader, 
				type, 
				config, 
				base, 
				resolver,
				markers,
				progressMonitor);
	}

	@Override
	public <T> T create(ObjectLoader loader, String type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void load(
			ObjectLoader loader, 
			Object config, 
			Object target, 
			URI base,
			BiConsumer<Object, BiConsumer<Object,ProgressMonitor>> resolver, 
			Collection<? extends Marker> markers,
			ProgressMonitor progressMonitor) {
		
		throw new UnsupportedOperationException();		
	}
		
}
