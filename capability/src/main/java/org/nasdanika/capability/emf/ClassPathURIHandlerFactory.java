package org.nasdanika.capability.emf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

public class ClassPathURIHandlerFactory extends ServiceCapabilityFactory<Void, URIHandler> {
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return URIHandler.class == type && requirement == null;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<URIHandler>>> createService(
			Class<URIHandler> serviceType,
			Void serviceRequirement, 
			Loader loader, 
			ProgressMonitor progressMonitor) {
		
		return wrap(new URIHandlerImpl() {

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