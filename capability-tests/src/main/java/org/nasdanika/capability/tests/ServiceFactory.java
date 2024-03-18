package org.nasdanika.capability.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * Factory which delegates to {@link ServiceLoader} and handles requirements of type {@link Class}.
 */
public class ServiceFactory implements CapabilityFactory {
	
	private ClassLoader classLoader;

	public ServiceFactory() {
		
	}
	
	public ServiceFactory(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	@Override
	public CompletionStage<Iterable<CapabilityProvider<?>>> create(Object requirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<?>>>> resolver,
			ProgressMonitor progressMonitor) {
		if (requirement instanceof Class) {
			Class<?> serviceType = (Class<?>) requirement;
			ServiceLoader<?> services = classLoader == null ? ServiceLoader.load(serviceType) : ServiceLoader.load(serviceType, classLoader);
			List<CapabilityProvider<?>> ret = new ArrayList<>();
			for (Object service: services) {
				Flux<Object> publisher = Flux.just(service);
				ret.add(new CapabilityProvider<Object>() {

					@Override
					public Flux<Object> getPublisher() {
						return publisher;
					}
					
				});
			}
			return CompletableFuture.completedStage(ret);
		}
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}

}
