package org.nasdanika.capability.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.emf.ResourceContentsHandler.Requirement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Resource implementation that supports content handling via {@link ResourceContentsHandler}.
 */
public class ContentsHandlingResource extends ResourceImpl {

	private ResourceContentsHandler<EObject[]> resourceContentsHandler;

	@SuppressWarnings("unchecked")
	public ContentsHandlingResource(URI uri, CapabilityLoader capabilityLoader, ProgressMonitor progressMonitor) {
		super(uri);
		String lastSegment = getURI().lastSegment();
		if (!Util.isBlank(lastSegment)) {			
			String[] qualifiers = reverse(lastSegment.split("\\."));
			for (int i = qualifiers.length - 1; i >= 0 && resourceContentsHandler == null; i--) {
				Requirement handlerRequirement = ResourceContentsHandler.createRequirement(this, EObject[].class, qualifiers, i);
				@SuppressWarnings("rawtypes")
				ServiceCapabilityFactory.Requirement<Object, ResourceContentsHandler> serviceRequirement = ServiceCapabilityFactory.createRequirement(ResourceContentsHandler.class, null, handlerRequirement);
				resourceContentsHandler =  capabilityLoader.loadAll(serviceRequirement, progressMonitor)
						.stream()
						.map(e -> (ResourceContentsHandler<EObject[]>) e)
						.sorted((h1, h2) -> h1.getOrder().compareTo(h2.getOrder()))
						.findFirst()
						.orElse(null);
			}			
		}		
	}
	
	private static String[] reverse(String[] arr) {
	    int start = 0;
	    int end = arr.length - 1;
	    
	    while (start < end) {
	        // Swap elements using a temporary variable
	        String temp = arr[start];
	        arr[start] = arr[end];
	        arr[end] = temp;
	        
	        // Move pointers closer to the middle
	        start++;
	        end--;
	    }
	    return arr;
	}	
	
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		if (resourceContentsHandler == null) {
			super.doLoad(inputStream, options);	
		} else {	
			for (EObject eObject : resourceContentsHandler.load(inputStream, options)) {
				getContents().add(eObject);
			}
		}		
	}
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (resourceContentsHandler == null) {
			super.doSave(outputStream, options);	
		} else {	
			resourceContentsHandler.save(getContents().toArray(EObject[]::new), outputStream, options);
		}
	}

}
