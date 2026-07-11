package org.nasdanika.common;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public interface StreamOutput extends Output<OutputStream> {
	
	static StreamOutput of(URIConverter uriConverter) {
		return new StreamOutput() {
			
			@Override
			public OutputStream openOutput(URI uri) throws IOException {
				return uriConverter.createOutputStream(uri);
			}
			
		};
	}
	
	/**
	 * Default output that uses EMF URIConverter to create output streams
	 */
	static StreamOutput INSTANCE = of(new ResourceSetImpl().getURIConverter());

}
