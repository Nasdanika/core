package org.nasdanika.capability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;

/**
 * Loads capabilities from {@link CapabilityFactory}'s.
 */
public class CapabilityLoader {
	
	protected Collection<CapabilityFactory> factories = new ArrayList<>();
	
	public CapabilityLoader(Iterable<CapabilityFactory> factories) {
		factories.forEach(this.factories::add);
	}
	
	public Collection<CapabilityFactory> getFactories() {
		return factories;
	}

	/**
	 * Loads factories from the service loader.
	 */
	public CapabilityLoader() {
		ServiceLoader.load(CapabilityFactory.class).forEach(factories::add);
	}
	
	public CapabilityLoader(ClassLoader classLoader) {
		ServiceLoader.load(CapabilityFactory.class, classLoader).forEach(factories::add);
	}
	
	/**
	 * Maps requirements to capability publishers
	 */
	private Map<Object, CompletionStage<Iterable<CapabilityProvider<?>>>> registry = new ConcurrentHashMap<>();
	
	/**
	 * Asynchronously loads providers which are not yet in the registry
	 * @param requirement
	 * @param progressMonitor
	 * @return
	 */
	public Iterable<CapabilityProvider<?>> load(Object requirement, ProgressMonitor progressMonitor) {		
		Queue<Runnable> constructionQueue = new ConcurrentLinkedQueue<>(); // 
		BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<?>>>> constructor = new BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<?>>>>() {

			@Override
			public CompletionStage<Iterable<CapabilityProvider<?>>> apply(Object req, ProgressMonitor constructionMonitor) {
				CompletionStage<Iterable<CapabilityProvider<?>>> result = registry.get(requirement);
				if (result != null) {
					return result;
				}
				CompletableFuture<Iterable<CapabilityProvider<?>>> ret  = new CompletableFuture<>();
				registry.put(requirement, ret);  
				constructionQueue.add(() -> { // A runnable which completes the future
					createCapabilityProviders(req, this, constructionMonitor).whenComplete((r, e) -> {
						if (e == null) {
							((CompletableFuture<Iterable<CapabilityProvider<?>>>) ret).complete(r);
						} else {
							((CompletableFuture<Iterable<CapabilityProvider<?>>>) ret).completeExceptionally(e);
						}
					});
				});
				return ret;
			}
			
		};
		CompletionStage<Iterable<CapabilityProvider<?>>> result = constructor.apply(requirement, progressMonitor); // Puts to registry, fills the queue with immediate dependencies.
		Runnable workItem;
		while ((workItem = constructionQueue.poll()) != null) {
			workItem.run();
		}
		
		return result.toCompletableFuture().join();		
	}	
	
	/**
	 * 
	 * @param requirement
	 * @param parallel
	 * @param elementProvider Accepts a requirement and a consumer of the capability providers element
	 *  and a progress monitor.
	 * The consumer is invoked when the target element is created from the source element. 
	 * @param progressMonitor
	 * @return
	 */
	protected CompletionStage<Iterable<CapabilityProvider<?>>> createCapabilityProviders(
			Object requirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<?>>>> resolver, 
			ProgressMonitor progressMonitor) {

		Collection<CompletableFuture<Iterable<CapabilityProvider<?>>>> accumulator = new ArrayList<>();
		for (CapabilityFactory factory: factories) {
			// TODO - split progress monitor
			CompletionStage<Iterable<CapabilityProvider<?>>> rcs = factory.create(requirement, resolver, progressMonitor);
			accumulator.add(rcs.toCompletableFuture());
		}		
		
		if (accumulator.isEmpty()) {
			return CompletableFuture.completedStage(Collections.emptyList());
		}
		
		CompletableFuture<Void> allOf = CompletableFuture.allOf(accumulator.toArray(new CompletableFuture[accumulator.size()]));
		Function<Void, Iterable<CapabilityProvider<?>>> collector = arg -> {
			Collection<CapabilityProvider<?>> ret = new ArrayList<>();
			for (CompletableFuture<Iterable<CapabilityProvider<?>>> ae: accumulator) {
				ae.join().forEach(ret::add);;
			}
			return ret;
		};
		return allOf.thenApply(collector);
	}

}
