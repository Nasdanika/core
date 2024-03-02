package org.nasdanika.ncore.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Creates YAML resources which load/save models to/from YAML. 
 
 */
public class YamlResourceFactory implements Resource.Factory {

	@Override
	public Resource createResource(URI uri) {
		return new YamlResource(uri) {
			
			@Override
			protected Iterable<YamlHandler> getHandlers() {
				return handlers;
			}
		};
		
	}
	
	protected List<YamlHandler> handlers = new ArrayList<>();
	
	public List<YamlHandler> getHandlers() {
		return handlers;
	}

}
