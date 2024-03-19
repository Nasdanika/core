package org.nasdanika.capability.tests;

import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Stream;

import org.nasdanika.capability.ServiceFactory;

/**
 * Factory which delegates to {@link ServiceLoader} and handles requirements of type {@link Class}.
 */
public class TestServiceFactory<S> extends ServiceFactory<S> {
	
	@Override
	protected Stream<Provider<S>> stream(Class<S> service) {
		return ServiceLoader.load(service).stream();
	}

}
