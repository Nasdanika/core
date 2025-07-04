package org.nasdanika.capability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.nasdanika.common.Closeable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;

/**
 * Loads capabilities from {@link CapabilityFactory}'s.
 */
public class CapabilityLoader implements Closeable {
	
	protected Collection<CapabilityFactory<Object,Object>> factories = new ArrayList<>();
	
	public CapabilityLoader(Iterable<CapabilityFactory<Object,Object>> factories) {
		factories.forEach(this.factories::add);
	}
	
	public Collection<CapabilityFactory<Object,Object>> getFactories() {
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
	
	public CapabilityLoader(ModuleLayer moduleLayer) {
		ServiceLoader.load(moduleLayer, CapabilityFactory.class).forEach(factories::add);
	}
	
	/**
	 * Maps requirements to capability publishers
	 */
	private Map<Object, CompletionStage<Iterable<CapabilityProvider<Object>>>> registry = new ConcurrentHashMap<>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> CompletionStage<Iterable<CapabilityProvider<T>>> load(
			Object requirement, 
			Consumer<Runnable> jobCollector, 
			ProgressMonitor progressMonitor) {
		CompletionStage<Iterable<CapabilityProvider<Object>>> result = registry.get(requirement);
		if (result != null) {
			return (CompletionStage) result;
		}
		CompletableFuture<Iterable<CapabilityProvider<Object>>> ret  = new CompletableFuture<>();
		registry.put(requirement, ret);
		progressMonitor.worked(1, "Created a registry entry for requirement", requirement);
		
		CapabilityFactory.Loader subLoader = new CapabilityFactory.Loader() {

			@Override
			public <Т> CompletionStage<Iterable<CapabilityProvider<Т>>> load(Object rq, ProgressMonitor pm) {
				if (Objects.equals(requirement, rq)) {
					// Preventing blocking at .join() below
					throw new IllegalArgumentException("Requirement loading loop for " + requirement);
				}
				return (CompletionStage) CapabilityLoader.this.load(rq,jobCollector,pm);
			}
			
			@Override
			public CapabilityLoader getCapabilityLoader() {
				return CapabilityLoader.this;
			}
			
		};
		
		Runnable job = () -> { // A runnable which completes the future			
			createCapabilityProviders(requirement, subLoader, progressMonitor)
				.whenComplete((r, e) -> {						
					if (e == null) {
						((CompletableFuture<Iterable<CapabilityProvider<Object>>>) ret).complete(r);
						progressMonitor.worked(1, "Completed capability providers", requirement);
					} else {
						((CompletableFuture<Iterable<CapabilityProvider<Object>>>) ret).completeExceptionally(e);
						progressMonitor.worked(Status.ERROR, 1, "Exception while completing capability providers", requirement, e);
					}
				});
			progressMonitor.worked(1, "Created capability providers", requirement);
		};
		jobCollector.accept(job);
		return (CompletionStage) ret;		
	}
	
	/**
	 * Asynchronously loads providers which are not yet in the registry
	 * @param requirement
	 * @param progressMonitor
	 * @return
	 */
	public <T> Iterable<CapabilityProvider<T>> load(Object requirement, ProgressMonitor progressMonitor) {		
		Queue<Runnable> constructionQueue = new ConcurrentLinkedQueue<>(); // 
		AtomicBoolean done = new AtomicBoolean();
		CompletionStage<Iterable<CapabilityProvider<T>>> result = load(
				requirement, 
				job -> {
					if (done.get()) {
						throw new IllegalStateException("Loading is done");
					}
					
					constructionQueue.add(job);
				}, 
				progressMonitor); 
		Runnable job;
		while ((job = constructionQueue.poll()) != null) {
			job.run();
		}
		done.set(true);		
		return result.toCompletableFuture().join();		
	}
		
	public <T> List<T> loadAll(Object requirement, ProgressMonitor progressMonitor) {
		List<T> ret = Collections.synchronizedList(new ArrayList<>());
		Iterable<CapabilityProvider<T>> capabilityProviders = load(requirement, progressMonitor);
		for (CapabilityProvider<T> cp: capabilityProviders) {
			cp.getPublisher().filter(Objects::nonNull).collectList().block().forEach(ret::add);
		}
		
		return ret;		
	}
	
	public <T> T loadOne(Object requirement, ProgressMonitor progressMonitor) {
		Iterable<CapabilityProvider<T>> capabilityProviders = load(requirement, progressMonitor);
		for (CapabilityProvider<T> cp: capabilityProviders) {
			return cp.getPublisher().blockFirst();
		}
		
		return null;		
	}	
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <R,S> Iterable<CapabilityProvider<S>> loadServices(
			Class<S> serviceType, 
			Predicate<? super ServiceCapabilityFactory<R,S>> factoryPredicate,
			R serviceRequirement,
			ProgressMonitor progressMonitor) {		

		ServiceCapabilityFactory.Requirement<R,S> serviceReq = new ServiceCapabilityFactory.Requirement<R,S>(serviceType, factoryPredicate, serviceRequirement);
		return (Iterable) load(serviceReq, progressMonitor);
	}
	
	public <R,S> List<S> loadAllServices(
			Class<S> serviceType, 
			Predicate<? super ServiceCapabilityFactory<R,S>> factoryPredicate,
			R serviceRequirement,
			ProgressMonitor progressMonitor) {
	
		List<S> ret = Collections.synchronizedList(new ArrayList<>());
		for (CapabilityProvider<S> cp: loadServices(serviceType, factoryPredicate, serviceRequirement, progressMonitor)) {
			cp.getPublisher().filter(Objects::nonNull).collectList().block().forEach(ret::add);
		}
		
		return ret;		
	}
	
	public <R,S> S loadService(
			Class<S> serviceType, 
			Predicate<? super ServiceCapabilityFactory<R,S>> factoryPredicate,
			R serviceRequirement,
			ProgressMonitor progressMonitor) {
	
		for (CapabilityProvider<S> cp: loadServices(serviceType, factoryPredicate, serviceRequirement, progressMonitor)) {
			return cp.getPublisher().blockFirst();
		}
		
		return null;		
	}
	
	/**
	 * Information about capability factory - requirement it satisfied, providers created, dependency requirements
	 * FactoryNode can be used for troubleshooting and dependency visualization Requirement -> Factories -> Dependency Requirements
	 * Visualization can generated using the diagram module (https://docs.nasdanika.org/core/diagram/index.html) or ECharts model (https://docs.nasdanika.org/models/echarts/index.html).  
	 */
	public static record FactoryNode(
			Object requirement,
			CapabilityFactory<Object,Object> factory,
			Iterable<CapabilityProvider<Object>> providers,
			Collection<Object> dependencies) {}
	
	private Collection<FactoryNode> factoryNodes = Collections.synchronizedCollection(new ArrayList<>());
	
	/**
	 * Can be used to examine dependencies, e.g. build a dependency graph
	 * @return
	 */
	public Collection<FactoryNode> getFactoryNodes() {
		return Collections.unmodifiableCollection(factoryNodes);
	}
	
//	private <T>  load(Object requirement, ProgressMonitor progressMonitor) 
		
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
	protected CompletionStage<Iterable<CapabilityProvider<Object>>> createCapabilityProviders(
			Object requirement,
			CapabilityFactory.Loader loader, 
			ProgressMonitor progressMonitor) {

		Collection<CompletableFuture<Iterable<CapabilityProvider<Object>>>> accumulator = new ArrayList<>();
		for (CapabilityFactory<Object,Object> factory: factories) {
			if (factory.canHandle(requirement)) {
				// TODO - split progress monitor
				Collection<Object> dependencies = Collections.synchronizedCollection(new ArrayList<>());
				CapabilityFactory.Loader subLoader = new CapabilityFactory.Loader() {

					@Override
					public <Т> CompletionStage<Iterable<CapabilityProvider<Т>>> load(Object rq, ProgressMonitor pm) {
						dependencies.add(rq);
						return loader.load(rq, pm); 
					}
					
					@Override
					public CapabilityLoader getCapabilityLoader() {
						return CapabilityLoader.this;
					}
					
				};
				CompletionStage<Iterable<CapabilityProvider<Object>>> rcs = factory.create(requirement,	subLoader, progressMonitor);
				rcs = rcs.thenApply(cp -> {
					factoryNodes.add(new FactoryNode(requirement, factory, cp, Collections.unmodifiableCollection(dependencies)));
					return cp;
				});
				accumulator.add(rcs.toCompletableFuture());
			}
		}		
		
		if (accumulator.isEmpty()) {
			return CompletableFuture.completedStage(Collections.emptyList());
		}
		
		CompletableFuture<Void> allOf = CompletableFuture.allOf(accumulator.toArray(new CompletableFuture[accumulator.size()]));
		Function<Void, Iterable<CapabilityProvider<Object>>> collector = arg -> {
			Collection<CapabilityProvider<Object>> ret = new ArrayList<>();
			for (CompletableFuture<Iterable<CapabilityProvider<Object>>> ae: accumulator) {
				ae.join().forEach(ret::add);;
			}
			return ret;
		};
		return allOf.thenApply(collector);
	}

	@Override
	public void close(ProgressMonitor progressMonitor) {
		// Closing factories
		for (CapabilityFactory<Object, Object> cf: getFactories()) {
			if (cf instanceof Closeable) {
				((Closeable) cf).close(progressMonitor.split("Closing capability factory", 1));
			}
		}
	}

}
