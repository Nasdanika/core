package org.nasdanika.capability;

import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * Factory which delegates to {@link ServiceLoader} and handles requirements of type {@link Class}.
 */
public abstract class ServiceFactory implements CapabilityFactory {
	
	/**
	 * Service requirement. providerPredicate is applied to the service implementation type returned by {@link Provider}. 
	 * The predicate may use, for example, type annotations to select which service to instantiate.
	 * Service predicate is used on the service instance.
	 * @param <T> Service type
	 */
	public record Requirement<T>(
			Class<T> serviceType, 
			Predicate<Class<?>> providerPredicate,
			Predicate<? super T> servicePredicate) {}
	
	protected abstract <S> Stream<Provider<S>> stream(Class<S> service);
	
	protected CapabilityProvider<?> createServiceCapabilityProvider(Object service) {
		return new CapabilityProvider<Object>() {
			Flux<Object> publisher = Flux.just(service);

			@Override
			public Flux<Object> getPublisher() {
				return publisher;
			}
			
		};		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<Iterable<CapabilityProvider<?>>> create(Object requirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<?>>>> resolver,
			ProgressMonitor progressMonitor) {
		if (requirement instanceof Class) {
			requirement = new Requirement<Object>((Class<Object>) requirement, null, null);
		}
		
		if (requirement instanceof Requirement) {
			Requirement<Object> theRequirement = (Requirement<Object>) requirement;
			
			@SuppressWarnings("rawtypes")
			List<CapabilityProvider<?>> ret = (List) stream(theRequirement.serviceType())
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
