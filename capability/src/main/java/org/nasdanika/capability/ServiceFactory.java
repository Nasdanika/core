package org.nasdanika.capability;

import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * Factory which handles requirements of type {@link Class}.
 */
public abstract class ServiceFactory<S> implements CapabilityFactory<Object,S> {
	
	/**
	 * Service requirement. providerPredicate is applied to the service implementation type returned by {@link Provider}. 
	 * The predicate may use, for example, type annotations to select which service to instantiate.
	 * Service predicate is used on the service instance.
	 * @param <T> Service type
	 */
	public record Requirement<S>(
			Class<S> serviceType, 
			Predicate<Class<?>> providerPredicate,
			Predicate<? super S> servicePredicate) {}
	
	protected abstract Stream<Provider<S>> stream(Class<S> service);
	
	@Override
	public boolean canHandle(Object requirement) {
		return requirement instanceof Class || requirement instanceof Requirement;
	}
	
	protected CapabilityProvider<S> createServiceCapabilityProvider(S service) {
		return new CapabilityProvider<S>() {
			Flux<S> publisher = Flux.just(service);

			@Override
			public Flux<S> getPublisher() {
				return publisher;
			}
			
		};		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<Iterable<CapabilityProvider<S>>> create(
			Object requirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		if (requirement instanceof Class) {
			requirement = new Requirement<S>((Class<S>) requirement, null, null);
		}
		
		if (requirement instanceof Requirement) {
			Requirement<S> theRequirement = (Requirement<S>) requirement;
			
			List<CapabilityProvider<S>> ret = stream(theRequirement.serviceType())
				.filter(p -> theRequirement.providerPredicate() == null || theRequirement.providerPredicate().test(p.type()))
				.map(Provider::get)
				.filter(s -> theRequirement.servicePredicate() == null || theRequirement.servicePredicate().test(s))
				.map(this::createServiceCapabilityProvider)
				.toList();
			
			return CompletableFuture.completedStage(ret);
		}
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}

}
