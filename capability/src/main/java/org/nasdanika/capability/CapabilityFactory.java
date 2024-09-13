package org.nasdanika.capability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

/**
 * Creates providers of capabilities satisfying the requirement.
 * This is a service interface intended to be used with {@link ServiceLoader}. 
 */
public interface CapabilityFactory<R,C> {
		
	interface Loader {
		
		/**
		 * 
		 * @param requirement
		 * @param progressMonitor
		 * @return
		 */
		<Т> CompletionStage<Iterable<CapabilityProvider<Т>>> load(Object requirement, ProgressMonitor progressMonitor);
				
		default <T> CompletionStage<List<T>> loadAll(Object requirement, ProgressMonitor progressMonitor) {
			CompletionStage<Iterable<CapabilityProvider<T>>> cpcs = load(requirement, progressMonitor);
			return cpcs.thenApply(capabilityProviders -> {
				List<T> ret = Collections.synchronizedList(new ArrayList<>());
				for (CapabilityProvider<T> cp: capabilityProviders) {
					cp.getPublisher().subscribe(ret::add);
				}
				
				return ret;						
			});
		}
		
		
		
		default <T> CompletionStage<T> loadOne(Object requirement, ProgressMonitor progressMonitor) {
			CompletionStage<Iterable<CapabilityProvider<T>>> cpcs = load(requirement, progressMonitor);
			return cpcs.thenApply(capabilityProviders -> {
				for (CapabilityProvider<T> cp: capabilityProviders) {
					return cp.getPublisher().blockFirst();
				}
				
				return null;						
			});
		}	
		
	}		
	
	boolean canHandle(Object requirement);
	
	/**
	 * Asynchronously creates providers of capabilities satisfying the requirement.
	 * @param requirement Some requirement object. E.g. if the requirement is a class, then class instances would be capabilities.
	 * Such capabilities can be loaded by the {@link ServiceLoader} or by other means.  
	 * @param loader Use to obtain dependency capabilities by requirements. E.g. service instance by service class.
	 * @return
	 */
	CompletionStage<Iterable<CapabilityProvider<C>>> create(
			R requirement, 
			Loader loader,
			ProgressMonitor progressMonitor);	
	
}
