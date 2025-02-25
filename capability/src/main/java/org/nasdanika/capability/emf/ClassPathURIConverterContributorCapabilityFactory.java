package org.nasdanika.capability.emf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Contributes {@link URIHandler} loading from classpath schema
 */
public class ClassPathURIConverterContributorCapabilityFactory extends URIConverterContributorCapabilityFactory {

	@Override
	protected void contribute(
			URIConverter uriConverter, 
			Loader loader,
			ProgressMonitor progressMonitor) {	
		uriConverter.getURIHandlers().add(0, new URIHandlerImpl() {

			@Override
			public boolean canHandle(URI uri) {
				return uri != null && Util.CLASSPATH_SCHEME.equals(uri.scheme());
			}

			@Override
			public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
				return DefaultConverter.INSTANCE.toInputStream(uri);
			}
			
		});
		
	}
	
}
