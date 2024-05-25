package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.persistence.ObjectLoader;
import org.nasdanika.persistence.ObjectLoaderResource;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;

import reactor.core.publisher.Flux;

/**
 * Base class for capability factories loading objects from a {@link ResourceSet} 
 */
public abstract class ObjectLoaderCapabilityFactory<R,S> extends ServiceCapabilityFactory<R,S> {

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<S>>> createService(
			Class<S> serviceType, 
			R serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		ResourceSetRequirement resourceSetRequirement = getResourceSetRequirement(serviceRequirement); 
		Requirement<Object, ResourceSet> resourceSetServiceRequirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class, this::testResourceSetFactory, resourceSetRequirement);
		CompletionStage<Iterable<CapabilityProvider<Object>>> rsCS = resolver.apply(resourceSetServiceRequirement, progressMonitor);
		return rsCS.thenApply(resourceSets -> load(serviceRequirement, resourceSets, progressMonitor));
	}

	/**
	 * Override to customize resource set loading/creation
	 * @return
	 */
	protected ResourceSetRequirement getResourceSetRequirement(R serviceRequirement) {
		return null;
	}
	
	/**
	 * Override to filter resource set factories
	 * @param resourceSetFactory
	 * @return
	 */
	protected boolean testResourceSetFactory(ServiceCapabilityFactory<?, ?> resourceSetFactory) {
		return true;
	}

	protected Iterable<CapabilityProvider<S>> load(
			R serviceRequirement,
			Iterable<CapabilityProvider<Object>> resourceSetCapabilityProviders,
			ProgressMonitor progressMonitor) {
		
		Collection<CapabilityProvider<S>> ret = new ArrayList<>();
		
		for (CapabilityProvider<Object> rcp: resourceSetCapabilityProviders) {
			ret.add(new CapabilityProvider<S>() {
				
				@Override
				public Flux<S> getPublisher() {
					return rcp
							.getPublisher()
							.map(ResourceSet.class::cast)
							.map(rs -> { 
								configureResourceSet(serviceRequirement, rs, progressMonitor);
								return rs;
							})
							.map(resourceSet ->	 load(serviceRequirement, resourceSet, progressMonitor))
							.filter(Objects::nonNull);
				}
			});
		}
		
		return ret;
	}
	
	protected void configureResourceSet(
			R serviceRequirement, 
			ResourceSet resourceSet, 
			ProgressMonitor progressMonitor) {
		
		ObjectLoaderResourceFactory objectLoaderResourceFactory = createObjectLoaderResorceFactory(serviceRequirement, resourceSet, progressMonitor);
		
		Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("yml", objectLoaderResourceFactory);
		extensionToFactoryMap.put("yaml", objectLoaderResourceFactory);
		extensionToFactoryMap.put("json", objectLoaderResourceFactory);
		
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("data", objectLoaderResourceFactory);
		
	}

	protected ObjectLoaderResourceFactory createObjectLoaderResorceFactory(
			R serviceRequirement, 
			ResourceSet resourceSet, 
			ProgressMonitor progressMonitor) {		
		
		return new ObjectLoaderResourceFactory() {
			
			@Override
			protected ObjectLoader getObjectLoader(Resource resource) {
				return ObjectLoaderCapabilityFactory.this.getObjectLLoader(resourceSet, resource, progressMonitor);
			}
			
			@Override
			protected Context getContext(Resource resource) {
				return ObjectLoaderCapabilityFactory.this.getContext(resource, serviceRequirement, progressMonitor);
			}
			
			@Override
			protected ProgressMonitor getProgressMonitor(Resource resource) {
				return progressMonitor;
			}

			@Override
			protected BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> getResolver(Resource resource) {
				return ObjectLoaderCapabilityFactory.this.getResolver(resourceSet, resource, progressMonitor);
			}
			
		};
	}
	
	protected Context getContext(Resource resource, R serviceRequirement, ProgressMonitor progressMonitor) {
		return Context.EMPTY_CONTEXT;
	}
		
	protected abstract S load(R requirement, ResourceSet resourceSet, ProgressMonitor progressMonitor);
	
	protected BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> getResolver(ResourceSet resourceSet, Resource resource, ProgressMonitor progressMonitor) {
		return null;
	}	

	/**
	 * Object loader used by {@link ObjectLoaderResourceFactory} and {@link ObjectLoaderResource}.
	 * @param resourceSet
	 * @param resource
	 * @param progressMonitor
	 * @return
	 */
	protected ObjectLoader getObjectLLoader(ResourceSet resourceSet, Resource resource, ProgressMonitor progressMonitor) {
		EObjectLoader eObjectLoader = new EObjectLoader(resourceSet);
		eObjectLoader.setMarkerFactory(new GitMarkerFactory());
		return eObjectLoader;
	}

}
