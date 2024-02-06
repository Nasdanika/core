package org.nasdanika.common;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

/**
 * Resource factory which doesn't load anything.  
 */
public class NopResourceFactory implements Resource.Factory {

	@Override
	public Resource createResource(URI uri) {
		return new ResourceImpl(uri) {
			
			@Override
			public boolean isLoaded() {
				return true;
			}

			@Override
			public void load(Map<?, ?> options) throws IOException {
				// NOP
			}
			
		};
	}		

}
